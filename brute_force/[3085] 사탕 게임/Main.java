import java.io.*;

public class Main {
	
	public static int n, maxCnt;
	public static char[][] board;
	
	// 사탕 색이 다른 인접한 두 칸 고르기
	public static void chooseCandies() {
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n; y++) {
				// 현재 칸과 오른쪽 칸 비교
				if(y + 1 < n && board[x][y] != board[x][y + 1]) {
					exchangeCandies(x, y, x, y + 1); // 사탕 교환
					findMaxCnt(); // 가장 긴 연속 부분 확인(먹을 수 있는 사탕의 최대 개수 구하기)
					exchangeCandies(x, y, x, y + 1); // 사탕 되돌려 놓기
				}
				
				// 현재 칸과 아래쪽 칸 비교
				if(x + 1 < n && board[x][y] != board[x + 1][y]) {
					exchangeCandies(x, y, x + 1, y); // 사탕 교환
					findMaxCnt(); // 가장 긴 연속 부분 확인
					exchangeCandies(x, y, x + 1, y); // 사탕 되돌려 놓기 
				}
			}
		}
	}
	
	public static void exchangeCandies(int x, int y, int nx, int ny) {
		char tmp = board[x][y];
		board[x][y] = board[nx][ny];
		board[nx][ny] = tmp;
	}
	
	public static void findMaxCnt() {
		// 행별로 가장 긴 연속 부분 확인
		for(int i = 0; i < n; i++) {
			char curColor = board[i][0]; // 현재 연속되는 색
			int cnt = 1;
			
			for(int j = 1; j < n; j++) {
				char nextColor = board[i][j]; // 다음 칸의 색
				
				if(curColor == nextColor) { // 색이 같다면 (연속된다면)
					cnt += 1;
					maxCnt = Math.max(maxCnt, cnt); // 최대 값 갱신
				} else { // 연속되지 않는다면
					maxCnt = Math.max(maxCnt, cnt); // 최대 값 갱신
					curColor = nextColor; // 연속되는 색 변경
					cnt = 1; // 카운트 1로 초기화
				}
				
				// 현재 연속되는 색과 마지막 칸의 색이 같은 경우 최대값 갱신을 해줘야 함
				// - 이 방법 대신 색이 같은 경우마다 최대 값 갱신해주는 방법이 있음
//				maxCnt = Math.max(maxCnt, cnt);
			}
		}
		
		// 열별로 가장 긴 연속 부분 확인
		for(int i = 0; i < n; i++) {
			char curColor = board[0][i];
			int cnt = 1;
			
			for(int j = 1; j < n; j++) {
				char nextColor = board[j][i];
				
				if(curColor == nextColor) {
					cnt += 1;
					maxCnt = Math.max(maxCnt, cnt);
				} else {
					maxCnt = Math.max(maxCnt, cnt);
					curColor = nextColor;
					cnt = 1;
				}
				
//				maxCnt = Math.max(maxCnt, cnt);
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		chooseCandies(); // 사탕 색이 다른 인접한 두 칸 고르기
		
		System.out.println(maxCnt);
	}
	
}