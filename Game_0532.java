package demo;

import java.util.*;
import common.Game;
import common.Machine;

public class Game_0532 extends Game {

	private int faulty;
	private ArrayList<Machine> list = new ArrayList<Machine>();

	public void addMachines(ArrayList<Machine> machines, int numFaulty) {

		list = machines;
		faulty = numFaulty;
	}

	public void startPhase() {
		ArrayList<Integer> x = new ArrayList<Integer>();
		Random random = new Random();
		int i;
		int k = 0;

		while(k <= x.size()) {
			if(x.size() == faulty){
				break;
			}
			i = random.nextInt(list.size());
			if(x.contains(i) == false) {
				x.add(i);
				k = k + 1;
			}
		}

		for( i=0;i<list.size();i++){
            if(x.contains(i)==true)
			{
				//System.out.println(i + " false ");
                list.get(i).setState(false);
			}
            else
			{
				//System.out.println(i + "true");
                list.get(i).setState(true);
			}
            list.get(i).setMachines(list);
        }

		i = random.nextInt(list.size());
		//System.out.println("Leader is: " + i);
		list.get(i).setLeader();
	}

}
