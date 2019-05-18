package fr.aksel.bataille_navale_and;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int[][] Croiseur    = new int[6][3];
    static int[][] Escorteurs  = new int[5][3];
    static int[][] Torpilleurs = new int[4][3];
    static int[][] Sous_marins = new int[3][3];
    final static int nbrCroiseur    = 1;
    final static int nbrEscorteurs  = 2;
    final static int nbrTorpilleurs = 3;
    final static int nbrSous_marins = 4;
    final static int W = 14;
    final static int H = 9;
    static int[][] scene = new int[H][W];






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence
        this.setContentView(R.layout.activity_main);


        Button btn1 =(Button) findViewById(R.id.b6_4);
        //btn1.setBackgroundColor(getResources().getColor(red));

        randomGame();
        ShowScene();


    }




    public static  void randomGame(){


        // settin all boxes to 0, the initial state
        scene = initialiseScene();
        initialiseBoxes();
        fullingInTheScene();


    }

    private static void fullingInTheScene() {

        insertShip(Croiseur,nbrCroiseur);
        insertShip(Escorteurs,nbrEscorteurs);
        insertShip(Torpilleurs, nbrTorpilleurs);
        insertShip(Sous_marins,nbrSous_marins);





    }


    static int  RandomX(){
        return  (int) (Math.random() * (W-1));
    }

    static int  RandomY(){
        return  (int) (Math.random() * (H-1));
    }

    private static void initialiseBoxes() {
        Croiseur    = fullingInShipsValues(Croiseur);
        Escorteurs  = fullingInShipsValues(Escorteurs);
        Torpilleurs = fullingInShipsValues(Torpilleurs);
        Sous_marins = fullingInShipsValues(Sous_marins);


    }

    private static int[][] fullingInShipsValues(int[][] ship) {
        int width  = ship.length;
        int height = ship[0].length;
        System.out.println("width : "+width);
        System.out.println("width : "+height);

        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                if(i==0 || i==(width-1) || j==0 || j==(height-1)){
                    System.out.println("yes");
                    ship[i][j] = 5;
                } else{
                    ship[i][j] = 1;
                }


            }
        }
        return ship;
    }

    // setting all boxes to 0
    private static int[][] initialiseScene() {
        int[][] cases = new int[W][H];
        for(int i = 0 ; i < W ; i++){
            for (int j=0 ; j<H ; j++ ){
                cases[i][j]=0;
            }
        }
        return cases;
    }

    private static void ShowScene() {


        for(int i = 0 ; i < 14 ; i++){
            for (int j=0 ; j<8 ; j++ ){
                int[] column = (int[])Array.get(scene,i);
                int value = (int) Array.get(column,j);
                System.out.print(value);
            }
            System.out.print("\n");
        }

    }

    private static void insertShip(int[][] ship,int nbrInsertedCroiseur) {

        int x,y;
        while(nbrInsertedCroiseur>0){
            x = RandomX();
            y = RandomY();
            int nbrEmptyBoxes =0;
            if(x<(W-ship.length) && y<(H-ship[0].length)){
                for(int i=x ; i<(x+ship.length);i++){
                    for (int j=y; j<(y+ship[0].length); j++){
                        if(scene[i][j]==0 || scene[i][j]==5){
                            nbrEmptyBoxes++;
                        }
                    }
                }
            }


            if(nbrEmptyBoxes >= (ship.length*ship[0].length)){
                for(int i=0 ; i<ship.length;i++){
                    for (int j=0; j<ship[0].length; j++){
                        int[] column = (int[])Array.get(ship,i);
                        int value = (int) Array.get(column,j);
                        scene[i+x][j+y]=value;
                    }
                }
                nbrInsertedCroiseur--;
            }



        }
    }


    void clicked(View v){
        Button clickedBtn = findViewById(v.getId());
        clickedBtn.setText("x");
    }

}
