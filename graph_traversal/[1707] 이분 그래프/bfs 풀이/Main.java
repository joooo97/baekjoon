import java.util.*;
import java.io.*;

public class Main {
	public static ArrayList<ArrayList<Integer>> graph;
	public static int[] group; // 0: 그룹 없음(방문 x), 1: 그룹 1, 2: 그룹 2

	public static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		group[start] = 1; // 그룹 지정, 방문 처리
		
		while(!q.isEmpty()) {
			int now = q.poll();
			int g = group[now];
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				int next = graph.get(now).get(i);
				
				// 인접 노드와 그룹이 같다면 이분 그래프가 아님
				if(group[next] == g) return false;
				
				// 인접 노드가 그룹이 없다면 현재 노드와 다른 그룹으로 지정
				if(group[next] == 0) {
					q.offer(next);
					group[next] = 3 - g;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력할 답
		StringTokenizer st;
		int k = Integer.parseInt(br.readLine());

		while(k-- > 0) {
			st = new StringTokenizer(br.readLine());
			// 정점 개수 V, 간선 개수 E
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			// 테스트 케이스마다 그래프와 그룹 배열을 초기화 해줘야 함!
			group = new int[V + 1];

			// 그래프 초기화
			graph = new ArrayList<>();
			for(int i = 0; i <= V; i++) {
				graph.add(new ArrayList<Integer>());
			}

			// 간선 연결
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph.get(u).add(v);
				graph.get(v).add(u);
			}

			boolean check = true; 
			// 연결 그래프가 아닌 단절 그래프일 수도 있으므로 
			// bfs를 한 번만 진행하는 것이 아닌 for문을 돌며 모든 정점에 대해 진행
			for(int i = 1; i <= V; i++) {
				// check == false라면 이분그래프가 아니므로 더이상 bfs 진행 x
				if(group[i] == 0 && check) {
					if(!bfs(i)) check = false;
				}
			}

			if(!check)
				sb.append("NO").append("\n");
			else
				sb.append("YES").append("\n");
		}
		System.out.println(sb);
	}
}