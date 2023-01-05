package com.recipe.blogRecipes.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
/**
 * Annotation that is used to resolve {@link Authentication#getPrincipal()} to a method. Provides core user information.
 *
 * @return {@link it.cgmconsulting.myblog.security.UserPrincipal}
 * @see org.springframework.security.core.userdetails.UserDetails UserDetails
 *
 * @author Adelchi Valenti
 */
public @interface CurrentUser {
}
