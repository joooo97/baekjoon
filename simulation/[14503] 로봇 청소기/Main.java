import java.util.*;
import java.io.*;

public class Main {
	public static int n, m, x, y, d;
	public static int[][] map; // 0: 빈 칸, 1: 벽
	public static boolean[][] clean_yn;
	// 북(0), 동(1), 남(2), 서(3)
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		clean_yn = new boolean[n][m];

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		clean_yn[x][y] = true; // 현재 위치 청소
		int cnt_clean = 1; // 청소 횟수
		int cnt_turn = 0; // 회전 횟수

		// 로봇 청소기 작동
		while(true) {
			// 2. 현재 방향의 왼쪽 방향부터 인접 칸 탐색
			d = (d-1 == -1) ? 3 : d-1; // 다음 방향
			int nx = x + dx[d];
			int ny = y + dy[d];
			cnt_turn += 1;

			// ! 지도의 첫 행, 마지막 행, 첫 열, 마지막 열에 있는 모든 칸은 벽이므로
			//   다음 위치는 맵의 범위를 벗어나지 않을 것이므로 if문으로 범위 확인 필요 없음
//			if(nx >= 0 && nx < n && ny >= 0 && ny < m) {}

			// 2-a. 청소하지 않은 공간 존재 - 다음 방향으로 전진 후 1번부터 진행
			if(map[nx][ny] == 0 && !clean_yn[nx][ny]) {
				x = nx;
				y = ny;
				clean_yn[x][y] = true; // 청소
				cnt_clean += 1;
				cnt_turn = 0;
				continue;
			}

			// 네 방향 모두 청소가 이미 되어있거나 벽인 경우
			if(cnt_turn == 4) {
				nx = x - dx[d];
				ny = y - dy[d];

				// 2-c. 뒤쪽 방향이 빈 칸인 경우 - 바라보는 방향을 유지한 채로 한 칸 후진 후 2번으로 돌아감
				if(map[nx][ny] == 0) {
					x = nx;
					y = ny;
					cnt_turn = 0;
				} else {
					// 2-d. 뒤쪽 방향도 벽인 경우 - 작동 멈춤
					break;
				}
			}				
		} // while문

		System.out.println(cnt_clean);
	}
}