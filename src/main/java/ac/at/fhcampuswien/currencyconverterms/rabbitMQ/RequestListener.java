package ac.at.fhcampuswien.currencyconverterms.rabbitMQ;

import ac.at.fhcampuswien.currencyconverterms.config.RabbitMQConfig;
import ac.at.fhcampuswien.currencyconverterms.dto.ConversionRequestDto;
import ac.at.fhcampuswien.currencyconverterms.dto.CustomExchangeRateDto;
import ac.at.fhcampuswien.currencyconverterms.service.CurrencyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestListener {

    Logger logger = LoggerFactory.getLogger(RequestListener.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    MessageConverter messageConverter;

    @RabbitListener(queues = RabbitMQConfig.EXCHANGERATE_MESSAGE_QUEUE)
    public double onConvertedValueRequest(ConversionRequestDto conversionRequestDto) {
        logger.warn("Retrieved request from CarInventoryMS!");
        return currencyService.getConvertedValue(1f, conversionRequestDto.getCurrentCurrency(), conversionRequestDto.getChosenCurrency());
    }

    @RabbitListener(queues = RabbitMQConfig.CUSTOM_EXCHANGERATE_MESSAGE_QUEUE)
    public double onCustomRateRequest(CustomExchangeRateDto customExchangeRateDto) {
        logger.warn("Retrieved request from CarInventoryMS!");
        return currencyService.getConvertedValue(customExchangeRateDto.getValue(), customExchangeRateDto.getCurrentCurrency(), customExchangeRateDto.getChosenCurrency());
    }
}


