import java.io.*;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// dp[i]: i번째까지 포도주가 있을 때 마실 수 있는 최대의 양
		int[] dp = new int[n+1];
		dp[1] = arr[1]; // 포도주가 1개인 경우 초기화
		
		// 예외 처리 (주어진 n이 2 이상인 경우에만 구해야 함)
		if(n >= 2) dp[2] = arr[1] + arr[2];
		
		for(int i = 3; i <= n; i++) {
			// i번째 포도주를 마시지 않는 경우
			dp[i] = dp[i-1];
			
			// i번째 포도주가 첫 번째로 마시는 포도주인 경우
			// -> i-1번째는 마시지 않고, i번째를 마신다.
			dp[i] = Math.max(dp[i], dp[i-2] + arr[i]);
			
			// i번째 포도주가 두 번째로 마시는 포도주인 경우 경우
			// -> i-2번째는 마시지 않고, i-1번째와 i번째를 모두 마신다.
			dp[i] = Math.max(dp[i], dp[i-3] + arr[i-1] + arr[i]);
		}
		System.out.println(dp[n]);
	}
}