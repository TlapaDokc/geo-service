package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;


import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {
    @Mock
    private LocalizationServiceImpl localizationService;
    @Mock
    private GeoServiceImpl geoService;
    private MessageSenderImpl messageSender;
    private static final String ipAddressMSC = "172.0.32.11";
    private static final String ipAddressNY = "96.44.183.149";
    private static final String ipAddressUSA = "96.36.165.135";
    private static final String ipAddressRU = "172.0.45.12";

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void sendMoscow() {
        Mockito.when(geoService.byIp(Mockito.eq(ipAddressMSC)))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddressMSC);
        String actualResult = messageSender.send(headers);
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void sendUSA() {
        Mockito.when(geoService.byIp(Mockito.eq(ipAddressUSA)))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddressUSA);
        String actualResult = messageSender.send(headers);
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void sendNY() {
        Mockito.when(geoService.byIp(Mockito.eq(ipAddressNY)))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddressNY);
        String actualResult = messageSender.send(headers);
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void sendRU() {
        Mockito.when(geoService.byIp(Mockito.eq(ipAddressRU)))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddressRU);
        String actualResult = messageSender.send(headers);
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
