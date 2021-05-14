package com.lihan.vocabularycard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeVocabularyCardAdapter(var vocabularys: ArrayList<Vocabulary>)  : RecyclerView.Adapter<HomeVocabularyCardViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeVocabularyCardViewHolder {
        return HomeVocabularyCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_vocabulary,parent,false))
    }

    override fun getItemCount() = vocabularys.size

    override fun onBindViewHolder(holder: HomeVocabularyCardViewHolder, position: Int) {
        holder.bindTo(vocabularys[position])
        holder.itemView.tag = position
        holder.itemView.setOnClickListener {
            holder.flip()
        }
    }

}