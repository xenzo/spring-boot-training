package com.example.demo.first.controller

import com.example.demo.first.model.Greeting
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class RestController {
    private val counter = AtomicLong()

    @RequestMapping(value = ["/"])
    fun greeting(@RequestParam(value = "name", defaultValue = "world") name: String): Greeting =
            Greeting(id = counter.incrementAndGet(), content = "Hello, $name!")
}
