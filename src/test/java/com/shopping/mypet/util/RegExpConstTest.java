package com.shopping.mypet.util;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpConstTest {


    @Test
    void regEx() {

        Pattern pwPattern = Pattern.compile(RegExpConst.REGEXP_USER_PW_TYPE2);

        // "(^[\\w]+$){2,12}";
        Pattern namePattern = Pattern.compile(RegExpConst.REGEXP_KOREAN_ENGLISH);

        Matcher pwMatcher1 = pwPattern.matcher("a123ass!");
        Matcher pwMatcher2 = pwPattern.matcher("abc123");
        Matcher nameMatcher = namePattern.matcher("sdfdsㅇ");


        boolean matches = pwMatcher1.matches();
        System.out.println(matches);
        boolean matches2 = pwMatcher2.matches();
        System.out.println(matches2);
        boolean nameCheck = nameMatcher.matches();
        System.out.println("nameCheck = " + nameCheck);


    }
}