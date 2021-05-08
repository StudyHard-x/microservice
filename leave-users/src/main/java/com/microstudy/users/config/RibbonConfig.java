package com.microstudy.users.config;
import configuration.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class RibbonConfig {


}
