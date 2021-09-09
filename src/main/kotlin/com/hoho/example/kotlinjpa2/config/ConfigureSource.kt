package com.hoho.example.kotlinjpa2.config

import mu.KotlinLogging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.annotation.PostConstruct

private val logger = KotlinLogging.logger {}

@ConstructorBinding
@ConfigurationProperties(prefix = "env")
data class ConfigureSource(
    val rootPath: String
) {
    @PostConstruct
    private fun postConstruct() {
        logger.debug(this.toString())
    }

    override fun toString(): String {
        return System.lineSeparator() + """
            ==== ConfigureSource ===================
            rootPath  : $rootPath
            ========================================
        """.trimIndent()
    }
}
