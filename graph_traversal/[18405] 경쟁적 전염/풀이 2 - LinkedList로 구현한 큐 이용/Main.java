import java.io.*;
import java.util.*;

class Virus implements Comparable<Virus> {
	int x;
	int y;
	int no;
	int sec;
	
	Virus(int x, int y, int no, int sec) {
		this.x = x;
		this.y = y;
		this.no = no;
		this.sec = sec;
	}
	
	// 바이러스 번호순서대로 오름차순 정렬
	@Override
	public int compareTo(Virus other) {
		if(this.no > other.no) return 1;
		
		return -1;
	}
}

public class Main {
	public static int n, k, s;
	public static int[][] map;
	public static ArrayList<Virus> virus_list = new ArrayList<>(); // 처음에 주어진 바이러스
	public static Queue<Virus> q = new LinkedList<>();
	
	public static int[] dx = {-1, 1, 0, -0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		while(!q.isEmpty()) {
			Virus v = q.poll();
			
			if(v.sec == s) return;
			
			for(int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(map[nx][ny] == 0) {
					map[nx][ny] = v.no; // 바이러스 증식
					q.offer(new Virus(nx, ny, v.no, v.sec + 1));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 처음에 주어진 바이러스들을 리스트에 담기
				if(map[i][j] != 0) {
					virus_list.add(new Virus(i, j, map[i][j], 0));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		// 처음에 주어진 바이러스들을 번호순으로 오름차순 정렬
		// - 번호가 낮은 바이러스부터 증식해야 하므로
		Collections.sort(virus_list);
		
		// 처음에 주어진 바이러스들을 큐에 옮기기
		for(int i = 0; i < virus_list.size(); i++) {
			q.offer(virus_list.get(i));
		}
		
		bfs();
		
		System.out.println(map[x-1][y-1]);
	}

}