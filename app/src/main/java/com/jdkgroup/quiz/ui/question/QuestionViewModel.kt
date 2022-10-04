package com.jdkgroup.quiz.ui.question

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdkgroup.quiz.domain.model.Question
import com.jdkgroup.quiz.domain.use_cases.QuestionUseCases
import com.jdkgroup.quiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionUseCases: QuestionUseCases
) : ViewModel() {

    private val _question: MutableState<Question?> = mutableStateOf(null)
    val question: State<Question?> = _question

    private val _userSelectedAnswer: MutableState<String?> = mutableStateOf(null)
    val userSelectedAnswer: State<String?> = _userSelectedAnswer

    //for one time ui event (like show a snackbar) that won't fire on config change
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _isAnswerCorrect: MutableState<Boolean?> = mutableStateOf(null)

    init{
        getQuestion()
    }

    fun getQuestion(){
        // reset values to null for new question so previous states are reset
        _question.value = null
        _userSelectedAnswer.value = null
        _isAnswerCorrect.value = null

        viewModelScope.launch {
            // just adding delay so animation on load can be seen
            delay(1500)

            when(val result = questionUseCases.getQuestion.invoke()) {
                is Resource.Success -> {
                    _question.value = result.data
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackBarWithAction(result.msg.toString()))
                }
            }
        }
    }

    fun setAnswer(answer: String) {
        _isAnswerCorrect.value = null
        _userSelectedAnswer.value = answer
    }

    fun checkAnswer() {
        viewModelScope.launch {
            val result = questionUseCases.validateAnswer.invoke(
                _userSelectedAnswer.value,
                _question.value!!.correctAnswer)
            when(result) {
                is Resource.Success -> {
                    _isAnswerCorrect.value = result.data!!
                    _eventFlow.emit(UiEvent.IsAnswerCorrect(result.data))

                    // just adding delay so user can see correct answer before moving to next question
                    delay(1500)

                    getQuestion()
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackBarWithoutAction(result.msg.toString()))
                }
            }
        }
    }

    fun getBorderColor(answer: String): Color {
        return questionUseCases.getAnswerColorUseCase(
            possibleAnswer = answer,
            userSelectedAnswer = _userSelectedAnswer.value,
            correctAnswer = question.value!!.correctAnswer,
            isAnswerCorrect = _isAnswerCorrect.value
        )
    }

    sealed class UiEvent {
        data class ShowSnackBarWithoutAction(val message: String): UiEvent()
        data class ShowSnackBarWithAction(val message: String): UiEvent()
        data class IsAnswerCorrect(val isCorrect: Boolean): UiEvent()
    }

}