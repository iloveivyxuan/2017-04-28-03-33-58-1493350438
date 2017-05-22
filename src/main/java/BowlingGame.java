public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {       
        //System.out.println(recordsArray);

        String[] recordsArray = bowlingCode.split("|");
        List<Int> extend = new ArrayList<Int>();

        int scores = 0;
        int len = bowlingCode.length();
        int[] frameScore = new int[10];

        for (int charIndex = 0, framIndex = 0, IndexInFrame = 1; charIndex < len; charIndex++) {
            switch (bowlingCode.charAt(charIndex)) {
                case '|':
                    charIndex += 1;
                    framIndex += 1;
                    IndexInFrame = 1;
                    break;
                case 'X':
                    frameScore[framIndex] = 10;
                    extend.add(charIndex + 1);
                    extend.add(charIndex + 2);
                    charIndex += 1;
                case '-':
                    charIndex += 1;
                    IndexInFrame += 1;
                case '/':
                    frameScore[framIndex] = 10;
                    extend.add(charIndex + 1);
                    charIndex += 1;
                default:
                    if (IndexInFrame == 1) {
                        frameScore[framIndex] = bowlingCode.charAt(charIndex) - '0';
                    } else {
                        frameScore[framIndex] += bowlingCode.charAt(charIndex) - '0';
                    }
                    IndexInFrame += 1;
                    charIndex += 1;

            }
        }

        for (int i = 0; i < 10; i++) {
            scores = scores + frameScore[i];
        }

        for (int i = 0; i < extend.size(); i++) {
            if (bowlingCode.charAt(extend[i]) == 'X') {
                scores = scores + 10; 
            } else if (bowlingCode.charAt(extend[i]) == '-') {
                scores = scores + 0;
            } else if (bowlingCode.charAt(extend[i]) == '/') {
                scores = scores + 10 - bowlingCode.charAt(extend[i] - 1);
            } else {
                scores = scores + (bowlingCode.charAt(i) - '0');
            }
        }

        return scores;
    }
}
