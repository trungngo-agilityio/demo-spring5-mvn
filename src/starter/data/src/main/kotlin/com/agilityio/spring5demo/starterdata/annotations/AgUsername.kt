package com.agilityio.spring5demo.starterdata.annotations

import com.agilityio.spring5demo.starterdata.validations.AgUsernameConstraintValidator
import javax.validation.Constraint
import javax.validation.ReportAsSingleViolation


@Constraint(validatedBy = [AgUsernameConstraintValidator::class])
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.VALUE_PARAMETER
)
@ReportAsSingleViolation
@Retention(
    AnnotationRetention.RUNTIME
)
annotation class AgUsername(
    val message: String = "Invalid Username",
)
