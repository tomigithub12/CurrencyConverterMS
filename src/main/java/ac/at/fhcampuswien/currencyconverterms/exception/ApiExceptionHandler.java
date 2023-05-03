package ac.at.fhcampuswien.currencyconverterms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApiExceptionHandler {

    private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Europe/Vienna"));

    @ExceptionHandler(CurrencyServiceNotAvailableException.class)
    public ResponseEntity<?> handlegRPCServiceConnectionError(CurrencyServiceNotAvailableException currencyServiceNotAvailableException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiException apiException = new ApiException(
                currencyServiceNotAvailableException.getMessage(),
                httpStatus,
                timestamp
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }



}
