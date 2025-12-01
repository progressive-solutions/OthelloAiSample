import java.util.List;
import java.util.Random;

/**
 * AIの思考ロジックを実装するクラス。
 * 生徒は主にこのファイルを編集し、AIを強化します。
 */
public class ThinkingEngine {
    
    private final Random random = new Random();
    
    /**
     * 現在の盤面情報に基づいて最適な着手を決定する。
     * @param currentBoard 現在の盤面オブジェクト
     * @return 着手文字列 (例: "c5" または "pass")
     */
    public String think(Board currentBoard) {
        
        int myColor = currentBoard.getMyColor();
        
        // 1. 合法な着手リストを取得
        List<String> legalMoves = currentBoard.getLegalMoves(myColor);
        
        // 2. 合法手が一つもない場合
        if (legalMoves.isEmpty()) {
            return "pass";
        }
        
        // ----------------------------------------------------
        // 【⭐ 書き換えるべき思考ロジックの領域 ⭐】
        // ----------------------------------------------------
        
        // 現状：ランダムに合法手を選ぶ（最弱AI）
        String bestMove = chooseRandomMove(legalMoves);
        
        // ----------------------------------------------------
        // TODO:
        // 1. 評価関数 (Evaluation function) を実装する。
        // 2. ミニマックス法 (Minimax) やα-β法 (Alpha-Beta Pruning) を実装する。
        // 3. 探索深さを決定し、評価関数に基づき最適な手を返すように書き換える。
        // ----------------------------------------------------
        
        return bestMove;
    }
    
    /**
     * サンプルとしてランダムな手を選ぶロジック。
     */
    private String chooseRandomMove(List<String> moves) {
        int index = random.nextInt(moves.size());
        return moves.get(index);
    }
    
    // ----------------------------------------------------
    // 【💡 追加するメソッドの例 💡】
    // ----------------------------------------------------

    // private int evaluateBoard(Board board) { ... }
    // private int minimax(Board board, int depth) { ... }
    
}