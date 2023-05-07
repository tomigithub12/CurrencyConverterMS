package ac.at.fhcampuswien.currencyconverterms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class CustomExchangeRateDto implements Serializable {

    private float value;
    private String currentCurrency;
    private String chosenCurrency;
}
