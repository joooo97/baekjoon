import java.io.*;
import java.util.*;

//- 바이러스 종류: 1 ~ k
//- 1초마다 모든 바이러스 증식, 번호가 낮은 것부터 증식
//-> s초 후에 (x, y)에 존재하는 바이러스의 종류 출력
//-> 바이러스가 존재하지 않는다면 0 출력

/* 
* [일반 큐 이용 시 풀이]
* 1. 번호가 낮은 바이러스부터 증식
*  - 처음에 주어진 바이러스들을 리스트에 담기
*  - 바이러스 번호가 낮은 순서대로 리스트 정렬 (Comparable로 정렬 기준 제공)
*  - bfs 진행 시 바이러스 번호순대로 정렬된 처음 바이러스들을 큐에 넣은 후 진행
* 2. 1초마다 모든 바이러스가 상하좌우 방향으로 증식
*  - bfs 진행 (가까운 노드부터 우선적으로 탐색)
* 3. 바이러스 클래스 정보
*  - 바이러스 번호, 위치, 초
* 4. 바이러스 클래스 정렬 기준 
*  - 일반 큐는 큐에 들어온 순서대로 큐에서 꺼내지므로 바이러스 번호에 대해서만 정렬 기준 설정 후 큐에 담아준 후 bfs 진행
*/

class Virus implements Comparable<Virus> {
	int x;
	int y;
	int no;
	int sec;

	Virus(int x, int y, int no, int sec) {
		this.x = x;
		this.y = y;
		this.no = no;
		this.sec = sec;
	}

	// 바이러스 번호 순서대로 오름차순 정렬
	@Override
	public int compareTo(Virus other) {
		return this.no - other.no;
	}
}

public class Main {
	public static int n, k, s;
	public static int[][] map;
	public static ArrayList<Virus> virusList = new ArrayList<>(); // 처음에 주어진 바이러스
	public static Queue<Virus> q = new LinkedList<>();
	public static int[] dx = {-1, 1, 0, -0};
	public static int[] dy = {0, 0, -1, 1};

	public static void bfs() {
		while(!q.isEmpty()) {
			Virus v = q.poll();
			int x = v.x;
			int y = v.y;
			int no = v.no;
			int sec = v.sec;

			if(sec == s) return;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

				// 바이러스 증식
				if(map[nx][ny] == 0) {
					q.offer(new Virus(nx, ny, no, sec + 1));
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

				// 처음에 주어진 바이러스들을 리스트에 담기
				if(map[i][j] != 0) {
					virusList.add(new Virus(i, j, map[i][j], 0));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		// 바이러스 번호가 낮은 순서대로 리스트(처음에 주어진 바이러스) 정렬
		// - Comparable 구현하여 정렬 기준 제공
		Collections.sort(virusList);

		// 처음에 주어진 바이러스들을 큐에 옮기기
		for(int i = 0; i < virusList.size(); i++) {
			q.offer(virusList.get(i));
		}

		bfs();
		System.out.println(map[x-1][y-1]);
	}

}