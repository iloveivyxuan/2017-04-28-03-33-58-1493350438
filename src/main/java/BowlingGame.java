public class BowlingGame {
    public int[] pt = new int[24];
    public int findNot(int x){
        for(int i=x;;i++) if(pt[i]!=-1) return i;
    }
    public int getBowlingScore(String bowlingCode) {
        int sum = 0;
        for (int i = 0; i < 22; i++)
            pt[i] = 0;
        int len = bowlingCode.length();
        for (int i = 0, j = 0, k = 0; j < len; j++) {
            switch (bowlingCode.charAt(j)) {
                case 'X':
                    pt[i + k] = 10;
                    pt[i + k + 1]=-1;
                    break;
                case '/':
                    pt[i + k] = 10 - pt[i];
                    break;
                case '-':
                    pt[i + k] = 0;
                    k++;
                    break;
                case '|':
                    if(i<20)
                        i += 2;
                    k = 0;
                    break;
                default:
                    pt[i + k] = bowlingCode.charAt(j) - '0';
                    k++;
                    break;
            }
        }
        for(int i=0;i<10;i++){
            if(pt[i*2]==10){
                int t=findNot(i*2+1);
                sum=sum+pt[i*2]+pt[findNot(t+1)]+pt[t];
            }else if(pt[i*2]+pt[i*2+1]==10){
                sum=sum+10+pt[findNot((i+1)*2)];
            }else{
                sum=sum+pt[i*2]+pt[i*2+1];
            }
        }
        return sum;
    }
}