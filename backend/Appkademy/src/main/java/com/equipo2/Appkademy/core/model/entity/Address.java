package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.City;
import com.equipo2.Appkademy.core.model.enums.Country;
import com.equipo2.Appkademy.core.model.enums.Province;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({
        @AttributeOverride( name = "country", column = @Column(name = "country", nullable = false)),
        @AttributeOverride( name = "province", column = @Column(name = "province", nullable = false)),
        @AttributeOverride( name = "city", column = @Column(name = "city", nullable = false)),
        @AttributeOverride( name = "streetName", column = @Column(name = "street_name", nullable = false)),
        @AttributeOverride( name = "streetNumber", column = @Column(name = "street_number", nullable = false)),
        @AttributeOverride( name = "floorApt", column = @Column(name = "floor_apt", nullable = true))
})
public class Address {

    //TODO zip code?

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Province province;

    @Enumerated(EnumType.STRING)
    private City city;

    private String streetName;
    private String streetNumber;
    private String floorApt;

}
