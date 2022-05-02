import java.io.*;
import java.util.*;

/*
 * 1. 벽 3개 세우기 (완전 탐색)
 * 2. 바이러스 퍼뜨리기 (BFS 또는 DFS)
 */

class Node {
	int x;
	int y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static int n, m, ans;
	public static int[][] map, copyMap;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void putWalls(int cnt) {
		// 1. 현재까지 세운 벽이 3개인 경우
		if(cnt == 3) {
			// 1-1. 현재 지도 복사
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			
			// 1-2. 바이러스 전파
			// - 바이러스가 있는 위치를 시작으로 bfs 탐색 진행
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(copyMap[i][j] == 2)
						spreadVirus(i, j);
				}
			}
			
			// 1-3. 안전 영역의 최대 크기 갱신
			ans = Math.max(ans, getSizeOfSafeZone());
			return;
		}
		
		// 2. 현재 세운 벽이 3개 미만인 경우 벽 세우기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) { // 빈 칸인 경우
					map[i][j] = 1; // 벽 설치
					putWalls(cnt + 1);
					map[i][j] = 0; // 벽 허물기
				}
			}
		}
	}
	
	public static void spreadVirus(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				if(copyMap[nx][ny] == 0) { // 빈 칸이면 바이러스 전파
					q.offer(new Node(nx, ny));
					copyMap[nx][ny] = 2;
				}
			}
		}
	}
	
	public static int getSizeOfSafeZone() {
		int size = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copyMap[i][j] == 0) size += 1;
			}
		}
		return size;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		copyMap = new int[n][m];
		
		// 0: 빈 칸, 1: 벽, 2: 바이러스
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		putWalls(0);
		
		System.out.println(ans);
	}
}