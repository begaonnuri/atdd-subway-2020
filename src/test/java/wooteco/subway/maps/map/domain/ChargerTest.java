package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static wooteco.subway.maps.map.domain.Charger.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChargerTest {
    @DisplayName("10km 이내인 경우 기본 요금 반환")
    @Test
    void chargeByDistance() {
        int totalDistance = 9;
        Charger charger = new Charger(totalDistance);

        assertThat(charger.chargeByDistance()).isEqualTo(DEFAULT_FARE);
    }

    @DisplayName("10km 초과 50Km 이내인 경우 초과 요금 반환")
    @Test
    void chargeByDistance2() {
        int totalDistance = 16;
        Charger charger = new Charger(totalDistance);

        assertThat(charger.chargeByDistance()).isEqualTo(DEFAULT_FARE + 100);
    }

    @DisplayName("50km 초과인 경우 초과 요금 반환")
    @Test
    void chargeByDistance3() {
        int totalDistance = 66;
        Charger charger = new Charger(totalDistance);

        assertThat(charger.chargeByDistance()).isEqualTo(DEFAULT_FARE + 200);
    }
}