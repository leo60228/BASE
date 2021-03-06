package com.teamacronymcoders.base.modulesystem;

import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Module {
    String value();

    RegistrySide side() default RegistrySide.BOTH;
}
