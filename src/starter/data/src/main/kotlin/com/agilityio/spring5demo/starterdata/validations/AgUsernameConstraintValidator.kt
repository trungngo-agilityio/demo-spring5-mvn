package com.agilityio.spring5demo.starterdata.validations

import com.agilityio.spring5demo.starterdata.annotations.AgUsername
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class AgUsernameConstraintValidator : ConstraintValidator<AgUsername, String> {
    val maxLength = 30;

    override fun isValid(username: String?, context: ConstraintValidatorContext?): Boolean {
        return username == null;
    }
}
