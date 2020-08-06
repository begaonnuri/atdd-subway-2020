package wooteco.subway.maps.map.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import wooteco.subway.maps.line.domain.Lines;
import wooteco.subway.maps.map.domain.Charger;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;
import wooteco.subway.maps.station.dto.StationResponse;
import wooteco.subway.members.member.domain.LoginMember;

public class PathResponseAssembler {
    public static PathResponse assemble(SubwayPath subwayPath, Map<Long, Station> stations,
            Lines lines, LoginMember loginMember) {
        List<StationResponse> stationResponses = subwayPath.extractStationId().stream()
                .map(it -> StationResponse.of(stations.get(it)))
                .collect(Collectors.toList());

        int distance = subwayPath.calculateDistance();

        int fare = new Charger(subwayPath.calculateDistance(), lines.getMaxExtraFare()).charge();
        if (Objects.isNull(loginMember)) {
            fare = new Charger(subwayPath.calculateDistance(), lines.getMaxExtraFare()).charge();
        }

        return new PathResponse(stationResponses, subwayPath.calculateDuration(), distance, fare);
    }
}
