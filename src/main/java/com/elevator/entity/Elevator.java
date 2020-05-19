package com.elevator.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Elevator extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private int floorCnt;
	private JButton[] button;
	private JButton[] Button;
	private int CurPosition = 0;
	private int ToFloor = 0;
	private int Up = 1, Down = -1, Still = 0;
	private int Direction;
	private Thread thread;
	private boolean[] state;
	private JLabel jlabel = new JLabel();
	private JLabel jlabel1 = new JLabel();
	private Color color = Color.GRAY;
	private int Next_Direction;

	/**
	 * This is the default constructor
	 */
	public Elevator(int floorCnt) {
		this.floorCnt = floorCnt;
		initialize();
	}

	public Thread getthread() {
		return thread;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(152, 506);
		this.setLayout(new GridLayout(22, 2));
		this.setBackground(Color.LIGHT_GRAY);
		Direction = Still;
		Next_Direction = Still;
		button = new JButton[floorCnt];
		Button = new JButton[floorCnt];
		state = new boolean[floorCnt];
		thread = new Thread(this);
		jlabel.setText("STILL");
		jlabel.setFont(new Font("Consolas", Font.BOLD, 20));
		jlabel.setForeground(Color.blue);
		jlabel.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel1.setText("1");
		jlabel1.setFont(new Font("Consolas", Font.BOLD, 20));
		jlabel1.setForeground(Color.blue);
		jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(jlabel);
		this.add(jlabel1);
		for (int i = floorCnt-1; i >= 0; i--) {
			state[i] = false;
			button[i] = new JButton();
			Button[i] = new JButton();
			button[i].setText(String.valueOf(i + 1));
			Button[i].setText(String.valueOf(i + 1));
			Button[i].setBackground(Color.lightGray);
			Button[i].addActionListener(new Action());
			button[i].setBorder(javax.swing.BorderFactory.createLineBorder(Color.blue, 4));
			button[i].setEnabled(false);
			button[i].setBackground(Color.black);
			this.add(button[i]);
			this.add(Button[i]);
		}
		button[0].setBackground(Color.red);
	}

	class Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < floorCnt; i++) {
				if (e.getSource() == Button[i]) {
					state[i] = true;
					Button[i].setBackground(color);
					if (Direction == Still) {
						ToFloor = i;
					} else if (Direction == Up) {
						ToFloor = MaxToFloor();
					} else if (Direction == Down) {
						ToFloor = MinToFloor();
					}
				}
			}
		}
	}

	private int MaxToFloor() {
		int Max = -1;
		for (int i = floorCnt-1; i >= 0; i--) {
			if (state[i]) {
				Max = i;
				break;
			}
		}
		return Max;
	}

	private int MinToFloor() {
		int Min = -1;
		for (int i = 0; i < floorCnt; i++) {
			if (state[i]) {
				Min = i;
				break;
			}
		}
		return Min;
	}

	public void run() {
		while (true) {
			try {
				thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ToFloor > CurPosition) {
				Direction = Up;
				MoveUp();
				Direction = Still;
				jlabel.setText("STILL");
			} else if (ToFloor < CurPosition) {
				Direction = Down;
				MoveDown();
				Direction = Still;
				jlabel.setText("STILL");
			}
			for (int i = 0; i < floorCnt; i++) {
				if (state[i]) {
					if (Direction == Still) {
						ToFloor = i;
					} else if (Direction == Up) {
						ToFloor = MaxToFloor();
					} else if (Direction == Down) {
						ToFloor = MinToFloor();
					}
				}
			}
		}
	}

	private void MoveUp() {
		if (Next_Direction == Up || Next_Direction == Still) {
			jlabel.setText("UP");
			int OldCurPosition = CurPosition;
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[OldCurPosition].setBackground(Color.black);
			for (int i = OldCurPosition + 1; i < ToFloor; i++) {
				button[i].setBackground(Color.red);
				jlabel1.setText(String.valueOf(i + 1));
				try {
					thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				button[i].setBackground(Color.black);
				CurPosition = i;
				if (state[i]) {
					state[i] = false;
					Button[i].setBackground(null);
					button[i].setBackground(Color.white);
					try {
						thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					button[i].setBackground(Color.red);
					try {
						thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					button[i].setBackground(Color.black);
				}
			}
			button[ToFloor].setBackground(Color.red);
			jlabel1.setText(String.valueOf(ToFloor + 1));
			Button[ToFloor].setBackground(null);
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.white);
			try {
				thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.red);
			CurPosition = ToFloor;

			state[ToFloor] = false;
		} else if (Next_Direction == Down) {
			jlabel.setText("UP");
			int OldCurPosition = CurPosition;
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[OldCurPosition].setBackground(Color.black);
			for (int i = OldCurPosition + 1; i < ToFloor; i++) {
				button[i].setBackground(Color.red);
				jlabel1.setText(String.valueOf(i + 1));
				try {
					thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				button[i].setBackground(Color.black);
				CurPosition = i;
			}
			button[ToFloor].setBackground(Color.red);
			jlabel1.setText(String.valueOf(ToFloor + 1));
			Button[ToFloor].setBackground(null);
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.white);
			try {
				thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.red);
			CurPosition = ToFloor;
			state[ToFloor] = false;
			if (MinToFloor() != -1) {
				ToFloor = MinToFloor();
			}
			Next_Direction = Still;
			MoveDown();

		}
	}

	private void MoveDown() {
		if (Next_Direction == Down || Next_Direction == Still) {
			jlabel.setText("DOWN");
			int OldCurPosition = CurPosition;
			try {
				thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[OldCurPosition].setBackground(Color.black);
			for (int i = OldCurPosition - 1; i > ToFloor; i--) {

				button[i].setBackground(Color.red);
				jlabel1.setText(String.valueOf(i + 1));
				try {
					thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				button[i].setBackground(Color.black);
				CurPosition = i;
				if (state[i]) {
					state[i] = false;
					Button[i].setBackground(null);
					button[i].setBackground(Color.white);
					try {
						thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					button[i].setBackground(Color.red);
					try {
						thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					button[i].setBackground(Color.black);
				}
			}
			button[ToFloor].setBackground(Color.red);
			jlabel1.setText(String.valueOf(ToFloor + 1));
			Button[ToFloor].setBackground(null);
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.white);
			try {
				thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.red);
			CurPosition = ToFloor;
			state[ToFloor] = false;
		} else if (Next_Direction == Up) {
			jlabel.setText("DOWN");
			int OldCurPosition = CurPosition;
			try {
				thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[OldCurPosition].setBackground(Color.black);
			for (int i = OldCurPosition - 1; i > ToFloor; i--) {

				button[i].setBackground(Color.red);
				jlabel1.setText(String.valueOf(i + 1));
				try {
					thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				button[i].setBackground(Color.black);
				CurPosition = i;
			}
			button[ToFloor].setBackground(Color.red);
			jlabel1.setText(String.valueOf(ToFloor + 1));
			Button[ToFloor].setBackground(null);
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.white);
			try {
				thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			button[ToFloor].setBackground(Color.red);
			CurPosition = ToFloor;
			state[ToFloor] = false;
			if (MaxToFloor() != -1) {
				ToFloor = MaxToFloor();
			}
			Next_Direction = Still;
			MoveUp();
		}
	}

	public void setToFloor(int i) {
		state[i] = true;
		if (Direction == Still)
			ToFloor = i;
		else if (Direction == Up)
			ToFloor = MaxToFloor();
		else if (Direction == Down) {
			ToFloor = MinToFloor();
		}
	}

	public int getCurPosition() {
		return CurPosition;
	}

	public int getDirection() {
		return Direction;
	}

	public int getToFloor() {

		return ToFloor;
	}

	public void Set_NextDirectionUp() {
		Next_Direction = Up;
	}

	public void Set_NextDirectionDown() {
		Next_Direction = Down;
	}

	public int get_NextDirection() {
		return Next_Direction;
	}

	public int getFloorCnt() {
		return floorCnt;
	}

	public void setFloorCnt(int floorCnt) {
		this.floorCnt = floorCnt;
	}
}
