import java.util.*;

class Node {
	int x;
	int s; // 시간
	
	Node(int x, int s) {
		this.x = x;
		this.s = s;
	}
}

public class Main {
	public static int n, k, ans;
	public static boolean[] visited = new boolean[100001];
	public static int[] prev = new int[100001]; // 이전 위치 저장 배열
	public static StringBuilder sb = new StringBuilder();
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(n, 0));
		visited[n] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int s = now.s;
			
			if(x == k) { // 동생을 찾은 경우
				ans = s;
				return;
			}
			
			// x-1로 이동
			if(x - 1 >= 0 && !visited[x - 1]) {
				q.offer(new Node(x - 1, s + 1));
				visited[x - 1] = true;
				prev[x - 1] = x; // 이전 위치 기록
			}
			
			// x+1로 이동
			if(x + 1 <= 100000 && !visited[x + 1]) {
				q.offer(new Node(x + 1, s + 1));
				visited[x + 1] = true;
				prev[x + 1] = x;
			}
			
			// 2*x로 이동
			if(2 * x <= 100000 && !visited[2 * x]) {
				q.offer(new Node(2 * x, s + 1));
				visited[2 * x] = true;
				prev[2 * x] = x;
			}
		}
	}
	
	public static void findPath(int cur) {		
		// 현재 위치 전에 이동했던 위치가 존재하는 경우
		if(prev[cur] != -1) findPath(prev[cur]);
		
		sb.append(cur).append(" ");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		Arrays.fill(prev, -1);
		
		bfs();
		findPath(k); // 이동 경로 구하기
		
		System.out.println(ans);
		System.out.println(sb);
	}
}