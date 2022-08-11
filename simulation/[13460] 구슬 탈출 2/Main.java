import java.io.*;
import java.util.*;

/*
 - 네 방향으로 구슬 기울이기 가능
  - 두 구슬을 동시에 움직인다.
  - 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때까지이다.
   -> 구슬은 벽을 만나거나 구멍에 빠지기 전까지 계속 움직인다. (while문)
   -> 벽으로 이동했다면 한 칸 뒤로 가준다.
  - 두 구슬은 동시에 같은 칸에 있을 수 없다.
   -> 이동 후 구슬이 같은 칸에 있게 되는 경우, 나중에 이동한 구슬의 위치를 이동시킨다.
   -> 이동한 방향의 반대 방향으로 한 칸 이동시킨다.
 - 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력
  -> 빨간 구슬은 구멍에 빠져야 하고, 파란 구슬은 빠지면 안 된다.
  -> 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력
 */

class Marble {
	int rx;
	int ry;
	int bx;
	int by;
	int cnt; // 동작 횟수
	
	Marble(int rx, int ry, int bx, int by, int cnt) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
}

public class Main {
	public static int n, m, ans = -1;
	public static char[][] board;
	public static boolean[][][][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs(int rx, int ry, int bx, int by) {
		Queue<Marble> q = new LinkedList<>();
		q.offer(new Marble(rx, ry, bx, by, 0));
		visited[rx][ry][bx][by] = true;
		
		while(!q.isEmpty()) {
			Marble cur = q.poll();
			
			// 10번 이하로 움직여 빨간 구슬을  빼낼 수 없으면 -1 출력
			if(cur.cnt > 10) {
				ans = -1;
				return;
			}
			
			// 파란 구슬이 구멍에 빠진 경우 실패이므로 큐에서 다른 노드를 꺼내 다른 경우 살펴보기
			if(board[cur.bx][cur.by] == 'O') continue;
			
			// 파란 구슬이 구멍에 빠지지 않았고, 빨간 구슬이 구멍에 빠진 경우 성공
			if(board[cur.rx][cur.ry] == 'O') {
				ans = cur.cnt;
				return;
			}
			
			// 상하좌우 각 방향으로 구슬 기울이기
			// 구슬이 한 번만 이동하는 것이 아니라 벽을 만날 때까지 계속 같은 방향으로 이동한다.
			for(int i = 0; i < 4; i++) {
				// 이동한 구슬의 위치를 저장할 변수
				int n_rx = cur.rx;
				int n_ry = cur.ry;
				int n_bx = cur.bx;
				int n_by = cur.by;
				
				// 빨간 구슬 이동
				// 구슬이 벽을 만나거나 구멍에 빠질 때까지 이동한다.
				while(true) {
					// 이동
					n_rx += dx[i];
					n_ry += dy[i];
					
					// 벽으로 이동한 경우: 한 칸 뒤(빈 칸)로 되돌아가기
					if(board[n_rx][n_ry] == '#') {
						n_rx -= dx[i];
						n_ry -= dy[i];
						break;
					}
					
					// 구멍에 빠진 경우
					if(board[n_rx][n_ry] == 'O') break;
				}
				
				// 파란 구슬 이동
				while(true) {
					n_bx += dx[i];
					n_by += dy[i];
					
					if(board[n_bx][n_by] == '#') {
						n_bx -= dx[i];
						n_by -= dy[i];
						break;
					}
					
					if(board[n_bx][n_by] == 'O') break;
				}
				
				// while문을 벗어난 후 빨간 구슬과 파란 구슬의 위치는 빈 칸이거나 구멍이다.
				// - 구슬이 구멍에 빠지거나 빈 칸인 경우 큐에 넣고 방문처리하게 된다.
				//   (여기서 성공, 실패 결정할 수도 있고 큐에서 꺼낸 후 결정할 수도 있음)
				// - 구슬이 구멍에 빠진 경우는 큐에서 꺼낸 후 확인하여 성공실패 여부를 결정한다.
				
				// 두 구슬이 같은 위치에 있는 경우 위치 조정
				// 두 구슬이 빈 칸에 있고, 위치가 동일하다면
				if(n_rx == n_bx && n_ry == n_by && board[n_rx][n_ry] != 'O') {
					// 이동한 거리 구하기
					int dist_red = Math.abs(n_rx - cur.rx) + Math.abs(n_ry - cur.ry);
					int dist_blue = Math.abs(n_bx - cur.bx) + Math.abs(n_by - cur.by);
					
					// 빨간 구슬의 이동거리가 더 긴 경우 (빨간 구슬이 나중에 도착한 것)
					if(dist_red > dist_blue) {
						// 나중에 도착한 빨간 구슬을 반대 방향으로 한 칸 이동
						n_rx -= dx[i];
						n_ry -= dy[i];
					} else { // 파란 구슬이 나중에 도착한 경우
						n_bx -= dx[i];
						n_by -= dy[i];
					}
				}
				
				// 큐에 넣고 방문 처리
				if(!visited[n_rx][n_ry][n_bx][n_by]) {
					q.offer(new Marble(n_rx, n_ry, n_bx, n_by, cur.cnt + 1));
					visited[n_rx][n_ry][n_bx][n_by] = true;
				}
				
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new char[n][m];
		visited = new boolean[n][m][n][m];
		
		// 구슬의 위치
		int rx = 0, ry = 0;
		int bx = 0, by = 0;
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				board[i][j] = line.charAt(j);
				
				if(board[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				else if(board[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}
		
		bfs(rx, ry, bx, by);
		System.out.println(ans);
	}
}
