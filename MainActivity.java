package com.example.demouser.andog;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
ImageView myDog;
Button myFood;
Button myWater;
Button myNap;
TextView myPetText;
TextView textView;
TextView textView2;
TextView textView3;
TextView textView7;
int happiness = 100;
int hunger = 100;
float energy = 100;
boolean isTired = false;
int thirst = 100;
String dogBreed = "retriever";
private HashMap<String, ArrayList> breeds = new HashMap<>();
private ArrayList<Integer> myImageList = new ArrayList<>();

//to implement

    //have other stats effect happiness as they decrease

    //in set happiness, can it be generalized to set happiness for everything?
    // if at least 1 stat == 100 increase happiness

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDog = findViewById(R.id.myDog);
        myFood = findViewById(R.id.myFood);
        myWater = findViewById(R.id.myWater);
        myNap = findViewById(R.id.myNap);
        myPetText = findViewById(R.id.myPetText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView7 = findViewById(R.id.textView7);
        myImageList.add(R.drawable.happy_pup);
        myImageList.add(R.drawable.very_happy_pup);
        myImageList.add(R.drawable.sad_pup);
        myImageList.add(R.drawable.tired_pup);
        breeds.put(dogBreed,myImageList);


        //myImageView.setImageResource(myImageList.get(i));
        myDog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isTired) {
                    myPetText.setText("");
                    myDog.setImageResource((int) breeds.get(dogBreed).get(1));
                    if (happiness <= 95) {
                        happiness += 5;
                    } else {
                        happiness = 100;
                    }
                    textView2.setText("Happiness: " + happiness);
                    if (energy >= 5) {
                        energy -= 5;
                        textView3.setText("Energy: " + energy);
                    }


                }
            }


        });

        textView.setText("Hunger: " + hunger);
        textView2.setText("Happiness: " + happiness);
        textView3.setText("Energy "+ energy);
        textView7.setText("Thirst: "+thirst);

        myFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hunger < 100) {
                    hunger += 1;
                    textView.setText("Hunger: " + hunger);
                    if (hunger == 100) {
                        if(happiness <= 95){
                            happiness+=5;
                        } else {
                            happiness = 100;
                        }
                        textView2.setText("Happiness: " + happiness);
                    }
                }
            }
        });

        //water button functions like food one
        myWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thirst < 100){
                    thirst += 1;
                    textView7.setText("Thirst: "+thirst);
                    if (thirst == 100){
                        if(happiness <= 95){
                         happiness+=5;
                        } else {
                            happiness = 100;
                        }
                        textView2.setText("Happiness: "+ happiness);
                    }
                }
            }

    });

        myNap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTired = true;
                myFood.setEnabled(false);
                myWater.setEnabled(false);
                myDog.setImageResource((int) breeds.get(dogBreed).get(3));
                //delay
                nap();
            }
        });
        //nap button locks buttons and ?stats?
        //for duration of nap
        //counts upwards until energy is full

        statDecrease();

    }
    private void nap(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (energy < 97){
                    energy+=3;
                    nap();
                }
            }
        }, 1000);

        if (energy >= 97){
            energy = 100;
            textView3.setText("Energy: "+energy);
            isTired = false;
            myWater.setEnabled(true);
            myFood.setEnabled(true);

        }

    }
    private void statDecrease(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hunger > 0) {
                    hunger -= 1;
                    textView.setText("Hunger: " + hunger);
                }
                if (energy > 0){
                    energy -= 0.5;
                    textView3.setText("Energy: "+ energy);
                    if (energy < 20){
                        isTired = true;
                    }
                    Log.d("KAN", ""+isTired);
                }
                if (thirst > 0){
                    thirst -= 1;
                    textView7.setText("Thirst: "+thirst);
                }
                setHappiness();
                statDecrease();
            }
        },1000);
    }

    private void setHappiness (){
        //if tired is false
        if (happiness > 0 && hunger % 2 == 0){
            happiness -= 1;
            textView2.setText("Happiness: "+happiness);
            }
        if (!isTired) {
            if (happiness < 70) {
                myDog.setImageResource((int) breeds.get(dogBreed).get(2));
            } else {
                myDog.setImageResource((int) breeds.get(dogBreed).get(0));
            }
        } else {
            myDog.setImageResource((int) breeds.get(dogBreed).get(3));
        }

    }
}
