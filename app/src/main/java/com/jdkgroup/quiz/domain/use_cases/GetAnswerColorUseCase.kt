package com.jdkgroup.quiz.domain.use_cases


import androidx.compose.ui.graphics.Color

class GetAnswerColorUseCase {

    operator fun invoke(possibleAnswer: String,
        userSelectedAnswer: String?,
        correctAnswer: String,
        isAnswerCorrect: Boolean?,
    ): Color {
        return when (isAnswerCorrect) {
            null -> {
                Color.Gray
            }
            false -> {
                return when (possibleAnswer) {
                    userSelectedAnswer -> {
                        Color.Red
                    }
                    correctAnswer -> {
                        Color.Green
                    }
                    else -> {
                        Color.Gray
                    }
                }
            }
            true -> {
                return when (possibleAnswer) {
                    userSelectedAnswer -> {
                        Color.Green
                    }
                    correctAnswer -> {
                        Color.Red
                    }
                    else -> {
                        Color.Gray
                    }
                }

            }
        }
    }
}