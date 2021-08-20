package frontend.constant;

import org.springframework.http.HttpStatus;

public class ConstantResponse {

    public static final String SERVICE_UNAVAILABLE="Sorry server is currently down. Please try again later ";
    public static final HttpStatus SERVER_STATUS=HttpStatus.SERVICE_UNAVAILABLE;
    public static final Boolean STATUS=Boolean.FALSE;
    public static final String MESSAGE="Please provide valid input ";

    public static final String BAD_REQUEST="Validation failed";
    public static final Boolean VALIDATION_FAILED=Boolean.TRUE;
}
