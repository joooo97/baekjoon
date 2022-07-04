import java.io.*;
import java.util.*;

/*
 * - 중복 허용 x, 오름차순 o -> N과 M(2) 풀이
 * - 순서 풀이: check[] 대신 start 변수 필요
 * - 선택 풀이 가능
 */
public class Main {
	public static int n, m;
	public static int[] nums, arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt, int start_idx) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start_idx; i < n; i++) {
			// i번째 수(nums[i])를 선택하는 경우
			arr[cnt] = nums[i];
			recur(cnt + 1, i + 1);
			
			// i번째 수(nums[i])를 선택하지 않는 경우 -> 다음 반복문
		}
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