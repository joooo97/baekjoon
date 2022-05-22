import java.io.*;
import java.util.*;

class Node {
	int x; // 수빈이의 위치
	int t; // 걸린 시간
	
	Node(int x, int t) {
		this.x = x;
		this.t = t;
	}
}

public class Main {
	public static int n, k;
	public static boolean[] visited = new boolean[100001];
	public static int[] prev = new int[100001]; // 이전 위치
	public static StringBuilder sb = new StringBuilder();
	
	public static void bfs(int x) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, 0));
		visited[x] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			x = now.x;
			int t = now.t;

			// 동생을 찾은 경우
			if(x == k) {
				sb.append(t).append("\n");
				return;
			}
			
			// 걷는 경우 (x-1로 이동)
			if(x - 1 >= 0 && !visited[x - 1]) {
				q.offer(new Node(x - 1, t + 1));
				visited[x - 1] = true;
				prev[x - 1] = x; // 이전 위치 기록
			}
			
			// 걷는 경우 (x+1로 이동)
			if(x + 1 <= 100000 && !visited[x + 1]) {
				q.offer(new Node(x + 1, t + 1));
				visited[x + 1] = true;
				prev[x + 1] = x;
			}
			
			// 순간이동 (x*2 로 이동)
			if(x * 2 <= 100000 && !visited[x * 2]) {
				q.offer(new Node(x * 2, t + 1));
				visited[x * 2] = true;
				prev[x * 2] = x;
			}
		}
	}
	
	public static void findPath(int start, int end) {
		if(start != end) findPath(start, prev[end]);
		
		sb.append(end).append(" ");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 수빈 위치
		k = Integer.parseInt(st.nextToken()); // 동생 위치
		Arrays.fill(prev, -1);
		
		bfs(n); // 수빈이가 동생을 찾는 가장 빠른 시간 구하기
		findPath(n, k); // 이동 경로 구하기
		
		System.out.println(sb);
	}
	
}