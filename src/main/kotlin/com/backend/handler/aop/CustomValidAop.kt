package com.backend.handler.aop

import com.backend.handler.ex.CustomValidationException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
@Aspect
class CustomValidAop {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    fun postMapping(){}

    @Around("postMapping()")
    fun validationAdvice(proceedingJoinPoint:ProceedingJoinPoint) : Any {
        val args = proceedingJoinPoint.args
        for (arg in args) {
            val bindingResult = arg as? BindingResult ?: continue
            if (bindingResult.hasErrors()) {
                val errorMap = mutableMapOf<String, String?>()
                for (fieldError in bindingResult.fieldErrors) {
                    errorMap[fieldError.field] = fieldError.defaultMessage
                }
                throw CustomValidationException(errorMap = errorMap)
            }
        }
        return proceedingJoinPoint.proceed()
    }
}