package com.ieli.claims.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppUtils {

	public static String getToday(String pattern) {

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateStr = dateFormat.format(Calendar.getInstance().getTime());

		return dateStr;

	}

	public static String getDate(String date, String pattern) {

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateStr = dateFormat.format(date);

		return dateStr;

	}
}
