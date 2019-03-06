package com.example.demo.second

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@RestController
@RequestMapping(value = ["/"])
class FirstRestTemplateClient {
    @Autowired
    lateinit var restTemplate: RestTemplate
    @Autowired
    lateinit var feignClient: FirstFeignClient

    @GetMapping(value = ["/rest"])
    fun getFirst(): String? {
        return restTemplate.getForEntity<String>("http://first/").body
    }

    @GetMapping(value = ["/feign"])
    fun getFirstFeign(): String? {
        return feignClient.getFirst()
    }
}