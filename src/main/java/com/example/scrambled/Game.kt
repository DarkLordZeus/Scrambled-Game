package com.example.scrambled

import androidx.lifecycle.MutableLiveData

class GameViewModel: ViewModel() {
    companion object{
        private var listitem = mutableListOf<String>()
        private var position:Int=1
        private  var Trueanswers = mutableListOf<String>()
    }

    private var _Wordlist:MutableList<String> = mutableListOf()


    private lateinit var currentword:String

    private var _count:Int=0
    val count:Int get() = _count

    private var _score:Int=0
    val score:Int get() = _score

    private lateinit var _currentscrabledword:String
    val currentscrambledword:String get() = _currentscrabledword

    init {
        getnextword()

    }

    private fun getnextword(){
        currentword= allWordsList.random()
        val tempword=currentword.toCharArray()
        while (String(tempword).equals(currentword,false)) {
            tempword.shuffle()
        }
        if(_Wordlist.contains(currentword))
        {
            getnextword()
        }
        else{
            _currentscrabledword= String(tempword)
            ++_count
            _Wordlist.add(currentword)

        }

    }


    fun addtolist()
    {

        listitem.add(GameViewModel.position.toString() +": "+ currentscrambledword +":" +" " + currentword)
        position++
    }

    fun listTOarray(): Array<String> {

        val array:Array<String> = listitem.toTypedArray()
        return array
    }

    fun nextword()
    {

        getnextword()
    }


    fun reinitializeData() {
        numbers.currentnum.value=1
        numbers.scorecard.value=0
        _Wordlist.clear()
        listitem.clear()
        GameViewModel.position=1
        getnextword()

    }


    fun checkright(Scraaaam: String)
    {
        if(Scraaaam.equals(currentword,ignoreCase = true))
        {
            numbers.scorecard.value = numbers.scorecard.value?.plus(4)
        }
        numbers.currentnum.value = numbers.currentnum.value?.plus(1)

    }




}
