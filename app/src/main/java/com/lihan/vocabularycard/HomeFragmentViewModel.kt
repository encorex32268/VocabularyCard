package com.lihan.vocabularycard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application){

    private var vocabularys = MutableLiveData<ArrayList<Vocabulary>>()
    init {
        loadData()
    }

    fun getVocabularys(): LiveData<ArrayList<Vocabulary>> {
        return vocabularys
    }

    private fun loadData() {
        vocabularys.postValue(getVocabularyListSharedPreferences(SHAREDPREFERENCES_NOWLIST))
    }




}