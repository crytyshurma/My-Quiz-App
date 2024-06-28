package com.example.myquizapp;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myquizapp.databinding.ActivityResults2Binding;

public class ResultsActivity extends AppCompatActivity {

    ActivityResults2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_results2);
        binding.txtAnswer.setText(
                "Your score is: "
                +MainActivity.result
                +"/" + MainActivity.totalQuestions);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultsActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}