package com.example.demo.first.controller

import com.example.demo.first.model.Greeting
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RefreshScope
class RestController {
    private val counter = AtomicLong()
    @Value("\${example.property}")
    private var prop: String = ""

    @RequestMapping(value = ["/"])
    fun greeting(@RequestParam(value = "name", defaultValue = "world") name: String): Greeting =
            Greeting(id = counter.incrementAndGet(), content = "Hello, $name!, $prop")
}
