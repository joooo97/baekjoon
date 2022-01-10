import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// dp[i]: i를 제곱수들의 합으로 표현할 때에 그 항의 최소 개수
		int[] dp = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			dp[i] = i; // 1로만 이루어진 경우로 초기화
			for(int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
			}
		}
		
		System.out.println(dp[n]);
	}
}