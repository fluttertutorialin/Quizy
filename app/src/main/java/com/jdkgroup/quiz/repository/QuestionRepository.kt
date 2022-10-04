package com.jdkgroup.quiz.repository

import com.jdkgroup.quiz.domain.model.Question
import com.jdkgroup.quiz.util.Resource

interface QuestionRepository {

    suspend fun get(): Resource<Question>

}