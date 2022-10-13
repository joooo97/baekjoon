import java.io.*;
import java.util.*;

public class Main {
	public static int sum, ans;
	// 두 그룹의 돌 선택 여부 배열. 한 그룹의 돌의 가능한 최대 개수는 1500개
	public static boolean[][] visited = new boolean[1501][1501];
	
	// 두 그룹을 선택하는 과정을 반복
	public static void dfs(int a, int b, int c) {
		if(a == b && b == c) {
			ans = 1;
			return;
		}
		
		// 두 그룹 고르기
		compare(a, b);
		compare(a, c);
		compare(b, c);
	}
	
	// 돌 개수 비교
	public static void compare(int x, int y) {
		if(visited[x][y]) return;
		
		visited[x][y] = visited[y][x] = true;

		int small = Math.min(x, y);
		int big = Math.max(x, y);
		
		// 변경된 돌 개수로 다시 단계 진행
		dfs(small * 2, big - small, sum - x - y);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		sum = a + b + c;
		if(sum % 3 != 0) {
			System.out.println(0);
		} else {
			dfs(a, b, c);
			System.out.println(ans);
		}
		
	}

}