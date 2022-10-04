package com.jdkgroup.quiz.domain.model


data class Question(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val allAnswers: ArrayList<String>
)
