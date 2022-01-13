import java.io.*;
import java.util.*;

public class Main {

	public static int n, m;
	public static int[] board = new int[101]; // 사다리, 뱀 정보
	public static int[] d = new int[101]; // 주사위를 굴린 횟수
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		d[1] = 0;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			for(int i = 1; i <= 6; i++) { // 주사위 굴리기
				int y = x + i;
				
				if(y > 100) continue;
				
				// 다음 이동 칸에 뱀 또는 사다리가 존재하는 경우
				if(board[y] != 0) y = board[y];
				
				// 다음 이동 칸이 방문하지 않은 칸이라면
				if(d[y] == -1) {
					q.offer(y);
					d[y] = d[x] + 1;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Arrays.fill(d, -1);
		
		// 사다리, 뱀 정보 입력
		for(int i = 0; i < n+m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;
		}
		
		bfs();
		System.out.println(d[100]);
	}
}