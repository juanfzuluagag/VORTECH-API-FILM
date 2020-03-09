package es.com.vortech.film.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("Juan F. Zuluaga Gómez", "https://github.com/juanfzuluagag/VORTECH-API-FILM", "juanf.zuluagag@gmail.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("VORTECH - API - FILM", "Documentación para VORTECH-API-FILM", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    private static final Set<String> DEFAULT_PRODUCES_CONSUMES_AND_CONSUMES = new HashSet<String>(Collections.singletonList("application/json"));

    @Bean
    public Docket createDocketApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_CONSUMES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_CONSUMES_AND_CONSUMES);
    }
}
