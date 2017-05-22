import java.util.HashMap;
import java.util.Map;

public class BowlingGame {
    Map<Character, Integer> score = new HashMap<Character, Integer>();
    public int getBowlingScore(String bowlingCode) {
        score.put('X', 10);
        score.put('-', 0);
        String[] tenBowlingCode = bowlingCode.split("\\|\\|")[0].split("\\|");
        String extraBowling = bowlingCode.split("\\|\\|").length == 2 ? bowlingCode.split("\\|\\|")[1] : null;
        Integer totalScore = 0;

        for (int index = 0; index < tenBowlingCode.length; ++index) {
            if (index < 8) {
                totalScore += getFrameScoreBeforeEighthFrame(index, tenBowlingCode);
            } else if (index == 8) {
                totalScore += getFrameScoreEqualEighthFrame(index, tenBowlingCode, extraBowling);
            } else {
                totalScore += getFrameScoreEqualNinthFrame(index, tenBowlingCode, extraBowling);
            }
        }

        return totalScore;
    }

    public Integer getFrameScoreBeforeEighthFrame(Integer index, String[] tenBowlingCode) {

        //index frame strike
        if (tenBowlingCode[index].charAt(0) == 'X') {
            if (tenBowlingCode[index + 1].charAt(0) == 'X') { //index+1 frame strike
                if (tenBowlingCode[index + 2].charAt(0) == 'X') { //index+2 frame first code == 'X' strike

                    return 30;
                } else if (tenBowlingCode[index + 2].charAt(0) == '-') { //index+2 frame first code == '-' miss

                    return 20;
                } else {

                    return 20 + Integer.parseInt(String.valueOf(tenBowlingCode[index + 2].charAt(0))); ////index+2 frame other code
                }
            } else if (tenBowlingCode[index + 1].charAt(1) == '/') { //index+1 frame spare

                return 20;
            } else {
                boolean isEqual = "-".equals(String.valueOf(tenBowlingCode[index + 1].charAt(0)));
                return 10 + (isEqual == true ? Integer.parseInt(String.valueOf(tenBowlingCode[index + 1].charAt(1)))
                        : Integer.parseInt(String.valueOf(tenBowlingCode[index + 1].charAt(0))));
            }
        }

        //index frame spare
        if (tenBowlingCode[index].charAt(1) == '/') {
            if (tenBowlingCode[index + 1].charAt(0) == 'X') { //index+1 frame first code == 'X' strike

                return 20;
            } else if (tenBowlingCode[index + 1].charAt(0) == '-') { //index+1 frame first code == '-' miss

                return 10;
            } else {

                return 10 + Integer.parseInt(String.valueOf(tenBowlingCode[index + 1].charAt(0))); ////index+2 frame other code
            }
        }

        //index frame  no spare or strike
        if (tenBowlingCode[index].charAt(0) == '-') {

            return Integer.parseInt(String.valueOf(tenBowlingCode[index].charAt(1)));
        } else {

            return Integer.parseInt(String.valueOf(tenBowlingCode[index].charAt(0)));
        }

    }

    public Integer getFrameScoreEqualEighthFrame(Integer index, String[] tenBowlingCode, String extraBowling) {

        //index frame strike
        if (tenBowlingCode[index].charAt(0) == 'X') {
            if (tenBowlingCode[index + 1].charAt(0) == 'X') { //index+1 frame strike
                if (extraBowling.charAt(0) == 'X') { //index+2 frame first code == 'X' strike

                    return 30;
                } else if (extraBowling.charAt(0) == '-') { //index+2 frame first code == '-' miss

                    return 20;
                } else {

                    return 20 + Integer.parseInt(String.valueOf(extraBowling.charAt(0))); ////index+2 frame other code
                }
            } else if (tenBowlingCode[index + 1].charAt(1) == '/') { //index+1 frame spare

                return 20;
            } else {

                return 10 + tenBowlingCode[index + 1].charAt(0) == '-'
                        ? Integer.parseInt(String.valueOf(tenBowlingCode[index + 1].charAt(1)))
                        : Integer.parseInt(String.valueOf(tenBowlingCode[index + 1].charAt(0)));
            }
        }

        //index frame spare
        if (tenBowlingCode[index].charAt(1) == '/') {
            if (tenBowlingCode[index + 1].charAt(0) == 'X') { //index+1 frame first code == 'X' strike

                return 20;
            } else if (tenBowlingCode[index + 1].charAt(0) == '-') { //index+1 frame first code == '-' miss

                return 10;
            } else {

                return 10 + Integer.parseInt(String.valueOf(tenBowlingCode[index + 1].charAt(0))); ////index+2 frame other code
            }
        }

        //index frame  no spare or strike
        if (tenBowlingCode[index].charAt(0) == '-') {

            return Integer.parseInt(String.valueOf(tenBowlingCode[index].charAt(1)));
        } else {

            return Integer.parseInt(String.valueOf(tenBowlingCode[index].charAt(0)));
        }
    }

    public Integer getFrameScoreEqualNinthFrame(Integer index, String[] tenBowlingCode, String extraBowling) {
        Integer frameScore = 0;

        //index frame strike
        if (tenBowlingCode[index].charAt(0) == 'X') {
            if (score.containsKey(extraBowling.charAt(0))) {
                frameScore += score.get(extraBowling.charAt(0));
            }else{
                frameScore += Integer.parseInt(String.valueOf(extraBowling.charAt(0)));
            }
            if (score.containsKey(extraBowling.charAt(1))) {
                frameScore += score.get(extraBowling.charAt(1));
            }else{
                frameScore += Integer.parseInt(String.valueOf(extraBowling.charAt(1)));
            }
            return frameScore + 10;
        }

        //index frame spare
        if (tenBowlingCode[index].charAt(1) == '/') {
            if (score.containsKey(extraBowling.charAt(0))) {
                frameScore += score.get(extraBowling.charAt(0));
            }else{
                frameScore += Integer.parseInt(String.valueOf(extraBowling.charAt(0)));
            }

            return frameScore + 10;
        }

        //index frame not spare or strike
        if (tenBowlingCode[index].contains("-")) {
            if (score.containsKey(tenBowlingCode[index].charAt(0))) {
                frameScore += score.get(tenBowlingCode[index].charAt(0));
            } else {
                frameScore += Integer.parseInt(String.valueOf(tenBowlingCode[index].charAt(0)));
            }

            return frameScore;
        }

        return 0;
    }

}