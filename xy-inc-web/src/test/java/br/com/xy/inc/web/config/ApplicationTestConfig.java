package br.com.xy.inc.web.config;

import br.com.xy.inc.web.Application;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Application.class)
public class ApplicationTestConfig {
}
