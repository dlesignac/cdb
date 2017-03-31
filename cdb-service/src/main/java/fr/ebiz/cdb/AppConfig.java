package fr.ebiz.cdb;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DBConfig.class)
@ComponentScan("fr.ebiz.cdb.service")
public class AppConfig {

}
