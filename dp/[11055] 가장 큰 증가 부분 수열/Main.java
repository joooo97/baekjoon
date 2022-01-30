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
		
		int[] dp = new int[n];
		for(int i = 0; i < n; i++) {
			dp[i] = arr[i];
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) 
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
			}
		}
		
		int ans = dp[0];
		for(int i = 1; i < n; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}
}