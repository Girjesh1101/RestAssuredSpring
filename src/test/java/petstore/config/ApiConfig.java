package petstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApiConfig {

    @Value("${petstore.base.url}")
    private String petStoreBaseUrl;

    @Value("${swagger.base.url}")
    private  String swaggerBaseUrl;

    @Value("${test.timeout}")
    private  int timeout;

    @Value("${test.retry.count}")
    private int retryCount;


    public String getSwaggerBaseUrl() {
        return swaggerBaseUrl;
    }

    public String getPetStoreBaseUrl() {
        return petStoreBaseUrl;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getTimeout() {
        return timeout;
    }



}
