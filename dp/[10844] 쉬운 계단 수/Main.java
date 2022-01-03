import java.io.*;

public class Main {
	public static int mod = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] dp = new long[n+1][10]; // d[i][j]: i자리이고, j로 끝나는 계단 수
		
		// 길이가 1인 계단 수 초기화
		for(int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <= 9; j++) {
				// 0 <= j <= 8
				if(j < 9) dp[i][j] += dp[i-1][j+1];
				// 1 <= j <= 9
				if(j > 0) dp[i][j] += dp[i-1][j-1];
				
				dp[i][j] %= mod;
			}
		}
		
		// 길이가 n인 계단 수의 개수
		long ans = 0;
		for(int i = 0; i <= 9; i++) {
			ans += dp[n][i];
		}
		System.out.println(ans % mod);
	}
}