package web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

/**
 * @author adbhutrai
 *
 */
public class Answer2 {
	private static int testCases;
	private static List<Answer2.CastleWar> cases = new ArrayList<Answer2.CastleWar>();

	private static class CastleWar {
		private int cannons;
		private int assinedFirers;
		private List<Integer> assinatedFirersPoitions;
		private int totalFiredCannonBalls;

		private CastleWar() {
			assinatedFirersPoitions = new ArrayList<Integer>();
		}
		
		private void validateFirersLimit() {
			if (assinedFirers < 1 || assinedFirers > 10) {
				throw new IllegalArgumentException(
						"No of assacinated firers exceeded less "
						+ "than the limit or excceded the permitted limit from 10.");
			}
		}

		private  void validateCannonsAndFirersLimit() {
			if (cannons < 1 || cannons > 1000
					|| cannons < assinedFirers) {
				throw new IllegalArgumentException(
						"No of assacinated firers exceeded "
						+ "the no of cannons or cannons limit are excceded from 1000 limits.");
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "canons=" + cannons + " " + "assasinatedFirers="
					+ assinedFirers + "\nassinatedFirersPoitions="
					+ assinatedFirersPoitions + "\ntotalFiredCannonBalls="
					+ totalFiredCannonBalls + "\n";
		}
	}

	public static void main(String[] args) throws IOException {
		String fileName = "C:\\Users\\adbhutrai\\Desktop\\input1.txt";
		readAndProcessInput(fileName);
	}

	/**
	 * @param fileName
	 * @throws IOException
	 */
	public static void readAndProcessInput(String fileName) throws IOException {
		BufferedReader bufferedReader = null;
		Scanner scanner = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			int count = 0;
			String line = bufferedReader.readLine();
			testCases = Integer.parseInt(line);
			validateTestCaseLimit();
			Answer2.CastleWar input = null;
			while ((line = bufferedReader.readLine()) != null) {
				scanner = new Scanner(line);
				while (scanner.hasNext()) {
					if (count % 2 == 0) {
						input = new Answer2.CastleWar();
						input.cannons = scanner.nextInt();
						input.assinedFirers = scanner.nextInt();
						input.validateCannonsAndFirersLimit();
						input.validateFirersLimit();
						cases.add(input);
					} else {
						input.assinatedFirersPoitions.add(scanner.nextInt());

					}
				}
				scanner.close();
				count++;
			}
		} catch (IOException e) {
			if(scanner !=null)
				scanner.close();
			throw e;
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		maxFiredCannonBallsInOrder();
	}

	private static void maxFiredCannonBallsInOrder() {
		int count = 0;
		for (Answer2.CastleWar input : cases) {
			int previousAssassin = 0;
			for (int assacinatedFirer : input.assinatedFirersPoitions) {
				int leftCount = assacinatedFirer - (previousAssassin + 1);
				previousAssassin = assacinatedFirer;
				int rightCount = input.cannons - assacinatedFirer;
				input.totalFiredCannonBalls = input.totalFiredCannonBalls + (leftCount + rightCount);
			}
			count++;
			out.format("Case %s:%s  %n", count, input.totalFiredCannonBalls);

		}
	}
	private static void maxFiredCannonBalls() {
		int count = 0;
		for (Answer2.CastleWar input : cases) {
			List<Integer> cannonsList = prepareCanons(input.cannons);
			for (int i : input.assinatedFirersPoitions) {
				i = i - 1;
				cannonsList.set(i, 0);
				int leftCount = getLeftCount(cannonsList, i);
				int rightCount = getRightCount(cannonsList,
						input.cannons, i);
				input.totalFiredCannonBalls = input.totalFiredCannonBalls + (leftCount + rightCount);
			}
			count++;
			System.out.format("Case %s:%s  %n", count,
					input.totalFiredCannonBalls);

		}
	}

	private static void validateTestCaseLimit() {
		if (testCases < 1 || testCases > 100) {
			throw new IllegalArgumentException("No of test cases " + testCases
					+ " has exeeceded the limit of 1 <=S<=100.");
		}
	}

	private static List<Integer> prepareCanons(int x) {
		List<Integer> cannonsList = new ArrayList<Integer>();
		for (int i = 0; i < x; i++) {
			cannonsList.add(1);
		}
		return cannonsList;
	}

	private static int getRightCount(List<Integer> cannonsList, 
			int x, int i) {
		int rightCount = 0;
		for (int j = i + 1; j < x; j++) {
			if (cannonsList.get(j) != 0) {
				rightCount++;
			} else {
				break;
			}
		}
		return rightCount;
	}

	private static int getLeftCount(List<Integer> cannonsList, 
			int i) {
		int leftCount = 0;
		for (int j = i - 1; j >= 0; j--) {
			if (cannonsList.get(j) != 0) {
				leftCount++;
			} else {
				break;
			}
		}
		return leftCount;
	}
}
