import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.*;
import java.util.stream.Collectors;

public class Time {
    private static final Logger logger = Logger.getLogger("Timestamps");

    public static void main(String[] args) {
        //listZones();
        convertDateToCETTimestamp(2017, Month.SEPTEMBER, 1);
    }

    // given a date, translate to TS following CET
    private static long convertDateToCETTimestamp(int year, Month month, int day) {
        LocalDateTime epoch = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime ldt = LocalDateTime.of(year, month, day, 0, 0);

        ZonedDateTime cet = ldt.atZone(ZoneId.of("CET"));
        ZonedDateTime utc = epoch.atZone(ZoneId.of("UTC"));

        long ts = ChronoUnit.SECONDS.between(utc, cet);

        return ts;
    }

    private static void listZones() {

        List<String> foundZones = ZoneId.getAvailableZoneIds().stream()
                .filter(zone -> zone.contains("CET") || zone.contains("UTC"))
                .collect(Collectors.toList());

        foundZones.forEach((logger::info));

    }
}

