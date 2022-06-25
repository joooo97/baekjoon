import java.io.*;
import java.util.*;

// - 중복 없이 M개 (오름차순 o)
public class Main {
	public static int n, m;
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt, int start) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= n; i++) {
			// 현재 수 i를 선택하는 경우
			arr[cnt] = i;
			recur(cnt + 1, i + 1); // 중복 없어야 하므로 i+1 부터 선택 가능
			
			// 현재 수 i를 선택하지 않는 경우 -> 다음 반복문(cnt 번째 수 다시 선택해야 함)
		}
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