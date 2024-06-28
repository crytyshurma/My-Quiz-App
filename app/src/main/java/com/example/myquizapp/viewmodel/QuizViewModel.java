package com.example.myquizapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myquizapp.model.QuestionList;
import com.example.myquizapp.repository.QuizRepository;

public class QuizViewModel extends ViewModel {


    QuizRepository repository =  new QuizRepository();
    LiveData<QuestionList> questionListLiveData;

    public QuizViewModel() {
        questionListLiveData = repository.getQuestionsFromAPI();
    }

    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
