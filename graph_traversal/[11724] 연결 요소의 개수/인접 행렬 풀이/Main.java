import java.io.*;
import java.util.*;

public class Main {
	public static int n, m;
	public static int[][] graph;
	public static boolean[] visited;
	
	public static void dfs(int x) {
		visited[x] = true;
		
		for(int y = 1; y <= n; y++) {
			if(graph[x][y] == 1 && !visited[y]) dfs(y);
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n + 1][n + 1];
		visited = new boolean[n + 1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = graph[v][u] = 1;
		}
		
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}