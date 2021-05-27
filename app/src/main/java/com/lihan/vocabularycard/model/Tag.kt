package com.lihan.vocabularycard.model

data class Tag(
    var color : Int,
    var name : String
){
    constructor():this(-1,"未分類")
    override fun toString(): String {
        return "Tag(color=$color, name='$name')"
    }
}
