package Search;

import java.util.Arrays;

import com.sun.javafx.geom.Point2D;


public class state<T> {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(mymatrix);
		result = prime * result + ((mypoint == null) ? 0 : mypoint.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		state other = (state) obj;
		if (!Arrays.deepEquals(mymatrix, other.mymatrix))
			return false;
		if (mypoint == null) {
			if (other.mypoint != null)
				return false;
		} else if (!mypoint.equals(other.mypoint))
			return false;
		return true;
	}
	private T[][] mymatrix;
	private Point2D mypoint;
	public int DfromG;
	private int rotated;
	private state<T> myfather;
	
	@SuppressWarnings("unchecked")
	public state(T[][] matrix,int DfromG) 
	{
		this.myfather = null;
		this.rotated = 0;
		mymatrix = (T[][])new Character[matrix.length][matrix[0].length];	
		for (int i=0; i<matrix.length; i++)
			for (int j=0; j<matrix[0].length; j++)
				mymatrix[i][j] = matrix[i][j];	
		this.DfromG = DfromG;
		
	}
	@SuppressWarnings("unchecked")
	public state(Point2D mypoint, int rotated, state<T> myfather,int DfromG) {
		super();
		this.DfromG = DfromG;
		this.mypoint = new Point2D(mypoint.x,mypoint.y);
		this.rotated = rotated;
		this.myfather = myfather;
		mymatrix = (T[][])new Character[myfather.getlength()][myfather.getwidth()];	
		for (int i=0; i<myfather.getlength(); i++)
			for (int j=0; j<myfather.getwidth(); j++)
				mymatrix[i][j] = myfather.getnode(new Point2D(i,j));
	}
	public T getnode(Point2D point)	
	{
		return mymatrix[(int)point.x][(int)point.y];
	}
	public void setnode(Point2D point,T newt) 
	{
		mymatrix[(int)point.x][(int)point.y]=newt;
	}
	public T[][] getmatrix() 
	{
		return mymatrix;
	}
	public int getlength() 
	{
		return mymatrix.length;
	}
	public int getwidth() 
	{
		return mymatrix[0].length;
	}
	public void print() 
	{
		String temp = "";
		for (int i=0; i<mymatrix.length; i++)
		{
			for (int j=0; j<mymatrix[0].length; j++)
				temp+=(char)mymatrix[i][j];
			System.out.println(temp);
			temp = "";
		}
	}
	public state<T> getMyfather() {
		return myfather;
	}
	public int getRotated() {
		return rotated;
	}
	public Point2D getMypoint() {
		return mypoint;
	}
	public void setMypoint(Point2D point) {
		mypoint = new Point2D(point.x,point.y);
	}

}
