import java.io.*;
import java.util.*;

// 전체 단지 수, 각 단지별 집의 개수 출력
// 1. 전체 단지 수 구하기
// - map 전체에 대해 for문을 돌며 집이 있고 방문하지 않은 경우, bfs진행 후 총 단지 수(group_cnt)늘리기
//   -> bfs를 진행한 곳은 방문여부(visited)가 true로 되어 있을 것이기 때문에 false일 때마다 bfs를 진행
// - bfs를 진행하여 연결된 집들마다 같은 단지 번호를 부여
// 2. 각 단지별 집의 개수 구하기
// - 총 단지 수 크기의 배열을 생성한 후 단지별 집의 개수 세기

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
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] group;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs(int x, int y, int group_no) {
		Queue<Node> q = new LinkedList<>();
		// 시작 노드 큐에 넣고 방문 처리
		q.offer(new Node(x, y));
		visited[x][y] = true;
		group[x][y] = group_no; // 단지 번호 부여
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			x = now.x;
			y = now.y;
			
			// 인접 노드 확인
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(map[nx][ny] == 1 && !visited[nx][ny]) {
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
					group[nx][ny] = group_no; // 단지 번호 부여
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		group = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 총 단지 수 세기, bfs 진행하여 연결된 집들마다 같은 단지 번호 부여하기
		int group_cnt = 0; // 총 단지 수
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// 집이 있고(1), 방문하지 않았다면 bfs 진행하여 단지 번호 부여
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, ++group_cnt);
				}
			}
		}

		// 각 단지에 속하는 집의 수 세기
		int[] house_cnt = new int[group_cnt + 1]; // 각 단지별 집의 수
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int group_no = group[i][j]; // 단지 번호
				
				if(group_no != 0) house_cnt[group_no] += 1;
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(group_cnt).append("\n"); // 총 단지 수
		
		// 단지별 집의 수 오름차순 정렬 (house_cnt[1] ~ house_cnt[group_cnt])
		Arrays.sort(house_cnt, 1, group_cnt + 1);
		for(int i = 1; i <= group_cnt; i++) {
			sb.append(house_cnt[i]).append("\n");
		}
		
		System.out.println(sb);
	}
}