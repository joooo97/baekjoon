import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	int d;
	
	Node(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
public class Main {
	public static int n, m;
	public static int[][] maze;
	public static boolean[][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
 
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 1));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int y = now.y;
			int d = now.d;
			
			if(x == n-1 && y == m-1) {
				System.out.println(d);
				System.exit(0);
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				if(maze[nx][ny] == 1 && !visited[nx][ny]) {
					q.offer(new Node(nx, ny, d + 1));
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
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