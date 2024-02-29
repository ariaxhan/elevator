# README for Elevator #

## Table of Contents ##
+ Building
+ Elevator
+ ElevatorMain
+ Floor
+ Passenger
+ Simulation
+ Time
+ variables

# Building
	Class for the building that the simulation runs in. The building is generated with floors and elevators. The floors and elevators are stored in either linkedlists or arraylists based on the properties file. 

# Elevator
	Class for elevators. Each one is generated with a unique id. Passengers are stored in a linkedlist or an arraylist based on what the properties file specified. 

# ElevatorMain
	Main class to run the simulation. Reads in variables from the properties file, creates the building object, and runs the simulation. 

# Floor
	Class for floors. Each floor has two queues, one for passengers going up and one for passengers going down. As passengers are generated, they are put into the appropriate queue at their start floor. 

# Passenger
	Class to generate passengers with a randoms start and end floor. The probability in the properties file is used to determine if a passenger is generated each time the constructor is called. Each passenger is given a unique id number when it is generated. 

# Simulation
	Class to run the simulation on a building. Include passenger generation as well as elevator travel, load, and unload. 

# Time
	Class to record passenger start/end ticks and print out the average, longest, and shortest journey times.

# variables
	Class to store all of the variables read in from the properties file. Ensures that the variables can be accessed by any of the classes.

