import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int max = Integer.MIN_VALUE;
	public static int min = Integer.MAX_VALUE;
	public static int[] num, oper;

	public static void solve(int idx, int cur) {
		if(idx == n) {
			max = Math.max(max, cur);
			min = Math.min(min, cur);
			return;
		}

		// + 연산
		if(oper[0] > 0) {
			oper[0] -= 1;
			solve(idx + 1, cur + num[idx]);
			oper[0] += 1;
		}

		// - 연산
		if(oper[1] > 0) {
			oper[1] -= 1;
			solve(idx + 1, cur - num[idx]);
			oper[1] += 1;
		}

		// * 연산
		if(oper[2] > 0) {
			oper[2] -= 1;
			solve(idx + 1, cur * num[idx]);
			oper[2] += 1;
		}

		// / 연산
		if(oper[3] > 0) {
			oper[3] -= 1;
			solve(idx + 1, cur / num[idx]);
			oper[3] += 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		oper = new int[4];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		solve(1, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
}