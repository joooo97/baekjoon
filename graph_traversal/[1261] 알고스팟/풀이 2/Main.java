import java.io.*;
import java.util.*;

// 가중치: 부수는 벽의 개수 (0, 1)
// - 가중치가 두 개 존재 -> 덱 이용
// - 다음 이동 칸이 빈 방인 경우: 벽 안 부숨 (가중치: 0)
// - 다음 이동 칸이 벽인 경우: 벽 부숨 (가중치: 1)

class Node implements Comparable<Node> {
	int x;
	int y;
	int cnt;
	
	Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
	@Override
	public int compareTo(Node other) {
		// 현재까지 벽 부순 횟수에 대해 오름차순 정렬
		return this.cnt - other.cnt;
	}
}

public class Main {
	public static int n, m;
	public static int[][] maze;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
			
	public static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			
			if(x == n-1 && y == m-1) {
				System.out.println(cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
				
				if(maze[nx][ny] == 0) { // 빈 방인 경우
					pq.offer(new Node(nx, ny, cnt));
					visited[nx][ny] = true;
				} else { // 벽인 경우
					pq.offer(new Node(nx, ny, cnt + 1));
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		maze = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				maze[i][j] = line.charAt(j) - '0';
			}
		}
		
		bfs();
	}

}