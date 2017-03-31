package fr.ebiz.cdb;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
@ComponentScan("fr.ebiz.cdb.console")
public class CLIConfig {

}
