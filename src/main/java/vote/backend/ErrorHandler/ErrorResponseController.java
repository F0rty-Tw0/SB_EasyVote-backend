package vote.backend.ErrorHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import vote.backend.ErrorHandler.ErrorResponse;

import javax.el.MethodNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorResponseController {

    //creating method that will throw new ErrorResponse
    private ErrorResponse createErrorResponse(
            String message, //to get the message
            HttpStatus httpStatus, //to get the status
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
    public ResponseEntity<ErrorResponse> errorBadRequest(MethodNotFoundException error, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = error.getMessage();

        return new ResponseEntity<ErrorResponse>(
                createErrorResponse(message, httpStatus, webRequest), httpStatus
        );
    }

    //404 not found
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> errorNotFound(EmptyResultDataAccessException error, WebRequest webRequest){
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    String message = error.getMessage();

        return new ResponseEntity<ErrorResponse>(
                createErrorResponse(message, httpStatus, webRequest), httpStatus
        );
    }

    //401 unauthorized

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> errorUnauthorize(DataIntegrityViolationException error, WebRequest request){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String message = error.getMessage();

        return new ResponseEntity<ErrorResponse>(
                createErrorResponse(message, httpStatus, request), httpStatus
        );
    }

    //409 conflict
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> errorConflict(DataIntegrityViolationException error, WebRequest request){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String message = error.getMessage();

        return new ResponseEntity<ErrorResponse>(
                createErrorResponse(message, httpStatus, request), httpStatus
        );
    }

    //500 InternalServer Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> errorInternalServer(Exception error, WebRequest request){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message= error.getMessage();

        return new ResponseEntity<ErrorResponse>(
                createErrorResponse(message, httpStatus, request), httpStatus
        );
    }


}