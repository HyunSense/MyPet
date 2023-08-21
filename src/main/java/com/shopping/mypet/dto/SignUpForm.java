package com.shopping.mypet.dto;

import com.shopping.mypet.util.RegExpConst;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.shopping.mypet.util.RegExpConst.*;

@Getter @Setter @ToString
public class SignUpForm {

    @NotBlank(groups = NotBlankGroup.class)
    @Pattern(regexp = REGEXP_USER_ID_TYPE2, groups = PatternGroup.class)
    private String loginId;

    @NotBlank(groups = NotBlankGroup.class)
    @Pattern(regexp = REGEXP_USER_PW_TYPE1, groups = PatternGroup.class)
    private String password;

    @NotBlank
    private String repeatPassword;

    @NotBlank(groups = NotBlankGroup.class)
    @Email
    private String email;

    @NotBlank(groups = NotBlankGroup.class)
    @Pattern(regexp = REGEXP_KOREAN_ENGLISH, groups = PatternGroup.class)
    private String name;

    @NotBlank(groups = NotBlankGroup.class)
    @Pattern(regexp = REGEXP_KOREAN_ENGLISH, groups = PatternGroup.class)
    private String nickName;

    @NotBlank
    private String address;

    @NotBlank
    private String detailAddress;

}
