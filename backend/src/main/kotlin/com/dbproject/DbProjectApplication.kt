package com.dbproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DbProjectApplication

fun main(args: Array<String>) {
	runApplication<DbProjectApplication>(*args)
}
