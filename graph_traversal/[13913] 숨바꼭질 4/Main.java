import java.io.*;
import java.util.*;

class Node {
	int x;
	int sec;
	
	Node(int x, int sec) {
		this.x = x;
		this.sec = sec;
	}
}

public class Main {
	public static int n, k;
	public static boolean[] visited = new boolean[100001];
	public static int[] prev = new int[100001]; // 이전 위치 기록
	public static StringBuilder sb = new StringBuilder();
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(n, 0));
		visited[n] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x; // 현재 위치
			int s = cur.sec; // 걸린 시간
			
			if(x == k) {
				sb.append(s).append("\n");
				return;
			}
			
			// 걷는 경우
			if(x-1 >= 0 && !visited[x-1]) {
				q.offer(new Node(x-1, s+1));
				visited[x-1] = true;
				prev[x-1] = x;
			}
			
			if(x+1 <= 100000 && !visited[x+1]) {
				q.offer(new Node(x+1, s+1));
				visited[x+1] = true;
				prev[x+1] = x;
			}
			
			// 순간이동
			if(x*2 <= 100000 && !visited[x*2]) {
				q.offer(new Node(x*2, s+1));
				visited[x*2] = true;
				prev[x*2] = x;
			}
		}
	}

	// x에서 y까지의 경로 출력
	public static void printPath(int x, int y) {
		if(x != y) 
			printPath(x, prev[y]);
		
		sb.append(y).append(" ");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		bfs();
		printPath(n, k);
		System.out.println(sb);
	}
}
