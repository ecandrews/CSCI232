/*
 * implement divide and conquer algorithm to solve the closest-pair problem
 * hard-code or randomly generate points
 * runtime should be O( nlogn )
 */
import java.util.*;

public class CPP {

	public static void main(String []args) {
		Random random = new Random();
		int numPoints = random.nextInt(25) + 1; //total number of points

		//array to store the points
		double [][] allPoints = new double[numPoints][2];
		double [][] pointsByX = new double[numPoints][2];
		double [][] pointsByY = new double[numPoints][2];
		for(int i = 0; i < numPoints; i++) { //loop through to generate random x and y values
			allPoints[i][0] = random.nextInt(12);
			allPoints[i][1] = random.nextInt(12);
		} //end for loop
		printArray(allPoints);
		System.out.println("calling mergeSort...");
		pointsByX = mergeSort(allPoints, 0);
		printArray(pointsByX);
		pointsByY = mergeSort(allPoints, 1);
		//printArray(pointsByX);
		double xResult = closestPair(pointsByX);
		double yResult = closestPair(pointsByY);
		if(xResult < yResult) {
			//xResult is the smallest minimum distance
		} else if(xResult == yResult) {
			//results are equal
		} else {
			//yResult is the smallest
		}
	}

	public static double closestPair(double[][] points) {
		if(points.length == 1) {
			System.out.println("Only one point: minimum distance is INF");
			System.exit(0);
		}
		if(points.length == 2) {
			return distance(points[0], points[1]);
		}
		double [] m = points[points.length / 2];
		//divide points into p1 and p2 at m
		double [][] p1 = new double[points.length / 2][2];
		double [][] p2 = new double[points.length - (points.length / 2)][2];
		
		for(int i = 0; i < points.length / 2; i++) {
			p1[i] = points[i];
		}
		for(int i = points.length / 2; i < points.length - (points.length / 2); i++) {
			p2[i] = points[i];
		}
		
		double dL = closestPair(p1);
		double dR = closestPair(p2);
		double dC = closestPairXCut(m, points, Math.min(dL, dR));
		double d = min(dL, dR, dC);

		return d; //CHANGE THIS
	}
	
	public static double closestPairXCut(double [] medianPoint, double[][] points, double min) { //m s d
		
		/*
		 * for every point in points
		 * 		if |p.x-m| < d
		 *  		put p into array Sc
		 * for every point pi in Sc such that pi.x < m
		 *  	d <- min {d, dis(pi-3,pi), dis(pi-2,pi), dis(pi-1,pi), dis(pi,pi+1), dis(pi,pi+2), dis(pi, pi+3)}
		 * return d
		 */
		double [][] sC = new double[points.length][2];
		int i = 0;
		for(double [] point: points) { //for every point in points
			if(point[0] < min) { //if |p.x-m| < d
				sC[i] = point; //put p into array sC
				i++;
			}
		}
		for(i = 0; i < sC.length - 1; i++) { //for every point in sC
			if(sC[i][0] < medianPoint[0]) { //such that pi.x < m
				min = minOfSix(min, distance(sC[i-3], sC[i]), distance(sC[i-2], sC[i]), distance(sC[i-1], sC[i]), distance(sC[i], sC[i+1]), distance(sC[i], sC[i+2]), distance(sC[i], sC[i+3]));
			}
		}
		return min; //return d
	}
	
	public static double minOfSix(double d0, double d1, double d2, double d3, double d4, double d5, double d6) {
		double [] allPoints = new double[6];
		allPoints[0] = d1;
		allPoints[1] = d2;
		allPoints[2] = d3;
		allPoints[3] = d4;
		allPoints[4] = d5;
		allPoints[5] = d6;
		for(double point: allPoints) {
			if(point < d0) {
				d0 = point;
			}
		}
		return d0;
	}
	
	public static double min(double one, double two, double three) {
		if(one <= two && one <= three) {
			return one;
		} else if(two <= one && two <= three) {
			return two;
		} else {
			return three;
		}
	}

	public static double distance(double [] p1, double [] p2) {
		double dist = Math.abs((p2[0] - p1[0])/(p2[1] - p1[1]));
		return dist;
	}

	public static void printArray(double [][] points) {
		for(int i = 0; i <= points.length - 1; i++) {
			System.out.println("[" + points[i][0] + ", " + points[i][1] + "]");
		}
	}

	public static double[][] mergeSort(double [][] points, int sortBy) {

		if(points.length > 1) {
			double [][] first = new double[points.length / 2][2];
			double [][] last = new double[points.length - (points.length / 2)][2];
			System.arraycopy(points, 0, first, 0, points.length / 2);
			System.arraycopy(points, 0, last, 0, points.length - (points.length /2));

			System.out.println("calling printArray from inside mergeSort...");
			printArray(first);
			printArray(last);
			System.out.println();

			first = mergeSort(first, sortBy);
			last = mergeSort(last, sortBy);

			int i, j, k; //i is the index of the main array
			i = j = k = 0; //j is the index of which element from first is being compared

			if(sortBy == 0) { //sort by X

				while(first.length != j && last.length != k) {
					if(first[j][0] < last[k][0]) {
						points[i] = first[j];
						i++;
						j++;
					} else {
						points[i] = last[k];
						i++;
						k++;
					} //end if else
				} //end while loop
				while(first.length != j) {
					points[i] = first[j];
					i++;
					j++;
				}
				while(last.length != k) {
					points[i] = last[k];
					i++;
					k++;
				}

			} else { //sort by Y

				while(first.length != j && last.length != k) {
					if(first[j][1] < last[k][1]) {
						points[i] = first[j];
						i++;
						j++;
					} else {
						points[i] = last[k];
						i++;
						k++;
					} //end if else
				} //end while loop
				while(first.length != j) {
					points[i] = first[j];
					i++;
					j++;
				}
				while(last.length != k) {
					points[i] = last[k];
					i++;
					k++;
				} //end last while loop
			} //end sort by Y
		} //end if points.length > 1

		return points;
	} //end mergeSort method
}
