import java.io.*;
import java.util.*;

public class Main {
	public static int n, ans;
	public static int[] t, p;
	
	// day: 오늘 날짜, sum: 현재까지 수익
	public static void recur(int day, int sum) {
		// solve함수 재귀 호출 시 day가 무조건 1씩 늘어나는 게 아니기 때문에
		// day가 n+1을 넘어갈 수도 있게 되므로 조건문을 통해 확인해 줘야 한다.
		// - 퇴사날이 된 경우: if(day == n+1)
		// - 불가능한 경우: if(day > n+1)
		
		// 퇴사날(n+1일)이 되었다면 수익 갱신
		if(day == n+1) {
			ans = Math.max(ans, sum);
			return;
		}
		
		if(day > n+1) return;
		
		// 오늘 상담을 하는 경우
		recur(day + t[day], sum + p[day]);
		
		// 오늘 상담을 하지 않는 경우
		recur(day + 1, sum);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		t = new int[n+1];
		p = new int[n+1];
		
		StringTokenizer st = null;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		recur(1, 0);
		System.out.println(ans);
	}
}