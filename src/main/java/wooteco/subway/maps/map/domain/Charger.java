package wooteco.subway.maps.map.domain;

import java.util.Objects;

import wooteco.subway.members.member.domain.LoginMember;

public class Charger {
    static final int DEFAULT_FARE = 1250;
    static final int EXTRA_FARE = 100;
    private static final int FIRST_THRESHOLD = 10;
    private static final int SECOND_THRESHOLD = 50;
    static final int DEDUCTED_AMOUNT = 350;
    static final double CHILD_DISCOUNT_RATE = 0.5;
    static final double YOUTH_DISCOUNT_RATE = 0.8;

    private int distance;
    private int maxExtraFare;
    private LoginMember loginMember;

    public Charger(int distance, int maxExtraFare,
            LoginMember loginMember) {
        this.distance = distance;
        this.maxExtraFare = maxExtraFare;
        this.loginMember = loginMember;
    }

    public int charge() {
        int totalFare = chargeByDistance() + maxExtraFare;
        if (Objects.isNull(loginMember)) {
            return totalFare;
        }
        return chargeByAge(totalFare);
    }

    private int chargeByAge(int totalFare) {
        if (loginMember.isChild()) {
            return (int)((totalFare - DEDUCTED_AMOUNT) * CHILD_DISCOUNT_RATE);
        }
        if (loginMember.isYouth()) {
            return (int)((totalFare - DEDUCTED_AMOUNT) * YOUTH_DISCOUNT_RATE);
        }
        return totalFare;
    }

    private int chargeByDistance() {
        if (distance < FIRST_THRESHOLD) {
            return DEFAULT_FARE;
        }
        if (distance < SECOND_THRESHOLD) {
            return chargeExtraFareByDistance(10, 5);
        }
        return chargeExtraFareByDistance(50, 8);
    }

    private int chargeExtraFareByDistance(int standard, int unit) {
        int fare = DEFAULT_FARE;
        while (distance >= standard + unit) {
            distance -= unit;
            fare += EXTRA_FARE;
        }
        return fare;
    }
}
