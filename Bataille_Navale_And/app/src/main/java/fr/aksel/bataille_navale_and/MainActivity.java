package fr.aksel.bataille_navale_and;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    // declaration of ships
    static int[][] Croiseur    = new int[6][3];
    static int[][] Escorteurs  = new int[5][3];
    static int[][] Torpilleurs = new int[4][3];
    static int[][] Sous_marins = new int[3][3];
    // number of each ship that will be inserted
    final static int nbrCroiseur    = 1;
    final static int nbrEscorteurs  = 2;
    final static int nbrTorpilleurs = 3;
    final static int nbrSous_marins = 4;
    // scene dimensions WIDTH and HEIGHT
    final static int W = 14;
    final static int H = 9;
    // the scene of the game
    static int[][] scene = new int[H][W];



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Create the game
        randomGame();



        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence
        this.setContentView(R.layout.activity_main);

    }

    // generate a random scene
    public static  void randomGame(){
        // settin all boxes in the scene to 0, the initial state
        scene = initialiseScene();
        // initilise the ships
        initialiseShips();
        // inserting the ships into the scene
        fullingInTheScene();
    }
    private static void fullingInTheScene() {
        // insert each ship in the scene with the mentioned number of times
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

    private static void initialiseShips() {
        Croiseur    = fullingInShipsValues(Croiseur);
        Escorteurs  = fullingInShipsValues(Escorteurs);
        Torpilleurs = fullingInShipsValues(Torpilleurs);
        Sous_marins = fullingInShipsValues(Sous_marins);


    }

    private static int[][] fullingInShipsValues(int[][] ship) {
        int width  = ship.length;
        int height = ship[0].length;
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                if(i==0 || i==(width-1) || j==0 || j==(height-1)){
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

    // insert the ship into the scene
    private static void insertShip(int[][] aShip,int nbrInsertedShips) {

        int x,y;
        int [][] ship = aShip;
        int nbrEmptyBoxes;
        while(nbrInsertedShips>0){
            x = RandomX();
            y = RandomY();
            nbrEmptyBoxes =0;
            // rotating or not the ship depending on the random value
            if((int)(Math.random())*100 % 2==0){
                ship = rotateShip(ship);
            }


            // verifying that there is enough space to insert the ship
            if(x<(W-ship.length) && y<(H-ship[0].length)){
                for(int i=x ; i<(x+ship.length);i++){
                    for (int j=y; j<(y+ship[0].length); j++){
                        if(scene[i][j]==0 || scene[i][j]==5){
                            nbrEmptyBoxes++;
                        }
                    }
                }
            }

            // if place is ok to insert then inert
            if(nbrEmptyBoxes >= (ship.length*ship[0].length)){
                for(int i=0 ; i<ship.length;i++){
                    for (int j=0; j<ship[0].length; j++){
                        int[] column = (int[])Array.get(ship,i);
                        int value = (int) Array.get(column,j);
                        scene[i+x][j+y]=value;
                    }
                }
                nbrInsertedShips--;
            }
        }
    }

    // rotate Right 90°
    private static int[][] rotateShip(int[][] ship) {
        int width  = ship.length;
        int height = ship[0].length;
        int [][] verticalShip = new int[height][width];
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                int[] column = (int[])Array.get(ship,i);
                int value = (int) Array.get(column,j);
                verticalShip[j][i] = value;
            }
        }
        return verticalShip;
    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void clicked(View v){
        Button clickedBtn = findViewById(v.getId());
        int indexBtn = clickedBtn.getImeActionId();
        int x=0,y=0;
        // convert the index to x,y coordinates
        while((indexBtn-13)>0){
            indexBtn = indexBtn - 13;
            y++;
        }
        x=indexBtn;
        if(scene[x][y]==1){
            //clickedBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            Drawable fire = ContextCompat.getDrawable(this, R.drawable.fireicon);
            clickedBtn.setBackground(fire);

        } else{
            clickedBtn.setText(".");
            clickedBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
            //clickedBtn.setVisibility(View.INVISIBLE);
            clickedBtn.setBackgroundColor(android.R.attr.colorError);
        }
    }

}
