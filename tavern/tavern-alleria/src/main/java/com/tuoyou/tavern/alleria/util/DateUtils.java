package com.tuoyou.tavern.alleria.util;

import com.google.common.base.Strings;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DateUtils {
	static final Logger LOGGER = Logger.getLogger(DateUtils.class.getName());
	public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter FIX_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss");
	public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter SIMPLE_8_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	public static final DateTimeFormatter DEFAULT_MIN_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	public static final SimpleDateFormat DEFAULT_DATE_FORMATTER_BY_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DEFAULT_DATETIME_FORMATTER1 = new SimpleDateFormat("yyyy-MM-dd");

	public DateUtils() {
	}

	public static final String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
		return Objects.isNull(dateTime) ? null : dateTime.format(Objects.isNull(formatter) ? DEFAULT_DATETIME_FORMATTER : formatter);
	}

	public static final String formatDate(LocalDate date, DateTimeFormatter formatter) {
		return Objects.isNull(date) ? null : date.format(Objects.isNull(formatter) ? DEFAULT_DATE_FORMATTER : formatter);
	}

	public static final String formatTime(LocalTime time, DateTimeFormatter formatter) {
		return Objects.isNull(time) ? null : time.format(Objects.isNull(formatter) ? DEFAULT_TIME_FORMATTER : formatter);
	}

	public static final String formatDate(Date date) {
		return Objects.isNull(date) ? null : DEFAULT_DATE_FORMATTER_BY_DATE.format(date);
	}

	public static final String formatDate(Date date, SimpleDateFormat formatter) {
		return Objects.isNull(date) ? null : formatter.format(date);
	}

	public static final Date convert2Date(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		} else {
			long timestamp = Timestamp.valueOf(localDateTime).getTime();
			return new Date(timestamp);
		}
	}

	public static final LocalDateTime parseByDate(Date date) {
		if (date == null) {
			return null;
		} else {
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp.toLocalDateTime();
		}
	}

	public static final String getDayOfWeekInChinese(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		switch (dayOfWeek) {
			case MONDAY:
				return "星期一";
			case TUESDAY:
				return "星期二";
			case WEDNESDAY:
				return "星期三";
			case THURSDAY:
				return "星期四";
			case FRIDAY:
				return "星期五";
			case SATURDAY:
				return "星期六";
			case SUNDAY:
				return "星期日";
			default:
				return "";
		}
	}

	public static final LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
		return dateTimeStr != null && !dateTimeStr.isEmpty() ? LocalDateTime.parse(dateTimeStr, Objects.isNull(formatter) ? DEFAULT_DATETIME_FORMATTER : formatter) : null;
	}

	public static final LocalDate parseDate(String dateTimeStr, DateTimeFormatter formatter) {
		return dateTimeStr != null && !dateTimeStr.isEmpty() ? LocalDate.parse(dateTimeStr, Objects.isNull(formatter) ? DEFAULT_DATE_FORMATTER : formatter) : null;
	}

	public static LocalDate parseDate(String date) {
		try {
			return LocalDate.parse(date, DEFAULT_DATE_FORMATTER);
		} catch (Exception var6) {
			try {
				return LocalDate.parse(date, SIMPLE_8_FORMATTER);
			} catch (Exception var5) {
				try {
					return LocalDate.parse(date, DEFAULT_DATETIME_FORMATTER);
				} catch (Exception var4) {
					return null;
				}
			}
		}
	}

	public static final long convertTimestamp(Timestamp timestamp) {
		return timestamp == null ? -1L : timestamp.getTime();
	}

	public static final LocalTime parseTime(String timeStr, DateTimeFormatter formatter) {
		return timeStr != null && !timeStr.isEmpty() ? LocalTime.parse(timeStr, Objects.isNull(formatter) ? DEFAULT_TIME_FORMATTER : formatter) : null;
	}

	public static final LocalTime parseTimeMin(String timeStr, DateTimeFormatter formatter) {
		return timeStr != null && !timeStr.isEmpty() ? LocalTime.parse(timeStr, Objects.isNull(formatter) ? DEFAULT_MIN_FORMATTER : formatter) : null;
	}

	public static final String formatTimestamp(Timestamp timestamp, DateTimeFormatter formatter) {
		return timestamp == null ? "" : timestamp.toLocalDateTime().format(formatter);
	}

	public static final String formatDate(String date, DateTimeFormatter fromPatter, DateTimeFormatter toPatter) {
		if (!Strings.isNullOrEmpty(date) && !"0".equals(date)) {
			try {
				return formatDate(parseDate(date, fromPatter), toPatter);
			} catch (Exception var4) {
				LOGGER.log(Level.WARNING, "convert date failed!", var4);
				return date;
			}
		} else {
			return date;
		}
	}

	public static final String formatDateTime(String date, String time, DateTimeFormatter fromPatter, String def) {
		if (!Strings.isNullOrEmpty(date) && !"0".equals(date)) {
			try {
				String _part = Strings.nullToEmpty(time);
				String _date = date.concat("000000".concat(_part).substring(_part.length()));
				return formatDateTime(parseDateTime(_date, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), fromPatter);
			} catch (Exception var6) {
				LOGGER.log(Level.WARNING, "convert date failed!", var6);
				return def;
			}
		} else {
			return def;
		}
	}
}