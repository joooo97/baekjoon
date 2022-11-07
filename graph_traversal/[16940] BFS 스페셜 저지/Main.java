import java.io.*;
import java.util.*;

public class Main {
	
	public static int n;
	public static int[] order;
	public static boolean[] visited;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		visited[1] = true;
		
		HashSet<Integer> set = new HashSet<>();
		int order_idx = 1; // 두 번째 순서부터 확인
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			// x와 연결된, 방문하지 않은 모든 노드 y들을 set에 담기
			for(int y : graph.get(x)) {
				if(!visited[y]) set.add(y);
			}
			
			// BFS 순서 확인하기
			for(int i = 0; i < set.size(); i++) {
				int node = order[order_idx++];
				
				if(set.contains(node)) {
					q.offer(node);
					visited[node] = true;
				} else {
					return false;
				}
			}
			
			set.clear();
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		order = new int[n];
		visited = new boolean[n+1];
		
		// 그래프 초기화
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 연결
		StringTokenizer st;
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// BFS 방문 순서 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		if(bfs()) System.out.println(1);
		else System.out.println(0);
	}

}