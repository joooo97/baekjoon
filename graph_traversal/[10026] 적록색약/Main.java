import java.io.*;

public class Main {
	public static int n;
	public static char[][] map;
	public static boolean[][] visited;
	// 상하좌우
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	// 적록색약이 아닌 사람
	public static void dfsNonRG(int x, int y, char color) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			if(!visited[nx][ny] && map[nx][ny] == color)
				dfsNonRG(nx, ny, color);
		}
	}
	
	// 적록색약인 사람
	public static void dfsRG(int x, int y, char color) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
			
			// B
			if(color == 'B') {
				if(map[nx][ny] == 'B') dfsRG(nx, ny, 'B');
			} else { // R 또는 G
				if(map[nx][ny] == 'R' || map[nx][ny] == 'G') dfsRG(nx, ny, color);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		// 적록색약이 아닌 사람
		int cntNonRG = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					dfsNonRG(i, j, map[i][j]);
					cntNonRG += 1;
				}
			}
		}
		
		visited = new boolean[n][n];
		
		// 적록색약인 사람
		int cntRG = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					dfsRG(i, j, map[i][j]);
					cntRG += 1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cntNonRG).append(" ").append(cntRG);
		System.out.println(sb);
	}
	
}