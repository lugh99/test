package com.elevator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.elevator.controller.ElevatorController;

/**
 * 主运行程序，启动elevator
 * @author lugh
 * @date 2020年5月19日
 */
public class ElevatorApplication {
	public static void main(String[] args) {
		ElevatorController elevatorController = new ElevatorController(20, 6);//create 20 floors and 6 elevators
		elevatorController.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		elevatorController.setSize(944, 573);
		elevatorController.setVisible(true);
		
		//press the button
		elevatorController.runElevator();
		//open the door
		elevatorController.OpenDoor();
		//close the door
		elevatorController.closeDoor();
	}

}
