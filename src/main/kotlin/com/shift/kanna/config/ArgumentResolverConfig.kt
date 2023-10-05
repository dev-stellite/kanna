package com.shift.kanna.config

import com.shift.kanna.controller.argument.CurrentProjectMemberArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ArgumentResolverConfig(
    private val currentProjectMemberArgumentResolver: CurrentProjectMemberArgumentResolver,
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)

        resolvers.add(currentProjectMemberArgumentResolver)
    }
}