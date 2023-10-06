package in.reqres.models;

import lombok.Data;

@Data
public class RegistrationResponseModel {

    private int id;
    private String token, error;
}
