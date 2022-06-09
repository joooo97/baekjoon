import java.io.*;
import java.util.*;

// 가중치: 부수는 벽의 개수 (0, 1)
// - 가중치가 두 개 존재 -> 덱 이용 
// - 다음 이동 칸이 빈 방인 경우: 벽 안 부숨 (가중치: 0)
// - 다음 이동 칸이 벽인 경우: 벽 부숨 (가중치: 1)

class Node {
	int x;
	int y;
	int cnt; // 현재까지 부순 벽의 개수
	
	Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {
	public static int n, m;
	public static int[][] maze;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
			
	public static void bfs() {
		Deque<Node> deque = new ArrayDeque<>();
		deque.offer(new Node(0, 0, 0));
		visited[0][0] = true;
		
		while(!deque.isEmpty()) {
			Node now = deque.pollFirst();	
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
					deque.offerFirst(new Node(nx, ny, cnt));
					visited[nx][ny] = true;
				} else { // 벽인 경우
					deque.offerLast(new Node(nx, ny, cnt + 1));
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