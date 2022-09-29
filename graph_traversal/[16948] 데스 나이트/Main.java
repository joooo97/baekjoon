import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	public static int n, r1, c1, r2, c2;
	public static int[][] d;
	
	public static int[] dx = {-2, -2, 0, 0, 2, 2};
	public static int[] dy = {-1, 1, -2, 2, -1, 1};
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r1, c1));
		d[r1][c1] = 0;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(d[nx][ny] == -1) {
					q.offer(new Node(nx, ny));
					d[nx][ny] = d[x][y] + 1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		d = new int[n][n];

		for(int i = 0; i < n; i++) {
			Arrays.fill(d[i], -1);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		
		bfs();
		
		System.out.println(d[r2][c2]);
	}

}