import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		// 테트로미노 놓아보기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int sum = 0;
				
				// 1. 민트색 테트로미노
				// 1-1. 원본
				if(j+3 < m) {
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i][j+2];
					sum += arr[i][j+3];					
					ans = Math.max(ans, sum);
				}
				// 1-2. 90도 회전
				if(i+3 < n) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+2][j];
					sum += arr[i+3][j];
					ans = Math.max(ans, sum);
				}
				
				// 2. 노란색 테트로미노
				if(i+1 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					ans = Math.max(ans, sum);
				}
				
				// 3. 주황색 테트로미노
				// 3-1. 원본
				if(i+2 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+2][j];
					sum += arr[i+2][j+1];
					ans = Math.max(ans, sum);
				}
				// 3-2. 90도 회전
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i][j+2];
					sum += arr[i+1][j];
					ans = Math.max(ans, sum);
				}
				// 3-3. 180도 회전
				if(i+2 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i+1][j+1];
					sum += arr[i+2][j+1];
					ans = Math.max(ans, sum);
				}
				// 3-4. 270도 회전
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j+2];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					sum += arr[i+1][j+2];
					ans = Math.max(ans, sum);
				}
				// 3-5. 원본의 상하반전
				if(i+2 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i+1][j];
					sum += arr[i+2][j];
					ans = Math.max(ans, sum);
				}
				// 3-6. 90도 회전 후 상하반전
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					sum += arr[i+1][j+2];
					ans = Math.max(ans, sum);
				}
				// 3-7. 180도 회전 후 상하반전
				if(i+2 < n && j-1 >= 0) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+2][j];
					sum += arr[i+2][j-1];
					ans = Math.max(ans, sum);
				}
				// 3-8. 270도 회전 후 상하반전
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i][j+2];
					sum += arr[i+1][j+2];
					ans = Math.max(ans, sum);
				}
				
				// 4. 초록색 테트로미노
				// 4-1. 원본
				if(i+2 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					sum += arr[i+2][j+1];
					ans = Math.max(ans, sum);
				}
				// 4-2. 90도 회전
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j+1];
					sum += arr[i][j+2];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					ans = Math.max(ans, sum);
				}
				// 4-3. 원본의 상하반전
				if(i+2 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j+1];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					sum += arr[i+2][j];
					ans = Math.max(ans, sum);
				}
				// 4-4. 90도 회전 후 상하반전
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i+1][j+1];
					sum += arr[i+1][j+2];
					ans = Math.max(ans, sum);
				}
				
				// 5. 분홍색 테트로미노
				// 5-1. 원본
				if(i+1 < n && j+2 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i][j+2];
					sum += arr[i+1][j+1];
					ans = Math.max(ans, sum);
				}
				// 5-2. 90도 회전
				if(i+2 < n && j-1 >= 0) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+2][j];
					sum += arr[i+1][j-1];
					ans = Math.max(ans, sum);
				}
				// 5-3. 180도 회전
				if(i-1 >= 0 && j+2 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i][j+1];
					sum += arr[i][j+2];
					sum += arr[i-1][j+1];
					ans = Math.max(ans, sum);
				}
				// 5-4. 270도 회전
				if(i+2 < n && j+1 < m) {
					sum = 0;
					sum += arr[i][j];
					sum += arr[i+1][j];
					sum += arr[i+1][j+1];
					sum += arr[i+2][j];
					ans = Math.max(ans, sum);
				}
			}
		}
		System.out.println(ans);
	}
}