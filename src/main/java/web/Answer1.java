package web;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adbhutrai
 *
 */
public class Answer1 {
	private static final char NOUGHTS = 'O';
	private static final char CROSSES = 'X';
	private static int testCases;
	private static List<Answer1.Game> cases = new ArrayList<Answer1.Game>();

	private Answer1() {

	}

	private static class Game {
		private char nextPlayer;
		private char[][] gameBoard;
		private boolean winner;
		private char winnerChar;

		private Game() {
			gameBoard = new char[3][3];
		}

		private boolean verticalCheck() {
			for (int i = 0; i < gameBoard.length; i++) {
				int countX = 0;
				int countO = 0;
				int count_ = 0;
				for (int j = 0; j < gameBoard[i].length; j++) {
					if (gameBoard[j][i] == CROSSES) {
						countX++;
					} else if (gameBoard[j][i] == NOUGHTS) {
						countO++;
					} else {
						count_++;
					}
					if (checkWinnerIdentified(countX, countO, count_))
						return true;
				}
			}
			return false;
		}

		private boolean horizontalCheck() {
			for (int i = 0; i < gameBoard.length; i++) {
				int countX = 0;
				int countO = 0;
				int count_ = 0;
				for (int j = 0; j < gameBoard[i].length; j++) {
					if (gameBoard[i][j] == CROSSES) {
						countX++;
					} else if (gameBoard[i][j] == NOUGHTS) {
						countO++;
					} else {
						count_++;
					}
					if (checkWinnerIdentified(countX, countO, count_))
						return true;
				}
			}
			return false;
		}

		private boolean checkWinnerIdentified(int countX, int count0, int count_) {
			if (countX > 2
					|| (countX == 2 && count_ == 1 && nextPlayer == CROSSES)) {
				winnerChar = CROSSES;
				winner = true;
				return true;
			}
			if (count0 > 2
					|| (count0 == 2 && count_ == 1 && nextPlayer == NOUGHTS)) {
				winnerChar = NOUGHTS;
				winner = true;
				return true;
			}
			return false;
		}

		private boolean diagonalCheck() {
			int countX = 0;
			int countO = 0;
			int count_ = 0;
			for (int i = 0; i < gameBoard.length; i++) {
				if (gameBoard[i][i] == CROSSES) {
					countX++;
				} else if (gameBoard[i][i] == NOUGHTS) {
					countO++;
				} else {
					count_++;
				}
			}
			if (checkWinnerIdentified(countX, countO, count_))
				return true;
			countX = 0;
			countO = 0;
			count_ = 0;
			int j = 0;
			for (int i = gameBoard.length - 1; i >= 0; i--) {
				if (gameBoard[i][j] == CROSSES) {
					countX++;
				} else if (gameBoard[i][j] == NOUGHTS) {
					countO++;
				} else {
					count_++;
				}
				j++;
			}
			if (checkWinnerIdentified(countX, countO, count_))
				return true;
			return false;
		}

	}

	public static void main(String[] args) throws IOException {
		String fileName = "C:\\Users\\adbhutrai\\Desktop\\game2.txt";
		readAndProcessInput(fileName);

	}

	/**
	 * @param fileName
	 * @throws IOException
	 */
	/**
	 * @param fileName
	 * @throws IOException
	 */
	public static void readAndProcessInput(String fileName) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			int count = 0;
			String line = bufferedReader.readLine();
			testCases = Integer.parseInt(line);
			validateTestCaseLimit();
			Answer1.Game game = null;
			while ((line = bufferedReader.readLine()) != null) {
				count++;
				if (count % 4 == 1) {
					game = new Answer1.Game();
					game.nextPlayer = line.charAt(0);
					cases.add(game);

				} else {
					int index = 0;
					for (char gameInput : line.toCharArray()) {
						game.gameBoard[count - 2][index++] = gameInput;
					}
					if (count == 4)
						count = 0;

				}

			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		processResults(cases);
	}

	private static void processResults(List<Game> cases) {
		int count = 0;
		for (Game game : cases) {
			if (!game.verticalCheck()&& !game.horizontalCheck())
					game.diagonalCheck();
			out.println("Case " + (++count) + ": "
					+ (game.winner ? game.winnerChar + " wins" : "Draw"));
		}
	}

	private static void validateTestCaseLimit() {
		if (testCases < 1) {
			throw new IllegalArgumentException("No test"
					+ " cases to test.");
		}
	}

}
