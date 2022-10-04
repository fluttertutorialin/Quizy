package com.jdkgroup.quiz.network.model

import com.google.gson.annotations.SerializedName

data class QuestionDto(

    @SerializedName("id")
    var id: String,

    @SerializedName("question")
    var question: String,

    @SerializedName("correctAnswer")
    var answerCorrect: String,

    @SerializedName("incorrectAnswers")
    var answersIncorrect: ArrayList<String>

)
