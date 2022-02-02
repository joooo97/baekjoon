import java.io.*;
import java.util.*;

// 1. DFS를 진행하여 순환선에 속하는 역(사이클에 포함되는 정점)찾기
//  - 사이클에 포함되는 정점은 거리를 0으로 저장 후 큐에 넣기
// 2. BFS를 진행하여 지선에 속하는 역들의 거리 저장
//  - 지선에 속하는 역들의 거리는 순환선과의 최단 거리가 됨
public class Main {
	public static int n;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static boolean[] visited;
	public static int[] dist; // 순환선과의 거리
	public static Queue<Integer> q = new LinkedList<>();

	// dfs: 순환선에 속하는 역 찾기(사이클에 포함되는 정점 찾기)
	// -2 반환: 이미 사이클에 포함되는 정점들을 모두 찾은 경우
	// -1 반환: 사이클을 찾지 못한 경우
	// 1 ~ n 반환: 사이클의 시작 인덱스
	public static int dfs(int prev, int x) {
		visited[x] = true;
		
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			// y가 이미 방문한 정점인 경우 사이클인지 확인
			if(visited[y]) {
				if(y != prev) { // y -> z -> x -> y 순으로 방문 (= 사이클을 찾은 것)
					// - 현재 정점 x는 사이클에 포함되는 정점(x의 거리는 0, 큐에 넣기)
					// - 사이클을 찾았으므로 사이클의 시작 정점 y를 리턴
					dist[x] = 0;
					q.offer(x);
					return y;
				}
				// y == prev라면 y -> x -> y 순으로 방문한 것 (= 사이클이 아님)
				// - for문을 돌며 x와 연결된 또 다른 정점 탐색
			} else { // y에 아직 방문하지 않은 경우
				// 방문 후 반환된 결과에 따라 처리
				int res = dfs(x, y);

				// 1. 이미 사이클에 포함되는 정점들을 모두 찾은 경우
				// - 다른 정점으로의 탐색을 더 이상 진행하지 않고 -2를 계속 반환 
				if(res == -2) return -2;
				// 2. 아직 사이클을 찾지 못했으므로 다른 정점으로의 탐색을 계속 진행
				if(res == -1) continue;
				
				// 3. res > 0인 경우(= 사이클의 시작 정점이 반환된 경우)
				// - 현재 정점 x는 사이클에 포함되는 정점 (x의 거리는 0, 큐에 넣기)
				dist[x] = 0;
				q.offer(x);
				
				if(x == res) return -2; // 3-1. x는 사이클의 시작 정점이므로 사이클에 포함되는 정점을 모두 찾았으므로 -2 리턴
				return res; // 3-2. 사이클에 포함되는 정점이 더 있으므로 시작 정점의 인덱스를 계속 반환
			}
		} // for
		return -1; // 사이클을 찾지 못한 경우
	}
	
	// 지선에 속하는 정점의 거리 = 순환선과의 최단 거리
	public static void bfs() {
		while(!q.isEmpty()) {
			int x = q.poll();
			
			for(int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				// 거리가 아직 기록되지 않은 정점(지선)이면 거리 기록 후 큐에 넣기
				// = 아직 방문하지 않았다면 큐에 넣기
				if(dist[y] == -1) {
					dist[y] = dist[x] + 1;
					q.offer(y);
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n + 1];
		dist = new int[n + 1];
		
		// 그래프, 거리배열 초기화
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
			dist[i] = -1;
		}
		
		// 간선 연결
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		dfs(0, 1); // 사이클(순환선) 찾기
		bfs(); // 지선들의 거리(순환선과의 거리) 구하기
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(dist[i]).append(" ");
		}
		System.out.println(sb);
	}
}