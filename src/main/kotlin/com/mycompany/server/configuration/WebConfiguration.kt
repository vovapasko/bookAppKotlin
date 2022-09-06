package com.mycompany.server.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "webconfigs", ignoreInvalidFields = false)
class WebConfiguration {

    var cors: Cors = Cors()

    class Cors {
        var allowedOrigins: Array<String> = emptyArray()
    }

}