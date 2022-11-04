import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	public static int ans;
	public static char[][] map = new char[8][8];
	public static boolean[][] visited;
	
	// 방향 배열: 상하좌우, 대각선4개, 현재
	public static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1, 0};
	public static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1, 0};
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(7, 0));
		
		while(!q.isEmpty()) {
			// - 큐에는 1초 후(9방향 중 이동 가능한 방향), 2초 후("), 3초 후(") ..의 노드들이 계속 담기게 된다.
			//   큐: [1초(상)/1초(좌)][ 2초(상->상),2초(상->하),2초(상->우)/2초(좌->상),2초(좌->좌) ]
			// - 위와 같이 큐에 담기게 되고, 매초마다 벽의 위치가 달라지게 되므로
			//   해당 초의 노드 개수만큼(현재 큐의 사이즈이자 []안의 개수만큼) 각 노드에서 9방향을 모두 확인한 후 벽을 이동시켜야 한다.
			//   즉, 1초 노드들 확인 -> 벽 이동, 2초 노드들 확인 -> 벽 이동 ...
			// - 그렇기 때문에 초가 지날 때마다 큐의 사이즈를 확인하여 현재 큐 사이즈 만큼 다 확인했다면 벽을 이동시켜야 한다.
			int cur_q_size = q.size();
			for(int i = 0; i < cur_q_size; i++) { // 현재 큐에 담긴 노드의 개수만큼, 즉 매초마다 노드가 다 꺼내진 후에 벽을 이동시킬 수 있다.
				Node node = q.poll();
				int x = node.x;
				int y = node.y;

				// 이전 초에서 벽이 캐릭터의 위치로 내려온 경우 이동 불가하므로 다른 노드 확인
				// - 캐릭터 이동(이전 초) -> 벽 이동(이전 초) -> "캐릭터 위치 확인(현재)"
				if(map[x][y] == '#') continue;
				
				// 현재 위치가 도착 위치라면 
				if(x == 0 && y == 7) {
					ans = 1;
					return;
				}
				
				// 현재 노드에서 모든 방향 확인
				for(int d = 0; d < 9; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || map[nx][ny] == '#') continue;
					
					// 이동
					q.offer(new Node(nx, ny));
				}
				
			}
			
			// 현재 초의 노드들(각기 다른 위치의 노드들)을 모두 확인했다면 벽 이동
			moveWalls();
		}		
	}
	
	public static void moveWalls() {
		for(int i = 7; i >= 0; i--) {
			for(int j = 0; j < 8; j++) {
				if(map[i][j] == '#') {
					map[i][j] = '.';
					
					if(i != 7) map[i+1][j] = '#';
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		bfs();
		System.out.println(ans);
	}

}