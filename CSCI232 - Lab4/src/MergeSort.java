/**
 * Merge sort algorithm for the ClosestPair problem
 * @author Kyle Melton -02312584
 * @author James George -02215548
 * @author Elizabeth Andrews -02224876
 */
public class MergeSort {
	
	/**
	 * This method merge sorts the provided points array
	 * @param points array
	 * @param coord - to sort by x values, pass in a 0, to sort by y values, pass in a 1
	 * @return the points sorted by least to greatest by the coordinate of choosing
	 */
	public static Coordinate[] mergeSort(Coordinate[] points, int coord) {
		if (points.length <= 1) {
			return points;
		}
		Coordinate[] first = new Coordinate[points.length / 2];
		Coordinate[] last = new Coordinate[points.length - first.length];
		System.arraycopy(points, 0, first, 0, first.length);
		System.arraycopy(points, first.length, last, 0, last.length);
		
		mergeSort(first, coord);
		mergeSort(last, coord);
		
		Coordinate[] result = new Coordinate[points.length];
		merge(first, last, coord, result);
		return result;
		
	}
	
	/**
	 * This method merges the divided results from mergeSort
	 * @param first half of the large array
	 * @param last half of the large array
	 * @param coord - to sort by x values, pass in a 0, to sort by y values, pass in a 1
	 * @param result - Pass by reference the final merged arrays
	 */
	public static void merge(Coordinate[] first, Coordinate[] last, int coord,
			Coordinate[] result) {
		int iFirst = 0, iLast = 0;
		int i;
		for (i = 0; iFirst < first.length && iLast < last.length; i++) {
			if (first[iFirst].getXOrY(coord) < last[iLast].getXOrY(coord)) {
				result[i] = first[iFirst];
				iFirst++;
			} else {
				result[i] = last[iLast];
				iLast++;
			}
		}
		System.arraycopy(first, iFirst, result, i, first.length - iFirst);
		System.arraycopy(last, iLast, result, i, last.length - iLast);
	}

/**
 * This method merge sorts the provided points array
 * @param points array
 * @param coord - to sort by x values, pass in a 0, to sort by y values, pass in a 1
 * @return the points sorted by least to greatest by the coordinate of chosing
 */
public static double[][] mergeSort(double[][] points, int coord) {
	if (points.length <= 1) {
		return points;
	}
	double [][] first = new double[points.length / 2][2];
	double [][] last = new double[points.length - first.length][2];
	System.arraycopy(points, 0, first, 0, first.length);
	System.arraycopy(points, first.length, last, 0, last.length);
	
	mergeSort(first, coord);
	mergeSort(last, coord);
	
	double[][] result = new double[points.length][2];
	merge(first, last, coord, result);
	return result;
	
}

/**
 * This method merges the divided results from mergeSort
 * @param first half of the large array
 * @param last half of the large array
 * @param coord - to sort by x values, pass in a 0, to sort by y values, pass in a 1
 * @param result - Pass by reference the final merged arrays
 */
	public static void merge(double[][] first, double[][] last, int coord,
			double[][] result) {
		int iFirst = 0, iLast = 0;
		int i;
		for (i = 0; iFirst < first.length && iLast < last.length; i++) {
			if (first[iFirst][coord] < last[iLast][coord]) {
				result[i] = first[iFirst];
				iFirst++;
			} else {
				result[i] = last[iLast];
				iLast++;
			}
		}
		System.arraycopy(first, iFirst, result, i, first.length - iFirst);
		System.arraycopy(last, iLast, result, i, last.length - iLast);
	}
}