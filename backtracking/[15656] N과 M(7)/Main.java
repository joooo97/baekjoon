import java.io.*;
import java.util.*;

// 중복 허용 o, 오름차순 x
// 순서 풀이만 가능: check[] 필요 x
public class Main {
	public static int n, m;
	public static int[] nums, arr;
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
			arr[cnt] = nums[i];
			recur(cnt + 1);
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
		recur(0);
		System.out.println(sb);
	}
	
}