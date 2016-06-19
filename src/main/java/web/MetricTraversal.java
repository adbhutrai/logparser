package web;

public class MetricTraversal {

	public static void main(String[] args) {
		int[][] metric = {
				{1, 2, 3}, 
				{4, 5, 6},
				{7, 8, 9}};
		for (int i = 0; i < metric.length; i++) {
			for (int j = 0; j < metric[i].length; j++) {
				System.out.print(metric[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		for (int i = 0; i < metric.length; i++) {
			for (int j = 0; j < metric[i].length; j++) {
				System.out.print(metric[j][i]);
			}
			System.out.println();
		}

	}

}
