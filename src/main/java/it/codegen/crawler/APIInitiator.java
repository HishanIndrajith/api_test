package it.codegen.crawler;

import it.codegen.crawler.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class APIInitiator
{
	public static void main( String[] args )
	{
		SpringApplication.run( APIInitiator.class,args );
	}
}
