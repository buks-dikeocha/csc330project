package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDateTime;
import java.util.List;

public class User {
	private String userID;
	private String userName;
	private String firstName;
	private String lastName;
	private List<Appointment> events;
	private AccountType accType;
	private LocalDateTime accCreated;
}
