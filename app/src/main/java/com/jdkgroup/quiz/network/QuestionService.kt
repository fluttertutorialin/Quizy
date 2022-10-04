package com.jdkgroup.quiz.network

import com.jdkgroup.quiz.network.model.QuestionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionService {

    @GET("api/questions")
    suspend fun get(
        @Query("limit") limit: Int = 1
    ) : List<QuestionDto>
}