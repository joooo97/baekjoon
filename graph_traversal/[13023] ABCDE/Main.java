import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static boolean[] visited;
	
	// cnt: 연결된 정점 수
	public static void dfs(int x, int cnt) {
		if(cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}
		
		visited[x] = true;
		
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			if(!visited[y]) dfs(y, cnt + 1);
		}
		
		visited[x] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		
		// 그래프 초기화
		for(int i = 0; i < n; i++) {
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

		for(int i = 0; i < n; i++) {
			dfs(i, 1);
		}
		
		System.out.println(0);
	}
	
}