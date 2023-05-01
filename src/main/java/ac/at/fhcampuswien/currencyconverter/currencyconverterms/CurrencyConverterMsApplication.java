package ac.at.fhcampuswien.currencyconverter.currencyconverterms;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConverterMsApplication {

	@PostConstruct
	public void dummyMethod(){
		CurrencyService currencyService = new CurrencyService();
		currencyService.getCurrencyCodes();
		currencyService.getConvertedValue();
	}
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterMsApplication.class, args);
	}

}
