package oliin.apps.workplacer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@OpenAPIDefinition
//@Configuration
//public class SpringdocConfiguration {
//
//    @Bean
//    public OpenAPI baseOpenAPI() {
//        return new OpenAPI().info(new Info()
//                .title("Sabre Gateway API")
//                .version("1.0.0")
//                .description("Sabre API for Workplacer Mobile App"));
//    }
//}
//
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("oliin.apps"))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Tech Interface - Spring Boot Swagger Configuration")
                .description("\"Swagger configuration for application \"")
                .version("1.1.0")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }
    //for Swagger api doc generation
    //http://localhost:8091/v2/api-docs
}