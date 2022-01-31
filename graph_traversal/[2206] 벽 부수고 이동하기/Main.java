import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	int cnt; // 현재까지 벽 부순 횟수 (0 또는 1만 가능)
	int dist; // 현재까지의 이동 거리

	Node(int x, int y, int cnt, int dist) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dist = dist;
	}
}

public class Main {
	public static int n, m;
	public static int[][] map;
	public static boolean[][][] visited; // [x좌표][y좌표][(x, y)에 도달했을 때 벽 부순 횟수 - 0 또는 1]
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};

	public static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0, 1));
		visited[0][0][0] = true;

		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int dist = now.dist;

			// (n, m) 칸에 도달했다면 종료
			// - 벽을 부수고 (n, m)에 도달 했을 때와 부수지 않고 (n, m)에 도달하지 않았을 때를 따로 비교하지 않아도 되는 이유는
			//   bfs 탐색 중 큐에서 꺼내지는 순서가 곧 방문 순서가 되기 때문에 먼저 (n, m)에 도착했다는 뜻(= 최단 경로라는 뜻)
			if(x == n-1 && y == m-1) {
				return dist;
			}

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

				// 다음 칸이 빈 칸이라면
				// - 현재까지의 벽 부순 횟수와 상관 없이 이동 가능
				// - 벽 부순 횟수는 그대로, 이동 거리는 1 증가
				if(map[nx][ny] == 0) {
					if(!visited[nx][ny][cnt]) {
						q.offer(new Node(nx, ny, cnt, dist + 1));
						visited[nx][ny][cnt] = true;
					}
				} else { // 다음 칸이 벽이라면
					// - 현재까지 벽 부순 횟수가 0일 때만 부수고 이동할 수 있음
					// - 벽 부순 횟수는 1로, 이동 거리는 1 증가
					if(cnt == 0 && !visited[nx][ny][1]) {
						q.offer(new Node(nx, ny, 1, dist + 1));
						visited[nx][ny][1] = true;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m][2];

		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}
}