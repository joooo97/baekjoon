import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n+1][3];
		
		StringTokenizer st;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp[i][j]: i번 집을 j색으로 칠하는 비용의 최솟값
		int[][] dp = new int[n+1][3];
		for(int i = 1; i <= n; i++) {
			// i번 집을 빨간색으로 칠하는 최소 비용
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			// i번 집을 초록색으로 칠하는 최소 비용
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			// i번 집을 파란색으로 칠하는 최소 비용
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
		}
		
		System.out.println(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));
	}
}