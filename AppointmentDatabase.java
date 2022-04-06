package edu.cuny.csi.csc330.groupproject;

import java.util.ArrayList;
import java.util.Map;

public class AppointmentDatabase {
	private Map<String, ArrayList<Appointment>> platformEvents;
}

/*
host scheduler() and descheduler() will access this map

Map:

{

	"host_id" : [Appointment],
	"host_id" : [Appointment],
	...

}

*/