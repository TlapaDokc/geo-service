package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {
    @Test
    void locale() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String actualResultRU = localizationService.locale(Country.RUSSIA);
        String actualResultUSA = localizationService.locale(Country.USA);
        String expectedResultRU = "Добро пожаловать";
        String expectedResultUSA = "Welcome";
        Assertions.assertEquals(expectedResultRU, actualResultRU);
        Assertions.assertEquals(expectedResultUSA, actualResultUSA);
    }
}
