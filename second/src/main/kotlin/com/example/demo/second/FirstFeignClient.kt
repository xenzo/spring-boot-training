package com.example.demo.second

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("first")
interface FirstFeignClient {
    @RequestMapping(method = [RequestMethod.GET], value = ["/"], consumes = ["application/json"])
    fun getFirst(): String?
}