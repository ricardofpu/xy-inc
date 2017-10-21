package br.com.xy.inc.jdbc.repository.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "br.com.xy.inc.jdbc.repository")
@EnableAutoConfiguration
public class RepositoryConfig {
}
