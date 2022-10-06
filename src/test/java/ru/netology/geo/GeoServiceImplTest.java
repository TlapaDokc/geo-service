package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void byIp(String ip, Location result) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location actualResult = geoService.byIp(ip);
        Location expectedResult = result;
        Assertions.assertEquals(expectedResult.getCountry(), actualResult.getCountry());
        Assertions.assertEquals(expectedResult.getCity(), actualResult.getCity());
        Assertions.assertEquals(expectedResult.getStreet(), actualResult.getStreet());
        Assertions.assertEquals(expectedResult.getBuiling(), actualResult.getBuiling());
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("96.36.165.135", new Location("New York", Country.USA, null, 0)),
                Arguments.of("172.0.45.12", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("127.0.0.1", new Location(null, null, null, 0))
        );
    }
}
