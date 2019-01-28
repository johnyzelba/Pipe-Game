package Search;


import com.sun.javafx.geom.Point2D;
import java.util.Queue;



public interface searchable<T> {
	
	 public state<T> getfirststate();
	 public solution isGoal(state<T> corrent);
	 public Point2D getstart();
	 public Queue<state<T>> getposiblestates(state<T> corrent);
	 public state<T> createnewstate(Point2D mypoint, int rotated, state<T> myfather);

}
