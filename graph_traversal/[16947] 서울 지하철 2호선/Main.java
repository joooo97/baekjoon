import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static boolean[] visited;
	public static Queue<Integer> q = new LinkedList<>(); // 순환선에 포함되는 정점을 담을 큐
	public static int[] dist; // 순환선과의 거리
	
	public static int dfs(int x, int prev) {
		visited[x] = true;
		
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			// 1. 다음 정점을 이미 방문한 경우
			if(visited[y]) {
				// 1-1. 다음 정점과 이전 방문 정점이 다른 경우 - 사이클을 찾은 것
				if(y != prev) {
					// 현재 정점은 사이클에 포함됨
					q.offer(x);
					dist[x] = 0;
					return y; // 다음 정점을 사이클의 시작 정점으로 정하고 반환
				}
				// 1-2. 다음 정점과 이전 방문 정점이 같은 경우 - 사이클이 아님
				//  -> 다음 for문 진행하여 사이클을 찾아야 함
			} else { // 2. 다음 정점을 아직 방문하지 않은 경우
				int res = dfs(y, x); // 다음 정점 방문
				// res: -1 또는 0 또는 사이클에 포함되는 정점의 인덱스
				
				// 2-1. 사이클에 포함되는 정점들을 이미 모두 구한 경우
				if(res == -1) return -1; // 더 이상 구하지 않아도 됨
				
				// 2-2. 다음 정점 y는 사이클에 포함되지 않는 정점이므로 다른 정점을 확인해야 함
				if(res == 0) continue;
				
				// 2-3. 현재 정점이 사이클에 포함되는 경우
				q.offer(x);
				dist[x] = 0;
				
				// 2-3-1. 현재 정점이 사이클에 포함되는 마지막 정점인 경우
				if(res == x) return -1; // 더 이상 구하지 않아도 됨
				
				// 2-3-2. 사이클에 포함되는 정점이 더 존재하는 경우
				return res; // 사이클에 포함되는 또 다른 정점 구하기
			}
			
		}
		return 0; // 사이클에 포함되지 않는 정점
	}
	
	public static void bfs() {
		while(!q.isEmpty()) {
			int x = q.poll();
			
			for(int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				if(dist[y] == -1) {
					q.offer(y);
					dist[y] = dist[x] + 1;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		dist = new int[n+1];
		Arrays.fill(dist, -1);
		
		// 그래프 초기화
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
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
		
		// 사이클(순환선) 찾기
		dfs(1, 0);
		
		// 각 역과 순환선 사이의 거리 구하기
		bfs();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(dist[i]).append(" ");
		}

		System.out.println(sb);
	}
}