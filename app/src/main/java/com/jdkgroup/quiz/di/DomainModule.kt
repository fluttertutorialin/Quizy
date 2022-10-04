package com.jdkgroup.quiz.di

import com.jdkgroup.quiz.domain.use_cases.GetAnswerColorUseCase
import com.jdkgroup.quiz.domain.use_cases.GetQuestionUseCase
import com.jdkgroup.quiz.domain.use_cases.QuestionUseCases
import com.jdkgroup.quiz.domain.use_cases.ValidateAnswerUseCase
import com.jdkgroup.quiz.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideUseCases(questionRepository: QuestionRepository): QuestionUseCases {
        return QuestionUseCases(
            getQuestion = GetQuestionUseCase(questionRepository),
            validateAnswer = ValidateAnswerUseCase(),
            getAnswerColorUseCase = GetAnswerColorUseCase()
        )
    }

}