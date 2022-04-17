def solution(S):
  N = len(S)
  nxt = dict()
  dp = [None] * (N+1)
  dps = [None] * (N+1)
  dp[N] = dps[N] = 0
  for i in range(N-1, -1, -1):
    dps[i] = N-i
    if S[i] in nxt:
      j = nxt[S[i]]
      dps[i] = min(dp[j+1], dps[j])
    dp[i] = min (1+dp[i+1], dps[i])
    nxt[S[i]] = i

  return dp[0]


print (solution("abccac"))
print (solution("abcdabcdabcd"))
print (solution("axaabyab"))
print (solution("tqweqrtyr"))