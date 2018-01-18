package com.example.demouser.andog_local;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int happiness = 100;
    int hunger = 100;
    int happyHunger = 0;
    TextView textView;
    TextView textView2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        textView.setText("Hunger: "+hunger);
        textView2.setText("Happiness: "+happiness);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hunger < 100) {
                    hunger += 1;
                    happyHunger -= 1;
                    textView.setText("Hunger: " + hunger);
                    if(hunger == 100){
                        happyHunger = 0;
                        happiness = 100;
                        textView2.setText("Happiness: "+happiness);
                    }
                }
            }
        });

        statDecrease();




    }

    private void statDecrease(){


        if(hunger > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hunger -= 1;
                    textView.setText("Hunger: " + hunger);
                    happyHunger += 1;
                    setHappiness();
                    statDecrease();
                }
            },1000);
        }
    }

    private void setHappiness (){
        if (happiness > 0 && happyHunger % 2 == 0){
            happiness -= 1;
            textView2.setText("Happiness: "+happiness);
        }
    }
}
