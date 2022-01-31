import java.io.*;
import java.util.*;

public class Main {
	public static int n, m;
	public static boolean[] visited;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void dfs(int x) {
		visited[x] = true;
		
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			if(!visited[y]) dfs(y);
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n + 1];
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
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