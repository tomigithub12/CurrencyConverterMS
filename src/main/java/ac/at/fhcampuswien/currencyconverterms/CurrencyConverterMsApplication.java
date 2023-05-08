package ac.at.fhcampuswien.currencyconverterms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CurrencyConverterMS API", version = "1.0"))
public class CurrencyConverterMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterMsApplication.class, args);
	}

}
