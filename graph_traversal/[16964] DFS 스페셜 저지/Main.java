import java.io.*;
import java.util.*;

public class Main {
	
	public static int n, idx = 1, ans = 1;
	public static int[] order;
	public static boolean[] check;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void dfs(int x) {
		check[x] = true;
		
		// x와 연결된, 방문하지 않은 모든 정점 y를 set에 담기
		HashSet<Integer> set = new HashSet<>();
		for(int y : graph.get(x)) {
			if(!check[y]) set.add(y);
		}
		
		// x의 인접노드 중 아직 방문하지 않은 정점들을 찾아 모두 set에 담았기 때문에
		// 현재 확인할 순서의 노드(order[idx])가 set에 담겨있는지만 확인하면 된다.
		for(int i = 0; i < set.size(); i++) {
			int node = order[idx++];
			
			if(set.contains(node)) {
				dfs(node);
			} else {
				ans = 0;
				return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		order = new int[n];
		check = new boolean[n+1];
		
		// 그래프 초기화
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		// 간선 연결
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// DFS 방문 순서 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1);
		System.out.println(ans);
	}

}