package array;

import java.util.*;


public class RobotThrows {
	public static void main(String[] args) {
		int maxNumOfSubstrings = new RobotThrows().getTotal(Arrays.asList("5", "-2", "4", "Z", "X", "9", "+", "+"));
		System.out.println(maxNumOfSubstrings);
	}

	public int getTotal(List<String> allThrows) {
		int total = 0;
		Stack<Integer> scores = new Stack<Integer>();
		for (String aScore : allThrows) {
			int score = evaulateScore(aScore, scores);
			total += score;
			System.out.println(String.format("Score %d, total %d", score, total));
			if (!"Z".equals(aScore))
				scores.push(score);
		}
		return total;
	}

	private int evaulateScore(String aScore, Stack<Integer> scores) {
		int score = 0;
		if ("X".contentEquals(aScore)) {
			score = scores.isEmpty() ? 0 : 2 * (scores.peek());
		} else if ("Z".contentEquals(aScore)) {
			score = scores.isEmpty() ? 0 : -1 * (scores.pop());
		} else if ("+".contentEquals(aScore)) {
			if (scores.size() >= 2) {
				int lastScore = scores.pop();
				int lastSecondScore = scores.pop();
				score = lastSecondScore + lastScore;
				scores.push(lastSecondScore);
				scores.push(lastScore);
			} else if (scores.size() == 1) {
				int lastScore = scores.pop();
				score = lastScore;
				scores.push(lastScore);
			}
		} else {
			score = Integer.valueOf(aScore);
		}

		return score;
	}
}
