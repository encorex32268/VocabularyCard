package com.lihan.vocabularycard

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.lihan.vocabularycard.databinding.ActivityCreateBinding
import com.pes.androidmaterialcolorpickerdialog.ColorPicker


class CreateActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateBinding
    private lateinit var tag : Tag
    private val TAG = CreateActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.createToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tag = getTagObject()

        binding.apply {

            activityCreateConstraintLayout.setOnClickListener {
                createColorTagNameTextView.clearFocus()
                createCardBackVocabularyExplain.clearFocus()
                createCardFrontVocabulary.clearFocus()

            }

            createColorPicker.setOnClickListener {
                val colorPicker = ColorPicker(this@CreateActivity)
                colorPicker.color = tag.color
                colorPicker.show()
                colorPicker.enableAutoClose()
                colorPicker.setCallback { selectedColor ->
                    tag.color = selectedColor
                    saveTagObject(tag)
                    binding.apply {
                        tag.apply {
                            createCardFront.setCardBackgroundColor(color)
                            createCardBack.setCardBackgroundColor(color)
                            createColorView.setBackgroundColor(color)
                            itemHomeVocabularyBack.homeCardFrontLayout.setBackgroundColor(color)
                            itemHomeVocabularyFront.homeCardBackLayout.setBackgroundColor(color)
                        }

                    }
                }
            }
            createCardFrontVocabulary.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    itemHomeVocabularyFront.itemHomeFrontTextView.text = p0
                }
            })
            createCardBackVocabularyExplain.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    itemHomeVocabularyBack.itemHomeBackTextView.text = p0
                }
            })

            tag.apply {
                createColorView.setBackgroundColor(color)
                createCardBack.setCardBackgroundColor(color)
                createCardFront.setCardBackgroundColor(color)
                createColorTagNameTextView.setText(name)
                itemHomeVocabularyBack.homeCardFrontLayout.setBackgroundColor(color)
                itemHomeVocabularyFront.homeCardBackLayout.setBackgroundColor(color)

            }



            createFloatingActionButton.setOnClickListener {
                if(createCardFrontVocabulary.text.toString().isNullOrEmpty() || createCardBackVocabularyExplain.text.toString().isNullOrEmpty())return@setOnClickListener
                val tagList = getTagListSharedPreferences(SHAREDPREFERENCES_TAGLIST)
                tag.name = createColorTagNameTextView.text.toString()?:"Unkown"
                tagList.add(tag)
                val vocabulary = Vocabulary().apply {
                    front = createCardFrontVocabulary.text.toString()
                    back = createCardBackVocabularyExplain.text.toString()
                    tag = tag
                }
                val nowList = getVocabularyListSharedPreferences(SHAREDPREFERENCES_NOWLIST)
                Log.d(TAG, "onCreate: ${nowList.size}")
                nowList.add(vocabulary)
                saveVocabularyListSharedPreferences(nowList,SHAREDPREFERENCES_NOWLIST)

                val vocabularyList = getVocabularyListSharedPreferences(SHAREDPREFERENCES_VOCABULARYLIST)
                vocabularyList.add(vocabulary)
                saveVocabularyListSharedPreferences(vocabularyList, SHAREDPREFERENCES_VOCABULARYLIST)

                finish()

            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    Log.d("focus", "touchevent")
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}