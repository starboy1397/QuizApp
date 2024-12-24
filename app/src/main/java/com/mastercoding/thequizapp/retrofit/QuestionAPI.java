package com.mastercoding.thequizapp.retrofit;

import com.mastercoding.thequizapp.model.Question;
import com.mastercoding.thequizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionAPI {
    //Used to define the structure and behaviour of
    //network request to a RESTful API.
    //Acts as a bridge between android app and the web services

    @GET("myquizapi.php") //end point
    Call<QuestionList> getQuestions();


}
