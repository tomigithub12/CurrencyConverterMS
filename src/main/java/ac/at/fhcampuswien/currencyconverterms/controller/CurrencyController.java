package ac.at.fhcampuswien.currencyconverterms.controller;


;
import ac.at.fhcampuswien.currencyconverterms.dto.CurrencyResponseDto;
import ac.at.fhcampuswien.currencyconverterms.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/currency",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Currency", description = "Endpoints for managing currency.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyController {

    private final RabbitTemplate rabbitTemplate;
    public CurrencyController(RabbitTemplate rabbitTemplate, CurrencyService currencyService) {
        this.rabbitTemplate = rabbitTemplate;
        this.currencyService = currencyService;
    }

    @Autowired
    CurrencyService currencyService;
    @GetMapping("/currencyCodes")
    @Operation(
            summary = "Lists all currency codes.",
            tags = {"Currency"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CurrencyService.class))),
                    @ApiResponse(description = "Currency Service is not available!", responseCode = "500", content = @Content)
            })
    public ResponseEntity<CurrencyResponseDto> getCurrencyCode() {
        CurrencyResponseDto currencyResponseDto = new CurrencyResponseDto();
        currencyResponseDto.setCurrencyCodes(currencyService.getCurrencyCodes());
        return new ResponseEntity<>(currencyResponseDto, HttpStatus.OK);
    }
    //TODO


}
