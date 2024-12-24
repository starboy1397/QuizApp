package com.mastercoding.thequizapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.thequizapp.model.Question;
import com.mastercoding.thequizapp.model.QuestionList;
import com.mastercoding.thequizapp.retrofit.QuestionAPI;
import com.mastercoding.thequizapp.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    //interact with API service interfaces
    // handling data retrieval and operations

    private QuestionAPI questionAPI;

    public QuizRepository() {
        //New retrofit instance this creates an instance of your retrofit instance class
        // which is responsible for retrofit instance with a predefined base URL and JSON converter.
        //.getRetrofitInstance this method call gets the retrofit instance configured by your
        // retrofit instance class
        // .create method creates an implementation of the API interface
        // The question api interface likely contains method for making network request to
        //retrieve questions from the API
        this.questionAPI = new RetrofitInstance().getRetrofitInstance().create(QuestionAPI.class) ;
    }

    public LiveData<QuestionList> getQuestionsFromAPI() {
        //MutableLiveData commonly used to hold and observe data
        MutableLiveData<QuestionList> data  = new MutableLiveData<>();
        Call<QuestionList> response = questionAPI.getQuestions();
        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                QuestionList questionList = response.body();
                data.setValue(questionList);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });
        return data;
    }
}
