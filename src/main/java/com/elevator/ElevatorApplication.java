package com.elevator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.elevator.manager.ManagerUI;

/**
 * 主运行程序，启动elevator
 * @author lugh
 * @date 2020年5月19日
 */
public class ElevatorApplication {
	public static void main(String[] args) {
		ManagerUI managerUI = new ManagerUI(20, 6);//create 20 floors and 6 elevators
		managerUI.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		managerUI.setSize(944, 573);
		managerUI.setVisible(true);
	}

}
