package com.jdkgroup.quiz.domain.use_cases


import com.jdkgroup.quiz.util.Resource

class ValidateAnswerUseCase {

    operator fun invoke(userAnswer: String?, actualAnswer: String): Resource<Boolean> {
        return if(userAnswer.isNullOrEmpty()) {
            Resource.Error("No answer provided!")
        } else {
            Resource.Success(userAnswer == actualAnswer)
        }
    }

}