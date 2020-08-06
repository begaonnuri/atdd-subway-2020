package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.*;
import static wooteco.subway.maps.map.domain.Charger.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChargerTest {
    @DisplayName("10km 이내인 경우 기본 요금 반환")
    @Test
    void chargeByDistance() {
        int totalDistance = 9;
        Charger charger = new Charger(totalDistance, 0);

        assertThat(charger.chargeByDistance()).isEqualTo(DEFAULT_FARE);
    }

    @DisplayName("10km 초과 50Km 이내인 경우 초과 요금 반환")
    @Test
    void chargeByDistance2() {
        int totalDistance = 15;
        Charger charger = new Charger(totalDistance, 0);

        assertThat(charger.chargeByDistance()).isEqualTo(DEFAULT_FARE + EXTRA_FARE);
    }

    @DisplayName("50km 초과인 경우 초과 요금 반환")
    @Test
    void chargeByDistance3() {
        int totalDistance = 58;
        Charger charger = new Charger(totalDistance, 0);

        assertThat(charger.chargeByDistance()).isEqualTo(DEFAULT_FARE + EXTRA_FARE);
    }

    @DisplayName("라인에 추가요금이 있는 경우 추가요금을 더해서 반환")
    @Test
    void charge() {
        int totalDistance = 15;
        int maxExtraFare = 1000;
        Charger charger = new Charger(totalDistance, maxExtraFare);

        assertThat(charger.charge()).isEqualTo(DEFAULT_FARE + EXTRA_FARE + maxExtraFare);
    }
}