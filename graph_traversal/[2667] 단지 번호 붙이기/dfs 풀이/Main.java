import java.io.*;
import java.util.*;

// 총 단지 수, 각 단지 내 집의 수 오름차순 출력
public class Main {
	public static int n;
	public static int[][] map, group;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y, int group_no) {
		// 단지 번호 부여 및 방문 처리
		group[x][y] = group_no;
		
		// 인접 노드 확인
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			// 집이 있고, 단지 번호가 부여되지 않은 경우
			if(map[nx][ny] == 1 && group[nx][ny] == 0) dfs(nx, ny, group_no);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		group = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				map[i][j] = line.charAt(j) -  '0';
			}
		}
		
		
		// 1. dfs 진행하여 단지 번호 부여하기, 총 단지 개수 세기
		int group_cnt = 0; // 총 단지 수
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1 && group[i][j] == 0) {
					dfs(i, j, ++group_cnt);
				}
			}
		}
		
		// 2. 각 단지별 집의 수 세기
		int[] home_cnt = new int[group_cnt]; // 각 단지별 집의 수
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {	
				if(group[i][j] != 0) home_cnt[group[i][j] - 1] += 1;
			}
		}
		
		Arrays.sort(home_cnt); // 단지 내 집의 수 오름차순 정렬
		
		StringBuilder sb = new StringBuilder();
		sb.append(group_cnt).append("\n");
		
		for(int i = 0; i < group_cnt; i++) {
			sb.append(home_cnt[i]).append("\n");
		}
		System.out.println(sb);
	}
	
}