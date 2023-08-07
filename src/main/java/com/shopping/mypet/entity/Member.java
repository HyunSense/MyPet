package com.shopping.mypet.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class Member {

    private Long id;
    private String loginId;
    private String password;


}
