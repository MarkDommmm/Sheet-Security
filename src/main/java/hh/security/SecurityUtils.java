package hh.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Optional;

public final class SecurityUtils {

  private SecurityUtils() {
  }

  /**
   * Get the login of the current user.
   *
   * @return the login of the current user.
   */
  public static Optional<UserDetails> getCurrentUserLogin() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return Optional.ofNullable(securityContext.getAuthentication())
        .map(authentication -> {
          if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser;
          }
          return null;
        });
  }



}
