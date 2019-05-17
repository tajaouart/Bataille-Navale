package fr.aksel.bataille_navale_and;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import static fr.aksel.bataille_navale_and.R.color.red;

public class MainActivity extends AppCompatActivity {

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


    }




    void randomScen(){


    }

    void clicked(View v){
        Button clickedBtn = findViewById(v.getId());
        clickedBtn.setText("x");
    }

}
