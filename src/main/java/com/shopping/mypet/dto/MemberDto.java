package com.shopping.mypet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;


}
