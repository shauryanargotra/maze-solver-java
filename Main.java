import java.util.*;

class Main {
    static int[][] maze =
            {
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 2, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
                    { 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1 },
                    { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1 },
                    { 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 },
                    { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
                    { 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1 },
                    { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
                    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
                    { 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

            };

    static final int PATH = 0;
    static final int WALL = 1;
    static final int START = 2;
    static final int END = 3;
    static final int MOVE = 4;
    static final int DEADEND = 5;

    /**
     This method will display our maze. Use nested for loops and if statements to draw. :)
     */
    public static void displayMaze() {
        String RED = "\u001b[38;5;1m";
        String BLUE = "\u001b[38;5;4m";
        String RESET = "\u001b[0m";


        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == PATH){
                    System.out.print(" ");
                } else if (maze[r][c] == WALL) {
                    System.out.print("🍀");
                } else if (maze[r][c] == START) {
                    System.out.print("🧍‍");
                } else if (maze[r][c] == END) {
                    System.out.print("🎁");
                } else if (maze[r][c] == MOVE) {
                    System.out.print("🏃‍");
                }
            }
            System.out.println();
        }
    }

    /**
     This method will find the end of our maze.
     @return   An int array containng the (x, y) coordinate of the end of the maze x will be element 0, y will be element 1
     */
    public static int[] findEnd() {
        int x = 1;
        int y = -1;
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == END){
                    x = r;
                    y = c;
                }
            }
        }

        int[] arr = {x,y};
        return arr;
    }

    /**
     This method will find the start of our maze.
     @return   An int array containng the (x, y) coordinate of the end of the maze x will be element 0, y will be element 1
     */
    public static int[] findStart() {
        int x = 1;
        int y = -1;
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == START){
                    x = r;
                    y = c;
                }
            }
        }
        int[] arr = {x, y};
        return arr;
    }

    /**
     * This method will determine if the given x, y coordinate is inside the boundaries of the maze
     * @return   true if the (x, y) coordinate is in the maze
     */
    public static boolean inGrid(int x, int y) {
        if (x >= 0 && x <= 9 && y >= 0 && y <= 9){
            return true;
        }else {
            return false;
        }
    }

    /**
     * This method will recursively make moves in the maze until it is solved, or proved unsolvable.
     * @param    x - an int for the current x position
     * @param    y - an int for the current y position
     * @return   true if the maze is solved, false if we are unable to solve
     */
    public static boolean move(int x, int y) {
        /**
         *Pseudo Code
         *Check if (x,y) in Grid
         *  Check if (x,y) is the end of the maze
         *    Return true
         *  Check if (x,y) is a valid move (PATH or START)
         *    Assign the current cell to Move
         *    IF any possible move (NORTH, SOUTH, WEST, EAST) succeeds
         *      return true
         *    ELSE
         *      Invalid path -> reset cell to PATH
         *    IF cell is start
         *      return false
         *ELSE
         *  return false (outside grid)
         */
        int[] arr = {x, y};

        if (inGrid(x, y)) {
            if (maze[x][y] == END) {
                return true;
            } else if (maze[x][y] == START || maze[x][y] == PATH) {
                maze[x][y] = MOVE;

                int NORTH = maze[x + 1][y];
                int EAST = maze[x][y + 1];
                int WEST = maze[x][y - 1];
                int SOUTH = maze[x - 1][y];

                if (move(x-1, y)|| move(x,y+1) || move(x+1,y) || move(x+1,y)) {
                    return true;
                } else {
                    maze[x][y] = PATH;
                }
                if (maze[x][y] == START){
                    return false;
                }
            }
        }
        return false;
    }

    public static void main (String[] args) {
        int[] start = findStart();
        int[] end = findEnd();

        displayMaze();
        System.out.println();

        if (start[0] != -1 && start[1] != -1 && end[0] != -1 && end[1] != -1 && move(start[0], start[1]+1)) {
            displayMaze();
        } else {
            System.out.println("Unsolvable maze. :(");
        }
    }
}