package bfsdfs.leetcode;

import java.util.*;

public class Leetcode773 {

    public int slidingPuzzle(int[][] board) {
        int zeroX = 0;
        int zeroY = 0;

        // 인접한 요소로 움직일 때 사용하는 배열. (상하 좌우)
        int[][] dxdy = new int[][] {
                {1,0}, {-1,0},
                {0,1}, {0,-1}
        };

        int rowLen = board.length;
        int colLen = board[0].length;

        // '0' 위치 찾기.
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }

        // 입력값이 solved array인 경우.
        if (isSlidingPuzzle(board)) {
            return 0;
        }

        // swap된 배열이 담는 Queue 자료구조.
        Queue<ZeroNode> queue = new LinkedList<>();

        // 입력 초기 보드 저장.
        queue.add(new ZeroNode(zeroX, zeroY, board,0));

        // 탐색한 Board을 담고 있는 객체.
        Boards boards = new Boards();

        // 입력 초기 보드 저장.
        boards.add(new Board(board));


        // BFS 탐색 진행.
        while (!queue.isEmpty()) {
            ZeroNode poll = queue.poll();

            // 현재 보드가 slidingPuzzle(Solved Array)라면 현재 swapCount를 리턴 하고 종료,
            if (isSlidingPuzzle(poll.board)) {
                return poll.swapCount;
            }

            // 인접 배열 탐색.
            for (int i = 0; i < 4; i++) {
                int dx = poll.x + dxdy[i][0];
                int dy = poll.y + dxdy[i][1];

                // 배열 범위 초과시 무시.
                if (dx < 0 || dy < 0 || dx >= rowLen || dy >= colLen) {
                    continue;
                }

                // 인접한 요소와 swap한 배열 리턴. (deep copy를 함수로 빼서 구현._
                int[][] copySwapBoard = getCopySwapBoard(poll.board, poll.x, poll.y, dx, dy);
                Board copyBoard = new Board(copySwapBoard);

                // 기존에 탐색한 배열이 아닌 경우에만, queue와 board에 추가.
                if (!boards.contains(copyBoard)) {
                    // swap count 증가.
                    queue.add(new ZeroNode(dx, dy, copySwapBoard, poll.swapCount + 1));
                    boards.add(copyBoard);
                }


            }
        }
        return -1;
    }


    private int[][] getCopySwapBoard(final int[][] board,
                                     final int x, final int y,
                                     final int dx, final int dy) {
        /**
         * swap 한 후 Deep copy된 배열 리턴.
         */
        int[][] arr = new int[board.length][];

        for (int i = 0; i < board.length; i++) {
            arr[i] = Arrays.copyOf(board[i], board[i].length);
        }

        int tmp = arr[x][y];
        arr[x][y] = arr[dx][dy];
        arr[dx][dy] = tmp;

        return arr;
    }

    private boolean isSlidingPuzzle(final int[][] board) {
        int[][] slidingPuzzle = {{1,2,3}, {4,5,0}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != slidingPuzzle[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static class Boards {
        /* Board 일급 컬렉션.
         */
        private final List<Board> boards = new ArrayList<>();

        public Boards() {
        }

        public void add(Board board) {
            boards.add(board);
        }

        // 중복 board 검증.
        public boolean contains(Board newBoard) {
            return boards.stream()
                    .anyMatch(board -> board.isSame(newBoard));
        }
    }

    private static class Board {
        /* board 객체. (board 동일성 검정을 위해 구현.)
         * */

        int[][] board;

        public Board(final int[][] board) {
            this.board = board;
        }

        public boolean isSame(final Board newBoard) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != newBoard.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private static class ZeroNode {
        /*
         * 현재 swap된 배열의 정보와 '0'의 위치, 현재까지 swap된 횟수를 가지고 있는 객체.
         */
        int x;
        int y;
        int[][] board;
        int swapCount;

        public ZeroNode(final int x, final int y, final int[][] board, final int swapCount) {
            this.x = x;
            this.y = y;
            this.board = board;
            this.swapCount = swapCount;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{3,2,4}, {1,5,0}};

        Leetcode773 leetcode773 = new Leetcode773();
        int cnt = leetcode773.slidingPuzzle(board);

        System.out.println(cnt);
    }
}
