import java.io.*;
import java.util.*;

public class Main {
	
	public static int n, ans = Integer.MAX_VALUE;
	public static int[][] arr;
	public static boolean[] team;
	public static int[] start, link;
	
	public static void recur(int idx, int cnt_start, int cnt_link) {
		if(idx == n) {
			// 한 팀이라도 팀원이 0명인 경우 return
			if(cnt_start == 0 || cnt_link == 0) return;
			
			makeTeam(cnt_start, cnt_link); // 팀 나누기
			ans = Math.min(ans, getDifference()); // 능력치 차이 갱신
			return;
		}
		
		// idx번째 팀원을 스타트 팀으로 선택
		team[idx] = true;
		recur(idx+1, cnt_start+1, cnt_link);
		
		// idx번째 팀원을 링크 팀으로 선택
		team[idx] = false;
		recur(idx+1, cnt_start, cnt_link+1);
	}
	
	public static void makeTeam(int cnt_start, int cnt_link) {
		start = new int[cnt_start];
		link = new int[cnt_link];
		
		int idx_s = 0; // 스타트 팀 인덱스
		int idx_l = 0; // 링크 팀 인덱스
		for(int i = 0; i < n; i++) {
			if(team[i]) start[idx_s++] = i; // 스타트 팀
			else link[idx_l++] = i; // 링크 팀
		}
	}
	
	public static int getDifference() {
		int sum_s = 0; // 스타트 팀 능력치 합
		int sum_l = 0; // 링크 팀 능력치 합
		
		// 스타트 팀 능력치 계산
		for(int i = 0; i < start.length; i++) {
			for(int j = 0; j < start.length; j++) {
				sum_s += arr[start[i]][start[j]];
			}
		}
		
		// 링크 팀 능력치 계산
		for(int i = 0; i < link.length; i++) {
			for(int j = 0; j < link.length; j++) {
				sum_l += arr[link[i]][link[j]];
			}
		}
		
		return Math.abs(sum_s - sum_l);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		team = new boolean[n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, 0);
		System.out.println(ans);
	}

}