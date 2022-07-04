import java.io.*;
import java.util.*;

// 중복 허용 x, 오름차순 o -> N과 M(2) 풀이
// 선택 풀이
public class Main {
	public static int n, m;
	public static int[] nums, arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt, int idx) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(idx >= n) return;
		
		// 현재 수 nums[idx]를 선택하는 경우
		arr[cnt] = nums[idx];
		recur(cnt + 1, idx + 1);
		
		// 현재 수 nums[idx]를 선택하지 않는 경우
		recur(cnt, idx + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		nums = new int[n];
		arr = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		recur(0, 0);
		System.out.println(sb);
	}
}