package org.usfirst.frc250.FRCDeepSpace2019;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class LogUtil {

	// RFC2822
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z",
			Locale.US);

	public static String getTimestamp() {
		return DATE_FORMAT.format(new Date());
	}

	public static String genSessionName() {
		// Avoids having to check if a file exists
		// Unlikely to cause a collision
		int seed = (new Random()).nextInt();
		String name = Integer.toHexString(seed).toUpperCase();
		return name;
	}

	public static String fromBool(boolean in) {
		// Saves on disk usage by not including decimals
		return in ? "1" : "0";
	}
}