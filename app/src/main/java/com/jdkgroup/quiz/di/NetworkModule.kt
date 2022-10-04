package com.jdkgroup.quiz.di

import com.google.gson.GsonBuilder
import com.jdkgroup.quiz.network.QuestionService
import com.jdkgroup.quiz.network.model.QuestionDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideQuestionService(): QuestionService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://the-trivia-api.com/") //TODO replace with string res
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(QuestionService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionDtoMapper(): QuestionDtoMapper {
        return QuestionDtoMapper()
    }

}