package com.jdkgroup.quiz.network.model

import com.jdkgroup.quiz.domain.model.Question
import com.jdkgroup.quiz.domain.util.DomainMapper

class QuestionDtoMapper : DomainMapper<QuestionDto, Question> {

    override fun mapToDomainModel(model: QuestionDto): Question {
        //TODO could this be done somewhere else? maybe in the business logic?
        val answersAll = model.answersIncorrect
        answersAll.add(model.answerCorrect)
        answersAll.shuffle()

        return Question(
            id = model.id,
            question = model.question,
            correctAnswer = model.answerCorrect,
            allAnswers = answersAll
        )
    }

}