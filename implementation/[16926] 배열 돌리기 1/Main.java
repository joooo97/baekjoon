import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int group_n = Math.min(n, m) / 2; // 그룹의 수
		
		while(r-- > 0) {
			for(int g = 0; g < group_n; g++) {
				
				int start_value = arr[g][g]; // 처음 수는 지워지기 때문에 기록해둔다.
				
				// 상 위치의 원소들 이동
				for(int y = g; y < m-1-g; y++) {
					arr[g][y] = arr[g][y+1];
				}
				
				// 우 위치의 원소들 이동
				for(int x = g; x < n-1-g; x++) {
					arr[x][m-1-g] = arr[x+1][m-1-g];
				}
				
				// 하 위치의 원소들 이동
				for(int y = m-1-g; y > g; y--) {
					arr[n-1-g][y] = arr[n-1-g][y-1];
				}
				
				// 좌 위치의 원소들 이동
				for(int x = n-1-g; x > g; x--) {
					arr[x][g] = arr[x-1][g];
				}
				
				// 기록해 둔 처음 수 넣어주기
				arr[g+1][g] = start_value;
			}
		}
		
		// 회전 후 배열 출력
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