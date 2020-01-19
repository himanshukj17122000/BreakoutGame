package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BrickWall {
    public static Brick[][] wall;

    public void main(){
    try {
        File file = new File("Level1.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            if(sc.hasNextInt()){
                int hits= sc.nextInt();
                for(int i=0;i<4;i++){
                    for(int j=0;j<6;j++){
                        if(hits==1){
                            bomb bo= new bomb();
                            wall[i][j]= bo;

                        }
                        if(hits==2){
                            normalbr norm= new normalbr();
                            wall[i][j]= norm;
                        }
                        if(hits==3){
                            powerup power= new powerup();
                            wall[i][j]= power;
                        }
                        if(hits==4){
                            stone sto= new stone();
                            wall[i][j]= sto;
                        }
                        if(hits==0){

                        }
                    }
                }
            }
        }

//        while (scanner.hasNext()) {
//            if (scanner.hasNextInt()) {
//                integers.add(scanner.nextInt());
//            } else {
//                scanner.next();
//            }
//        }


    } catch (
    FileNotFoundException e) {
        //do something with e, or handle this case
    }
    }

}
