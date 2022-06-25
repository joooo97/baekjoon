import java.io.*;
import java.util.*;

// - 중복 없이 M개 (오름차순 o)
// - 순서, 선택 풀이 가능
// - 선택 풀이이므로 num 변수도 필요, 재귀함수 종료 조건 필요
public class Main {
	public static int n, m;
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt, int num) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(num > n) return;
		
		// 현재 수 num을 선택하는 경우
		arr[cnt] = num;
		recur(cnt + 1, num + 1); // 중복 없어야 하므로 num + 1부터 선택 가능
		
		// 현재 수 num을 선택하지 않는 경우
		recur(cnt, num + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		
		recur(0, 1);
		System.out.println(sb);
	}
}