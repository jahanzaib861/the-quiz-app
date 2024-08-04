package com.example.quizappwithfirebase

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.quizappwithfirebase.databinding.ActivityQuizScreenBinding
import com.example.quizappwithfirebase.databinding.ScoreDialogueBinding

class QuizScreen : AppCompatActivity(),View.OnClickListener {
    companion object{
        var questionModelList : List<QuestionModel> = listOf()
        var time : String = ""
    }
    lateinit var binding: ActivityQuizScreenBinding
    var currentQuestionIndex = 0
    var selectedAnswer = ""
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn0.setOnClickListener(this@QuizScreen)
            btn1.setOnClickListener(this@QuizScreen)
            btn2.setOnClickListener(this@QuizScreen)
            btn3.setOnClickListener(this@QuizScreen)
            btnNext.setOnClickListener(this@QuizScreen)
        }
        loadQuestions()
        startTimer()


    }

    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L //converting time into milli seconds
        object :CountDownTimer(totalTimeInMillis,1000){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished/1000
                val minutes = seconds/60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTv.text = String.format("%02d:%02d",minutes,remainingSeconds)
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }
        }.start()
    }

    private fun loadQuestions() {

        selectedAnswer = ""  // also we will set selected ans equal to empty bcz everytime we select the ans it will store some variable so when we load the another Q the selectedAns should be empty

        if (currentQuestionIndex == questionModelList.size){ // it means if the current question is the last Q of the questionModelList then we will call the fun finishQuiz() and we will return from here
            finishQuiz()
            return // we called it so that it will return from here and don't go below
        }

        binding.apply {
            questionIndicatorTv.text = "Question ${currentQuestionIndex + 1}/ ${questionModelList.size}"
            questionProgressIndicator.progress = (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }

    }

    override fun onClick(view: View?) {

        binding.apply {

            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))

        }

        val clickedButton = view as Button
        if (clickedButton.id == R.id.btn_next){
            if (selectedAnswer == questionModelList[currentQuestionIndex].correct){
                score++
                Log.i("score if quiz", score.toString())
            }
            //next button is clicked
            currentQuestionIndex++
            loadQuestions()

        }else{

            onBackPressedDispatcher.addCallback(this,object :OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (currentQuestionIndex==0){
                        finishQuiz()
                        return
                    }
                    currentQuestionIndex--
                    loadQuestions()
                }

            })

            selectedAnswer = clickedButton.text.toString() //whatever the option is selected we are storing that option in selectedAnswer
            //option button is clicked
            clickedButton.setBackgroundColor(getColor(R.color.orange))
            if (selectedAnswer == questionModelList[currentQuestionIndex].correct){
                clickedButton.setBackgroundColor(Color.GREEN)
            }

        }

    }
    private fun finishQuiz(){ //we will call it in loadQuestions()
        //in finish quiz we will show the dialogue, for that we will create the layout which will show that if we pass the quiz or not and then we will show the dialogue box

        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat()) * 100).toInt()

        val dialogueBinding = ScoreDialogueBinding.inflate(layoutInflater)
        dialogueBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            if (percentage>60){
                scoreTitle.text = "Congrats! you have passed"
                scoreTitle.setTextColor(Color.BLUE)
            }else{
                scoreTitle.text = "Oops! you have failed"
                scoreTitle.setTextColor(Color.RED)
            }
            scoreSubtitle.text = "$score out of $totalQuestions are correct"
            finishBtn.setOnClickListener {
                finish()
            }
        }

        AlertDialog.Builder(this)
            .setView(dialogueBinding.root)
            .setCancelable(false)
            .show()
    }
}