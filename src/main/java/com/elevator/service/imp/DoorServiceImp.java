package com.elevator.service.imp;

import com.elevator.entity.Elevator;
import com.elevator.service.DoorService;

public class DoorServiceImp implements DoorService {

	@Override
	public void openDoor(Elevator elevator, int elFloor) {
		System.out.println("open the "+elevator.getName()+"and "+elFloor+" floor  door!");
	}

	@Override
	public void closeDoor(Elevator elevator, int elFloor) {
		System.out.println("close the "+elevator.getName()+"and "+elFloor+" floor  door!");
	}

}
