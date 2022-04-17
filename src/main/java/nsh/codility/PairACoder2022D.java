package nsh.codility;

import java.util.HashMap;
import java.util.Map;

public class PairACoder2022D implements PairACoder2022Interface {

	public int solution(String S) {
		int N = S.length();
		Map<Character, Integer> nxt = new HashMap<>();

		int[] dp = new int[N + 1];
		int[] dps = new int[N + 1];
		dp[N] = 0;
		dps[N] = 0;

		for (int i = N - 1; i > -1; i--) {
			dps[i] = N - i;
			if (nxt.containsKey(S.charAt(i))) {
				int j = nxt.get(S.charAt(i)).intValue();
				dps[i] = Math.min(dp[j + 1], dps[j]);
			}
			dp[i] = Math.min(1 + dp[i + 1], dps[i]);
			nxt.put(S.charAt(i), Integer.valueOf(i));
		}

		return dp[0];
	}
}
