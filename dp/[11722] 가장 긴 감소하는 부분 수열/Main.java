import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i]: i번째 수로 끝나는 부분 수열의 길이
		int[] dp = new int[n];
		for(int i = 0; i < n; i++) {
			dp[i] = 1; // arr[i]만 수열에 포함되는 경우
			for(int j = 0; j < i; j++) {
				if(arr[j] > arr[i])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		
		int ans = dp[0];
		for(int i = 1; i < n; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}
}