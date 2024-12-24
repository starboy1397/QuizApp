package com.mastercoding.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mastercoding.thequizapp.databinding.ActivityMainBinding;
import com.mastercoding.thequizapp.model.Question;
import com.mastercoding.thequizapp.model.QuestionList;
import com.mastercoding.thequizapp.viewmodel.QuizViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<Question> questionList;

    static int result = 0;
    static int totalQuestions = 0;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Resetting the scores
        result = 0;
        totalQuestions = 0;

        //Creating an instance of 'QuizViewModel'
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        //Displaying the first question
        DisplayFirstQuestion();
        binding.btnNext.setOnClickListener(view -> {
           DisplayNextQuestion(); 
        });

    }

    private void DisplayNextQuestion() {

        //direct the user to the results activity
        if (binding.btnNext.getText().equals("Finish")){
            Intent i = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(i);
            finish();
        }

        //Displaying the question
        int selectedId = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            //more question to display
            if((questionList.size() - i) > 0) {

                //getting the number of questions
                totalQuestions = questionList.size();
                //check if the chosen option is correct
                if (radioButton.getText().toString().equals(questionList.get(i).getCorrectOption())) {
                    result++;
                    binding.textResult.setText("Correct Answers : " + result);
                }
                if (i==0) {
                    i++;
                }
                //displaying the next question
                binding.txtQuestion.setText("Question " + (i + 1) + ": " + questionList.get(i).getQuestion());
                binding.radio1.setText(questionList.get(i).getOption1());
                binding.radio2.setText(questionList.get(i).getOption2());
                binding.radio3.setText(questionList.get(i).getOption3());
                binding.radio4.setText(questionList.get(i).getOption4());

                //check if it is he last question
                if (i == (questionList.size() - 1) ) {
                    binding.btnNext.setText("Finish");
                }
                binding.radioGroup.clearCheck();
                i++;

            }else {
                if (radioButton.getText().toString().equals(
                        questionList.get(i-1).getCorrectOption()
                )){
                    result++;
                    binding.textResult.setText("Correct Answers : " + result);
                }
            }
        } else {
            Toast.makeText(this,
                    "Please select an option",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void DisplayFirstQuestion() {
        //observing live data from view model
        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {

                //called when data inside livedata changes
                questionList = questions;
                binding.txtQuestion.setText("Question 1: "+questionList.get(0).getQuestion());
                binding.radio1.setText(questionList.get(0).getOption1());
                binding.radio2.setText(questionList.get(0).getOption2());
                binding.radio3.setText(questionList.get(0).getOption3());
                binding.radio4.setText(questionList.get(0).getOption4());
            }
        });
    }
}