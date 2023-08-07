package com.shopping.mypet.util;

public abstract class RegExpConst {

    /**
     * [정규식 모음-4] 사용자 정보 관련 정규식
     */

// 1. 사용자 이메일에 대한 정규식
    public static final String REGEXP_LIGHT_USER_EMAIL = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$";  // 언더바(_), 하이픈(-) 제외
    public static final String REGEXP_TIGHT_USER_EMAIL = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}+$"; // 언더바(_), 하이픈(-) 포함 및 길이 지정
    public static final String REGEXP_USER_EMAIL = "\\w+@\\w+\\.\\w+(\\.\\w+)?";

    // 2. 사용자 아이디에 대한 정규식 - 영문 숫자 조합 6~10자리
    public static final String REGEXP_USER_ID = "^[a-z]{1}[a-z0-9]{5,10}+$";
    public static final String REGEXP_USER_ID2 = "^[a-z0-9]{1}[a-z0-9]{5,10}+$";

    // 3-1. 사용자 패스워드에 대한 정규식 - 대소문자 + 숫자 + 특수문자 조합으로 8 ~ 20자리로 정의 한다.
    public static final String REGEXP_USER_PW_TYPE1 = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}+$";

    // 3-2. 사용자 패스워드에 대한 정규식 - 알파벳으로 시작 + 숫자 조합으로 6자리 이상으로 정의 한다.
    public static final String REGEXP_USER_PW_TYPE2 = "^([a-zA-z]{1}[a-z0-9](\\W)+){5,15}+$";



    // 4. 핸드폰 번호 타입1에 대한 정규식 => ex) 01012345678
    public static final String REGEXP_PHONE_TYPE1 = "^[\\d]{11}+$";

    // 5. 핸드폰 번호 타입2에 대한 정규식 => ex) 010-1234-5678
    public static final String REGEXP_PHONE_TYPE2_1 = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})+$";
    public static final String REGEXP_PHONE_TYPE2_2 = "^[\\d]{2,3}-[\\d]{3,4}-[\\d]{4}+$";

    // 6. 핸드폰 번호 타입3에 대한 정규식 => ex) +82-010-1234-5678
    public static final String REGEXP_PHONE_TYPE3 = "^\\+82-01([0|1|6|7|8|9])-([\\d]{3,4})-([\\d]{4})+$";

    // 7. 핸드폰 번호 타입4에 대한 정규식 => ex) +82-10-1234-5678
    public static final String REGEXP_PHONE_TYPE4 = "^\\+82-10-([\\d]{3,4})-([\\d]{4})+$";

    // 8. 주민등록번호 타입에 대한 정규식
    public static final String REGEXP_REGISTRATION_NUM = "^[\\d]{6}-[1-4][\\d]{6}+$";

    // 9. 우편 번호에 대한 정규식
    public static final String REGEXP_POSTAL_CODE = "^[\\d]{3}-[\\d]{2}+$";

}
