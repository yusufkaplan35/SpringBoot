package com.tpe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class UserRequest {

    @NotBlank(message = "Please provide firstName")
    private String firstName;

    @NotBlank(message = "Please provide lastName")
    private String lastName;

    @NotBlank(message = "Please provide userName")
    private String userName;

    @NotBlank(message = "Please provide Password")
    private String password;
}