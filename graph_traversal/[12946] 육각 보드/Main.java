import java.io.*;

/* 
 1. 색칠해야 하는 칸이 없다면 - 색의 개수: 0개
 2. 색칠해야 하는 칸이 있다면 (우선, 색은 최소 1개 이상 필요)
  2-1. 색칠해야 하는 칸들이 모두 인접하지 않는다면 - 색의 최소 개수: 1개
  2-2. 색칠해야 하는 칸 중 인접한 칸이 존재한다면
  2-2-1. 색칠해야 하는 칸들이 이분 그래프라면 - 색의 최소 개수: 2개
  2-2-2. 색칠해야 하는 칸들이 이분 그래프가 아니라면 - 색의 최소 개수: 3개
         (육각보드를 다 칠해야 하는 경우에도 최소 3개의 색으로 모두 칠할 수 있기 때문)
 -> 즉, 색칠해야 하는 칸이 존재한다면 우선 개수를 1로 초기화한 후 이분 그래프인지 확인한다.
 -> 2-1) 탐색이 모두 끝난 후 ans가 그대로 1이라면 색칠해야 하는 칸들이 모두 인접하지 않는다는 뜻
 */
public class Main {
	public static int n, ans;
	public static char[][] board; 
	// 0: 아직 칠하지 않은 칸, 1: 색1, 2: 색2
	public static int[][] color;
	public static int[] dx = {-1, -1, 0, 0, 1, 1};
	public static int[] dy = {0, 1, -1, 1, -1, 0};
	
	// (x, y) 칸을 c로 색칠
	public static void dfs(int x, int y, int c) {
		color[x][y] = c; // 색칠하기
		
		for(int i = 0; i < 6; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			if(board[nx][ny] == 'X') {
				// 색칠해야 하는 칸들끼리 인접한 경우가 존재한다는 것이므로 색은 최소 2개가 필요함
				ans = Math.max(ans, 2);
				
				// 인접 칸을 아직 칠하지 않았다면 현재 칸과 다른 색으로 칠하기
				// - 현재 칸이 1이라면 인접 칸은 2로, 현재 칸이 2라면 인접 칸은 1로 칠하기
				if(color[nx][ny] == 0) dfs(nx, ny, 3-c);
				
				// 인접한 칸이 현재 칸과 색이 같다면(이분 그래프가 아니라면)
				// - 칸이 변을 공유하는 경우(=인접한 경우) 같은 색으로 칠할 수 없다는 문제의 조건을 만족하지 않으므로
				//   색은 최소 3개가 필요하다.
				if(color[nx][ny] == c) {
					ans = 3;
					return;
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		color = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		// 색칠해야 하는 칸들이 이분 그래프인지 확인해야 하는데
		// 색칠해야 하는 칸들 중 서로 연결되지 않은 칸이 존재한다면 단절 그래프라고 볼 수 있고,
		// 단절 그래프인 경우 탐색을 딱 한 번만 진행하게 되면 모든 정점에 대해 확인할 수 없기 때문에
		// for문을 돌며 방문하지 않은 모든 정점을 시작 정점으로 탐색을 진행해야 한다. 
		// (= 인접한 칸들끼리의 그룹마다 탐색을 진행해야 한다.)
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// 색칠해야 하는 칸인데 아직 칠하지 않았다면(= 아직 방문하지 않은 경우)
				if(board[i][j] == 'X' && color[i][j] == 0) {
					// 색칠해야 하는 칸이 존재한다면 색은 최소 1개 이상
					ans = Math.max(ans, 1);
					dfs(i, j, 1); // 색1로 지정한 후 탐색 시작
				}
			}
		}
		System.out.println(ans);
	}
}