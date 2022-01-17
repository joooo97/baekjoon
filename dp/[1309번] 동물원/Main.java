import java.io.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// dp[i][0]: i번째 줄에 사자를 한 마리도 배치하지 않는 경우의 수
		// dp[i][1]: i번째 줄의 왼쪽칸에만 사자를 배치하는 경우의 수
		// dp[i][2]: i번째 줄의 오른쪽칸에만 사자를 배치하는 경우의 수
		int[][] dp = new int[n+1][3];
		dp[1][0] = dp[1][1] = dp[1][2] = 1;
		
		for(int i = 2; i <= n; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901);
	}
}