package com.system.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(PasswordEncoderGenerator.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        logger.info(passwordEncoder.encode("1234"));

    }
}
