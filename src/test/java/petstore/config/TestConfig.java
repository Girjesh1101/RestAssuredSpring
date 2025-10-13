package petstore.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.RequestBuilder;

import static io.restassured.config.EncoderConfig.encoderConfig;


@Configuration
@ComponentScan(basePackages = "petstore")
@Import(ApiConfig.class)
public class TestConfig {

    @Autowired
    private  ApiConfig apiConfig;

    @Bean
    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(apiConfig.getPetStoreBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setConfig(RestAssuredConfig.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", apiConfig.getTimeout())
                                .setParam("http.socket.timeout", apiConfig.getTimeout()))
                        .encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .build();
    }
}

