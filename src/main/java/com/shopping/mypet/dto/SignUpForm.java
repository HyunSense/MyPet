package com.shopping.mypet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SignUpForm {

//    @NotBlank
    private String loginId;

//    @NotBlank
    private String password;

//    @NotBlank
    private String repeatPassword;

    private String email;

    private String name;

//    @NotEmpty
    private String nickName;

    private String address;
    private String detailAddress;

}
