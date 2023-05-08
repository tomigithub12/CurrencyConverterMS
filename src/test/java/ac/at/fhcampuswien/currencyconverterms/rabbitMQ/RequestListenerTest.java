package ac.at.fhcampuswien.currencyconverterms.rabbitMQ;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ac.at.fhcampuswien.currencyconverterms.config.RabbitMQConfig;
import ac.at.fhcampuswien.currencyconverterms.dto.ConversionRequestDto;
import ac.at.fhcampuswien.currencyconverterms.dto.CustomExchangeRateDto;
import ac.at.fhcampuswien.currencyconverterms.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestListenerTest {

    RequestListener requestListener;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Mock
    CurrencyService currencyService;

    @Mock
    MessageConverter messageConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        requestListener = new RequestListener();
        requestListener.rabbitTemplate = rabbitTemplate;
        requestListener.currencyService = currencyService;
        requestListener.messageConverter = messageConverter;
    }

    @Test
    public void onConvertedValueRequest_shouldReturnConvertedValue() {
        ConversionRequestDto conversionRequestDto = new ConversionRequestDto();
        conversionRequestDto.setCurrentCurrency("EUR");
        conversionRequestDto.setChosenCurrency("USD");
        when(currencyService.getConvertedValue(any(Float.class), any(String.class), any(String.class))).thenReturn(1.23);

        double actualResult = requestListener.onConvertedValueRequest(conversionRequestDto);

        assertEquals(1.23, actualResult);
    }

    @Test
    public void onCustomRateRequest_shouldReturnConvertedValue() {
        CustomExchangeRateDto customExchangeRateDto = new CustomExchangeRateDto();
        customExchangeRateDto.setCurrentCurrency("EUR");
        customExchangeRateDto.setChosenCurrency("USD");
        customExchangeRateDto.setValue(10f);
        when(currencyService.getConvertedValue(any(Float.class), any(String.class), any(String.class))).thenReturn(12.3);

        double actualResult = requestListener.onCustomRateRequest(customExchangeRateDto);

        assertEquals(12.3, actualResult);
    }
}