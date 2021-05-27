package com.lihan.vocabularycard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lihan.vocabularycard.model.SHAREDPREFERENCES_NOWLIST
import com.lihan.vocabularycard.model.Vocabulary
import com.lihan.vocabularycard.model.getVocabularyListSharedPreferences
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

    fun refresh(){
        loadData()
    }



}