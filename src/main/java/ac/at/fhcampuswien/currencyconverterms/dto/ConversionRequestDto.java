package ac.at.fhcampuswien.currencyconverterms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversionRequestDto implements Serializable {

    private String currentCurrency;
    private String chosenCurrency;
}
