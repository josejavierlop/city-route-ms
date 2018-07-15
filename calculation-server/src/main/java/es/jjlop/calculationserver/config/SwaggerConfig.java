package es.jjlop.calculationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "City-Route Calculation Service",
                "City-Route Calculation Service",
                "v1",
                "https://www.gnu.org/licenses/gpl.html",
                new Contact("Jose Javier Lop", "https://www.linkedin.com/in/jos%C3%A9-javier-lop-845422b/", "josejavierlop@gmail.com"),
                "GPL", "https://www.gnu.org/licenses/gpl.html", Collections.emptyList());
    }
}
