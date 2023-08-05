package com.equipo2.Appkademy.core.converter;

import com.equipo2.Appkademy.core.model.enums.Currency;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, String> {


    @Override
    public String convertToDatabaseColumn(Currency currency) {
        return currency != null ? currency.getCode() : null;
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        return dbData != null ? Currency.getInstance(dbData) : null;
    }
}
