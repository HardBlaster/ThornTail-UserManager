package hu.innovitech.web.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressVO {

    private Integer postalCode;
    private String city;
    private String street;
    private Integer house;

}
