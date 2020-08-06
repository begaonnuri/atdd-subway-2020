package wooteco.subway.maps.line.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.google.common.collect.Lists;
import wooteco.subway.maps.line.domain.Line;

public class LineResponse {
    private Long id;
    private String name;
    private String color;
    private int extraFare;
    private LocalTime startTime;
    private LocalTime endTime;
    private int intervalTime;
    private List<LineStationResponse> stations;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LineResponse() {
    }

    public LineResponse(Long id, String name, String color, int extraFare,
            LocalTime startTime, LocalTime endTime, int intervalTime,
            List<LineStationResponse> stations, LocalDateTime createdDate,
            LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.extraFare = extraFare;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalTime = intervalTime;
        this.stations = stations;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static LineResponse of(Line line, List<LineStationResponse> stations) {
        return new LineResponse(line.getId(), line.getName(), line.getColor(), line.getExtraFare(),
                line.getStartTime(), line.getEndTime(), line.getIntervalTime(), stations,
                line.getCreatedDate(), line.getModifiedDate());
    }

    public static LineResponse of(Line line) {
        return new LineResponse(line.getId(), line.getName(), line.getColor(), line.getExtraFare(),
                line.getStartTime(), line.getEndTime(), line.getIntervalTime(),
                Lists.newArrayList(), line.getCreatedDate(), line.getModifiedDate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getExtraFare() {
        return extraFare;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public List<LineStationResponse> getStations() {
        return stations;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
