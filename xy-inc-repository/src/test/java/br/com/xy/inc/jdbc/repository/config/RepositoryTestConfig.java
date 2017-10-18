package br.com.xy.inc.jdbc.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@Import(RepositoryConfig.class)
@ActiveProfiles({"test", "postgresql"})
@ComponentScan(basePackages = "br.com.xy.inc.jdbc.repository")
public class RepositoryTestConfig {
}
