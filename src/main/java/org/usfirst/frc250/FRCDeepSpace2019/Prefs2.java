package org.usfirst.frc250.FRCDeepSpace2019;

import edu.wpi.first.wpilibj.Preferences;
import java.util.Hashtable;
import java.util.Set;

public class Prefs2 {

	// Doubles Hastable
	private Hashtable<String, Double> doubles = new Hashtable<String, Double>(100);
	private Hashtable<String, Integer> ints = new Hashtable<String, Integer>(100);
	private static Prefs2 _prefs; // Instance of Self
	private Preferences prefs; // WPILIB Preferences Object

	private void initDefaults() {
		// Intake
		doubles.put("Cargo Intake Speed", .8); // Unknown
		doubles.put("Cargo Flywheel Speed", .8); // Unknown

		// Talon
		ints.put("CAN Sensor Timeout MS", 10);

		//Hatch
		doubles.put("Hatch Belt Speed", 0.6);

		// Drive
		doubles.put("Deadband Width", 0.05);
		
		// drive ratios: 50/12  50/24  wheel: 19" to 1 revolution
		doubles.put("Drive Encoder To Wheel Ratio", (12.0/50) * (24/50));
		doubles.put("Encoder Ticks Per Inch Driven", 14.6);
		doubles.put("Drive Inches Per Degree", .2);
		ints.put("Drive MM Target Threshold", 30);
		ints.put("Drive Max Acceleration", 2000);
		ints.put("Drive Max Velocity", 5000);
		
		//Autos

		//NAVX
		doubles.put("Collision Threshold", .5);
		
		//Climb
		doubles.put("X Off Balance Threshold", 3.0);
		doubles.put("Y Off Balance Threshold", 8.0);
	}

	public static synchronized Prefs2 getInstance() {
		if (_prefs == null) {
			_prefs = new Prefs2();
		}
		return _prefs;
	}

	public Prefs2() {
		prefs = Preferences.getInstance();
		initDefaults();
	}

	public double getDouble(String preferenceKey) {
		if (!doubles.containsKey(preferenceKey) && !prefs.containsKey(preferenceKey)) {
			// No Preference value set on SmartDashboard and no Default value
			// set in this class, return 0
			System.out.println("Prefs - Key '" + preferenceKey + "' does not exist, set in SmartDashboard or "
					+ getClass().getName());
			System.out.println("doubles:" + doubles.containsKey(preferenceKey) + " prefs:"
					+ prefs.containsKey(preferenceKey));
			System.out.println("doubles keys:" + doubles);
			System.out.println("prefs keys:" + prefs.getKeys());

			return 0;
		}
		return prefs.getDouble(preferenceKey, doubles.get(preferenceKey));
	}

	public void printPrefs() {
		System.out.println("Preferences");
		// Loop thru each default double key and output value that will be
		// returned
		Set<String> doubleKeys = doubles.keySet();
		for (String key : doubleKeys) {
			if (!prefs.containsKey(key)) {
				System.out.println(" - Double '" + key + "' = " + getDouble(key) + "(Pref Set: "
						+ prefs.containsKey(key) + ")");
			}
		}

		// Loop thru each default int key and output value that will be returned
		Set<String> intKeys = ints.keySet();
		for (String key : intKeys) {
			if (!prefs.containsKey(key)) {
				System.out.println(" - Int '" + key + "' = " + getInt(key) + "(Pref Set: "
						+ prefs.containsKey(key) + ")");
			}
		}
		// Loop thru each default int key and output value that will be returned
		for (String key : intKeys) {
			if (prefs.containsKey(key)) {
				System.out.println(" - Int '" + key + "' = " + getInt(key) + "(Pref Set: "
						+ prefs.containsKey(key) + ")");
			}
		}

		// Loop thru each default double key and output value that will be
		// returned
		for (String key : doubleKeys) {
			if (prefs.containsKey(key)) {
				System.out.println(" - Double '" + key + "' = " + getDouble(key) + "(Pref Set: "
						+ prefs.containsKey(key) + ")");
			}
		}
	}

	public int getInt(String preferenceKey) {
		if (!ints.containsKey(preferenceKey) && !prefs.containsKey(preferenceKey)) {
			// If preference value not set on SmartDashboard and default value
			// not set in this class, return 0
			System.out.println("Prefs - Key '" + preferenceKey + "' does not exist, set in SmartDashboard or "
					+ getClass().getName());
			return 0;
		}
		return prefs.getInt(preferenceKey, ints.get(preferenceKey));
	}
}