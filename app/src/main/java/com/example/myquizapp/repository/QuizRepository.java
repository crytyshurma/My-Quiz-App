package com.example.myquizapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myquizapp.model.QuestionList;
import com.example.myquizapp.retrofit.QuestionsAPI;
import com.example.myquizapp.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    //Interacts with the api service interfaces
    //handling data retrieval and operations
    private QuestionsAPI questionsAPI;

    public QuizRepository(){
        this.questionsAPI = new RetrofitInstance()
                .getRetrofitInstance()
                .create(QuestionsAPI.class);
    }
    public LiveData<QuestionList> getQuestionsFromAPI(){
        MutableLiveData<QuestionList> data = new MutableLiveData<>();

        Call<QuestionList> response= questionsAPI.getQuestions();
        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                QuestionList list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable throwable) {

            }
        });
       return data;
    }
}
