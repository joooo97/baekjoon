import java.io.*;
import java.util.*;

public class Main {
	
	public static int n;
	public static StringBuilder sb = new StringBuilder();
	public static int[] num, selected = new int[6];
	
	public static void recur(int idx, int num_idx) {
		if(idx == 6) {
			for(int i = 0; i < 6; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(num_idx >= n) return;
		
		// 현재 수 num[idx]를 선택하는 경우
		selected[idx] = num[num_idx];
		recur(idx + 1, num_idx + 1);
		
		// 현재 수 num[idx]를 선택하지 않는 경우
		recur(idx, num_idx + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			if(n == 0) break;
			
			num = new int[n];
			for(int i = 0; i < n; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			recur(0, 0);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}