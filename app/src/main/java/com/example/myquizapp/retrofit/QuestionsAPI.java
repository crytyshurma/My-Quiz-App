package com.example.myquizapp.retrofit;

import com.example.myquizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {
    //used to define the structure and behaviour of
    // network requests to a RESTful API
    //acts as a bridge between android app and web service


      @GET("myquizapi.php")
    Call<QuestionList> getQuestions();


}