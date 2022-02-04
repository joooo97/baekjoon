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
		
		// 1. dp1[i]: i번째 수로 끝나는 가장 긴 증가하는 부분 수열의 길이
		int[] dp1 = new int[n];
		for(int i = 0; i < n; i++) {
			dp1[i] = 1;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) dp1[i] = Math.max(dp1[i], dp1[j] + 1);
			}
		}
		
		// 2. dp2[i]: i번째 수로 시작하는 가장 긴 감소하는 부분 수열의 길이
		int[] dp2 = new int[n];
		for(int i = n-1; i >= 0; i--) {
			dp2[i] = 1;
			for(int j = n-1; j > i; j--) {
				if(arr[i] > arr[j]) dp2[i] = Math.max(dp2[i], dp2[j] + 1);
			}
		}
		
		// 3. 가장 긴 바이토닉 수열의 길이 구하기
		// -> dp1[i] + dp2[i] - 1 (수열에서 i번째 값이 중복되므로 길이 1 빼주기)
		// -> dp1[i] + dp2[i]의 값은 dp1[i], dp2[i] 각각의 값보다 항상 크므로
		//    세 경우를 비교하지 않고 dp1[i] + dp2[i]-1 의 최대값만 구하면 된다.
		int ans = 0;
		for(int i = 0; i < n; i++) {
			ans = Math.max(ans, dp1[i] + dp2[i] - 1);
		}
		System.out.println(ans);
	}
}