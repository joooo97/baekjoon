import java.io.*;
import java.util.*;

// 문제에서 주어진 사이클의 정의
// 1. 모든 점의 색은 같다
// - 같은 색의 그룹별로 탐색 진행하여 사이클의 유무 확인해야 함
//  -> for문을 돌며 방문하지 않은 모든 정점에 대해 dfs탐색
// 2. 사이클에 포함되는 정점은 4개 이상이다.
// - 상하좌우로만 이동 가능하기 때문에 사이클을 찾은 경우 정점은 최소 4개 이상이 됨
//  -> 굳이 사이클에 포함되는 각 정점들과 총 개수를 알 필요가 없을 듯
//  -> 지하철 2호선 문제같이 int로 사이클 시작 정점을 반환하며 현재 정점과 비교하지 않아도 될 듯
public class Main {
	public static int n, m;
	public static char[][] color;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};

	public static void dfs(int px, int py, int x, int y) {
		visited[x][y] = true;

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

			// 색이 다르면 무시
			if(color[nx][ny] != color[x][y]) continue;

			// 다음 이동 정점을 이미 방문했다면 사이클인지 확인
			if(visited[nx][ny]) {
				// 다음으로 이동할 정점이 이전 방문 정점과 다르다면 사이클을 찾은 것
				if(nx != px || ny != py) {
					System.out.println("Yes");
					System.exit(0);
				}
			} else { // 다음 이동 정점에 방문하지 않은 경우 방문
				dfs(x, y, nx, ny);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		color = new char[n][m];
		visited = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			color[i] = br.readLine().toCharArray();
		}

		// 같은 색의 그룹별로 탐색 진행하여 사이클의 유무 확인
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j]) dfs(-1, -1, i, j);
			}
		}
		System.out.println("No");
	}
}
