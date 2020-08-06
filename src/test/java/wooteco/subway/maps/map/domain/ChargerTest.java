package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.*;
import static wooteco.subway.maps.map.domain.Charger.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wooteco.subway.members.member.domain.LoginMember;

class ChargerTest {
    @DisplayName("10km 이내인 경우 기본 요금 반환")
    @Test
    void chargeByDistance() {
        int totalDistance = 9;
        Charger charger = new Charger(totalDistance, 0, null);

        assertThat(charger.charge()).isEqualTo(DEFAULT_FARE);
    }

    @DisplayName("10km 초과 50Km 이내인 경우 초과 요금 반환")
    @Test
    void chargeByDistance2() {
        int totalDistance = 15;
        Charger charger = new Charger(totalDistance, 0, null);

        assertThat(charger.charge()).isEqualTo(DEFAULT_FARE + EXTRA_FARE);
    }

    @DisplayName("50km 초과인 경우 초과 요금 반환")
    @Test
    void chargeByDistance3() {
        int totalDistance = 58;
        Charger charger = new Charger(totalDistance, 0, null);

        assertThat(charger.charge()).isEqualTo(DEFAULT_FARE + EXTRA_FARE);
    }

    @DisplayName("라인에 추가요금이 있는 경우 추가요금을 더해서 반환")
    @Test
    void charge() {
        int totalDistance = 15;
        int maxExtraFare = 1000;
        Charger charger = new Charger(totalDistance, maxExtraFare, null);

        assertThat(charger.charge()).isEqualTo(DEFAULT_FARE + EXTRA_FARE + maxExtraFare);
    }

    @DisplayName("어린이인 경우 기본요금에 할인 적용")
    @Test
    void chargeByAge() {
        int totalDistance = 9;
        int childAge = 8;
        LoginMember loginMember = new LoginMember(1L, "", "", childAge);
        Charger charger = new Charger(totalDistance, 0, loginMember);

        double expected = (DEFAULT_FARE - DEDUCTED_AMOUNT) * CHILD_DISCOUNT_RATE;
        assertThat(charger.charge()).isEqualTo((int)expected);
    }

    @DisplayName("어린이인 경우 거리 추가요금에 할인 적용")
    @Test
    void chargeByAge2() {
        int totalDistance = 15;
        int childAge = 8;
        LoginMember loginMember = new LoginMember(1L, "", "", childAge);
        Charger charger = new Charger(totalDistance, 0, loginMember);

        double expected = (DEFAULT_FARE + EXTRA_FARE - DEDUCTED_AMOUNT) * CHILD_DISCOUNT_RATE;
        assertThat(charger.charge()).isEqualTo((int)expected);
    }

    @DisplayName("청소년인 경우 기본요금에 할인 적용")
    @Test
    void chargeByAge3() {
        int totalDistance = 9;
        int youthAge = 15;
        LoginMember loginMember = new LoginMember(1L, "", "", youthAge);
        Charger charger = new Charger(totalDistance, 0, loginMember);

        double expected = (DEFAULT_FARE - DEDUCTED_AMOUNT) * YOUTH_DISCOUNT_RATE;
        assertThat(charger.charge()).isEqualTo((int)expected);
    }

    @DisplayName("청소년인 경우 거리 추가요금에 할인 적용")
    @Test
    void chargeByAge4() {
        int totalDistance = 15;
        int youthAge = 15;
        LoginMember loginMember = new LoginMember(1L, "", "", youthAge);
        Charger charger = new Charger(totalDistance, 0, loginMember);

        double expected = (DEFAULT_FARE + EXTRA_FARE - DEDUCTED_AMOUNT) * YOUTH_DISCOUNT_RATE;
        assertThat(charger.charge()).isEqualTo((int)expected);
    }
}