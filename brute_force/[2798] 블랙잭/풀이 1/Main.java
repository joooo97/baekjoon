import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, ans = 0;
	public static int[] arr;
	
	public static void recur(int startIdx, int cnt, int sum) {
		if(cnt == 3) {
			if(sum <= m) ans = Math.max(ans, sum);
			
			return;
		}
		
		for(int i = startIdx; i < n; i++) {
			// i번째 수 arr[i]를 선택하는 경우
			recur(i + 1, cnt + 1, sum + arr[i]);
			
			// i번째 수 arr[i]를 선택하지 않는 경우 -> 다음 반복문
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		recur(0, 0, 0);
		
		System.out.println(ans);
	}
	
}