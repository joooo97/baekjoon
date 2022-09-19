import java.io.*;
import java.util.*;

public class Main {
	public static int ans;
	public static boolean[] broken = new boolean[10];
	
	public static boolean canPressChannel(int ch) {
		String str_ch = String.valueOf(ch);
		
		for(int i = 0; i < str_ch.length(); i++) {
			if(broken[str_ch.charAt(i) - '0']) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		// 1. 고장난 버튼이 없는 경우
		// - 경우 1(목표 채널 번호를 눌러 이동) vs 경우 2(현재 채널 100에서 +, -를 눌러 이동)
		if(m == 0) {
			int a = String.valueOf(n).length();
			int b = Math.abs(n - 100);
			System.out.println(Math.min(a, b));
			System.exit(0);
		}
		
		// 2. 고장난 버튼이 있는 경우
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int brokenNum = Integer.parseInt(st.nextToken());
			broken[brokenNum] = true;
		}
		
		// 2-1. 현재 채널인 100번에서 + 또는 - 버튼만 눌러 이동하는 경우
		ans = Math.abs(n - 100);
		
		// 2-2. 특정 채널로 이동 후 + 또는 - 버튼을 눌러 이동하는 경우
		// - 목표 채널 n보다 큰 채널에서 n으로 이동하는 경우도 있으므로 100만 정도로 채널 크기 지정
		for(int ch = 0; ch <= 1000000; ch++) {
			// 현재 채널을 누를 수 없으면 넘어가기
			if(!canPressChannel(ch)) continue;
			
			// 눌러야 하는 버튼의 개수 구하기
			int len_ch = String.valueOf(ch).length(); // 누를 채널의 길이
			int cnt_sign = Math.abs(n - ch); // + 또는 - 버튼을 누르는 횟수
			
			// 버튼을 누르는 최소 횟수 갱신
			ans = Math.min(ans, len_ch + cnt_sign);
		}
		
		System.out.println(ans);
	}
	
}