import java.io.*;
import java.util.*;

public class Main {
	public static int n, ans;
	public static int[][] map;
	public static boolean[][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void findSafeZone(int height) {
		visited = new boolean[n][n];
		int safeZoneCnt = 0;
				
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j] && map[i][j] > height) {
					dfs(i, j, height);
					safeZoneCnt += 1;
				}
			}
		}
		ans = Math.max(ans,  safeZoneCnt);
	}
	public static void dfs(int x, int y, int h) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			if(!visited[nx][ny] && map[nx][ny] > h)
				dfs(nx, ny, h);
		}
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
		
		for(int h = 0; h <= maxHeight; h++)
			findSafeZone(h);
		
		System.out.println(ans);
	}
}