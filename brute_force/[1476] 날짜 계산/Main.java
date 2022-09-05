import java.io.*;
import java.util.*;

public class Main {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int e = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int curE = 1, curS = 1, curM = 1;
		
		for(int y = 1; ; y++) {
			
			if(curE == e && curS == s && curM == m) {
				System.out.println(y);
				break;
			}
			
			curE += 1;
			curS += 1;
			curM += 1;
			
			if(curE == 16) curE = 1;
			if(curS == 29) curS = 1;
			if(curM == 20) curM = 1;
		}
	}
}