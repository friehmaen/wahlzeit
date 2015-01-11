package org.wahlzeit.longtimeexposure;

import org.wahlzeit.utils.EnumValue;

/**
 * 
 * This class in an Enum and defines the type of exposure.
 * 
 * This class is part of the following collaborations:
 * - MetaData collaboration
 */
public enum ExposureType implements EnumValue {
	
	UNSPECIFIED(0), DARKNESS(1), NEUTRALFILTER(2), OVEREXPOSED(3);
	
	private static ExposureType[] allValues = {
		UNSPECIFIED, DARKNESS, NEUTRALFILTER, OVEREXPOSED
	};
	
	private static final String[] valueNames = {
		"Unspecified", "Darkness", "NeutralFilter", "OverExposed"
	};
	
	private int value;
	
	public static ExposureType fromString(String newType) throws IllegalArgumentException {
		for (ExposureType t : ExposureType.values()) {
			if (valueNames[t.asInt()].equals(newType)) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("invalid ExposureType string: " + newType);
	}
	
	public static ExposureType fromInt(int newType) throws IllegalArgumentException {
		assertIsValidIntValue(newType);
		
		return allValues[newType];
	}
	
	private ExposureType(int myValue) {
		value = myValue;
	}

	@Override
	public int asInt() {
		return value;
	}

	@Override
	public String asString() {
		return valueNames[value];
	}

	@Override
	public EnumValue[] getAllValues() {
		return allValues;
	}

	@Override
	public String getTypeName() {
		return "ExposureType";
	}
	
	private static void assertIsValidIntValue(int myValue) {
		if ((myValue < 0) || (myValue > 4)) {
			throw new IllegalArgumentException("invalid ExposureType int: " + myValue);
		}
	}

}
