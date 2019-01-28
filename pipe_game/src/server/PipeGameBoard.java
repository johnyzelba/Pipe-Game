package server;

import java.util.Arrays;

public class PipeGameBoard {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cols;
		result = prime * result + Arrays.deepHashCode(matrix);
		result = prime * result + rows;
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
		PipeGameBoard other = (PipeGameBoard) obj;
		if (cols != other.cols)
			return false;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		if (rows != other.rows)
			return false;
		return true;
	}
	private final int cols,rows;
	private char[][] matrix;
	
	public PipeGameBoard(int cols, int rows, String string)
	{
		this.cols=cols;
		this.rows=rows;
		this.matrix=new char[rows][cols];
		for (int i=0; i<rows; i++)
		{
			for (int j=0; j<cols; j++)
				this.matrix[i][j]=string.charAt(cols*i +j);		
		}
		
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public char getMatrixindex(int i, int j) {
		return matrix[i][j];
	}
	public Character[][] getMatrix() {
		Character[][] temp = new Character[matrix.length][matrix[0].length];
		for (int i=0; i<rows; i++)
		{
			for (int j=0; j<cols; j++)
				temp[i][j]=this.matrix[i][j];		
		}	
		return temp;
	}


}
