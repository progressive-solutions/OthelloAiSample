import java.util.ArrayList;
import java.util.List;

/**
 * AI内部で使用するオセロ盤の状態管理とルール処理を行うクラス。
 * AIEntryから盤面文字列を受け取り、操作を可能にする。
 * 生徒は原則としてこのファイルを変更する必要はありません。
 */
public class Board {
    public static final int EMPTY = 0;
    public static final int BLACK = 1; 
    public static final int WHITE = 2; 
    public static final int SIZE = 8;
    
    private int[][] cells;
    private final int myColor;
    private final int opponentColor;
    
    public Board(int color) {
        this.myColor = color;
        this.opponentColor = (color == BLACK) ? WHITE : BLACK;
        this.cells = new int[SIZE][SIZE];
    }
    
    /** 盤面文字列から状態を初期化 */
    public void initialize(String boardString) {
        if (boardString.length() != SIZE * SIZE) {
            return; 
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = Character.getNumericValue(boardString.charAt(i * SIZE + j));
            }
        }
    }
    
    // --- 思考エンジンで使用するユーティリティメソッド ---

    /** 現在の手番で合法な着手可能な座標のリストを取得 */
    public List<String> getLegalMoves(int color) {
        List<String> moves = new ArrayList<>();
        // 座標を文字列 (a1～h8) に変換
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (isLegalMove(r, c, color)) {
                    char colChar = (char) ('a' + c);
                    String moveStr = String.valueOf(colChar) + (r + 1);
                    moves.add(moveStr);
                }
            }
        }
        return moves;
    }

    /** 座標が合法手であるかを判定 (着手色を指定) */
    public boolean isLegalMove(int row, int col, int color) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || cells[row][col] != EMPTY) {
            return false;
        }

        int opponent = (color == BLACK) ? WHITE : BLACK;
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int r = row + dr[i];
            int c = col + dc[i];
            boolean foundOpponent = false;

            while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && cells[r][c] == opponent) {
                foundOpponent = true;
                r += dr[i];
                c += dc[i];
            }

            if (foundOpponent && r >= 0 && r < SIZE && c >= 0 && c < SIZE && cells[r][c] == color) {
                return true; 
            }
        }
        return false;
    }
    
    /** 石の数をカウント */
    public int countStones(int color) {
        int count = 0;
        for (int[] row : cells) {
            for (int cell : row) {
                if (cell == color) {
                    count++;
                }
            }
        }
        return count;
    }

    // --- その他、思考エンジンで使用する必要なメソッドをここに追加 ---
    // 例: 着手を適用し、新しい盤面状態（Boardオブジェクト）を返すメソッドなど
    
    public int getCell(int r, int c) {
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) return -1;
        return cells[r][c];
    }
    
    public int getMyColor() {
        return myColor;
    }
    
    public int getOpponentColor() {
        return opponentColor;
    }
}