package com.agilityio.spring5demo.startermongodb.configurations

import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.config.EnableMongoAuditing

@Configuration
@AutoConfigureBefore(
    MongoAutoConfiguration::class,
    EmbeddedMongoAutoConfiguration::class
)
@EnableConfigurationProperties(
    AgStarterMongodbProperties::class
)
@EnableMongoAuditing
class AgStarterMongodbConfiguration {

    /**
     * If the [AgStarterMongodbProperties] application context is not set up,
     * the default context shall be created.
     */
    @Bean
    @ConditionalOnMissingBean
    fun starterMongodbProperties(): AgStarterMongodbProperties {
        return AgStarterMongodbProperties()
    }

    @Bean
    @Primary
    fun mongoProperties(starterMongodbProperties: AgStarterMongodbProperties): MongoProperties {
        val mongoProperties = MongoProperties()
        mongoProperties.authenticationDatabase = starterMongodbProperties.authDatabase
        mongoProperties.database = starterMongodbProperties.database
        mongoProperties.uri = starterMongodbProperties.uri
        return mongoProperties
    }
//
//    // FIXME: MatchIfMissing should be true
//    @Bean
//    @ConditionalOnProperty(
//        prefix = "mongodb",
//        name = ["migrationEnabled"],
//        havingValue = "true",
//        matchIfMissing = false
//    )
//    fun mongobee(
//        mongoProperties: MongoProperties,
//        starterMongodbProperties: StarterMongodbProperties
//    ): Mongobee {
//        val mongobee = Mongobee(mongoProperties.uri)
//        mongobee.setChangeLogsScanPackage(starterMongodbProperties.migrationPackage)
//        return mongobee
//    }
}
