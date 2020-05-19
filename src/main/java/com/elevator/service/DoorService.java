package com.elevator.service;

import com.elevator.entity.Elevator;

/**
 * the door service
 * @author lugh
 * @date 2020-05-19
 */
public interface DoorService {
	
	/**
	 * open the door
	 * @param elevator
	 * @param elFloor
	 */
	public void openDoor(Elevator elevator, int elFloor);
	
	/**
	 * close the door
	 * @param elevator
	 * @param elFloor
	 */
	public void closeDoor(Elevator elevator, int elFloor);
}
