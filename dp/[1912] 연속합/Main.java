import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i]: i번째 수로 끝나는 가장 큰 연속합
		int[] dp = new int[n];
		dp[0] = arr[0];
		for(int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
		}
		
		// 가장 큰 연속합이 음수일 수도 있으므로 ans의 초기값을 0으로 설정하면 오답이 나올 수 있음
		int ans = dp[0];
		for(int i = 1; i < n; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}
}