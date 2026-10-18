package week07;

import java.util.*;

class Position {
	int row;
	int col;

	Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class Maze {
	private int[][] grid;
	private int rows;
	private int cols;

	Maze(int[][] grid) {
		this.grid = grid;
		this.rows = grid.length;
		this.cols = grid[0].length;
	}

	int getRows() { return rows; }
	int getCols() { return cols; }

	boolean isValid(int row, int col) {
		return row >= 0 && row < rows
			&& col >= 0 && col < cols;
	}

	boolean isPath(int row, int col) {
		return isValid(row, col) && grid[row][col] == 1;
	}

	boolean isWall(int row, int col) {
		return isValid(row, col) && grid[row][col] == 0;
	}
}

class MazeSolver {
	private Maze maze;
	private Position start;
	private Position exit;

	private final int[] dr = {-1, 1, 0, 0};
	private final int[] dc = {0, 0, -1, 1};

	MazeSolver(Maze maze, Position start, Position exit) {
		this.maze = maze;
		this.start = start;
		this.exit = exit;
	}

	// BFS through ordinary path cells.
	// Distance counts cells, so the starting cell has distance 1.
	private int[][] bfs(Position source) {
		int rows = maze.getRows();
		int cols = maze.getCols();

		int[][] dist = new int[rows][cols];
		Queue<Position> queue = new LinkedList<>();

		dist[source.row][source.col] = 1;
		queue.offer(source);

		while (!queue.isEmpty()) {
			Position current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = current.row + dr[d];
				int nc = current.col + dc[d];

				if (maze.isPath(nr, nc) && dist[nr][nc] == 0) {
					dist[nr][nc] = dist[current.row][current.col] + 1;
					queue.offer(new Position(nr, nc));
				}
			}
		}

		return dist;
	}

	int[] solve() {
		int rows = maze.getRows();
		int cols = maze.getCols();

		int[][] fromStart = bfs(start);
		int[][] fromExit = bfs(exit);

		int validWalls = 0;
		int shortestDistance = Integer.MAX_VALUE;

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {

				if (!maze.isWall(r, c)) {
					continue;
				}

				boolean works = false;

				// Try every pair of neighboring path cells:
				// one reachable from start, one reachable from exit.
				for (int a = 0; a < 4; a++) {
					int sr = r + dr[a];
					int sc = c + dc[a];

					if (!maze.isValid(sr, sc)
							|| fromStart[sr][sc] == 0) {
						continue;
					}

					for (int b = 0; b < 4; b++) {
						int er = r + dr[b];
						int ec = c + dc[b];

						if (!maze.isValid(er, ec) || fromExit[er][ec] == 0) {
							continue;
						}

						int distance = fromStart[sr][sc] + 1 + fromExit[er][ec];
						works = true;

						if (distance < shortestDistance) {
							shortestDistance = distance;
						}
					}
				}

				if (works) validWalls++;
			}
		}

		if (validWalls == 0) {
			shortestDistance = 0;
		}

		return new int[] {validWalls, shortestDistance};
	}
}

public class P06_IndianaJ {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int M = input.nextInt();
		int N = input.nextInt();

		// Input coordinates are 1-based.
		Position start = new Position(
			input.nextInt() - 1,
			input.nextInt() - 1
		);

		Position exit = new Position(
			input.nextInt() - 1,
			input.nextInt() - 1
		);

		int[][] grid = new int[M][N];

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				grid[r][c] = input.nextInt();
			}
		}

		Maze maze = new Maze(grid);
		MazeSolver solver = new MazeSolver(maze, start, exit);

		int[] answer = solver.solve();

		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}
}
