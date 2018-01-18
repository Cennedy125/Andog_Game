package com.example.demouser.andog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ImageView myDog;
Button myFood;
Button myWater;
Button myNap;
TextView myPetText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDog = findViewById(R.id.myDog);
        myFood = findViewById(R.id.myFood);
        myWater = findViewById(R.id.myWater);
        myNap = findViewById(R.id.myNap);
        myPetText= findViewById(R.id.myPetText);
        final ArrayList<Integer> myImageList = new ArrayList<>();
        myImageList.add(R.drawable.happy_pup);
        myImageList.add(R.drawable.very_happy_pup);
        myImageList.add(R.drawable.sad_pup);
        myImageList.add(R.drawable.tired_pup);


        //myImageView.setImageResource(myImageList.get(i));
        myDog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myPetText.setText("");
                myDog.setImageResource(myImageList.get(1));


            }


    });
    }
}
