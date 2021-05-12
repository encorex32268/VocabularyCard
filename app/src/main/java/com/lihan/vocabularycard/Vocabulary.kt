package com.lihan.vocabularycard

import java.util.*

data class Vocabulary(
    var front : String ,
    var back : String ,
    var tag : Tag,
    var position : Int
){
    constructor():this("","",Tag(),1)

    override fun toString(): String {
        return "Vocabulary(front='$front', back='$back', tag=${tag.toString()})"
    }


}