package org.csuf.cpsc411.simplehttpclient

import java.util.*

class Claim(var id : String?, var title : String?, var date :  String?, var isSolved:Boolean?) {
    override fun toString(): String = title.toString() + ' ' + date.toString() + ' ' + isSolved.toString()
}