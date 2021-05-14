package com.lihan.vocabularycard

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


fun Fragment.setIntSharedPreferences(key : String , value : Int){
    requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
        .putInt(key,value)
        .commit()
}
fun Fragment.getIntSharedPreferences(key : String) : Int {
    return requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA,Context.MODE_PRIVATE).getInt(key,-1)
}

fun Fragment.saveVocabularyListSharedPreferences(list: ArrayList<Vocabulary>, key: String?) {
    val editor = requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
    val gson = Gson()
    val json: String = gson.toJson(list)
    editor.putString(key, json)
    editor.commit()
}

fun Fragment.getVocabularyListSharedPreferences(key: String?): ArrayList<Vocabulary> {
    val prefs = requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(key, "").toString()
    val type = object : TypeToken<ArrayList<Vocabulary>>() {}.type
    return gson.fromJson(json, type)?:ArrayList<Vocabulary>()
}

fun Fragment.saveTagListSharedPreferences(list: ArrayList<Tag>, key: String?) {
    val editor = requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
    val gson = Gson()
    val json: String = gson.toJson(list)
    editor.putString(key, json)
    editor.commit()
}

fun Fragment.getTagListSharedPreferences(key: String?): ArrayList<Tag> {
    val prefs = requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(key, "").toString()
    val type = object : TypeToken<ArrayList<Tag>>() {}.type
    return gson.fromJson(json, type)?:ArrayList<Tag>()
}
fun Fragment.getTagObject() : Tag{
    val prefs = requireContext().getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(SHAREDPREFERENCES_TAG, "")?:""
    return gson.fromJson(json, Tag::class.java)?:Tag()
}

fun Activity.saveTagObject(tag: Tag){
    val editor = getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
    val gson = Gson()
    val json = gson.toJson(tag)
    editor.putString(SHAREDPREFERENCES_TAG, json)
    editor.commit()
}

fun Activity.getTagObject() : Tag{
    val prefs = getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(SHAREDPREFERENCES_TAG, "")?:""
    return gson.fromJson(json, Tag::class.java)?:Tag()
}

fun Activity.getTagListSharedPreferences(key: String?): ArrayList<Tag> {
    val prefs = getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(key, "").toString()
    val type = object : TypeToken<ArrayList<Tag>>() {}.type
    return gson.fromJson(json, type)?:ArrayList<Tag>()
}

fun Activity.saveVocabularyListSharedPreferences(list: ArrayList<Vocabulary>, key: String?): Boolean {
    val editor = getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
    val gson = Gson()
    val json: String = gson.toJson(list)
    editor.putString(key, json)
    return editor.commit()
}

fun Activity.getVocabularyListSharedPreferences(key: String?): ArrayList<Vocabulary> {
    val prefs = getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(key, "").toString()
    val type = object : TypeToken<ArrayList<Vocabulary>>() {}.type
    return gson.fromJson(json, type)?:ArrayList<Vocabulary>()
}



fun AndroidViewModel.saveVocabularyListSharedPreferences(list: ArrayList<Vocabulary>, key: String?) {
    val context = getApplication<Application>().applicationContext
    val editor = context.getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE).edit()
    val gson = Gson()
    val json: String = gson.toJson(list)
    editor.putString(key, json)
    editor.commit()
}

fun AndroidViewModel.getVocabularyListSharedPreferences(key: String?): ArrayList<Vocabulary> {
    val context = getApplication<Application>().applicationContext
    val prefs = context.getSharedPreferences(SHAREDPREFERENCES_DATA, Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String = prefs.getString(key, "").toString()
    val type = object : TypeToken<ArrayList<Vocabulary>>() {}.type
    return gson.fromJson(json, type)?:ArrayList<Vocabulary>()
}