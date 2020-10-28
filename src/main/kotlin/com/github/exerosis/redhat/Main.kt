package com.github.exerosis.redhat

import kotlin.text.Charsets.UTF_8

val PATTERN_LIST = Regex("\\s?[, ]\\s?")

fun main() {
    val onOrder = ArrayEvent<(Sequence<String>) -> (Unit)>()
    val onResult = Shop(onOrder)
    onResult { println(it) }
    println("Orders:")
    System.`in`.reader(UTF_8).forEachLine {
        onOrder(it.split(PATTERN_LIST).asSequence())
    }
}