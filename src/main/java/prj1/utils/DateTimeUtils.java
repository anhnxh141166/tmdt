package prj1.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import prj1.commons.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

@Slf4j
public class DateTimeUtils {

  public static final String YYYYMMDD = "yyyyMMdd";
  public static final String HHMMSS = "hhmmss";
  public static final String DDMMYYYY = "dd/MM/yyyy";
  public static final String YYYY_MM_DD = "yyyy-MM-dd";

  private DateTimeUtils() {
  }

  public static String reformatDate(String inputDate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
    SimpleDateFormat simpleDateVNFormat = new SimpleDateFormat(Constants.VN_DATE_FORMAT);
    String outputDate = inputDate;
    try {
      if (StringUtils.isNotEmpty(inputDate)) {
        inputDate = inputDate.substring(0, 10);
        inputDate = StringUtils.replace(inputDate, Constants.DASH, Constants.DIVISION);
        Date universalDateFormat = simpleDateFormat.parse(inputDate);
        outputDate = simpleDateVNFormat.format(universalDateFormat);
      }
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return outputDate;
  }

  public static String reformatStringLocalDateTimeToLocalDate(String inputDate) {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    SimpleDateFormat simpleDateVNFormat = new SimpleDateFormat("dd/MM/yyyy");
    String outputDate = StringUtils.EMPTY;
    try {
      if (StringUtils.isNotEmpty(inputDate) && !StringUtils.equals(inputDate, "null")) {
//        inputDate = StringUtils.replace(inputDate, Constants.DASH, Constants.DIVISION);
        Date universalDateFormat = simpleDateFormat.parse(inputDate);
        outputDate = simpleDateVNFormat.format(universalDateFormat);
      }
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return outputDate;
  }

  public static String reformatP2PActionDate(String inputDate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
        Constants.DATETIME_FORMAT_WITHOUT_SEPARATOR);
    SimpleDateFormat simpleDateVNFormat = new SimpleDateFormat(
        Constants.VN_DATETIME_FORMAT_WITH_SEPARATOR);
    String outputDate = StringUtils.EMPTY;
    try {
      if (StringUtils.isNotEmpty(inputDate)) {
        inputDate = StringUtils.replace(inputDate, Constants.DASH, Constants.DIVISION);
        Date universalDateFormat = simpleDateFormat.parse(inputDate);
        outputDate = simpleDateVNFormat.format(universalDateFormat);
      }
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
      return inputDate;
    }
    return outputDate;
  }

  public static String reformatDateP2PActionAt(String inputDate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    SimpleDateFormat simpleDateVNFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String outputDate = StringUtils.EMPTY;
    try {
      if (StringUtils.isNotEmpty(inputDate) && !StringUtils.equals(inputDate, "null")) {
//        inputDate = StringUtils.replace(inputDate, Constants.DASH, Constants.DIVISION);
        Date universalDateFormat = simpleDateFormat.parse(inputDate);
        outputDate = simpleDateVNFormat.format(universalDateFormat);
      }
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return outputDate;
  }

  public static String reformatDateToVnFormatForMail(Date inputDate) {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    try {
      if (ObjectUtils.isNotEmpty(inputDate)) {
        return dateFormatter.format(inputDate);
      } else {
        return StringUtils.EMPTY;
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return StringUtils.EMPTY;
    }
  }

  public static String calculateTimeSecondsToLocalDate(LocalDateTime startDateTime,
      Long inputSecond) {
    ZoneId zoneId = ZoneId.of("VST");
    ZonedDateTime vst = ZonedDateTime.of(startDateTime, zoneId);
    long l = vst.toEpochSecond();
    long l2 = l + inputSecond;
    long l3 = l2 * 60 * 1000;
    Instant instant = Instant.ofEpochMilli(l3);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    return String.valueOf(localDateTime);
  }

  public static boolean isValidDateTime(String format, String str) {
    DateFormat sdf = new SimpleDateFormat(format);
    sdf.setLenient(false);
    try {
      sdf.parse(str);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }


  public static String convertDateToString(Date date, String pattern) {
    if (date == null) {
      return null;
    }

    return new SimpleDateFormat(pattern).format(date);
  }

  public static Date convertStringToDate(String dateStr, String pattern) {
    if (dateStr == null) {
      return null;
    }

    try {
      return new SimpleDateFormat(pattern).parse(dateStr);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String reFormatDateTimeString(String s, String oldPattern, String newPattern) {
    if (StringUtils.isEmpty(s)) {
      return Strings.EMPTY;
    }
    LocalDateTime localDate = LocalDateTime.parse(s, DateTimeFormatter.ofPattern(oldPattern));
    return localDate.format(DateTimeFormatter.ofPattern(newPattern));
  }

  public static String reFormatDateString(String s, String oldPattern, String newPattern) {
    if (StringUtils.isEmpty(s)) {
      return Strings.EMPTY;
    }
    LocalDate localDate = LocalDate.parse(s, DateTimeFormatter.ofPattern(oldPattern));
    return localDate.format(DateTimeFormatter.ofPattern(newPattern));
  }

  public static LocalDate stringToLocalDate(String postingDate) {
    try {
      return LocalDate.parse(postingDate);
    } catch (Exception ex) {
      return null;
    }
  }

  public static Boolean isBefore(LocalDate date1, LocalDate date2) {
    if (ObjectUtils.isEmpty(date1) || ObjectUtils.isEmpty(date2)) {
      return false;
    }
    return date1.isBefore(date2);
  }

  public static Boolean isAfter(LocalDate date1, LocalDate date2) {
    if (ObjectUtils.isEmpty(date1) || ObjectUtils.isEmpty(date2)) {
      return false;
    }
    return date1.isAfter(date2);
  }


  private static LocalDate addBusinessDays(LocalDate localDate, int days,
      Optional<List<LocalDate>> holidays) {
    if (localDate == null || days <= 0 || holidays.isEmpty()) {
      throw new IllegalArgumentException(
          "Invalid method argument(s) " + "to addBusinessDays(" + localDate + "," + days + ","
              + holidays + ")");
    }

    Predicate<LocalDate> isHoliday = date -> holidays.map(localDates -> localDates.contains(date))
        .orElse(false);

    Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
        || date.getDayOfWeek() == DayOfWeek.SUNDAY;

    LocalDate result = localDate;
    while (days > 0) {
      result = result.plusDays(1);
      if (isHoliday.or(isWeekend).negate().test(result)) {
        days--;
      }
    }
    return result;
  }

  public static LocalDateTime addBusinessDaysByMinutes(LocalDateTime localDate, int minutes,
      Optional<List<LocalDate>> holidays) {
    int days = minutes / (24 * 60);
    int minuteLessThan1Day = minutes % (24 * 60);
    if (localDate == null || minutes <= 0 || holidays.isEmpty()) {
      throw new IllegalArgumentException(
          "Invalid method argument(s) " + "to addBusinessDays(" + localDate + "," + days + ","
              + holidays + ")");
    }

    Predicate<LocalDate> isHoliday = date -> holidays.map(localDates -> localDates.contains(date))
        .orElse(false);

    Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
        || date.getDayOfWeek() == DayOfWeek.SUNDAY;

    LocalDateTime result = localDate;
    while (days > 0) {
      result = result.plusDays(1);
      if (isHoliday.or(isWeekend).negate().test(LocalDate.from(result))) {
        days--;
      }
    }
    if (minuteLessThan1Day > 0) {
      result = result.plusMinutes(minuteLessThan1Day);
      while (isHoliday.or(isWeekend).test(LocalDate.from(result))) {
        result = result.plusDays(1);
      }
    }
    return result;
  }

  public static LocalDate dayOfThisYear(String mouthAndDay, String charFormatTime) {
    if (StringUtils.isEmpty(mouthAndDay) || StringUtils.isEmpty(charFormatTime)) {
      return null;
    }
    if (mouthAndDay.length() - charFormatTime.length() * 2 != 8) {
      return null;
    }
    try {
      String[] split = mouthAndDay.split(charFormatTime);
      return LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(split[1].trim()),
          Integer.parseInt(split[2].trim()));
    } catch (Exception ex) {
      return null;
    }

  }


}
