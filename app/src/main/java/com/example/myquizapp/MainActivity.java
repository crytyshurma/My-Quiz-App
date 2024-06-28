package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myquizapp.databinding.ActivityMainBinding;
import com.example.myquizapp.model.Question;
import com.example.myquizapp.model.QuestionList;
import com.example.myquizapp.viewmodel.QuizViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;
    QuizViewModel  quizViewModel;
    List<Question> questionList;
    static int result= 0;
    static int totalQuestions=0;

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //data binding
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        //Resetting the scores
        result = 0;
        totalQuestions =0;

        //creating instance of 'QuizViewModel'
        quizViewModel = new ViewModelProvider(this ).get(QuizViewModel.class);//This is the older way to instantiate replace it with new one

        //Display the first question:

     DisplayFirstQuestion();

     binding.btnNext.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             DisplayNextQuestion();
         }
     });

    }

    public void DisplayFirstQuestion(){
        //observing LiveData from a ViewModel
        quizViewModel.getQuestionListLiveData().observe(
                this,
                new Observer<QuestionList>() {
                    @Override
                    public void onChanged(QuestionList questions) {
                        // Called when the data inside LIVEDATA changes
                        questionList = questions;
                        binding.txtQuestion.setText("Question 1: "+questions.get(0).getQuestion());
                        binding.radio1.setText(questions.get(0).getOption1());
                        binding.radio2.setText(questions.get(0).getOption2());
                        binding.radio3.setText(questions.get(0).getOption3());
                        binding.radio4.setText(questions.get(0).getOption4());
                    }
                }
        );
    }

    public void DisplayNextQuestion(){
        //Direct user to the results activity
        if (binding.btnNext.getText().equals("Finish")){
            Intent intent= new Intent(MainActivity.this,ResultsActivity.class);
            startActivity(intent);
            finish();
        }



        //Displaying the question
        int selectedOption = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedOption != -1) {
            RadioButton radioButton = findViewById(selectedOption);


            //More questions to display??
            if ((questionList.size()) - i > 0) {
                //getting number of questions
                totalQuestions = questionList.size();
                //chechking if the option is correct
                if (radioButton.getText().toString().equals(
                        questionList.get(i).getCorrectOption()
                )){
                    result++;

                    binding.txtResult.setText(
                            "Correct Answers: "+result
                    );
                }
                if(i==0)
                {
                    i++;
                }
                //Displaying the next question
                binding.txtQuestion.setText("Question "+(i+1)+":"+
                        questionList.get(i).getQuestion());
                binding.radio1.setText(questionList.get(i).getOption1());
                binding.radio2.setText(questionList.get(i).getOption2());
                binding.radio3.setText(questionList.get(i).getOption3());
                binding.radio4.setText(questionList.get(i).getOption4());

                //check if it is the last question
                if(i == (questionList.size() -1)){
                    binding.btnNext.setText("Finish");
                }
                binding.radioGroup.clearCheck();
                i++;

            }else{
                if (radioButton.getText().toString().equals(
                        questionList.get(i-1).getCorrectOption()
                )){
                    result++;
                    binding.txtResult.setText("Correct Answers : "+result);
                }

            }// i have missed some code here to check if it is needed or not as i think it as unnecessary

        }else{
            Toast.makeText(this,
                    "No option Selected",
                    Toast.LENGTH_SHORT).show();
        }


    }

}