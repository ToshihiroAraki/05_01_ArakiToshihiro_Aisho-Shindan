import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class kadai_java_01 {
    public static void main(String[] args) {
        System.out.println("相性診断ツール");
        //Scannerクラス初期化
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        //重複しない乱数を生成
        for(int i = 0 ; i < 6 ;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        //質問を配列に格納
        int[] answer = new int[7];//回答番号配列
        int[] scoreSum = new int[7];//合計スコア配列
        String[] question = new String[6];//質問の配列
        int[] myAnswer = new int[7];//私の回答番号
            question[0]="肉が好き?";
            question[1]="お酒が好き?";
            question[2]="野球観戦が好き?";
            question[3]="サッカー観戦が好き?";
            question[4]="ディズニーランドが好き?";
            question[5]="プログラミングが好き?";
            myAnswer[0]= 4;
            myAnswer[1]= 4;
            myAnswer[2]= 4;
            myAnswer[3]= 1;
            myAnswer[4]= 2;
            myAnswer[5]= 4;
            myAnswer[6]= 1;
        String answerCandidate = "\n1.いいえ 2.どちらかといえばいいえ 3.どちらかといえばはい 4.はい 5.中断する";
        loop: for(int i = 0 ; i < question.length ; i++ ) {
            //入力値の検査のため繰り返し処理
            while (true) {
                System.out.println(question[list.get(i)] + answerCandidate);
                if (sc.hasNextInt()) {
                    answer[list.get(i)] = sc.nextInt();
                    //中断するためのbreak処理(whileとforの両方抜けるのでラベル（loop）付き
                    if(answer[list.get(i)] == 5){
                        answer[list.get(i)] = 0;//合計点に影響しないように0を代入
                        System.out.println("質問を中断しました。");
                        break loop;
                    }
                    //入力した値が1~4であることをチェック
                    if (answer[list.get(i)] < 1 || answer[list.get(i)] > 4) {
                        System.out.println("1～4の整数を入力してください。");
                    }else{
                        //自分の回答と対象者の回答の差を絶対値にして満点(4)から引いてスコアにする。
                        scoreSum[list.get(i)] = 4 - Math.abs(myAnswer[list.get(i)] - answer[list.get(i)]);
                        if(answer[5] == 4){//プログラミングが好きだと答えた場合のみ以下実行
                            System.out.println("以下のうち一番好きな言語を選択してください");
                            System.out.println("1.java 2.python 3.php 4.javascript");
                            answer[6] = sc.nextInt();
                            scoreSum[6] = 4 - Math.abs(myAnswer[6] - answer[6]);
                        }
                        break;//1~4ならwhile処理抜け出して次の質問へ(next i)
                    }
                }else {
                    //整数じゃないもの(少数や文字列)が入力された時の処理
                    System.out.println("整数を入力してください");
                    sc.next();
                }
            }
        }
        //answer配列(回答スコア)を合計する
        int score = java.util.Arrays.stream(scoreSum).sum();
//        System.out.println(score);
        //スコアに応じてコメント表示
        System.out.println(score + "/28point");
        if(score == 28){
            System.out.println("fantastic!(^^)!\n相性はばっちりです！！");
        }else if(score > 20){
            System.out.println("相性はとてもよいです(*^^*)");
        }else if (score > 14){
            System.out.println("普通ですね('_')");
        }else if(score > 8){
            System.out.println("微妙です。。。(-.-)");
        }else{
            System.out.println("最悪です。。。(~_~メ)");
        }
    }
}
