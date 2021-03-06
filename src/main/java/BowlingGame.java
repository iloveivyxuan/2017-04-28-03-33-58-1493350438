class Bowling {
    public static void main(String []args){
        BowlingGame game = new BowlingGame("X|7/|9-|X|-8|8/|-6|X|X|X||81");
        System.out.println(game.score());
    }
}

class FrameScore {
    public int scoreOne;
    public int scoreTwo;
    public int addition = 0;
    public String type = "Default";

    public FrameScore(String str) {
        str = str.replaceAll("-", "0");
        if(str.contains("X")) {
            this.scoreOne = 10;
            this.scoreTwo = 0;
            this.type = "Strick";
        } else if (str.contains("/")) {
            String[] scores = str.split("");
            this.scoreOne = Integer.parseInt(scores[0]);
            this.scoreTwo = 10 - Integer.parseInt(scores[0]);
            this.type = "Spare";
        } else {
            String[] scores = str.split("");
            this.scoreOne = Integer.parseInt(scores[0]);
            this.scoreTwo = Integer.parseInt(scores[1]);
        }
    }

    public int rawScore() {
        return this.scoreOne + this.scoreTwo;
    }

    public int score() {
        return this.scoreOne + this.scoreTwo + this.addition;
    }

    public void printSelf() {
        System.out.println(this.scoreOne + ", " + this.scoreTwo + ", " + this.addition);
    }
}

public class BowlingGame {
    String[] addition = new String[2];
    FrameScore[] frameScores = new FrameScore[10];
    FrameScore[] calcScores = new FrameScore[12];
    public BowlingGame(String scoreString) {
        String[] splitString = scoreString.split("\\|\\|");
        String[] scores = splitString[0].split("\\|");
        String additionStr;
        if(splitString.length > 1) {
            additionStr = splitString[1];
        } else {
            additionStr = "00";
        }
        switch (additionStr.length()) {
            case 0 :
                additionStr = "00";
            case 1 :
                additionStr += "0";
        }
        String[] additionString = additionStr.split("");
        this.addition[0] = additionString[0] + "0";
        this.addition[1] = additionString[1] + "0";

        for(int i = 0; i < 10; i += 1) {
            this.frameScores[i] = new FrameScore(scores[i]);
            this.calcScores[i] = new FrameScore(scores[i]);
        }
        this.calcScores[10] = new FrameScore(this.addition[0]);
        this.calcScores[11] = new FrameScore(this.addition[1]);

        for(int i = 0; i < 10; i += 1) {
            FrameScore fScr = this.frameScores[i];
            if(fScr.type == "Strick") {
                fScr.addition += this.calcScores[i + 1].rawScore();
                if(this.calcScores[i + 1].type == "Strick") {
                    fScr.addition += this.calcScores[i + 2].scoreOne;
                }
            } else if(fScr.type == "Spare") {
                fScr.addition += this.calcScores[i + 1].scoreOne;
            }
        }
    }

    public int score() {
        int sum = 0;
        for(int i = 0; i < 10; i += 1) {
            sum += this.frameScores[i].score();
            this.frameScores[i].printSelf();
        }
        return sum;
    }


}