import java.io.*;
import java.util.*;

// 구하는 문제: 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
// - 정점: 수빈이와 동생의 위치
// - 가중치: 시간이며, 주어진 가중치는 0 또는 1로 다르다.
//  -> 가중치가 다른 문제에서의 최단 경로를 구하는 문제이다.
//  -> 가중치가 작은 것부터 먼저 처리해야 한다.(큐에 넣어줘야 함)

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
		Deque<Node> deque = new ArrayDeque<>();
		deque.offer(new Node(n, 0));
		visited[n] = true;
		
		while(!deque.isEmpty()) {
			Node now = deque.pollFirst(); // poll();
			int x = now.x;
			int t = now.t;
			
			if(x == k) {
				System.out.println(t);
				return;
			}
			
			// 순간 이동: 0초 후 2*X로 이동
			if(2*x <= 100000 && !visited[2*x]) {
				deque.offerFirst(new Node(2*x, t));
				visited[2*x] = true;
			}
			
			// 걷는 경우: 1초 후 X-1 또는 X+1로 이동
			if(x-1 >= 0 && !visited[x-1]) {
				deque.offerLast(new Node(x-1, t+1));
				visited[x-1] = true;
			}
			
			if(x+1 <= 100000 && !visited[x+1]) {
				deque.offerLast(new Node(x+1, t+1));
				visited[x+1] = true;
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