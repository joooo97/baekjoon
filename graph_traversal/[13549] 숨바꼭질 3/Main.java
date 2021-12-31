import java.io.*;
import java.util.*;

class Node {
	int x;
	int t;
	
	Node(int x, int t) {
		this.x = x;
		this.t = t;
	}
}

public class Main {
	public static int n, k;
	public static boolean[] visited = new boolean[100001];
	
	public static void bfs() {
		ArrayDeque<Node> deque = new ArrayDeque<>();
		deque.offer(new Node(n, 0));
		visited[n] = true;
		
		while(!deque.isEmpty()) {
			Node now = deque.poll();
			int x = now.x;
			int t = now.t;
			
			// 동생을 찾았다면 시간 출력 후 종료
			if(x == k) {
				System.out.println(t);
				System.exit(0);
			}
			
			// 0초 후 - 순간이동을 하는 경우
			if(2 * x <= 100000 && !visited[2 * x]) {
				deque.offerFirst(new Node(2 * x, t));
				visited[2 * x] = true;
			}
			
			// 1초 후 - 걷는 경우
			if(x - 1 >= 0 && !visited[x - 1]) {
				deque.offerLast(new Node(x - 1, t + 1));
				visited[x - 1] = true;
			}
			
			if(x + 1 <= 100000 && !visited[x + 1]) {
				deque.offerLast(new Node(x + 1, t + 1));
				visited[x + 1] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		bfs();
	}
}