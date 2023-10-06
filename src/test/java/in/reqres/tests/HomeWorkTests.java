package in.reqres.tests;

import in.reqres.TestBase;
import in.reqres.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static in.reqres.specs.RegistrationSpec.*;
import static in.reqres.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class HomeWorkTests extends TestBase {

    @Test
    @DisplayName("Успешное создание пользователя")
    void successfulCreateUser() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("amogus");
        userData.setJob("qa");

        UserResponseModel response =
                step("Отправка запроса создания пользователя", () ->
                        given(createOrUpdateUserRequestSpec)
                                .body(userData)
                                .when()
                                .post("/users")
                                .then()
                                .spec(createUser201ResponseSpec)
                                .extract().as(UserResponseModel.class));

        step("Проверка полей ответа", () -> {
            assertThat(response.getName()).isEqualTo("amogus");
            assertThat(response.getJob()).isEqualTo("qa");
            assertThat(response.getId()).isNotNull();
            assertThat(response.getCreatedAt()).isNotNull();
        });
    }

    @Test
    @DisplayName("Изменение данных пользователя")
    void successfulUpdateUser() {
        UserBodyModel newUserData = new UserBodyModel();
        newUserData.setName("keker");
        newUserData.setJob("kreker");

        UserResponseModel response =
                step("Отправка запроса обновления пользователя", () ->
                        given(createOrUpdateUserRequestSpec)
                                .body(newUserData)
                                .when()
                                .put("/users/831")
                                .then()
                                .spec(updateUser200ResponseSpec)
                                .extract().as(UserResponseModel.class));

        step("Проверка полей ответа", () -> {
            assertThat(response.getName()).isEqualTo("keker");
            assertThat(response.getJob()).isEqualTo("kreker");
            assertThat(response.getUpdatedAt()).isNotNull();
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    void successfulDeleteUser() {
        step("Отправка запроса удаления пользователя", () ->
                given(deleteUserRequestSpec)
                        .when()
                        .delete("/users/831")
                        .then()
                        .spec(deleteUser204ResponseSpec));
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    void successfulRegisterUser() {
        RegistrationBodyModel userData = new RegistrationBodyModel();
        userData.setEmail("eve.holt@reqres.in");
        userData.setPassword("12345");

        RegistrationResponseModel response =
                step("Отправка запроса регистрации пользователя", () ->
                        given(registrationRequestSpec)
                                .body(userData)
                                .when()
                                .post("/register")
                                .then()
                                .spec(registration200ResponseSpec)
                                .extract().as(RegistrationResponseModel.class));

        step("Проверка полей ответа", () -> {
            assertThat(response.getId()).isEqualTo(4);
            assertThat(response.getToken()).isNotNull();
        });
    }

    @Test
    @DisplayName("Регистрация без пароля")
    void registrationUserWithoutPassword() {
        RegistrationBodyModel userData = new RegistrationBodyModel();
        userData.setEmail("eve.holt@reqres.in");

        RegistrationResponseModel response =
                step("Отправка запроса регистрации без пароля", () ->
                        given(registrationRequestSpec)
                                .body(userData)
                                .when()
                                .post("/register")
                                .then()
                                .spec(registrationWithoutPassword400ResponseSpec)
                                .extract().as(RegistrationResponseModel.class));

        step("Проверка полей ответа", () -> {
            assertThat(response.getError()).isEqualTo("Missing password");
        });
    }

    @Test
    @DisplayName("Получение списка пользователей")
    void successfulGetUsersList() {
        UsersListResponseModel response =
                step("Отправка запроса на получение списка пользователей", () ->
                        given(usersListRequestSpec)
                                .when()
                                .get("/users?page=2")
                                .then()
                                .spec(showUsersList200RequestSpec)
                                .extract().as(UsersListResponseModel.class));

        List<UsersListResponseModel.User> users = response.getData();
        UsersListResponseModel.User user = users.get(0);

        step("Проверка полей ответа", () -> {
            assertThat(users.size()).isEqualTo(response.getPerPage()).isEqualTo(6);
            assertThat(response.getPage()).isEqualTo(2);
            assertThat(user.getId()).isEqualTo(7);
        });
    }
}