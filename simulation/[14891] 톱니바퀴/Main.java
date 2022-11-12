import java.io.*;
import java.util.*;

public class Main {
	
	public static int[][] arr = new int[4][8]; // 톱니바퀴 정보
	
	public static void rotate(int no, int d) {
		
		// 회전 시키기 전, 해당 톱니바퀴의 양쪽의 톱니바퀴 확인하여 회전 방향 구하기
		int[] arr_dir = new int[4];
		arr_dir[no] = d;
		
		// 왼쪽 확인
		int cur = no;
		int left = no - 1;
		while(cur > 0) {
			if(arr[left][2] != arr[cur][6])
				arr_dir[left] = -arr_dir[cur];
			else break;
			
			cur -= 1;
			left -=1;
		}
		
		// 오른쪽 확인
		cur = no;
		int right = no + 1;
		while(cur < 3) {
			if(arr[cur][2] != arr[right][6])
				arr_dir[right] = -arr_dir[cur];
			else break;
			
			cur += 1;
			right += 1;
		}
		
		// 회전 진행
		for(int i = 0; i < 4; i++) {
			
			if(arr_dir[i] == 1) { // 시계 방향
				int start = arr[i][7];
				for(int j = 7; j >= 1; j--) {
					arr[i][j] = arr[i][j-1];
				}
				arr[i][0] = start;
			} else if(arr_dir[i] == -1) { // 반시계 방향
				int start = arr[i][0];
				for(int j = 0; j <= 6; j++) {
					arr[i][j] = arr[i][j+1];
				}
				arr[i][7] = start;
			}
		}
	}
	
	public static int getScore() {
		int score = 0;
		
		// 톱니바퀴의 12시 방향이 S극(1)이면 점수 추가
		if(arr[0][0] == 1) score += 1;
		if(arr[1][0] == 1) score += 2;
		if(arr[2][0] == 1) score += 4;
		if(arr[3][0] == 1) score += 8;
		
		return score;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 톱니바퀴 상태
		for(int i = 0; i < 4; i++) {
			String line = br.readLine();
			for(int j = 0; j < 8; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 회전 횟수
		int k = Integer.parseInt(br.readLine());
		
		while(k-- > 0) {
			// 회전 방법
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken()) - 1; // 회전할 톱니바퀴 번호
			int d = Integer.parseInt(st.nextToken()); // 회전할 방향 (1: 시계, -1: 반시계)
			
			rotate(no, d);
		}
		
		// 점수 계산
		System.out.println(getScore());
	}

}