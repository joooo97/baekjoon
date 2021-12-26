import java.io.*;
import java.util.*;

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
	public static int[][] box;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static Queue<Tomato> q = new LinkedList<>();

	public static void bfs() {
		int day = 0; // 모두 익을 때까지의 최소 날짜

		while(!q.isEmpty()) {
			Tomato t = q.poll();
			int x = t.x;
			int y = t.y;
			int d = t.day;
			day = d; // 탐색 진행하며 날짜 갱신
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

				if(box[nx][ny] == 0) {
					box[nx][ny] = 1; // 토마토 익히기
					q.offer(new Tomato(nx, ny, d + 1));					
				}
			}
		}
		ans = day;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		box = new int[n][m];

		boolean ok = true; // 처음부터 모두 익었는지 확인할 변수
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());

				// 익지 않은 토마토가 주어졌는지 체크
				if(box[i][j] == 0) ok = false;
				// 익은 토마토의 위치 기록
				if(box[i][j] == 1) q.offer(new Tomato(i, j, 0));
			}
		}

		// 처음부터 모두 익어있다면 0 출력 후 종료
		if(ok) {
			System.out.println(0);
			System.exit(0);
		}

		bfs(); // 토마토 익히기
		
		// 모두 익지는 못하는 상황인지 확인
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