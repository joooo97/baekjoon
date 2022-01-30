import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, ans;
	public static int[][] map, copy_map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	// 안전 영역 크기 구하기
	public static int getSizeOfSafeZone() {
		int size = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copy_map[i][j] == 0) size += 1;
			}
		}
		return size;
	}
	
	// 바이러스 전파 (dfs 탐색)
	// - dfs, bfs 둘 다 가능
	public static void spreadVirus(int x, int y) {
		copy_map[x][y] = 3; // 전파된 바이러스는 3으로 저장
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			
			if(copy_map[nx][ny] == 0) spreadVirus(nx, ny);
		}
	}

	
	public static void makeWall(int cnt) {
		if(cnt == 3) {
			// 현재 지도 복사
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					copy_map[i][j] = map[i][j];
				}
			}
			
			// 2. 바이러스 전파
			// 바이러스의 처음 위치(2)를 시작 지점으로 탐색
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(copy_map[i][j] == 2) spreadVirus(i, j);
				}
			}
			
			// 3. 최대 안전 영역 크기 갱신
			ans = Math.max(ans, getSizeOfSafeZone());
			
			return;
		}
				
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1; // 벽 세우기
					makeWall(cnt + 1);
					map[i][j] = 0; // 벽 허물기
				}
			}
		}		
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		copy_map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 벽 3개 세우기
		makeWall(0);
		
		System.out.println(ans);
	}
}