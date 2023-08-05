package com.equipo2.Appkademy.core.model.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Currency {

    ARS,
    USD,
    COP;

    public static Currency getInstance(String dbData) {
        return switch (dbData) {
            case "COP" -> Currency.COP;
            case "USD" -> Currency.USD;
            default -> Currency.ARS;
        };
    }

    public String getCode(){
        return this.toString();
    }

}
