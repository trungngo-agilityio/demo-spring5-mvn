package com.agilityio.spring5demo.startermongodb.configurations

import lombok.ToString
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.util.StringUtils
import javax.annotation.PostConstruct


@ToString
@ConfigurationProperties(prefix = "mongodb")
class AgStarterMongodbProperties {
    var authDatabase: String? = null
    var database: String? = null
    var uri: String? = null
    var migrationEnabled = true
    var migrationPackage: String? = null

    @Value("\${spring.application.name:}")
    private val applicationName: String? = null

    @PostConstruct
    fun init() {
        if (authDatabase == null) authDatabase = "admin"
        if (database == null) database = "db"

        if (uri == null) uri = "mongodb://user:pass@localhost:27017/$database"
        if (migrationEnabled && !StringUtils.hasText(migrationPackage)) {
            // If migration is enabled but no scan package is supplied, uses "migrations" package
            // Mongobee will use this to find the @ChangeLog-annotated classes
            migrationPackage = "com.agilityio." +
                applicationName!!.replace("-".toRegex(), "") + ".migrations"
        }
    }
}
