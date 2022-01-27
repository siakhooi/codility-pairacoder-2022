package nsh.codility;

public class PairACoder2022B implements PairACoder2022Interface {
	// B
	private final int BIGNUMBER = 100001;

	public int solution(String S) {
		int[] remainingIfMovedByAlphabet = new int[26];
		for (int i = 0; i < 26; i++)
			remainingIfMovedByAlphabet[i] = BIGNUMBER;

		int previousRemaining = 0;
		int currentRemaining = 0;
		for (int i = 0; i < S.length(); i++) {
			int c = S.charAt(i) - 'a';

			currentRemaining = previousRemaining + 1;

			if (currentRemaining > remainingIfMovedByAlphabet[c])
				currentRemaining = remainingIfMovedByAlphabet[c];
			if (remainingIfMovedByAlphabet[c] > previousRemaining)
				remainingIfMovedByAlphabet[c] = previousRemaining;
			previousRemaining = currentRemaining;
		}

		return currentRemaining;
	}
}
