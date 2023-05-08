package ac.at.fhcampuswien.currencyconverterms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomExchangeRateDto implements Serializable {

    private float value;
    private String currentCurrency;
    private String chosenCurrency;
}
