package ac.at.fhcampuswien.currencyconverterms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue convertedValueResponseQueue() {
        return new Queue("q.convertedValueResponse");
    }


    @Bean
    public Queue convertedValueRequestQueue() {
        return new Queue("q.convertedValueRequest");
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

}
