import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<ArrayList<Integer>> graph;
	public static int[] group; // 0: 그룹 없음(방문 x) / 1: 그룹 1 / 2: 그룹 2
	public static boolean check; // 이분 그래프인지 체크할 변수
	
	public static void dfs(int x, int g) {
		group[x] = g; // 방문 처리 및 그룹 지정
		
		// 인접 노드 확인
		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			// 인접 노드와 그룹이 같다면 이분 그래프가 아님
			if(group[y] == g) {
				check = false;
				return;
			}
			
			// 인접 노드에 방문하지 않았다면 방문, 현재 노드와 다른 그룹으로 지정
			if(group[y] == 0) dfs(y, 3 - g);
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			group = new int[v + 1];
			check = true;
			
			// 그래프 초기화
			graph = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i <= v; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			// 간선 연결
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			// 이분 그래프인지 확인
			for(int i = 1; i <= v; i++) {
				if(group[i] == 0 && check) dfs(i, 1);
			}
			
			if(!check) sb.append("NO").append("\n");
			else sb.append("YES").append("\n");
		}
		
		System.out.println(sb);
	}
}