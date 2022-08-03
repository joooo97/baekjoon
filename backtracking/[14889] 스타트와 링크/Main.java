import java.io.*;
import java.util.*;

/*
 * n명 -> n/2명씩 팀 나누기
 * 1,4번 있는 팀의 능력치 -> s14 + s41
 * 두 팀의 능력치 차이를 최소로 만들기
 */
public class Main {
	
	public static int n, ans = Integer.MAX_VALUE;
	public static int[][] ability; // 능력치 배열
	public static boolean[] team; // 팀 정보 (false는 스타트팀, true는 링크팀)
	
	// cnt: 링크팀으로 선택한 팀원 수, idx: idx번째 팀원부터 선택 가능함
	public static void divideTeams(int cnt, int idx) {
		// n/2명씩 팀을 나누었다면 팀원 배정하기
		if(cnt == n/2) {
			ArrayList<Integer> teamStart = new ArrayList<>();
			ArrayList<Integer> teamLink = new ArrayList<>();
			
			// 각 팀의 팀원 번호를 저장
			for(int i = 0; i < n; i++) {
				if(!team[i]) teamStart.add(i);
				else teamLink.add(i);
			}
			
			calculateAbility(teamStart, teamLink); // 능력치 계산
			return;
		}
		
		for(int i = idx; i < n; i++) {
			// i번째 팀원을 링크팀(true)으로 선택
			team[i] = true;
			divideTeams(cnt + 1, i + 1);
			
			// i번째 팀원을 링크팀으로 선택하지 않음 (스타트팀으로 선택) -> 다음 반복문
			team[i] = false;
		}
	}
	
	public static void calculateAbility(ArrayList<Integer> teamStart, ArrayList<Integer> teamLink) {
		// 각 팀의 총 능력치
		int sumStart = 0;
		int sumLink = 0;

		// 각 팀의 능력치 합 구하기
		for(int i = 0; i < n/2; i++) {
			for(int j = 0; j < n/2; j++) {
				sumStart += ability[teamStart.get(i)][teamStart.get(j)];
				sumLink += ability[teamLink.get(i)][teamLink.get(j)];
			}
		}
		
		// 최소 능력치 차이 갱신
		ans = Math.min(ans, Math.abs(sumStart -  sumLink));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ability = new int[n][n];
		team = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divideTeams(0, 0); // 팀 나누기
		
		System.out.println(ans);
	}
	
}