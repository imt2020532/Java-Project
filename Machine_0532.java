package demo;

import java.util.*;
import common.Location;
import common.Machine;

public class Machine_0532 extends Machine {

	private int distance;
	private int phase;
	private Location pos;
	private static int nextId = 0;
	private int id,faulty;
	private boolean state1;
	private boolean Round1;
	private boolean Round2;
	private boolean state2 = false;
	private String direction;
	private int turn;
	private int l,r,ll,rr;

	private ArrayList<Machine> m_list = new ArrayList<Machine>();
	

	public Machine_0532() {

		pos = new Location(0,0);
		turn = 0;
		direction = "up";
		phase = 0;
		l = 0;
		r = 0;
		ll = 0;
		rr = 0;
		Round1 = false;
		Round2 = false;
	}

	public void setStepSize(int stepSize) {
		distance = stepSize;
	}

	public void setState(boolean isCorrect) {
		state1 = isCorrect;
		Round1 = false;
		Round2 = false;
		l = 0;
		r = 0;
		ll = 0;
		rr = 0;

	}

	public void setLeader() {
		Random random = new Random();
		if(state1 == false) {
			int k;
			if(faulty == 0)
			k = 0;
		else
			k = random.nextInt(faulty);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		int x;
		int y = 0;
		do{
			if(list.size() == k){
				break;
			}
			x = random.nextInt(m_list.size());
			if(list.contains(x) == false){
				list.add(x);
				y = 1;
			}
		}while(y == 0);
		x = random.nextInt(2);
		for(Machine obj : m_list){
			if(list.contains(obj.getId()) == false){
				obj.sendMessage(id, phase, 0, x);
			}
		}
		}
		else{
			int x;
			x = random.nextInt(2);
			for(Machine obj : m_list){	
				obj.sendMessage(id, phase, 0, x);
			}
		}

	}

	public void sendMessage(int sourceId, int phaseNum, int roundNum, int decision) {
		Random random = new Random();
		if(roundNum == 0 ){
			if(state1 == false){
				decision = random.nextInt(3) ;
			}
			for(Machine obj : m_list){
				if(decision != 2)
					obj.sendMessage(id, phaseNum, 1, decision);
			}
		}
		else if(roundNum == 1){
			if(decision == 0){
				l = l + 1;
			}
			else if(decision == 1){
				r = r + 1;
			}
			if((l + r) >= 2*faulty + 1){
				if(state1 == true){
					if(l > r)
					turn = 0;
					else
					turn = 1;
				}
			else if(state1 == false){
				turn = random.nextInt(3);
			}
			if(Round1 == false && turn != 2){
				for(Machine obj : m_list){
					obj.sendMessage((int) obj.getId(), phaseNum, 2, turn);
				}
			}
			
			}
		}

		else if (roundNum == 2){
			if(decision == 0){
				ll = ll + 1;
			}
			else if (decision == 1){
				rr = rr + 1;
			}
			if((ll >= 2*faulty + 1 || rr >= 2*faulty + 1) && Round2==false){

				if(ll > rr) {
					turn = 0;
				}
				else
				turn = 1;
				if(state1 == false)
					turn = random.nextInt(2);
				Round2 = true;
			}

		}

	}

	public
	void move() {

		if(Round2 == true) {

		if(direction == "up" ){
			if(turn == 0){
				direction = "left";
			}
			else if(turn == 1){
				direction = "right";
			}
		}
		////////////////
		else if(direction == "down" ){
			if(turn == 0){
				direction = "right";
			}
			else if(turn == 1){
				direction = "left";
			}
		}
		////////////////
		else if(direction == "left" ){
			if(turn == 0){
				direction = "down";
			}
			else if(turn == 1){
				direction = "up";
			}
		}
		////////////////
		else if(direction == "right" ){
			if(turn == 0){
				direction = "up";
			}
			else if(turn == 1){
				direction = "down";
			}
		}
		phase = phase + 1;
		Round2 = false;
	}
		////////////////
		if(direction == "up" ){
			pos.setLoc(pos.getX(), pos.getY() + distance);
		}
		if(direction == "down" ){
			pos.setLoc(pos.getX(), pos.getY() - distance);
		}
		if(direction == "left" ){
			pos.setLoc(pos.getX() - distance, pos.getY());
		}
		if(direction == "right" ){
			pos.setLoc(pos.getX() + distance, pos.getY());
		}

			
	}

	//////////////////////
	public
	String name() {
		return "0532_"+id;
	}
	//////////////////////


	/////////////////////
	public int getid() {
		return id;
	}
	/////////////////////
	
	public boolean get_state1(){
		return state1;
	}

	public Location getPosition() {
		
		return pos;
	}

/////////////////////////////////////////////////////
	public void setMachines(ArrayList<Machine> list){
		
		m_list = list;
		if(list.size() % 3 == 0){
			faulty = (list.size() / 3) - 1;
		}
		else{
			faulty = list.size() / 3;
		}
		for(int i=0;i<list.size();i++){
            if(list.get(i) == this)
                id = i;
        }
		
	}
//////////////////////////////////////////////////////
	
	
	/* privatepublic int func(){
		int k =  0;
		for(int j = 0 ; j < fdec.size() ; j++){
			k = k + fdec.get(j);
		}
		return k;
	}*/

	
	
}
