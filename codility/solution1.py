def solution(S):
  N = len(S)
  dp = [None] * (N+1)
  dp[N] = 0
  for i in range(N-1, -1, -1):
    dp[i] = 1 + dp[i+1]
    for j in range(i+1, N):
      if S[i] == S[j]:
        dp[i] = min (dp[i], dp[j+1])
  return dp[0]


print (solution("abccac"))
print (solution("abcdabcdabcd"))
print (solution("axaabyab"))
print (solution("tqweqrtyr"))