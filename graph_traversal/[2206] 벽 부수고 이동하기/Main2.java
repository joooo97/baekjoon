import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	int cnt; // 현재까지 부순 벽의 개수
	int d; // 이동 거리
	
	Node(int x, int y, int cnt, int d) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.d = d;
	}
}

public class Main2 {
	public static int n, m, ans;
	public static int[][] map;
	// [x][y][0]: 현재까지 벽 부순 횟수는 0, [x][y][1]: 현재까지 벽 부순 횟수는 1
	public static boolean[][][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0, 1));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.x == n-1 && now.y == m-1) {
				ans = now.d;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				// 다음 칸이 벽이 아닌 경우: 현재까지 벽 부순 횟수에 상관 없이 이동
				if(map[nx][ny] == 0 && !visited[nx][ny][now.cnt]) { // 다음 칸이 벽이 아니고 아직 방문하지 않았다면
					q.offer(new Node(nx, ny, now.cnt, now.d + 1));
					visited[nx][ny][now.cnt] = true;
				}
				
				// 다음 칸이 벽인 경우: 현재까지 벽 부순 횟수가 0일 때만 벽 부수고 이동 가능
				// - 벽이면 벽을 부수지 않고는 이동 불가능하므로, 다음 이동 칸이 벽이고 벽을 부수지 않는 경우는 아예 방문 불가
				if(map[nx][ny] == 1 && now.cnt == 0 && !visited[nx][ny][1]) {
					q.offer(new Node(nx, ny, 1, now.d + 1));
					visited[nx][ny][1] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m][2];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(ans == 0 ? -1 : ans);
	}
}