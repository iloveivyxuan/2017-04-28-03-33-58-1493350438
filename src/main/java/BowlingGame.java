class FrameScore {
    public int scoreOne;
    public int scoreTwo;
    public int addition = 0;
    public String type = "Default";

    public FrameScore(String str) {
        str = str.replaceAll("-", "0");
        if(!!(str.contains("X"))) {
            this.scoreOne = 10;
            this.scoreTwo = 0;
            this.type = "Strick";
        } else if (!!(str.contains("/"))) {
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
}

public class BowlingGame {
    String[] addition = new String[2];
    FrameScore[] frameScores = new FrameScore[10];
    FrameScore[] calcScores = new FrameScore[12];

    public BowlingGame(String scoreString) {
        String[] scores = scoreString.split("\\|\\|")[0].split("\\|");
        String[] additionString = scoreString.split("\\|\\|")[1].split("");
        this.addition[0] = additionString[0] + "00";
        this.addition[1] = additionString[1] + "00";

        for(int i = 0; i < 10; i += 1) {
            this.frameScores[i] = new FrameScore(scores[i]);
            this.calcScores[i] = new FrameScore(scores[i]);
        }
        this.calcScores[10] = new FrameScore(Integer.parseInt(this.addition[0]));
        this.calcScores[11] = new FrameScore(Integer.parseInt(this.addition[1]));

        for(int i = 0; i < 10; i += 1) {
            FrameScore fScr = this.frameScores[i];
            if(fScr.type == "Strike") {
                fScr.addition += this.calcScores[i + 1].rawScore();
                if(this.calcScores[i + 1].type == "Strike") {
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
        }
        return sum;
    }
}