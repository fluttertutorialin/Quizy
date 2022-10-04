package com.jdkgroup.quiz.domain.use_cases

data class QuestionUseCases(
    val getQuestion: GetQuestionUseCase,
    val validateAnswer: ValidateAnswerUseCase,
    val getAnswerColorUseCase: GetAnswerColorUseCase
)
