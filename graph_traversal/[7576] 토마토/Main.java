import java.io.*;
import java.util.*;

// 왜 BFS를 이용하여 최단 경로를 구하는 문제인가?
// - 인접한 토마토가 익게 되고, 토마토가 모두 익는 최소 일 수를 구해야 함
// - 익은 토마토들의 위치를 모두 큐에 먼저 담은 후 BFS를 진행

class Tomato {
	int x;
	int y;
	int day;
	
	Tomato(int x, int y, int day) {
		this.x = x;
		this.y = y;
		this.day = day;
	}
}

public class Main {
	public static int n, m, ans;
	public static int[][] box; // 1:익음, 0:익지 않음, -1:토마토 x
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static Queue<Tomato> q = new LinkedList<>();
	
	
	public static void bfs() {		
		while(!q.isEmpty()) {
			Tomato t = q.poll();
			int x = t.x;
			int y = t.y;
			int day = t.day;
			// 익은 토마토가 큐에서 꺼내지므로 최소 일 수 갱신
			ans = Math.max(ans, day); // 최소 일 수 갱신
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				if(box[nx][ny] == 0) { // 인접한 토마토가 익지 않은 토마토인 경우
					box[nx][ny] = 1; // 익히기
					q.offer(new Tomato(nx, ny, day + 1));
				}
			}
		}		
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		box = new int[n][m];
		
		boolean check = true; // 처음부터 모두 익었는지 확인할 변수
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				
				// 익지 않은 토마토가 주어졌는지 체크
				if(box[i][j] == 0) check = false;
				// 익은 토마토라면 큐에 넣기
				if(box[i][j] == 1) q.offer(new Tomato(i, j, 0));
			}
		}
		
		// 모든 토마토가 처음부터 익어있는 상태
		if(check) {
			System.out.println(0);
			System.exit(0);
		}
		
		bfs(); // 토마토 익히기

		// 토마토가 모두 익지는 못하는 상황인지 확인
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(box[i][j] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		
		System.out.println(ans);
	}
}