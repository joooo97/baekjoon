import java.io.*;
import java.util.*;

public class Main {
	
	public static int n, m, k, ans = Integer.MIN_VALUE;
	public static int[][] board;
	public static boolean[][] check;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void recur(int px, int py, int cnt, int sum) {
		if(cnt == k) {
			// 최댓값 갱신 후 return
			ans = Math.max(ans, sum);
			return;
		}
		
		// px행부터 n-1행까지 확인할 건데 px행을 확인할 때는 py열의 칸부터 확인하고,
		// px행의 다음 행들을 확인할 때는 0열의 칸부터 확인하기 시작하면 됨
		for(int x = px; x < n; x++) {
			for(int y = (x == px) ? py : 0; y < m; y++) {
				// 현재 칸을 이미 선택했다면
				if(check[x][y]) continue;
				
				// 인접 칸 확인
				if(!isChoosable(x, y)) continue;

				// 현재 칸을 선택하는 경우
				check[x][y] = true;
				recur(x, y, cnt + 1, sum + board[x][y]);
				
				// 현재 칸을 선택하지 않는 경우
				check[x][y] = false;
			}
		}
	}
	
	public static boolean isChoosable(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			
			if(check[nx][ny]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		check = new boolean[n + 1][m + 1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, 0, 0);
		
		System.out.println(ans);
	}
	
}

