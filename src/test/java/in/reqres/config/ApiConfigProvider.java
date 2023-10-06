package in.reqres.config;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class ApiConfigProvider {

    ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());

    public void setUp() {
        RestAssured.baseURI = apiConfig.baseUrl();
        RestAssured.basePath = apiConfig.basePath();
    }
}
