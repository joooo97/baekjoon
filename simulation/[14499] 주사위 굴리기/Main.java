import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, x, y;
	public static int[][] map;
	public static int[] dice = new int[6];
	public static StringBuilder sb = new StringBuilder();

	// X 동 서 북 남
	public static int[] dx = {0, 0, 0, -1, 1};
	public static int[] dy = {0, 1, -1, 0, 0};

	public static void move(int d) {

		// 굴린 방향에 따른 주사위 값 변경
		if(d == 1) { // 동
			int tmp = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = dice[0];
			dice[0] = tmp;
		} else if(d == 2) { // 서
			int tmp = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = tmp;			
		} else if(d == 3) { // 북
			int tmp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = tmp;			
		} else { // 남
			int tmp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = dice[0];
			dice[0] = tmp;			
		}

		// 주사위 바닥면의 수가 칸에 복사
		if(map[x][y] == 0) {
			map[x][y] = dice[5];
		} else {
			// 칸의 수를 주사위 바닥으로 복사, 칸의 수는 0이 된다.
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		// 지도 입력
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 이동 명령
		st = new StringTokenizer(br.readLine());
		while(k-- > 0) {
			int d = Integer.parseInt(st.nextToken());

			int nx = x + dx[d];
			int ny = y + dy[d];

			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

			// 주사위 이동
			x = nx;
			y = ny;
			move(d);
			
			// 주사위 상단의 값 출력
			sb.append(dice[0]).append("\n");
		}

		System.out.println(sb);
	}

}