package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class BaseSpec {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification responseSpecWithStatus201 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification responseSpecWithStatus200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpecWithStatus204 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification responseSpecWithStatus400 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .build();
}
