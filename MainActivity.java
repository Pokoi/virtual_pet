///
/// Created by Jesus 'Pokoidev' Villar, jesusferminvillar@gmail.com (www.pokoidev.com)
/// On December 2018
/// Copyright © 2018 Pokoidev. Creative Commons License:
/// Attribution 4.0 International (CC BY 4.0)
///

package com.pokoidev.virtual_pet;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region PARAMETERS

    //······························································································
    // public


    //······························································································
    //private

    /**
     * The tag for the debug
     * This tag has the canonical name of this class
     */
    private static final String TAG = MainActivity.class.getCanonicalName();


    /**
     * The images of the pet
     */
    private ImageView alert_image;
    private ImageView body_image;
    private ImageView eyes_image;
    private ImageView mouth_image;

    /**
     * The buttons of the game
     */
    private ImageButton eat_button;
    private ImageButton drink_button;
    private ImageButton wash_button;
    private ImageButton sleep_button;

    /**
     * The buttons of the arrows
     */
    private ImageButton left_arrow_body;
    private ImageButton right_arrow_body;
    private ImageButton left_arrow_eyes;
    private ImageButton right_arrow_eyes;
    private ImageButton left_arrow_mouth;
    private ImageButton right_arrow_mouth;


    /**
     * The total number of needs
     */
    private static final byte NEEDS = 4;

    /**
     * Array with the body drawables
     */
    private Drawable [] body_images;

    /**
     * The current body drawable index
     */
    private int current_body;

    /**
     * Array with the eyes drawables
     */
    private Drawable [] eyes_images;

    /**
     * The current body drawable index
     */
    private int current_eyes;

    /**
     * Array with the mouth drawables
     */
    private Drawable [] mouth_images;

    /**
     * The current body drawable index
     */
    private int current_mouth;

    /**
     * The pet
     * Creates a pet object by calling the Pet's class builder method
     */
    Pet my_pet = new Pet();

    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region METHODS

    //······························································································
    // public


    //······························································································
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the buttons reference
        SetButtons();

        //Set the images reference
        SetImages();

        //Initialize the arrays values
        InitializeArrays();

        //Set the random initial image to the pet images
        RandomImages();

        //Set the on click events on the buttons
        ButtonsOnClickListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Creates a random need and the Alert
        Alert(RandomNeed());
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Starts the countdown
        StartsCountdown();

    }

    /**
     * Set the reference of the buttons
     */
    private void SetButtons(){

        /**
         * The buttons of the game
         */
        eat_button=findViewById(R.id.eat);
        drink_button=findViewById(R.id.drink);
        wash_button=findViewById(R.id.wash);
        sleep_button=findViewById(R.id.sleep);

        left_arrow_body=findViewById(R.id.l_arrow_body);
        right_arrow_body=findViewById(R.id.r_arrow_body);
        left_arrow_eyes=findViewById(R.id.l_arrow_eyes);
        right_arrow_eyes=findViewById(R.id.r_arrow_eyes);
        left_arrow_mouth=findViewById(R.id.l_arrow_mouth);
        right_arrow_mouth=findViewById(R.id.r_arrow_mouth);


    }

    /**
     * set the images of the pet
     */
    private void SetImages(){

        /**
         * The images of the pet
         */
        alert_image=findViewById(R.id.alert_image);
        body_image=findViewById(R.id.body);
        eyes_image=findViewById(R.id.eyes);
        mouth_image=findViewById(R.id.mouth);

        alert_image.setVisibility(View.INVISIBLE);
    }

    /**
     * Creates the Alert
     * This method set visible the drawable of the Alert
     * If there is no need, the drawable is invisible
     */
    private void Alert(String type){


        if(type!= null) {

            //Depends on the need
            switch (type) {

                case "hunger":
                    alert_image.setImageDrawable(getDrawable(R.drawable.alerta_comida));
                    alert_image.setVisibility(View.VISIBLE);
                    break;

                case "thirst":
                    alert_image.setImageDrawable(getDrawable(R.drawable.alerta_sed));
                    alert_image.setVisibility(View.VISIBLE);
                    break;

                case "sleepy":
                    alert_image.setImageDrawable(getDrawable(R.drawable.alerta_suenio));
                    alert_image.setVisibility(View.VISIBLE);
                    break;

                case "dirt":
                    alert_image.setImageDrawable(getDrawable(R.drawable.alerta_suciedad));
                    alert_image.setVisibility(View.VISIBLE);
                    break;

                case "none":
                    alert_image.setVisibility(View.INVISIBLE);
                    break;
            }
        }

    }

    /**
     * Method that returns a random need
     */
    private String RandomNeed(){
        int random = new Random().nextInt(NEEDS);
        String to_return = " ";

        if(random == 0){
            my_pet.hunger = my_pet.ALERT_VALUE;
            to_return = "hunger";
        }
        else if(random == 1){
            my_pet.thirst = my_pet.ALERT_VALUE;
            to_return = "thirst";
        }
        else if(random == 2){
            my_pet.dirt = my_pet.ALERT_VALUE;
            to_return = "dirt";
        }
        else if(random == 3){

            my_pet.sleepy = my_pet.ALERT_VALUE;
            to_return = "sleepy";
        }

        return to_return;
    }

    /**
     * Apply the modification when a left arrow is activated
     */
    private int LeftArrow(int initial_value, int array_length){

        //Decreases the given value
        initial_value -= 1;

        //Reset the value if it's out of array range
        if(initial_value < 0){
            initial_value = (array_length-1);
        }

        return initial_value;
    }

    /**
     * Apply the modification when a left arrow is activated
     */
    private int RigthArrow(int initial_value, int array_length){

        //Increases the given value
        initial_value += 1;

        //Reset the value if it's out of array range
        if(initial_value >= array_length-1){
            initial_value = 0;
        }

        return initial_value;
    }

    /**
     * Method that initialize the arrays
     */
    private void InitializeArrays(){

        //The array of images for the body of the pet
        body_images = new Drawable[]{
                getDrawable(R.drawable.b01),
                getDrawable(R.drawable.b02),
                getDrawable(R.drawable.b03),
                getDrawable(R.drawable.b04),
                getDrawable(R.drawable.b05)
        };

        //The array of images for the eyes of the pet
        eyes_images = new Drawable[]{
                getDrawable(R.drawable.e01),
                getDrawable(R.drawable.e02),
                getDrawable(R.drawable.e03),
                getDrawable(R.drawable.e04),
                getDrawable(R.drawable.e05)
        };

        //The array of images for the mouth of the pet
        mouth_images = new Drawable[]{
                this.getDrawable(R.drawable.m01),
                this.getDrawable(R.drawable.m02),
                this.getDrawable(R.drawable.m03),
                this.getDrawable(R.drawable.m04),
                this.getDrawable(R.drawable.m05)
        };
    }

    /**
     * Set the random initial current index of the images
     */
    private void RandomImages(){

        //Give a random value to the index of the images
        current_body = new Random().nextInt((body_images.length - 1));
        current_eyes = new Random().nextInt((eyes_images.length - 1));
        current_mouth = new Random().nextInt((mouth_images.length - 1));

        //Assing the images
        body_image.setImageDrawable(body_images[current_body]);
        eyes_image.setImageDrawable(eyes_images[current_eyes]);
        mouth_image.setImageDrawable(mouth_images[current_mouth]);


    }

    /**
     * Set the on click listener events to the buttons of the app
     */
    private void ButtonsOnClickListener(){

        ////////////////////////////////////////////////////////////////////////////////////////////
        //region Action buttons


        //The button for Eat
        eat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Check what is the current need
                String need = my_pet.CheckStatus();


                if (need.equals("hunger")) {

                    //Call the pet's Eat method
                    my_pet.Eat();

                    Alert("none");
                }
            }
        });

        //The button for Drink
        drink_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Check what is the current need
                String need = my_pet.CheckStatus();


                if (need.equals("thirst")) {

                    //Call the pet's Drink method
                    my_pet.Drink();

                    Alert("none");
                }
            }
        });

        //The button for sleep
        sleep_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Check what is the current need
                String need = my_pet.CheckStatus();

                if (need.equals("sleepy")) {

                    //Call the pet's Sleep method
                    my_pet.Sleep();

                    Alert("none");
                }
            }
        });

        //The button for Wash
        wash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Check what is the current need
                String need = my_pet.CheckStatus();

                if (need.equals("dirt")) {

                    //Call the pet's Wash method
                    my_pet.Wash();

                    Alert("none");
                }

            }
        });

        //endregion
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //region Image selection buttons

        //The left arrow of the body images
        left_arrow_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Updates the current index
                current_body = LeftArrow(current_body, body_images.length);

                //Set the new image
                body_image.setImageDrawable(body_images[current_body]);


            }
        });

        //The right arrow of the body images
        right_arrow_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Updates the current index
                current_body = RigthArrow(current_body, body_images.length);

                //Set the new image
                body_image.setImageDrawable(body_images[current_body]);

            }
        });

        //The left arrow of the eyes images
        left_arrow_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Updates the current index
                current_eyes = LeftArrow(current_eyes, eyes_images.length);

                //Set the new image
                eyes_image.setImageDrawable(eyes_images[current_eyes]);

            }

        });

        //The right arrow of the eyes images
        right_arrow_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Updates the current index
                current_eyes = RigthArrow(current_eyes, eyes_images.length);

                //Set the new image
                eyes_image.setImageDrawable(eyes_images[current_eyes]);

            }

        });

        //The left arrow of the mouth images
        left_arrow_mouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Updates the current index
                current_mouth = LeftArrow(current_mouth, mouth_images.length);

                //Set the new image
                mouth_image.setImageDrawable(mouth_images[current_mouth]);
            }

        });

        //The right arrow of the mouth images
        right_arrow_mouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Updates the current index
                current_mouth = RigthArrow(current_mouth, mouth_images.length);

                //Set the new image
                mouth_image.setImageDrawable(mouth_images[current_mouth]);
            }

        });

        //endregion
        ////////////////////////////////////////////////////////////////////////////////////////////

    }

    /**
     * Starts the countdown
     */
    private void StartsCountdown(){
        Intent intent = new Intent(getBaseContext(), CountdownService.class);
        startService(intent);
    }

    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
