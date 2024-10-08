package prj1.configurations;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

@Component("dateTimeProvider")
public class CustomDateTimeProvider implements DateTimeProvider {
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(ZonedDateTime.now());
  }
}
