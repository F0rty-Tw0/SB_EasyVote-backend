package vote.backend.ErrorHandler;

import java.time.LocalDateTime;
import javax.el.MethodNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ErrorResponseController {

  private boolean isAnonymous(Authentication authentication) {
    return authentication
      .getAuthorities()
      .stream()
      .anyMatch(role -> role.getAuthority().equals("ROLE_ANONYMOUS"));
  }

  // creating method that will throw new ErrorResponse
  private ErrorResponse createErrorResponse(
    String message, // to get the message
    HttpStatus httpStatus, // to get the status
    WebRequest webRequest // to get the description
  ) {
    return new ErrorResponse(
      message,
      webRequest.getDescription(false),
      httpStatus.value(),
      LocalDateTime.now()
    );
  }

  // 400 bad request
  @ExceptionHandler(MethodNotFoundException.class)
  public ResponseEntity<ErrorResponse> errorBadRequest(
    MethodNotFoundException error,
    WebRequest webRequest
  ) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String message = error.getMessage();

    return new ResponseEntity<>(
      createErrorResponse(message, httpStatus, webRequest),
      httpStatus
    );
  }

  // 404 not found
  @ExceptionHandler(
    value = {
      EmptyResultDataAccessException.class, ResourceNotFoundException.class,
    }
  )
  public ResponseEntity<ErrorResponse> errorNotFound(
    RuntimeException error,
    WebRequest webRequest
  ) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    String message = error.getMessage();

    return new ResponseEntity<>(
      createErrorResponse(message, httpStatus, webRequest),
      httpStatus
    );
  }

  // 401 & 409 unauthorized

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> errorUnauthorize(
    DataIntegrityViolationException error,
    WebRequest request
  ) {
    HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    String message = error.getMessage();

    return new ResponseEntity<>(
      createErrorResponse(message, httpStatus, request),
      httpStatus
    );
  }

  // 401 & 403 forbidden
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> errorUnauthorize(
    AccessDeniedException error,
    WebRequest request
  ) {
    String message = error.getMessage();
    HttpStatus httpStatus;
    Authentication authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();
    if (isAnonymous(authentication)) {
      httpStatus = HttpStatus.UNAUTHORIZED;
      message = "You must be logged in to access this resource";
    } else {
      httpStatus = HttpStatus.FORBIDDEN;
      message = "You are not authorized to perform this action";
    }
    return new ResponseEntity<>(
      createErrorResponse(message, httpStatus, request),
      httpStatus
    );
  }

  // 500 InternalServer Error
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> errorInternalServer(
    Exception error,
    WebRequest request
  ) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    String message = error.getMessage();

    return new ResponseEntity<>(
      createErrorResponse(message, httpStatus, request),
      httpStatus
    );
  }
}
