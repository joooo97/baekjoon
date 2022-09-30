import java.io.*;
import java.util.*;

public class Main {
	
	public static int n, m;
	public static int[][] arr;

	public static void rotate(int g, int row_end, int col_end, int cnt_r) {
		
		while(cnt_r-- > 0) {
			// 시작 값은 지워지므로 기록해두기
			int start_value = arr[g][g];
			
			// 상 그룹 이동
			for(int j = g; j < col_end; j++) {
				arr[g][j] = arr[g][j+1];
			}
			
			// 우 그룹 이동
			for(int i = g; i < row_end; i++) {
				arr[i][col_end] = arr[i+1][col_end];
			}
			
			// 하 그룹 이동
			for(int j = col_end; j > g; j--) {
				arr[row_end][j] = arr[row_end][j-1];
			}
			
			// 좌 그룹 이동
			for(int i = row_end; i > g; i--) {
				arr[i][g] = arr[i-1][g];
			}
			
			// 기록해두었던 시작 값 넣어주기
			arr[g+1][g] = start_value;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt_g = Math.min(n, m) / 2; // 전체 그룹 수
		
		// - 그룹별 회전 수 = r % 그룹별 원소의 개수
		// - 그룹별 원소의 개수 = 2(마지막 열 - 시작 열 + 1) + 2(마지막 행 - 시작 행 + 1) - 4 
		for(int g = 0; g < cnt_g; g++) { // 그룹 번호
			int row_end = n-1-g; // 그룹별 마지막 행
			int col_end = m-1-g; // 그룹별 마지막 열

			// 그룹별 원소의 개수
			int cnt_element = 2*(col_end - g + 1) + 2*(row_end - g + 1) - 4;
			// 그룹별 회전 수
			int cnt_r = r % cnt_element;
			
			// 회전 진행
			rotate(g, row_end, col_end, cnt_r);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}