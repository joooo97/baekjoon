import java.io.*;
import java.util.*;

public class Main {
	public static int l, c;
	public static char[] alphabet;
	public static int[] selected_idx;
	public static StringBuilder sb = new StringBuilder();
	
	public static void recur(int cnt, int startIdx) {
		if(cnt == l) {
			// 암호 구성 확인
			if(canBePassword()) {
				for(int i = 0; i < l; i++) {
					sb.append(alphabet[selected_idx[i]]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for(int i = startIdx; i < c; i++) {
			// i번째 수를 선택하는 경우
			selected_idx[cnt] = i;
			recur(cnt + 1, i + 1);
		}
	}
	
	// 현재 선택된 알파벳들이 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있는지 확인
	public static boolean canBePassword() {
		int cnt_mo = 0; // 모음 개수
		int cnt_ja = 0; // 자음 개수
		
		for(int i = 0; i < l; i++) {
			char alpha = alphabet[selected_idx[i]];
			
			if(alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u')
				cnt_mo += 1;
			else
				cnt_ja += 1;
		}
		
		return cnt_mo >= 1 && cnt_ja >= 2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		selected_idx = new int[l];
		alphabet = new char[c];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < c; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);
		
		recur(0, 0);
		
		System.out.println(sb);
	}
}