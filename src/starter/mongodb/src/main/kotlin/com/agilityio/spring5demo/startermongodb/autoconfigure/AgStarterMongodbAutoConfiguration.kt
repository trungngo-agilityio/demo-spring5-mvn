package com.agilityio.spring5demo.startermongodb.autoconfigure

import com.agilityio.spring5demo.startermongodb.configurations.AgStarterMongodbConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * Configuration for auto scanning Spring Integration specific components and annotations,
 * which aren't reachable by standard Spring component scan
 */
@Configuration
@Import(
    AgStarterMongodbConfiguration::class
)
class AgStarterMongodbAutoConfiguration
