import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] a, prev, dp;
	public static StringBuilder sb = new StringBuilder();
	
	// 가장 긴 증가하는 부분 수열을 출력
	public static void print(int idx) {
		// 수열에서 idx번째 이전의 수가 존재하는 경우
		if(prev[idx] != -1) print(prev[idx]);
		
		sb.append(a[idx]).append(" ");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new int[n]; // 주어진 수열
		prev = new int[n]; // 이전 값의 인덱스를 기록할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		// dp[i]: i번째 수로 끝나는 가장 긴 증가하는 부분 수열의 길이
		dp = new int[n];
		for(int i = 0; i < n; i++) {
			dp[i] = 1; // 부분 수열의 길이를 1로 초기화 (현재 i번째 수만 부분 수열에 존재)
			prev[i] = -1; // 수열에서 현재 수 전의 수는 존재하지 않으므로 -1로 초기화
			
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					prev[i] = j; // 앞에 올 수의 인덱스 기록
				}
			}
		}
		
		// 가장 긴 증가하는 부분 수열의 길이 구하기
		int length = 0;
		int idx = 0; // 수열의 마지막 값의 인덱스
		for(int i = 0; i < n; i++) {
			if(length < dp[i]) {
				length = dp[i];
				idx = i;
			}
		}

		// 출력
		sb.append(length).append("\n");
		print(idx); // 수열 출력 (idx번째 수로 끝나는 LIS(최장 증가 부분 수열))
		System.out.println(sb);
	}
}