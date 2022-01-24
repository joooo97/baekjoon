import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][n+1];
		
		StringTokenizer st;
		//  arr[1][1] ~ arr[n][n]까지 값을 채우게 됨
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= i ; j++) {                      
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp[i][j]: i층의 j번째 수로 내려왔을 때 합의 최대
		// dp[1][1] ~ dp[n][n]까지 값을 채우게 됨
		int[][] dp = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= i; j++) {
				// 대각선 오른쪽에서 내려오는 경우
				dp[i][j] = dp[i-1][j] + arr[i][j];
				
				// 대각선 왼쪽에서 내려오는 경우
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + arr[i][j]);
			}
		}
		
		// i층까지 내려왔을 때 합의 최대
		int ans = 0;
		for(int i = 1; i <= n; i++) {
			ans = Math.max(ans, dp[n][i]);
		}
		System.out.println(ans);
	}
}