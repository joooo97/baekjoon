import java.io.*;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[n+1][3];
		for(int i = 1; i <= n; i++) {
			// i번째 포도주를 마시지 않는 경우
			dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
			// i번째 포도주를 첫 번째로 마시는 경우
			dp[i][1] = dp[i-1][0] + arr[i];
			// i번째 포도주를 두 번째로 마시는 경우
			dp[i][2] = dp[i-1][1] + arr[i];
		}
		System.out.println(Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]));
	}
}