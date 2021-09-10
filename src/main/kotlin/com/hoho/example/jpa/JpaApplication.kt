package com.hoho.example.jpa

import com.hoho.example.jpa.config.ConfigureSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(value = [ConfigureSource::class])
class JpaApplication

fun main(args: Array<String>) {
	runApplication<JpaApplication>(*args)
}
