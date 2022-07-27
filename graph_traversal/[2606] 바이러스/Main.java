import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static boolean[] visited;
	public static int cnt = 0;
	
	public static void dfs(int x) {
		visited[x] = true;
		
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			if(!visited[y]) {
				cnt += 1;
				dfs(y);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int v = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());
		visited = new boolean[v + 1];
		
		// 그래프 초기화
		for(int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 연결
		for(int i = 0; i < e; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		dfs(1);
		
		System.out.println(cnt);
	}
	
}