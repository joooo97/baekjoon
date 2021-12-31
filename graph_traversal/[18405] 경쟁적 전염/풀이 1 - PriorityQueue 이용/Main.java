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
	
	// 우선 순위 큐는 큐에서 노드를 꺼낼 때마다 정렬 기준대로 꺼내지므로 시간, 번호 순으로 오름차순 정렬
	@Override
	public int compareTo(Virus other) {
		// 1. 시간 순으로 오름차순 정렬
		if(this.t != other.t)
			return this.t - other.t;
		
		// 2. 시간이 같다면 바이러스 번호 순으로 오름차순 정렬
		return this.no - other.no;
	}
}

public class Main {
	public static int n, k, s, x, y;
	public static int[][] board;
	public static PriorityQueue<Virus> pq = new PriorityQueue<>();
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		while(!pq.isEmpty()) {
			Virus v = pq.poll();
			
			if(v.t == s) return;
			
			for(int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(board[nx][ny] == 0) {
					pq.offer(new Virus(nx, ny, v.no, v.t + 1));
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
				
				// 0초 상태의 바이러스 위치 기록 (큐에 담기)
				if(board[i][j] != 0)
					pq.offer(new Virus(i, j, board[i][j], 0));
			}
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