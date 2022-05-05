package edu.cuny.csi.csc330.groupproject;

public enum Days {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	public String getShorthand() {
		switch (this){
			case MONDAY:
				return "MON";
			case TUESDAY:
				return "TUE";
			case WEDNESDAY:
				return "WED";
			case THURSDAY:
				return "THU";
			case FRIDAY:
				return "FRI";
			case SATURDAY:
				return "SAT";
			case SUNDAY:
				return "SUN";
			default:
				return "---";
		}
	}
	
	public String getFirst() {
		switch (this){
			case MONDAY:
				return "M";
			case TUESDAY:
				return "T";
			case WEDNESDAY:
				return "W";
			case THURSDAY:
				return "T";
			case FRIDAY:
				return "F";
			case SATURDAY:
				return "S";
			case SUNDAY:
				return "S";
			default:
				return "-";
		}
	}
}
