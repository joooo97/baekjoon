import java.io.*;
import java.util.*;

public class Main {
	public static int n, m;
	public static char[][] board;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y, int prev_x, int prev_y) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 게임판의 범위를 넘어가면 무시
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			// 색이 다르다면 무시
			if(board[x][y] != board[nx][ny]) continue;
			// 다음 이동 칸이 이전 이동 칸이라면 사이클이 될 수 없으므로 무시
			if(nx == prev_x && ny == prev_y) continue;
			
			// 사이클을 찾은 경우 (다음 이동 칸이 이미 방문한 칸이라면)
			if(visited[nx][ny]) {
				System.out.println("Yes");
				System.exit(0);
			}
			dfs(nx, ny, x, y);
		}		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new char[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				// 연결된 그래프(같은 색이 인접한 그래프)마다 탐색하기 위해 방문 여부 확인
				if(!visited[i][j]) dfs(i, j, -1, -1);
			}
		}
		System.out.println("No");
	}
}