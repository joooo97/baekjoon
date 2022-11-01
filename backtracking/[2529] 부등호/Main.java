import java.io.*;
import java.util.*;

public class Main {
	
	public static int k;
	public static long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
	public static String str_max, str_min;
	public static char[] sign;
	public static boolean[] check = new boolean[10];
	
	public static void updateMaxAndMin(String str_num) {
		long num = Long.parseLong(str_num);
		
		if(max < num) {
			max = num;
			str_max = str_num;
		}
		
		if(min > num) {
			min = num;
			str_min = str_num;
		}
	}
	
	public static boolean isPossible(int idx, int num, String str_num) {
		if(idx == 0) return true;
		
		int prev_num = str_num.charAt(idx-1) - '0'; // 이전 숫자
		if(sign[idx-1] == '<') {
			if(prev_num < num) return true;
		} else { // '>'
			if(prev_num > num) return true;
		}
		
		return false;
	}
	
	public static void recur(int idx, String str_num) {	
		if(idx == k+1) {
			updateMaxAndMin(str_num); // 최대값, 최소값 갱신
			return;
		}
		
		for(int i = 0; i <= 9; i++) {
			if(check[i]) continue;
			
			if(isPossible(idx, i, str_num)) {
				check[i] = true;
				recur(idx + 1, str_num + i);
				check[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		sign = new char[k];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			sign[i] = st.nextToken().charAt(0);
		}
		
		recur(0, "");
		
		System.out.println(str_max);
		System.out.println(str_min);
	}

}