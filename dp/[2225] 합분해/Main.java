import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// dp[k][n]: 정수 k개를 더해서 그 합이 n이 되는 경우의 수
		// dp[k][n] = dp[k-1][n-0] + dp[k-1][n-1] + ... + dp[k-1][n-n]
		long[][] dp = new long[K+1][N+1];
		dp[0][0] = 1;
		
		for(int k = 1; k <= K; k++) { // dp[k][n]에서 k의 인덱스
			for(int n = 0; n <= N; n++) { // dp[k][n]에서 n의 인덱스
				for(int i = 0; i <= n; i++) {
					dp[k][n] += dp[k-1][n-i];
					dp[k][n] %= 1000000000;
				}
			}
		}
		System.out.println(dp[K][N]);
	}
}