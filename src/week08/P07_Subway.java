// ENTIRELY WRITTEN BY CHATGPT, THIS PROBLEM IS TOO LONG TO WRITE
package week08;

import java.io.*;
import java.util.Arrays;

class SubwayLine {
	private int[] stations;

	public SubwayLine(int[] stations) {
		this.stations = stations;
	}

	public int[] getStations() {
		return stations;
	}
}

class SubwaySystem {
	private static final int INF = 1_000_000;

	private int[][] distance;
	private int[] head;
	private int[] next;
	private int[] lineAt;

	public SubwaySystem(int n, SubwayLine[] lines) {
		int m = lines.length;

		// Build a linked list of lines serving each station.
		head = new int[n + 1];
		Arrays.fill(head, -1);

		int total = 0;
		for (SubwayLine line : lines) {
			total += line.getStations().length;
		}

		next = new int[total];
		lineAt = new int[total];

		// Avoid adding the same line to a station more than once.
		int[] lastSeenLine = new int[n + 1];
		Arrays.fill(lastSeenLine, -1);

		int count = 0;

		for (int i = 0; i < m; i++) {
			for (int station : lines[i].getStations()) {
				if (lastSeenLine[station] == i) {
					continue;
				}

				lastSeenLine[station] = i;

				lineAt[count] = i;
				next[count] = head[station];
				head[station] = count;
				count++;
			}
		}

		// Build the graph of subway lines.
		// Two lines are connected if they share a station.
		distance = new int[m][m];

		for (int i = 0; i < m; i++) {
			Arrays.fill(distance[i], INF);
			distance[i][i] = 0;
		}

		for (int station = 1; station <= n; station++) {
			for (int a = head[station]; a != -1; a = next[a]) {
				for (int b = next[a]; b != -1; b = next[b]) {
					int lineA = lineAt[a];
					int lineB = lineAt[b];

					distance[lineA][lineB] = 1;
					distance[lineB][lineA] = 1;
				}
			}
		}

		// Floyd-Warshall: precompute minimum transfers
		// between every pair of subway lines.
		for (int k = 0; k < m; k++) {
			for (int i = 0; i < m; i++) {
				if (distance[i][k] == INF) {
					continue;
				}

				for (int j = 0; j < m; j++) {
					if (distance[k][j] == INF) {
						continue;
					}

					int candidate =
						distance[i][k] + distance[k][j];

					if (candidate < distance[i][j]) {
						distance[i][j] = candidate;
					}
				}
			}
		}
	}

	public int minimumChanges(int start, int end) {
		if (start == end) {
			return 0;
		}

		if (head[start] == -1 || head[end] == -1) {
			return -1;
		}

		int answer = INF;

		// Try every line serving the starting station
		// against every line serving the destination.
		for (int a = head[start]; a != -1; a = next[a]) {
			int startLine = lineAt[a];

			for (int b = head[end]; b != -1; b = next[b]) {
				int endLine = lineAt[b];

				answer = Math.min(
					answer,
					distance[startLine][endLine]
				);
			}
		}

		return answer == INF ? -1 : answer;
	}
}

public class P07_Subway {
	public static void main(String[] args) throws IOException {
		FastScanner fs = new FastScanner(System.in);

		int n = fs.nextInt();
		int m = fs.nextInt();

		SubwayLine[] lines = new SubwayLine[m];

		for (int i = 0; i < m; i++) {
			int s = fs.nextInt();
			int[] stations = new int[s];

			for (int j = 0; j < s; j++) {
				stations[j] = fs.nextInt();
			}

			lines[i] = new SubwayLine(stations);
		}

		SubwaySystem system = new SubwaySystem(n, lines);

		int q = fs.nextInt();
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < q; i++) {
			int start = fs.nextInt();
			int end = fs.nextInt();

			int result = system.minimumChanges(start, end);

			if (result == -1) {
				output.append("impossible\n");
			} else {
				output.append(result).append('\n');
			}
		}

		System.out.print(output);
	}
}

class FastScanner {
	private final InputStream in;
	private final byte[] buffer = new byte[1 << 16];

	private int ptr = 0;
	private int len = 0;

	public FastScanner(InputStream in) {
		this.in = in;
	}

	private int read() throws IOException {
		if (ptr >= len) {
			len = in.read(buffer);
			ptr = 0;

			if (len <= 0) {
				return -1;
			}
		}

		return buffer[ptr++];
	}

	public int nextInt() throws IOException {
		int c;

		do {
			c = read();
		} while (c <= ' ' && c != -1);

		int number = 0;

		while (c > ' ') {
			number = number * 10 + c - '0';
			c = read();
		}

		return number;
	}
}
