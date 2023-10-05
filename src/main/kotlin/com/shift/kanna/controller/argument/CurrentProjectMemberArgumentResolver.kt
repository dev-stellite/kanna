package com.shift.kanna.controller.argument

import com.shift.kanna.dto.account.CustomUserDetails
import com.shift.kanna.model.ProjectMember
import com.shift.kanna.service.ProjectService
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerMapping

@Component
class CurrentProjectMemberArgumentResolver(
    private val projectService: ProjectService,
): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return ProjectMember::class.java.isAssignableFrom(parameter.parameterType)
                && parameter.hasParameterAnnotation(CurrentProjectMember::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val uriTemplateVariables = webRequest.getAttribute(
            HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
            RequestAttributes.SCOPE_REQUEST
        ) as Map<*, *>
        val projectId = (uriTemplateVariables["projectId"] as String?)?.toLong()
            ?: throw IllegalArgumentException("Project ID is invalid")

        val user = SecurityContextHolder.getContext().authentication.principal
        if (user !is CustomUserDetails) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        return projectService.getProjectMemberByAccount(projectId, user.id)
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
    }
}