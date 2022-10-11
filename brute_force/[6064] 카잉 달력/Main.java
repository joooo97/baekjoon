import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// <x:y>가 나타내는 해 k를 출력. 유효하지 않다면 -1 출력
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			int ans = -1;
			for(int k = x; k <= m*n; k+=m) {
				
				// for문을 통해 주어진 x와 일치하는 년도만 확인하고 있으므로 y가 일치하는지만 검사하면 됨
				if(k % n == y) {
					ans = k + 1;
					break;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
}