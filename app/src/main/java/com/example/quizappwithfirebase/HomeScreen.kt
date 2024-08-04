package com.example.quizappwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizappwithfirebase.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {
    lateinit var binding: ActivityHomeScreenBinding
    lateinit var quizModelList:MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()
    }

    private fun getDataFromFirebase(){
        //dummy data
        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("what is Android?", mutableListOf("Language","OS","Product","none"),"OS"))
        listQuestionModel.add(QuestionModel("who invented Microsoft?", mutableListOf("steve hammer","allen micnulty","Bill Gates","Elon musk"),"Bill Gates"))
        listQuestionModel.add(QuestionModel("what is linux?", mutableListOf("machine system","robot","windows OP","all the above"),"windows OP"))
        listQuestionModel.add(QuestionModel("who owns Apple?", mutableListOf("Jimmy fallon","Steve jobs","Bill gates","Ambani"),"Steve jobs"))
        listQuestionModel.add(QuestionModel("which assistant Android uses?", mutableListOf("Siri","Cortana","Google Assistant","Alexa"),"Google Assistant"))

        val listScienceQuestions = mutableListOf<QuestionModel>()
        listScienceQuestions.add(QuestionModel("What is the chemical symbol for water?", mutableListOf("H2O","CO2","O2","NaCl"),"H2O"))
        listScienceQuestions.add(QuestionModel("Which planet is known as the Red Planet?", mutableListOf("Earth","Venus","Mars","Jupiter"),"Mars"))
        listScienceQuestions.add(QuestionModel(" What is the basic unit of life?", mutableListOf("Atom","Molecule","Cell","Tissue"),"Cell"))
        listScienceQuestions.add(QuestionModel("What do plants use to make their food?", mutableListOf("Oxygen","Water","Sunlight","Soil"),"Sunlight"))
        listScienceQuestions.add(QuestionModel(" Which organ in the human body is responsible for pumping blood?", mutableListOf("Brain","Stomach","Lungs","Heart"),"Heart"))

        val listGeographicQuestions = mutableListOf<QuestionModel>()
        listGeographicQuestions.add(QuestionModel("What is the largest continent by land area?", mutableListOf("Africa","Asia","Europe","North America"),"Asia"))
        listGeographicQuestions.add(QuestionModel("Which ocean is the largest by surface area?", mutableListOf("Atlantic Ocean"," Indian Ocean","Arctic Ocean","Pacific Ocean"),"Pacific Ocean"))
        listGeographicQuestions.add(QuestionModel("Which river is the longest in the world?", mutableListOf("Amazon River","Nile River","Yangtze River","Mississippi Rive"),"Nile River"))
        listGeographicQuestions.add(QuestionModel("Which country is known for having the Great Barrier Reef?", mutableListOf("South Africa","Australia","Brazil","United States"),"Australia"))
        listGeographicQuestions.add(QuestionModel("What is the capital city of France?", mutableListOf("Paris","London","Rome","Berlin"),"Paris"))

        quizModelList.add(QuizModel("1","programming","all the basic programming","10",listQuestionModel))
        quizModelList.add(QuizModel("2","Science","all the science Questions","20",listScienceQuestions))
        quizModelList.add(QuizModel("3","Geography","Boost your knowledge about geography","15",listGeographicQuestions))

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}