package com.github.christophpickl.codingdojo.wordcount

class WordCounter {
    fun count(text: String): Int {
        if (text.isEmpty()) {
            return 0
        }
        return text.split(" ").size
    }
}
