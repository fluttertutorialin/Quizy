package com.jdkgroup.quiz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdkgroup.quiz.domain.model.Question
import com.jdkgroup.quiz.ui.question.QuestionViewModel

@Composable
fun ShowQuiz(
    viewModel: QuestionViewModel,
    question: Question
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            //.border((BorderStroke(1.dp, Color.Red)))
            .verticalScroll(scrollState)
    ) {
        question.let { it ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(8.dp)
                //.background(Color.Cyan),
                , elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.question,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    //.background(Green)
                    .padding(8.dp)
            ) {
                for (answer in question.allAnswers) {
                    val colorBackground =
                        if (viewModel.userSelectedAnswer.value.equals(answer)) {
                            Color.White
                        } else Color.LightGray

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        border = BorderStroke(1.dp, viewModel.getBorderColor(answer)),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorBackground),
                        onClick = {
                            viewModel.setAnswer(answer)
                        }
                    ) {
                        Text(
                            text = answer
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                //.background(Color.Yellow)
            ) {
                Button(
                    onClick = { viewModel.checkAnswer() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    border = BorderStroke(1.dp, Color.DarkGray),
                    shape = RoundedCornerShape(60.dp)
                ) {
                    Text(
                        text = "Submit Answer",
                        style = MaterialTheme.typography.button
                    )
                }

                Spacer(modifier = Modifier.padding(bottom = 16.dp))

                ClickableText(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = AnnotatedString("Skip Question"),
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    onClick = { viewModel.getQuestion() },
                )
            }
        }
    }
}