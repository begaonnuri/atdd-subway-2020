package wooteco.subway.maps.map.domain;

public class Charger {
    static final int DEFAULT_FARE = 1250;
    private static final int EXTRA_FARE = 100;
    private static final int FIRST_THRESHOLD = 10;
    private static final int SECOND_THRESHOLD = 50;

    private int distance;

    public Charger(int distance) {
        this.distance = distance;
    }

    public int chargeByDistance() {
        if (distance < FIRST_THRESHOLD) {
            return DEFAULT_FARE;
        }
        if (distance < SECOND_THRESHOLD){
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
