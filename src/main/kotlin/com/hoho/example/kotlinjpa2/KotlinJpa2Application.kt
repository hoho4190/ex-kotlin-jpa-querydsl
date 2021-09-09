package com.hoho.example.kotlinjpa2

import com.hoho.example.kotlinjpa2.config.ConfigureSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(value = [ConfigureSource::class])
class KotlinJpa2Application

fun main(args: Array<String>) {
	runApplication<KotlinJpa2Application>(*args)
}
