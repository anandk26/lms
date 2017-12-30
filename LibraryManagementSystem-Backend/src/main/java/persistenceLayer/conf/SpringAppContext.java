package persistenceLayer.conf;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "persistenceLayer.model")
public class SpringAppContext {
    
}
