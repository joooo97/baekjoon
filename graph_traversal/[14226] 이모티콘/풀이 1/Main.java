import java.io.*;
import java.util.*;

class Node {
	int sc; // 화면 이모티콘 개수
	int c; // 클립보드 이모티콘 개수
	
	Node(int sc, int c) {
		this.sc = sc;
		this.c = c;
	}
}

//최소 비용 = 가중치 = 시간
//가중치인 시간이 모두 1이므로 BFS를 이용하여 문제 풀이
public class Main {
	public static int s;
	// time[스크린의 이모티콘 수][클립보드의 이모티콘 수]
	public static int[][] time = new int[1001][1001];
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(1, 0));
//		time[1][0] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int sc = now.sc;
			int c = now.c;
			int t = time[sc][c];
			
			// 화면에 s개의 이모티콘이 만들어졌다면 출력 후 종료
			if(sc == s) {
				System.out.println(t);
				System.exit(0);
			}
			
			// 연산 진행
			// 1. 화면에 있는 이모티콘을 클립보드에 저장: (sc, c) -> (sc, sc)
			if(time[sc][sc] == 0) {
				q.offer(new Node(sc, sc));
				time[sc][sc] = t + 1;
			}
			
			// 2. 클립보드에 있는 이모티콘을 화면에 붙여넣기: (sc, c) -> (sc + c, c)
			if(sc + c <= 1000 && time[sc + c][c] == 0) {
				q.offer(new Node(sc + c, c));
				time[sc + c][c] = t + 1;
			}
			
			// 3. 화면에 있는 이모티콘 하나 삭제: (sc, c) -> (sc - 1, c)
			if(sc - 1 >= 0 && time[sc - 1][c] == 0) {
				q.offer(new Node(sc - 1, c));
				time[sc - 1][c] = t + 1;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = Integer.parseInt(br.readLine());
		
		bfs();
	}
}