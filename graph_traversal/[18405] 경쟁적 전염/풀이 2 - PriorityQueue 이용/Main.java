import java.io.*;
import java.util.*;

//- 바이러스 종류: 1 ~ k
//- 1초마다 모든 바이러스 증식, 번호가 낮은 것부터 증식
//-> s초 후에 (x, y)에 존재하는 바이러스의 종류 출력
//-> 바이러스가 존재하지 않는다면 0 출력

/* 
* [우선순위 큐 이용 시 풀이]
* 1. 번호가 낮은 바이러스부터 증식
*  - 처음에 주어진 바이러스들을 우선순위 큐에 담기
* 2. 1초마다 모든 바이러스가 상하좌우 방향으로 증식
*  - bfs 진행 (가까운 노드부터 우선적으로 탐색)
* 3. 바이러스 클래스 정보
*  - 바이러스 번호, 위치, 초
* 4. 바이러스 클래스 정렬 기준 
*  - 우선순위 큐는 우선순위가 높은 순서대로 큐에서 꺼내지므로 바이러스의 시간, 번호 순서대로 오름차순 정렬
*/

class Virus implements Comparable<Virus> {
	int x;
	int y;
	int no;
	int sec;
	
	public Virus(int x, int y, int no, int sec) {
		this.x = x;
		this.y = y;
		this.no = no;
		this.sec = sec;
	}
	
	@Override
	public int compareTo(Virus other) {
		// 1. 시간 순으로 오름차순 정렬
		if(this.sec != other.sec)
			return this.sec - other.sec;
		
		// 2. 시간이 같다면 바이러스 번호 순으로 오름차순 정렬 
		return this.no - other.no;
	}
}

public class Main {
	
	public static int n, k, s;
	public static int[][] map;
	public static PriorityQueue<Virus> pq = new PriorityQueue<>();
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		while(!pq.isEmpty()) {
			Virus v = pq.poll();
			int x = v.x;
			int y = v.y;
			int no = v.no;
			int sec = v.sec;
			
			if(v.sec == s) return;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(map[nx][ny] == 0) {
					pq.offer(new Virus(nx, ny, no, sec + 1));
					map[nx][ny] = no;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 처음에 주어진 바이러스들을 큐에 담기
				if(map[i][j] != 0)
					pq.offer(new Virus(i, j, map[i][j], 0));
					
			}
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		bfs();
		System.out.println(map[x-1][y-1]);
	}
}