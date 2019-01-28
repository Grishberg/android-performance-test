package com.grishberg.performeter.samples

private const val RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"


class KotlinSample2 : Runnable {
    override fun run() {
        val words = "Hello, i am test string"
val charsSet = HashSet<Char>()
if (charsSet.isEmpty()) {
    RU.forEach {
        charsSet.add(it)
    }
}

val result = words.asSequence()
        .filter { it.isLetter() }
        .map { it.toLowerCase() }
        .all { charsSet.contains(it) }
    }
}