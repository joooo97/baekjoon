import java.io.*;
import java.util.*;

/*
 * 안전 영역 최대 개수 구하기
 1. 최대 높이 구하기
 2. 차례대로 물에 잠기는 높이(비의 양)로 지정하여 탐색 진행
  1) 물에 잠기는 높이: 0 ~ 입력받은 높이 중 최대 높이
   - 0부터 시작하는 이유: 아무 지역도 물에 잠기지 않을 수도 있다는 조건 때문
    -> 각 지점의 높이는 1이상 100이하, 아무 지역도 물에 잠기지 않으려면 물에 잠기는 높이는 0이어야 함
  2) 안전 영역 개수: 탐색(bfs/dfs) 진행 횟수
   - 물에 잠기는 높이가 달라질 때마다 안전 영역 최대 개수 갱신
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
	public static int n, ans;
	public static int[][] map;
	public static boolean[][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs(int x, int y, int h) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(!visited[nx][ny] && map[nx][ny] > h) {
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static void findSafeZone(int height) {
		// 물 높이 별로 map의 모든 값에 대해 탐색을 진행하기 때문에 방문 여부 배열 초기화해야 함
		visited = new boolean[n][n];
		int safeAreaCnt = 0; // 안전 영역 개수
					
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// 방문하지 않았고, 물에 잠기는 높이가 아니라면 탐색 진행
				if(!visited[i][j] && map[i][j] > height) {
					bfs(i, j, height);
					safeAreaCnt += 1;
				}
			}
		}
		ans = Math.max(ans, safeAreaCnt); // 안전 영역의 최대 개수 갱신
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		StringTokenizer st;
		int maxHeight = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		// 아무 지역도 물에 잠기지 않을 수 있다 -> 물에 잠기는 높이를 0부터 시작
		for(int h = 0; h <= maxHeight; h++) // 물에 잠기는 높이
			findSafeZone(h); // 안전 영역 구하기
		
		System.out.println(ans);
	}
}