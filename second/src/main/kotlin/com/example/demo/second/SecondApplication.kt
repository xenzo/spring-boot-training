package com.example.demo.second

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class SecondApplication

fun main(args: Array<String>) {
    runApplication<SecondApplication>(*args)
}