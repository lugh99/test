package com.elevator.service.imp;

import com.elevator.entity.Elevator;
import com.elevator.service.ElevatorService;

public class ElevatorServiceImp implements ElevatorService {

	@Override
	public Elevator createElevator(int floorCnt) {
		return new Elevator(floorCnt);
	}

	@Override
	public Elevator ManageDownElevator(int i, Elevator[] elevator, boolean[] DownState, int floorCnt, int elevatorCnt) {
		int Elevator1 = 0, Elevator2 = 0, Elevator = -1;
		int Distance1 = floorCnt;
		int Distance2 = floorCnt;
		int Distance = floorCnt;
		int Temp;
		for (int j = 0; j < elevatorCnt; j++) {
			if ((elevator[j].get_NextDirection() == -1) && (elevator[j].getDirection() == 1)) {
				if (i < elevator[j].getToFloor()) {
					Temp = Math.abs(i - elevator[j].getToFloor());
					if (Temp < Distance) {
						Elevator = j;
						Distance = Temp;
					}
				}
			}
		}
		if (Distance != floorCnt) {
			elevator[Elevator].Set_NextDirectionDown();
			elevator[Elevator].setToFloor(i);
			DownState[i] = false;
		} else {
			for (int j = 0; j < elevatorCnt; j++) {
				if ((elevator[j].getDirection() == -1) && (i < elevator[j].getCurPosition())
						&& (elevator[j].get_NextDirection() == -1)) {
					Temp = Math.abs(i - elevator[j].getCurPosition());
					if (Temp < Distance1) {
						Elevator1 = j;
						Distance1 = Temp;
					}
				}
			}
			for (int j = 0; j < elevatorCnt; j++) {
				if (elevator[j].getDirection() == 0) {
					Temp = Math.abs(i - elevator[j].getCurPosition());
					if (Temp < Distance2) {
						Elevator2 = j;
						Distance2 = Temp;
					}
				}
			}
			if ((Distance1 != floorCnt) || (Distance2 != floorCnt)) {
				if (Distance1 <= Distance2) {
					elevator[Elevator1].Set_NextDirectionDown();
					elevator[Elevator1].setToFloor(i);
					DownState[i] = false;
				} else if (Distance2 < Distance1) {
					elevator[Elevator2].Set_NextDirectionDown();
					elevator[Elevator2].setToFloor(i);
					DownState[i] = false;
				}
			}
		}
		if(Elevator == -1) return elevator[0];
		return elevator[Elevator];
	}

	@Override
	public Elevator ManageUpElevator(int i, Elevator[] elevator, boolean[] UpState, int floorCnt, int elevatorCnt) {
		int Elevator1 = 0, Elevator2 = 0, Elevator = 0;
		int Distance1 = floorCnt;
		int Distance2 = floorCnt;
		int Distance = floorCnt;
		int Temp;
		for (int j = 0; j < elevatorCnt; j++) {
			if ((elevator[j].get_NextDirection() == 1) && (elevator[j].getDirection() == -1)) {
				if (i > elevator[j].getToFloor()) {
					Temp = Math.abs(i - elevator[j].getToFloor());
					if (Temp < Distance) {
						Elevator = j;
						Distance = Temp;
					}
				}
			}
		}
		if (Distance != floorCnt) {
			elevator[Elevator].Set_NextDirectionUp();
			elevator[Elevator].setToFloor(i);
			UpState[i] = false;
		} else {
			for (int j = 0; j < elevatorCnt; j++) {
				if ((elevator[j].getDirection() == 1) && (i > elevator[j].getCurPosition())
						&& (elevator[j].get_NextDirection() == 1)) {
					Temp = Math.abs(i - elevator[j].getCurPosition());
					if (Temp < Distance1) {
						Elevator1 = j;
						Distance1 = Temp;
					}
				}
			}
			for (int j = 0; j < elevatorCnt; j++) {
				if (elevator[j].getDirection() == 0) {
					Temp = Math.abs(i - elevator[j].getCurPosition());
					if (Temp < Distance2) {
						Elevator2 = j;
						Distance2 = Temp;
					}
				}
			}
			if ((Distance1 != floorCnt) || (Distance2 != floorCnt)) {
				if (Distance1 <= Distance2) {
					elevator[Elevator1].Set_NextDirectionUp();
					elevator[Elevator1].setToFloor(i);
					UpState[i] = false;
				} else if (Distance2 < Distance1) {
					elevator[Elevator2].Set_NextDirectionUp();
					elevator[Elevator2].setToFloor(i);
					UpState[i] = false;
				}
			}
		}
		if(Elevator == -1) return elevator[0];
		return elevator[Elevator];
	}

}
