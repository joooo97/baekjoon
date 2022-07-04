import java.io.*;
import java.util.*;

/*
 * - 중복 허용 x, 오름차순 x -> N과 M(1) 풀이
 * - 순서 풀이: check[] 필요, 선택 풀이 불가
 */
public class Main {
	public static int n, m;
	public static int[] nums, arr; // nums: 주어진 수, arr: 수열
	public static boolean[] check;
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(check[i]) continue;
			
			// i번째 수를 선택하는 경우
			check[i] = true;
			arr[cnt] = nums[i];
			recur(cnt + 1);
			
			// i번째 수를 선택하지 않는 경우 -> 다음 반복문
			check[i] = false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		nums = new int[n];
		arr = new int[m];
		check = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
				
		// 사전 순 증가 순서대로 출력하기 위해 오름차순 정렬
		Arrays.sort(nums);
		
		recur(0);
		System.out.println(sb);
	}
}