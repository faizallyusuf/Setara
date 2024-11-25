package com.capstone.setara.view

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.R

class QnaActivity : AppCompatActivity() {

    // List pertanyaan dan pilihan jawaban
    private val questions = listOf(
        Question(
            text = "Which of the following are programming languages?",
            options = listOf("Java", "Python", "HTML", "CSS"),
            correctAnswers = listOf("Java", "Python")
        ),
        Question(
            text = "Which of the following are fruits?",
            options = listOf("Apple", "Carrot", "Banana", "Potato"),
            correctAnswers = listOf("Apple", "Banana")
        )
    )

    private var currentQuestionIndex = 0

    private lateinit var tvQuestion: TextView
    private lateinit var checkboxes: List<CheckBox>
    private lateinit var tvFeedback: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qna)

        tvQuestion = findViewById(R.id.tvQuestion)
        tvFeedback = findViewById(R.id.tvFeedback)

        checkboxes = listOf(
            findViewById(R.id.checkbox1),
            findViewById(R.id.checkbox2),
            findViewById(R.id.checkbox3),
            findViewById(R.id.checkbox4)
        )

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Menampilkan soal pertama
        displayQuestion()

        btnSubmit.setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        // Mengambil pertanyaan saat ini
        val currentQuestion = questions[currentQuestionIndex]

        // Menampilkan teks pertanyaan
        tvQuestion.text = currentQuestion.text

        // Menampilkan opsi jawaban di checkbox
        currentQuestion.options.forEachIndexed { index, option ->
            checkboxes[index].text = option
            checkboxes[index].isChecked = false // Reset pilihan
        }

        // Menyembunyikan checkbox jika tidak diperlukan
        for (i in currentQuestion.options.size until checkboxes.size) {
            checkboxes[i].text = ""
            checkboxes[i].isChecked = false
            checkboxes[i].visibility = android.view.View.GONE
        }

        tvFeedback.text = ""
    }

    private fun checkAnswer() {
        val currentQuestion = questions[currentQuestionIndex]

        // Mengambil jawaban yang dipilih
        val selectedAnswers = checkboxes.filter { it.isChecked }.map { it.text.toString() }

        // Memeriksa apakah jawaban benar
        if (selectedAnswers.containsAll(currentQuestion.correctAnswers) &&
            currentQuestion.correctAnswers.containsAll(selectedAnswers)
        ) {
            tvFeedback.text = "Correct!"
        } else {
            tvFeedback.text = "Wrong! Correct answers are: ${currentQuestion.correctAnswers.joinToString(", ")}"
        }

        // Menampilkan soal berikutnya jika ada
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            displayQuestion()
        } else {
            tvFeedback.text = "${tvFeedback.text}\nYou've completed the quiz!"
        }
    }
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswers: List<String>
)
