package com.shift.kanna

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KannaApplication

fun main(args: Array<String>) {
    runApplication<KannaApplication>(*args)
}
