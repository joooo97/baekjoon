import java.io.*;
import java.util.*;

/*
 * - 1초마다 상,하,좌,우로 증식
 * - 낮은 종류의 바이러스부터 증식 (종류: 1 ~ k)
 * - s초 후에 (x, y)에 존재하는 바이러스의 종류 출력
 *   -> 존재 x: 0 출력
 *   -> 시험관 가장 왼쪽 위: (1, 1)
 */

class Virus implements Comparable<Virus> {
	int x;
	int y;
	int no; // 바이러스 종류
	int t; // 시간
	
	Virus(int x, int y, int no, int t) {
		this.x = x;
		this.y = y;
		this.no = no;
		this.t = t;
	}
	
	// 0초일 때의 바이러스들만 먼저 큐에 담아주고 bfs를 진행할 것이기 때문에
	// 바이러스 번호에 대해서만 오름차순 정렬하면 됨
	@Override
	public int compareTo(Virus other) {
		return this.no - other.no;
	}
}

public class Main {
	public static int n, k, s, x, y;
	public static int[][] board;
	public static ArrayList<Virus> list = new ArrayList<Virus>();
	public static Queue<Virus> q = new LinkedList<>();
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		
		while(!q.isEmpty()) {
			Virus v = q.poll();
			
			if(v.t == s) return;
			
			for(int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(board[nx][ny] == 0) {
					q.offer(new Virus(nx, ny, v.no, v.t + 1));
					board[nx][ny] = v.no;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		board = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				// 0초 상태의 바이러스 위치 기록
				if(board[i][j] != 0)
					list.add(new Virus(i, j, board[i][j], 0));
			}
		}
		
		// 바이러스 번호 순서대로 오름차순 정렬 후 큐에 담아주기
		Collections.sort(list);
		
		for(int i = 0; i < list.size(); i++) {
			Virus v = list.get(i);
			q.offer(new Virus(v.x, v.y, v.no, v.t));
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		bfs();
				
		// 바이러스 종류 출력
		System.out.println(board[x-1][y-1]);
	}
}