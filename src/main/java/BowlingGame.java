public class BowlingGame {


    public int getBowlingScore(String bowlingCode) {

            if (bowlingCode == "X|7/|9-|X|-8|8/|-6|X|X|X||81") {
                
            }

        int totalScore = 0;

        StringBuffer stringBuffer = new StringBuffer(bowlingCode);
        for (int i = 0; i < stringBuffer.length(); i ++){
            if (stringBuffer.charAt(i) == '|'){
                stringBuffer.setCharAt(i, ' ');
            }
            if (stringBuffer.charAt(i) == '-'){
                stringBuffer.setCharAt(i,'0');
            }
        }
        String S = stringBuffer.toString() + "     ";

        char[] chars = S.toCharArray();
        int[] after = new int[33];

        for (int i = 0; i < chars.length; i ++){

            //strike
            if (chars[i] == 'X'){
                //X*/
                if (chars[i+3] == '/'){
                    after[i] = 20;
                }
                //XX*
                if (chars[i+2] == 'X' && chars[i+4] != ' '){
                    //XXX
                    if (chars[i+4] == 'X'){
                        after[i] = 30;
                    }
                    else {
                        after[i] = 20 + chars[i+4];
                    }
                }
                //第九格子
                if (chars[i+2] == 'X' && chars[i+4] == ' '){
                    if (chars[i+5] == 'X'){
                        after[i] = 30;
                    }
                    else if (chars[i+5] != ' '){
                        after[i] = 20 + chars[i+5] - '0';
                    }
                }
                else if (chars[i+2] != ' ' && chars[i+3] != '/' && chars[i+2] != 'X'){
                    after[i] = 10 + chars[i+2] + chars[i+3] - '0' - '0';
                }
            }

            //spare
            if (chars[i] == '/'){
                ///X*
                if (chars[i+2] == 'X'){
                    after[i-1] = 20;
                }
                else {
                    after[i-1] = 10 + chars[i+2] - '0';
                }
            }

            //last
            if (chars[i] !=' ' && chars[i+1] == ' ' && chars[i+2] == ' '){
                //X**
                if (chars[i] == 'X'){
                    //XX*
                    if (chars[i+3] == 'X'){
                        //XXX
                        if (chars[i+4] == 'X'){
                            after[i] = 30;
                        }
                        //XX*
                        else {
                            after[i] = 20 + chars[i+5];
                        }
                    }else if (chars[i+3] != ' '){
                        after[i] = 10 + chars[i+3] + chars[i+4] - '0' - '0';
                    }
                }
                ///**
                if (chars[i] == '/'){
                    ///X*
                    if (chars[i+3] == 'X'){
                        after[i-1] = 20;
                    }
                    ///**
                    else {
                        after[i-1] = 10 + chars[i+3] - '0';
                    }
                }
            }

            //others
            if (chars[i] != ' ' && chars[i] != 'X' &&chars[i] != '/'){
                if (chars[i+1] == '0'){
                    after[i] = chars[i] - '0';
                }
                if (chars[i] == '0' && chars[i+1] != ' '){
                    after[i] = chars[i+1] - '0';
                }
            }
        }

        for (int i = 0; i < 33; i++) {
            totalScore = totalScore + after[i];
        }
        return totalScore;
    }