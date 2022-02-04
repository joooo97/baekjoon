import java.util.*;
import java.io.*;

public class Main {
	public static int n, m, k;
	public static int[][] map;
	// visited[x][y][k]: k개의 벽을 부순 상태로 (x, y)칸에 도착한 경우
	public static boolean[][][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0, 1));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int dist = now.dist;
			
			if(x == n-1 && y == m-1) {
				return dist;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				// 다음 칸이 빈 칸이고 아직 방문하지 않았다면 이동
				if(map[nx][ny] == 0 && !visited[nx][ny][cnt]) {
					q.offer(new Node(nx, ny, cnt, dist + 1));
					visited[nx][ny][cnt] = true;
				}
				// 다음 칸이 벽이고, 현재 부순 벽의 개수가 k개 미만이고, 방문하지 않았다면 벽 부수고 이동
				else if(map[nx][ny] == 1 && cnt < k && !visited[nx][ny][cnt + 1]) {
					q.offer(new Node(nx, ny, cnt + 1, dist + 1));
					visited[nx][ny][cnt + 1] = true;
				}
				
			}
		}
		return -1;
	}
 
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m][k+1];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}
}

class Node {
	int x;
	int y;
	int cnt; // 벽 부순 횟수
	int dist; // 현재까지 이동 거리

	Node(int x, int y, int cnt, int dist) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dist = dist;
	}
}