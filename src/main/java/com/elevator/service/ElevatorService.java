package com.elevator.service;

import com.elevator.entity.Elevator;

/**
 * the elevator service
 * @author lugh
 * @date 2020-05-19
 */
public interface ElevatorService {
	
	/**
	 * Create a new elevator
	 * @param elevator
	 * @return JPanel
	 */
	public Elevator createElevator(int floorCnt);
	
	/**
	 * manage the down elevator
	 * @param i
	 * @param elevator
	 * @param DownState
	 * @return
	 */
	public boolean ManageDownElevator(int i, Elevator[] elevator, boolean[] DownState, int floorCnt, int elevatorCnt);
	
	/**
	 * manage the up elevator
	 * @param i
	 * @param elevator
	 * @param UpState
	 * @return
	 */
	public boolean ManageUpElevator(int i, Elevator[] elevator, boolean[] UpState, int floorCnt, int elevatorCnt);
}
