package com.grishberg.performeter.samples

private const val RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"


class KotlinSample1 : Runnable {
    override fun run() {
        val words = "Hello, i am test string"
val charsSet = HashSet<Char>()

if (charsSet.isEmpty()) {
    RU.forEach {
        charsSet.add(it)
    }
}

var hasLetters = false
words.forEach {
    val char = it.toLowerCase()
    if (!hasLetters && char.isLetter()) {
        hasLetters = true
    }
    if (char.isLetter() && !charsSet.contains(char)) {
        return
    }
}
    }
}