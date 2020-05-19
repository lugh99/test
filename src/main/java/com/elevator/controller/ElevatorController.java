package com.elevator.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.elevator.entity.Elevator;
import com.elevator.service.ElevatorService;
import com.elevator.service.imp.ElevatorServiceImp;

/**
 * manager the elevator UI
 * @author lugh
 * @date 2020-05-19
 */
public class ElevatorController extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private int floorCnt;
	private int elevatorCnt;
	private JPanel jContainPanel = null;
	private JPanel jPanel = null;
	private JButton[] UpButton;
	private JButton[] DownButton;
	private Elevator[] elevator;
	private boolean[] UpState;
	private boolean[] DownState;
	private Thread thread;
	private JLabel jlabel, jlabel1;
	
	private ElevatorService elevatorService =  new ElevatorServiceImp();

	/**
	 * This is the default constructor
	 */
	public ElevatorController(int floorCnt, int elevatorCnt) {
		this.floorCnt = floorCnt;
		this.elevatorCnt = elevatorCnt;
		initialize();
		thread.start();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		UpButton = new JButton[floorCnt];
		UpState = new boolean[floorCnt];
		DownButton = new JButton[floorCnt];
		DownState = new boolean[floorCnt];
		elevator = new Elevator[elevatorCnt];
		
		this.setSize(944, 573);
		this.setContentPane(getJContainPanel());
		this.setTitle("The elevator controller");
		thread = new Thread(this);
		for (int i = 0; i < floorCnt; i++) {
			UpState[i] = false;
			DownState[i] = false;
		}
	}

	/**
	 * Initializes the elevator jContainPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContainPanel() {
		if (jContainPanel == null) {
			jContainPanel = new JPanel();
			jContainPanel.setLayout(new GridLayout(1, 6));
			jContainPanel.add(getJPanel(), null);
			for (int i = 0; i < elevatorCnt; i++) {
				Elevator Ele = new Elevator(floorCnt);
				Ele.setBorder(BorderFactory.createTitledBorder(null, "Elevator" + (i + 1),
						TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
				Ele.getthread().start();
				jContainPanel.add(Ele);
				elevator[i] = Ele;
			}
		}
		return jContainPanel;
	}

	/**
	 * Initializes the whole contol Panel
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.setLayout(new GridLayout(22, 2));
			jlabel = new JLabel("UP");
			jlabel1 = new JLabel("DOWN");
			jlabel.setHorizontalAlignment(SwingConstants.CENTER);
			jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jPanel.add(jlabel);
			jPanel.add(jlabel1);
			jPanel.setBounds(new Rectangle(1, 0, 166, 533));
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Waiting-room", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, null, null));
			for (int i = floorCnt-1; i >= 0; i--) {
				UpButton[i] = new JButton();
				UpButton[i].setText("↑");
				UpButton[i].setBackground(Color.DARK_GRAY);
				UpButton[i].setForeground(Color.WHITE);
				UpButton[i].addActionListener(new Action());
				
				DownButton[i] = new JButton();
				DownButton[i].setText("↓");
				DownButton[i].setBackground(Color.DARK_GRAY);
				DownButton[i].setForeground(Color.WHITE);
				DownButton[i].addActionListener(new Action());
				
				jPanel.add(UpButton[i]);
				jPanel.add(DownButton[i]);
			}
			UpButton[19].setEnabled(false);
			DownButton[0].setEnabled(false);
			UpButton[19].setText("");
			DownButton[0].setText("");
		}
		return jPanel;
	}

	/**
	 * Add The waiting-room's control listener
	 * @author lugh
	 * @date 2020-05-19
	 */
	class Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < floorCnt; i++) {
				if (e.getSource() == UpButton[i]) {
					UpState[i] = true;
				} else if (e.getSource() == DownButton[i]) {
					DownState[i] = true;
				}
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < floorCnt; i++) {
				if (UpState[i]) {
					elevatorService.ManageUpElevator(i, elevator, UpState, floorCnt, elevatorCnt);
				}
				
				if (DownState[i]) {
					elevatorService.ManageDownElevator(i, elevator, DownState, floorCnt, elevatorCnt);
				}
			}
		}
	}

	public int getFloorCnt() {
		return floorCnt;
	}

	public void setFloorCnt(int floorCnt) {
		this.floorCnt = floorCnt;
	}

	public int getElevatorCnt() {
		return elevatorCnt;
	}

	public void setElevatorCnt(int elevatorCnt) {
		this.elevatorCnt = elevatorCnt;
	}
}