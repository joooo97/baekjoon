import java.io.*;
import java.util.*;

// - 중복 허용하여 M개 선택 (오름차순 x)
// - 오름차순 아니므로 순서 풀이만 가능
// - 중복 허용하므로 check 배열 필요 없음
public class Main {
	public static int n, m;
	public static int[] arr;
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
			// 현재 수 i를 선택하는 경우
			arr[cnt] = i;
			recur(cnt + 1);
			
			// 현재 수 i를 선택하지 않는 경우 -> 다음 반복문
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		
		recur(0);
		System.out.println(sb);
	}
}