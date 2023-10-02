package in.reqres;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HomeWorkTests extends TestBase {

    @Test
    @DisplayName("Успешное создание пользователя")
    void successfulCreateUser() {

        String userData = "{ \"name\": \"amogus\", \"job\": \"qa\" }";

        given()
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("amogus"),
                        "job", is("qa"),
                        "id", is(not(empty())),
                        "createdAt", is(not(empty())));

    }

    @Test
    @DisplayName("Изменение данных пользователя")
    void successfulUpdateUser() {

        String newUserData = "{ \"name\": \"keker\", \"job\": \"kreker\" }";

        given()
                .contentType(ContentType.JSON)
                .body(newUserData)
                .when()
                .put("/users/831")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("keker"),
                        "job", is("kreker"),
                        "updatedAt", is(not(empty())));
    }

    @Test
    @DisplayName("Удаление пользователя")
    void successfulDeleteUser() {

        given()
                .when()
                .delete("/users/831")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    void successfulRegisterUser() {

        String userData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"12345\" }";

        given()
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4),
                        "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("")
    void registrationUserWithoutPassword() {

        String userData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"\" }";

        given()
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
