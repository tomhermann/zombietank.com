package com.zombietank.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

/** Production ("prod") Profile */
@Target(ElementType.TYPE) 
@Retention(RetentionPolicy.RUNTIME)
@Profile("prod")
public @interface Prod {
}
