import java.util.*;
import java.io.*;

// 최소 비용 = 가중치 = 시간
// 가중치인 시간이 모두 1이므로 BFS를 이용하여 문제 풀이
public class Main {
	public static int n, k, ans;
	public static int[] time = new int[100001]; // 시간 기록 및 방문 여부 확인
	public static int[] prev = new int[100001]; // 이전 위치 저장
	public static StringBuilder sb = new StringBuilder();
	
	public static void findPath(int n, int k) {		
		if(n != k) findPath(n, prev[k]);
		
		sb.append(k).append(" ");
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
//		time[n] = 0;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			if(x == k) { // 동생을 찾은 경우
				ans = time[x];
				return;
			}
			
			// x-1로 이동
			if(x - 1 >= 0 && time[x - 1] == 0) {
				q.offer(x - 1);
				time[x - 1] = time[x] + 1;
				prev[x - 1] = x; // 이전 위치 기록
			}
			
			// x+1로 이동
			if(x + 1 <= 100000 && time[x + 1] == 0) {
				q.offer(x + 1);
				time[x + 1] = time[x] + 1;
				prev[x + 1] = x;
			}
			
			// 2*x로 이동
			if(2 * x <= 100000 && time[2 * x] == 0) {
				q.offer(2 * x);
				time[2 * x] = time[x] + 1;
				prev[2 * x] = x;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		bfs();
		findPath(n, k); // 이동 경로 구하기
		
		System.out.println(ans);
		System.out.println(sb);
	}
}