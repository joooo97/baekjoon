import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<ArrayList<Integer>> graph;
	public static int[] group; // 0: 그룹 없음, 1: 그룹 1, -1: 그룹 2
	public static StringBuilder sb = new StringBuilder();

	public static boolean dfs(int x, int g) {
		group[x] = g; // 정점 x를 g 그룹으로 지정(= 방문 처리)

		for(int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i); // 인접 정점

			// 인접 정점과 그룹이 같다면 이분 그래프가 아닌 것
			if(group[y] == g) return false;

			// 인접 정점의 그룹이 아직 정해지지 않은 경우 x와 다른 그룹으로 지정
			if(group[y] == 0) {
				if(!dfs(y, -g)) return false;
			}
		}

		return true;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringTokenizer st;
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()); // 정점 개수
			int e = Integer.parseInt(st.nextToken()); // 간선 개수
			group = new int[v + 1];

			// 그래프 초기화
			graph = new ArrayList<>();
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

			// dfs 또는 bfs 탐색을 통해 이분 그래프인지 확인
			// 단절 그래프일 수도 있으므로 아직 방문하지 않은 모든 정점에 대해 탐색 진행
			boolean ok = true; // 이분 그래프인지 확인
			for(int i = 1; i <= v; i++) {
				// 아직 방문하지 않았다면(= 그룹 미지정) 탐색 진행
				if(group[i] == 0) {
					if(!dfs(i, 1)) {
						ok = false;
						break;
					}
				}
			}
			if(ok) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}
}