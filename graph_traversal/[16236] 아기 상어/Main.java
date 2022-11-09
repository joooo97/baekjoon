import java.io.*;
import java.util.*;

class Fish {
	int x;
	int y;
	int d; // 상어와의 거리
	
	Fish(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

// BFS 탐색 위치
class Node {
	int x;
	int y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	public static int n;
	public static int[][] map, dist;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};

	// !물고기를 먹고난 후 상어의 크기를 bfs에서 갱신할 필요 없음
	// -> 물고기를 현재 먹는 것이 아니고, 가장 가까운 물고기만 구할 것이기 때문
	// -> 즉 '상어 -> 물고기1 -> 물고기2'의 경로라면
	//    가장 가까운 물고기 1을 구하기 위해 bfs를 진행하는 것이기 때문에
	//    상어의 크기는 알 필요 없고 상어와 각 물고기 간의 거리만 구하면 된다.
	
	// 상어의 매 위치마다 bfs 수행하여 가장 가까운 물고기 구하기
	public static Fish bfs(int sx, int sy, int shark_size) {
		Queue<Node> q = new LinkedList<>();
		List<Fish> fish_list = new ArrayList<>(); // 먹을 수 있는 물고기 리스트
		// 거리 및 방문 여부 배열 초기화
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], -1);
		}
		
		q.offer(new Node(sx, sy));
		dist[sx][sy] = 0;
		
		// 1. 상어와 각 물고기들 간의 거리 구하기, 먹을 수 있는 물고기들 구하기
		while(!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n || dist[nx][ny] != -1) continue;
				
				// 상어 < 물고기 -> 이동 X
				if(shark_size < map[nx][ny]) continue;
				
				// 빈 칸(0) -> 이동 O
				// 상어 == 물고기(0 아님) -> 이동 O
				// 상어 > 물고기(0 아님) -> 이동 O, 먹을 수 O
				
				// 먹을 수 있는 물고기 리스트에 담기
				if(map[nx][ny] != 0 && shark_size > map[nx][ny])
					fish_list.add(new Fish(nx, ny, dist[x][y]+1));
				
				// 이동
				q.offer(new Node(nx, ny));
				dist[nx][ny] = dist[x][y] + 1; // 방문 처리 및 거리 기록
			}
		}
		
		// 2. 가장 가까운 거리의 물고기 반환
		if(fish_list.isEmpty()) return null;
		
		Fish fish = fish_list.get(0);
		for(Fish cur : fish_list) {
			if(fish.d > cur.d) {
				fish = cur;
			}
			else if(fish.d == cur.d) { // 두 물고기의 거리가 같다면
				if(fish.x > cur.x) fish = cur; // 더 위에 있는 물고기
				else if(fish.x == cur.x && fish.y > cur.y) fish = cur; // 더 왼쪽에 있는 물고기
			}
		}
		
		return fish;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dist = new int[n][n];
		
		int shark_x = 0, shark_y = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					shark_x = i;
					shark_y = j;
					map[i][j] = 0;
				}
			}
		}
		
		int ans_time = 0;
		int shark_size = 2;
		int cnt_fish_eaten = 0; // 먹은 물고기 수
		while(true) {
			Fish f = bfs(shark_x, shark_y, shark_size); // 가장 가까운 물고기
			
			if(f == null) break;
			
			// 물고기를 먹는 경우
			map[f.x][f.y] = 0; // 빈 칸으로 만들기
			cnt_fish_eaten += 1;
			ans_time += f.d; // 먹은 물고기 거리만큼 걸린 시간 증가
			// 상어 이동
			shark_x = f.x;
			shark_y = f.y;
			
			if(cnt_fish_eaten == shark_size) {
				shark_size += 1;
				cnt_fish_eaten = 0;
			}
		}

		System.out.println(ans_time);
	}

}