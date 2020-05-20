package com.elevator.test;

import org.junit.Test;

import com.elevator.entity.Elevator;
import com.elevator.service.DoorService;
import com.elevator.service.ElevatorService;
import com.elevator.service.imp.DoorServiceImp;
import com.elevator.service.imp.ElevatorServiceImp;

public class ElevatorTest {
	
	private ElevatorService elevatorService = new ElevatorServiceImp();
	private DoorService doorService = new DoorServiceImp();
	
	@Test
	public void TestCreateElevator(){
		elevatorService.createElevator(20);
	}
	
	@Test
	public void TestManageDownElevator(){
		elevatorService.ManageDownElevator(4, new Elevator[6], new boolean[20], 20, 6);
	}
	
	@Test
	public void TestManageUpElevator(){
		elevatorService.ManageDownElevator(6, new Elevator[6], new boolean[20], 20, 6);
	}
	
	@Test
	public void TestOpenDoor(){
		doorService.openDoor(elevatorService.createElevator(20) , 3);
	}
	
	@Test
	public void TestCloseDoor(){
		doorService.closeDoor(elevatorService.createElevator(20) , 8);
	}
}
