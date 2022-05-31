import java.io.*;
import java.util.*;

class Node {
	int sc; // 화면에 있는 이모티콘 개수
	int c; // 클립보드에 있는 이모티콘 개수
	int t; // 걸린 시간
	
	Node(int sc, int c, int t) {
		this.sc = sc;
		this.c = c;
		this.t = t;
	}
}

public class Main {
 	public static int s;
 	// visited[화면][클립보드]
 	public static boolean[][] visited = new boolean[1001][1001];
 	
 	public static void bfs() {
 		Queue<Node> q = new LinkedList<>();
 		q.offer(new Node(1, 0, 0));
 		visited[1][0] = true;
 		
 		while(!q.isEmpty()) {
 			Node now = q.poll();
 			int sc = now.sc; // 화면
 			int c = now.c; // 클립보드
 			int t = now.t; // 시간
 			
 			if(sc == s) {
 				System.out.println(t);
 				return;
 			}
 			
 			// 연산 1
 			if(!visited[sc][sc]) {
 				q.offer(new Node(sc, sc, t + 1));
 				visited[sc][sc] = true;
 			}
 			
 			// 연산 2
 			if(sc + c <= 1000 && !visited[sc + c][c]) {
 				q.offer(new Node(sc + c, c, t + 1));
 				visited[sc + c][c] = true;
 			}
 			
 			// 연산 3
 			if(sc - 1 >= 0 && !visited[sc - 1][c]) {
 				q.offer(new Node(sc - 1, c, t + 1));
 				visited[sc - 1][c] = true;
 			}
 		}
 	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = Integer.parseInt(br.readLine());
		
		bfs();
	}

}