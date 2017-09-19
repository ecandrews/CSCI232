
public class Coordinate {
	
	private double x;
	private double y;
	private int i;
	
	
	public Coordinate(double x, double y, int i) {
		this.x = x;
		this.setY(y);
		this.setI(i);
	}


	/**
	 * 
	 * @return double x
	 */
	public double getX() {
		return x;
	}


	/**
	 * Sets x
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}


	/**
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}
	
	public double getXOrY(int i) {
		return x == 0 ? x : y;
	}

}