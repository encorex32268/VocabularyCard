package com.lihan.vocabularycard.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lihan.vocabularycard.model.Vocabulary
import com.lihan.vocabularycard.databinding.ItemHomeVocabularyBackBinding
import com.lihan.vocabularycard.databinding.ItemHomeVocabularyBinding
import com.lihan.vocabularycard.databinding.ItemHomeVocabularyFrontBinding

class HomeVocabularyCardViewHolder(itemView : View)  : RecyclerView.ViewHolder(itemView){

    private val binding = ItemHomeVocabularyBinding.bind(itemView)
    private val bindingFront = ItemHomeVocabularyFrontBinding.bind(itemView)
    private val bindingBack = ItemHomeVocabularyBackBinding.bind(itemView)

    fun bindTo(vocabulary: Vocabulary) {
        bindingFront.apply {
            homeCardBackLayout.setBackgroundColor(vocabulary.tag.color)
            itemHomeFrontTextView.text = vocabulary.front
        }
        bindingBack.apply {
            homeCardFrontLayout.setBackgroundColor(vocabulary.tag.color)
            itemHomeBackTextView.text = vocabulary.back
        }
    }
    fun flip(){
        binding.itemHomeEasyFlipView.flipTheView()
    }
}