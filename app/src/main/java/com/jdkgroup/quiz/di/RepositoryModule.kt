package com.jdkgroup.quiz.di

import com.jdkgroup.quiz.network.QuestionService
import com.jdkgroup.quiz.network.model.QuestionDtoMapper
import com.jdkgroup.quiz.repository.QuestionRepository
import com.jdkgroup.quiz.repository.QuestionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionRepository(questionService: QuestionService, mapper: QuestionDtoMapper)
    : QuestionRepository {
        return QuestionRepositoryImpl(questionService, mapper)
    }
}