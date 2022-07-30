import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, v;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int x) {
		visited[x] = true;
		sb.append(x).append(" ");
		
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			if(!visited[y]) dfs(y);
		}
	}
	
	public static void bfs(int sx) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(sx);
		visited[sx] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			sb.append(x).append(" ");
			
			for(int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				
				if(!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		visited = new boolean[n + 1];
		
		// 그래프 초기화
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		// 간선 연결
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}


		// 연결된 정점 중 정점 번호가 작은 것부터 방문하기 위해 정렬
		for(int i = 1; i <= n; i++) {
			Collections.sort(graph.get(i));
		}
		
		dfs(v);
		
		visited = new boolean[n + 1];
		sb.append("\n");
		
		bfs(v);
		
		System.out.println(sb);
	}
	
}