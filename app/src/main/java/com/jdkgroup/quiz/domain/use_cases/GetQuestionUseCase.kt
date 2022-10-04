package com.jdkgroup.quiz.domain.use_cases

import com.jdkgroup.quiz.repository.QuestionRepository

class GetQuestionUseCase(
    private val questionRepository: QuestionRepository
) {

    suspend fun invoke() = questionRepository.get()

}