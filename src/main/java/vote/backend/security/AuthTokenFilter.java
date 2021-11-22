package vote.backend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import vote.backend.entities.User.User;
import vote.backend.security.JWT.JwtUtils;
import vote.backend.services.NemService.NemDetailsServiceImpl;
import vote.backend.services.UserService.UserService;

public class AuthTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private NemDetailsServiceImpl nemDetailsService;

  @Autowired
  private UserService userService;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  )
    throws ServletException, IOException {
    try {
      String jwt = parseJwt(request);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getNemUserNameFromJwtToken(jwt);
        UserDetails nemDetails = nemDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          nemDetails,
          null,
          getGrantedAuthorities(jwt)
        );
        authentication.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      System.out.println("Cannot set user authentication: " + e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }
    return null;
  }

  private List<GrantedAuthority> getGrantedAuthorities(String jwt) {
    try {
      List<GrantedAuthority> authorities = new ArrayList<>();
      User user = userService.findUserByNemId(
        Long.parseLong(jwtUtils.getNemIdFromJwtToken(jwt))
      );
      authorities.add(
        new SimpleGrantedAuthority(user.getRole().getName().name())
      );
      return authorities;
    } catch (Exception e) {
      System.out.println("Cannot get granted authorities: " + e);
      return Collections.emptyList();
    }
  }
}
