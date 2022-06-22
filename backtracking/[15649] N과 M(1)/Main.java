import java.io.*;
import java.util.*;

// 1~N까지 자연 수 중 중복 없이 M개를 고른 수열
public class Main {
	public static int n, m;
	public static boolean[] check; // 수 선택 여부 배열
	public static int[] arr; // 수열 저장 배열
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(check[i]) continue; // 현재 수 i가 이미 선택됐다면 건너뛰기
			
			// 현재 수 i를 선택하는 경우
			check[i] = true; // 선택
			arr[cnt] = i; // 수열에 추가
			recur(cnt + 1); // 다음 번째 수(cnt + 1) 선택
			
			// 현재 수 i를 선택하지 않는 경우
			// - 반복문을 계속 돌며 현재 번째 수(cnt)를 다시 선택해야 함
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		check = new boolean[n + 1];
		arr = new int[m];
		
		recur(0);
		System.out.println(sb);
	}
}