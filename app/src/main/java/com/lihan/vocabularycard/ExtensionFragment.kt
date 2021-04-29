package com.lihan.vocabularycard

import android.content.Context
import androidx.fragment.app.Fragment



fun Fragment.setIntSharedPreferences(key : String , value : Int){
    requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
        .putInt(key,value)
        .apply()
}
fun Fragment.getIntSharedPreferences(key : String) : Int {
    return requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA,Context.MODE_PRIVATE).getInt(key,-1)
}