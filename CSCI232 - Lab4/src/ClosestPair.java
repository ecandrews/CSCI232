import java.util.ArrayList;

/**
 * ClosestPair problem in O(nlogn) time complexity
 * @author Kyle Melton -02312584
 * @author James George -02215548
 * @author Elizabeth Andrews -02224876
 */

public class ClosestPair {
	//Represents an array of (x, y) coordinates
	public static double[][] points = 
			{{2.0,7.0}, {4.0,13.0}, {5.0,8.0}, 
			{10.0,5.0}, {14.0,9.0}, {15.0,5.0}, 
			{17.0,7.0}, {19.0,10.0}, {22.0,7.0},
			{25.0,10.0}, {29.0,14.0}, {30.0,2.0}};

	public static Coordinate[] sX;
	public static Coordinate[] sY;
	public static double x1Final;
	public static double y1Final;
	public static double x2Final;
	public static double y2Final;
	/**
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Coordinate[] pointsArray = new Coordinate[points.length];
		points = MergeSort.mergeSort(points, 0);
		
		for (int i = 0; i < points.length; i++) {
			Coordinate newCoordinate = 
					new Coordinate(points[i][0], points[i][1], i);
			pointsArray[i] = newCoordinate;
			System.out.print("(" + newCoordinate.getX() + "," + newCoordinate.getY() + ")"); //print out the points
		}
		
		sX = pointsArray;
		sY = MergeSort.mergeSort(pointsArray, 1);
	
		System.out.println("\n---------------------------------------------");
		Coordinate shortest1 = null, shortest2 = null;
		
		double distance = closestPair(sX, shortest1, shortest2);
		//printResult(x1Final, y1Final, x2Final, y2Final, distance);
		System.out.print("Distance: " + distance);
	}

	/**
	 * This recursive method looks for the closest pair provided from the array of points
	 * @param points 
	 * @return the shortest distance between all points
	 */
	public static double closestPair(Coordinate[] points, Coordinate lowest, Coordinate lowest2) {
		if (Math.abs(points.length) == 1) {
			System.out.println(" Found result: INF");
			return Double.POSITIVE_INFINITY;
		} else if (Math.abs(points.length) == 2) {
			double distance = distance(points[0], points[1]);
			System.out.println(" Found result: P1: (" + points[0].getX() + "," + points[0].getY() +
					"), P2: (" + points[1].getX() + "," + points[1].getY() + "),"
							+ " Distance: " + distance);
			
			return distance;
		}
		int median = (points.length - 1)/2;
		
		System.out.println(" Dividing at Point[" + points[median].getI() + "]");
		Coordinate[] pointsLeft = new Coordinate[median +1];
		Coordinate[] pointsRight = new Coordinate[points.length - 1 - median];
		
		System.arraycopy(points, 0, pointsLeft, 0, median + 1);
		System.arraycopy(points, median + 1, pointsRight, 0, points.length - median - 1);
		
		System.out.println("Solving Problem: Point[" + pointsLeft[0].getI() +
				"]...Point[" + pointsLeft[median].getI() + "]");
		double distance1 = closestPair(pointsLeft, null, null);
		
		System.out.println("Solving Problem: Point["+ pointsRight[0].getI() +"]..."
				+ "Point[" + pointsRight[pointsRight.length - 1].getI() + "]");
		double distance2 = closestPair(pointsRight, null, null);
		
		System.out.println("Combining Problems: Point[" + pointsLeft[0].getI() +
				"]...Point[" + pointsLeft[median].getI() + "] and " +
				"Point["+  pointsRight[0].getI() +"]..." +
				"Point[" + pointsRight[pointsRight.length - 1].getI() + "]");
		double distance3 = closestPairX(median, points, Math.min(distance1, distance2));
		
		double finalDistance = Math.min(distance1, Math.min(distance2, distance3));
		
		return finalDistance;
	}
	
	/**
	 * This method looks for pairs that or near the median and 
	 * @param median line
	 * @param points - Array of coordinates
	 * @param distance the smallest distance so small
	 * @return the updated smallest distance after we check points near the median line
	 */
	public static double closestPairX(int median, Coordinate[] points, double distance){
		ArrayList<Coordinate> sC = new ArrayList<Coordinate>();
		for(Coordinate point : sY){
			if(Math.abs(point.getX() - points[median].getX()) < distance) {
				sC.add(point);
			}
		}
		
		double newDistance = Double.POSITIVE_INFINITY;
		int[] min = new int[2];
		
		for (int i = 0; i < sC.size()/2; i++) {
			for (int j = sC.size()/2; j < sC.size(); j++) {
				if (distance(sC.get(i), sC.get(j)) < newDistance) {
					newDistance = distance(sC.get(i), sC.get(j));
					if ( distance > newDistance)
						distance = newDistance;
					min[0] = i;
					min[1] = j;
				}
			}
		}
		
		System.out.println(" Found result: P1: (" + sC.get(min[0]).getX() + "," + sC.get(min[0]).getY() +
				"), P2: (" + sC.get(min[1]).getX() + "," + sC.get(min[1]).getY() + "),"
						+ " Distance: " + newDistance);
		return newDistance;
	}
	
	/**
	 * This method calculates the distance between point1 and point2
	 * @param point1 Coordinate
	 * @param point2 Coordinate
	 * @return double - the distance between point1 and point2.
	 */
	public static double distance(Coordinate point1, Coordinate point2) {
		return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + 
				Math.pow((point2.getY() - point1.getY()), 2));
	}
	
	//printResult(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getX(), distance);
	public static void printResult(double x1, double y1, double x2, double y2, double distance) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Final result: P1: (" + x1 + "," + y1 + "), P2: (" + x2 + "," + y2 + "), Distance: " + distance);
	}
	
}