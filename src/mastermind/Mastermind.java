package mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Mastermind {
    public static String globalColours = "RGBYPO";
    
    public static int getIndex(Character c){
        for(int i=0;i<globalColours.length();i++)
            if(globalColours.charAt(i)==c)
                return i;
        return 0;
    }
    
    public static Ball[] shuffle(Ball balls[],int ballIndex[]){
        ArrayList<Character> colors = new ArrayList();
        for(int i=0;i<balls.length;i++)
            colors.add(balls[i].getColor());
        Collections.shuffle(colors);
        for(int i=0;i<balls.length;i++){
            balls[i].setColor(colors.get(i));
        }
        return balls;
    }
    
    public static String printBalls(Ball[] balls){
        String out="";
        for(int i=0;i<balls.length;i++)
            out= out + Character.toString(balls[i].getColor());
        return out;
    }
    
    public static void main(String[] args) {
        int ballIndex[]=new int[4];
        Ball b1=new Ball();
        b1.setColor(globalColours.charAt(ballIndex[0]));
        Ball b2=new Ball();
        b2.setColor(globalColours.charAt(ballIndex[0]));
        Ball b3=new Ball();
        b3.setColor(globalColours.charAt(ballIndex[0]));
        Ball b4=new Ball();
        b4.setColor(globalColours.charAt(ballIndex[0]));
        Ball balls[] =new Ball[4];
        balls[0]=b1;
        balls[1]=b2;
        balls[2]=b3;
        balls[3]=b4;
        Check checker = new Check();
        String comparedFlags=checker.checkBalls(balls);
      //  System.out.println("Try count is "+ checker.tryCount + " and its generated placement of balls is " + printBalls(balls));
      //  System.out.println(checker.blackCount(comparedFlags));
        if(checker.blackCount(comparedFlags)<4){
            while(true){
                while(checker.whiteCount(comparedFlags)>0){
             //       System.out.println(checker.getAdminColor());
             //       System.out.println(comparedFlags);
                    ArrayList<Character> tempColors = new ArrayList();
                    for (Ball ball : balls) {
                        tempColors.add(ball.getColor());
                    }
                    Collections.shuffle(tempColors);
                 //   System.out.println(tempColors.toString());
                    for(int i=0;i<balls.length;i++){
                        balls[i].setColor(tempColors.get(i));
                        ballIndex[i]=getIndex(tempColors.get(i));
                    }
                    comparedFlags=checker.checkBalls(balls);  
                   // System.out.println("Try count is "+ checker.tryCount + " and its generated placement of balls is " + printBalls(balls));
                }
                int blackCount=checker.blackCount(comparedFlags);
                for(int i=blackCount;i<ballIndex.length;i++)
                    ballIndex[i]=(ballIndex[i]+1)%6;
                b1.setColor(globalColours.charAt(ballIndex[0]));
                b2.setColor(globalColours.charAt(ballIndex[1]));
                b3.setColor(globalColours.charAt(ballIndex[2]));
                b4.setColor(globalColours.charAt(ballIndex[3]));
                comparedFlags=checker.checkBalls(balls);
        //        System.out.println("Try count is "+ checker.tryCount + " and its generated placement of balls is " + printBalls(balls));
       //         System.out.println(comparedFlags);
                if(checker.blackCount(comparedFlags)==4)
                    break;
            }            
        }    
        System.out.println("The bot won after " + checker.tryCount + " tries!");
        System.out.println("Random placement of balls:"  + checker.getAdminColor());
    }
}
