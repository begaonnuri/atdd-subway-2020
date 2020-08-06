package wooteco.subway.maps.line.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lines {
    private final List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = lines;
    }

    public Lines extractLines(List<Long> lineIds) {
        List<Line> lines = new ArrayList<>();
        for (Long lineId : lineIds) {
            lines = this.lines.stream().filter(line -> line.isSameId(lineId))
                    .collect(Collectors.toList());
        }
        return new Lines(lines);
    }

    public int getMaxExtraFare() {
        return lines.stream()
                .mapToInt(Line::getExtraFare)
                .max()
                .orElse(0);
    }

    public List<Line> getLines() {
        return lines;
    }
}
