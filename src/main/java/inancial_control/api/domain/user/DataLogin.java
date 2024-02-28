package inancial_control.api.domain.user;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataLogin (
        @JsonAlias("email") String login, String password){
}
