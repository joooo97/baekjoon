import java.io.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// dp[i]: i를 1, 2, 3의 합으로 나타내는 방법의 수
		long[] dp = new long[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i = 4; i <= 1000000; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			dp[i] %= 1000000009;
		}
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		}
		System.out.println(sb);
	}
}