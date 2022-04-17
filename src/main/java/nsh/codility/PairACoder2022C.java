package nsh.codility;

public class PairACoder2022C implements PairACoder2022Interface {

	public int solution(String S) {
		int N = S.length();
		int[] dp = new int[N + 1];
		dp[N] = 0;
		for (int i = N - 1; i > -1; i--) {
			dp[i] = 1 + dp[i + 1];
			for (int j = i + 1; j < N; j++)
				if (S.charAt(i) == S.charAt(j))
					dp[i] = Math.min(dp[i], dp[j + 1]);
		}

		return dp[0];
	}
}
