import java.io.*;
import java.util.*;

// 중복 허용 o, 비내림차순
//  - 순서 풀이, 선택 풀이 모두 가능
// 순서 풀이
//  - 중복 허용 -> check배열 필요 x
//  - 비내림차순 -> start변수 필요, 다음 선택 가능 범위는 i부터
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
			// 현재 수 nums[i]를 선택하는 경우
			arr[cnt] = nums[i];
			recur(cnt + 1, i); // 중복 허용이므로 i+1이 아닌 i부터 선택 가능
			
			// 현재 수 nums[i]를 선택하지 않는 경우 -> 다음 반복문
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