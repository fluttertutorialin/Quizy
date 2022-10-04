package com.jdkgroup.quiz.repository

import com.jdkgroup.quiz.domain.model.Question
import com.jdkgroup.quiz.network.QuestionService
import com.jdkgroup.quiz.network.model.QuestionDto
import com.jdkgroup.quiz.network.model.QuestionDtoMapper
import com.jdkgroup.quiz.util.Resource

class QuestionRepositoryImpl(
    private val questionService: QuestionService,
    private val mapper: QuestionDtoMapper
) : QuestionRepository {

    override suspend fun get(): Resource<Question> {
        val listOfQuestionDto: List<QuestionDto>
        try{
            listOfQuestionDto = questionService.get()
        }
        catch(e: Exception) {
            return Resource.Error(e.message)
        }
        return Resource.Success(mapper.mapToDomainModel(listOfQuestionDto[0]))
    }

}