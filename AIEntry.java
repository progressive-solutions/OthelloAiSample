import java.io.PrintWriter;
import java.util.Scanner;

/**
 * AIプログラムのエントリポイント。
 * 標準入出力による通信プロトコルを処理し、ThinkingEngineに処理を委譲する。
 * 生徒は原則としてこのファイルを変更する必要はありません。
 */
public class AIEntry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out, true);
        
        int myColor = 0; // 1:BLACK, 2:WHITE
        
        // プロトコルに従い、ジャッジからの命令を処理
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            
            if (line.startsWith("COLOR")) {
                // 初期設定：自分の石の色を受け取る
                try {
                    myColor = Integer.parseInt(line.split(" ")[1]);
                } catch (Exception e) {
                    // エラー処理（ここでは省略）
                }
            } else if (line.startsWith("MOVE BOARD:")) {
                // 手番通知：思考開始を指示
                String boardString = line.substring("MOVE BOARD:".length());
                
                // 盤面をBoardオブジェクトに変換
                Board currentBoard = new Board(myColor);
                currentBoard.initialize(boardString);
                
                // 思考エンジンを呼び出す
                ThinkingEngine engine = new ThinkingEngine();
                String move = engine.think(currentBoard); 
                
                // 着手結果を出力
                writer.println(move);
                
            } else if (line.startsWith("QUIT")) {
                // 終了指示
                break;
            }
        }
        scanner.close();
    }
}