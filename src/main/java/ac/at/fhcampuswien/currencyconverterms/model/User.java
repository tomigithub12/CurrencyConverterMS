package ac.at.fhcampuswien.currencyconverterms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;

}
