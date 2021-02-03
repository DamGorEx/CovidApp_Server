package mcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("mcs.Model")
@EntityScan("mcs.Model")
@ComponentScan(basePackages = "mcs.Model")
@Import({SpringSecurityConfig.class, WebConfig.class})
public class CovidAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidAppApplication.class, args);
    }

}
