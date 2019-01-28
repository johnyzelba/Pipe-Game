package Search;

import java.util.LinkedList;
import java.util.Queue;
import com.sun.javafx.geom.Point2D;

import server.PipeGameBoard;

public class pipe_searchable implements searchable<Character> {
	private static state<Character> firststate;
	
	public state<Character> getfirststate() {
		return firststate;
	}
	
	public pipe_searchable(PipeGameBoard firstboard) {
		pipe_searchable.firststate = new state<>(firstboard.getMatrix(),0);
		pipe_searchable.firststate.setMypoint(getstart());
	}

	@Override
	public solution isGoal(state<Character> corrent) 
	{
		boolean flag = false;
		Point2D father = corrent.getMypoint();
		Character correntchar = (Character)corrent.getnode(corrent.getMypoint());
		if (father.y-1 >= 0)
		{
			if(( correntchar == '7' || correntchar == '-' || correntchar == 'J'|| correntchar == 's' ) && corrent.getnode(new Point2D(corrent.getMypoint().x, corrent.getMypoint().y-1))=='g')
				flag = true;
		}
		if (father.x-1 >= 0)
		{
			if(( correntchar == '|' || correntchar == 'L' || correntchar == 'J'|| correntchar == 's' ) && corrent.getnode(new Point2D(corrent.getMypoint().x-1, corrent.getMypoint().y))=='g')
				flag = true;
		}	
		if (father.y+1 < firststate.getwidth())
		{
		
			if(( correntchar == 'L' || correntchar == '-' || correntchar == 'F'|| correntchar == 's' ) && corrent.getnode(new Point2D(corrent.getMypoint().x, corrent.getMypoint().y+1))=='g')
				flag = true;
		}
		if (father.x+1 < firststate.getlength())
		{
			if(( correntchar == '7' || correntchar == '|' || correntchar == 'F'|| correntchar == 's' ) && corrent.getnode(new Point2D(corrent.getMypoint().x+1, corrent.getMypoint().y))=='g')
				flag = true;
		}
		if(flag)
		{
			String lineS = "";
			solution solu = new solution();
			state<Character> tempstate = corrent;
			while(tempstate.getMyfather()!= null)
			{
				lineS = (int)tempstate.getMypoint().x+","+(int)tempstate.getMypoint().y+","+tempstate.getRotated()+"\n";
				tempstate = tempstate.getMyfather();
				solu.addtosolution(lineS);
			}
//			String lineS = "",allS = "";
//			state<Character> tempstate = corrent;
//			while(tempstate.getMyfather()!= null)
//			{
//				lineS = (int)tempstate.getMypoint().x+","+(int)tempstate.getMypoint().y+","+tempstate.getRotated()+"\n";
//				tempstate = tempstate.getMyfather();
//				allS += lineS;
//			}
//			System.out.println(allS);
			return solu;
		}

		return null;
	}

	public int howmachremotations(char x) 
	{
		switch(x) 
		{
			case'L':
			case'F':
			case'7':
			case'J':
				return 4;
			case'|':
			case'-':
				return 2;
			default:
				return 0;
		}
	}
	public Point2D getstart()
	{
		for (int i=0; i<firststate.getlength(); i++)
			for (int j=0; j<firststate.getwidth(); j++)
				if (firststate.getnode(new Point2D(i,j)) == 's')
					return new Point2D(i,j);
		return null;
	}
	public Point2D getend()
	{
		for (int i=0; i<firststate.getlength(); i++)
			for (int j=0; j<firststate.getwidth(); j++)
				if (firststate.getnode(new Point2D(i,j)) == 'g')
					return new Point2D(i,j);
		return null;
	}

	public state<Character> createnewstate(Point2D mypoint, int rotated, state<Character> myfather)
	{

		state<Character> temp = new state<Character>(mypoint,rotated,myfather,getdis(mypoint));
		temp.setnode(myfather.getMypoint(), ' ');
		return temp;
	}
	public void turnacube(Point2D father,state<Character> corrent)
	{
		Character correntchar = (Character) corrent.getnode(father);
			switch(correntchar) {
			case'L':
				corrent.setnode(father, 'F');
				break;
			case'F':
				corrent.setnode(father, '7');
				break;
			case'7':
				corrent.setnode(father, 'J');
				break;
			case'J':
				corrent.setnode(father, 'L');
				break;
			case'|':
				corrent.setnode(father, '-');
				break;
			case'-':
				corrent.setnode(father, '|');
				break;
			default:
				break;
			}
	}
	

	public Queue<state<Character>> getposiblestates(state<Character> corrent)
	{
		Point2D father = corrent.getMypoint();
		Queue<state<Character>> kidsbag = new LinkedList<>();
		Character correntchar = (Character) corrent.getnode(corrent.getMypoint());
		Point2D kidpoint;
		char checked;
		if (father.y-1 >= 0)
		{
			kidpoint = new Point2D(father.x,father.y-1);
			checked = (Character) corrent.getnode(kidpoint);
			for(int i = 0;i<howmachremotations(checked);i++)
			{
				checked = (Character)corrent.getnode(kidpoint);
				if((checked == 'F' || checked == 'L' || checked == '-' || checked == 'g') && (correntchar == '-' || correntchar == 'J' || correntchar == 's' || correntchar == '7'))
					kidsbag.add(createnewstate(kidpoint,i,corrent));
				turnacube(kidpoint, corrent);

			}
		}
		if (father.x-1 >= 0)
		{
			kidpoint = new Point2D(father.x-1,father.y);
			checked = (Character)corrent.getnode(kidpoint);
			for(int i = 0;i<howmachremotations(checked);i++)
			{	
				checked = (Character)corrent.getnode(kidpoint);
				if((checked == 'F' || checked == '|' || checked == '7'|| checked == 'g') && (correntchar == '|' || correntchar == 'J' || correntchar == 's' || correntchar == 'L'))
					kidsbag.add(createnewstate(kidpoint,i,corrent));
				turnacube(kidpoint, corrent);
			}
		}	
		if (father.y+1 < firststate.getwidth())
		{
			kidpoint = new Point2D(father.x,father.y+1);
			checked = (Character)corrent.getnode(kidpoint);
			for(int i = 0;i<howmachremotations(checked);i++)
			{
				checked = (Character)corrent.getnode(kidpoint);
				if((checked == 'J' || checked == '-' || checked == '7'|| checked == 'g') && (correntchar == '-' || correntchar == 'F' || correntchar == 's' || correntchar == 'L'))
					kidsbag.add(createnewstate(kidpoint,i,corrent));
				turnacube(kidpoint, corrent);
			}
		}
		if (father.x+1 < firststate.getlength())
		{
			kidpoint = new Point2D(father.x+1,father.y);
			checked = (Character)corrent.getnode(kidpoint);
			for(int i = 0;i<howmachremotations(checked);i++)
			{
				checked = (Character)corrent.getnode(kidpoint);
				if((checked == 'J' || checked == '|' || checked == 'L'|| checked == 'g') && (correntchar == '|' || correntchar == 'F' || correntchar == 's' || correntchar == '7'))
					kidsbag.add(createnewstate(kidpoint,i,corrent));
				turnacube(kidpoint, corrent);
			}
		}
		return kidsbag;
	}
	public int getdis(Point2D mypoint)
	{
		return (int) Math.abs((getend().x - mypoint.x) + Math.abs((getend().y-mypoint.y))) ;
	}

}
