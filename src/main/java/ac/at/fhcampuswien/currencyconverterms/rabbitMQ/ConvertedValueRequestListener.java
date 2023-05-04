package ac.at.fhcampuswien.currencyconverterms.rabbitMQ;

import ac.at.fhcampuswien.currencyconverterms.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConvertedValueRequestListener {
    @RabbitListener(queues = {"q.convertedValueRequest"})
    public void onConvertValueRequest(User user) {
        System.out.println("HOLLAFUCKBOYGURLAL");
        System.out.println(user.getLastName());
    }
}


