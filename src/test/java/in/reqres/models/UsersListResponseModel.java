package in.reqres.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersListResponseModel {

    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private List<User> data;

    @Data
    public static class User {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }
}
