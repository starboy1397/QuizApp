package com.mastercoding.thequizapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.thequizapp.model.QuestionList;
import com.mastercoding.thequizapp.repository.QuizRepository;

public class QuizViewModel extends ViewModel {

    //the repository is used to interact with data sources
    QuizRepository repository = new QuizRepository();

    // in this case its expected to hol the question list object
    LiveData<QuestionList> questionListLiveData;

    public QuizViewModel() {
        questionListLiveData = repository.getQuestionsFromAPI();
    }
    //getter method to access external components eg:- Ui components to access question list
    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }

}
