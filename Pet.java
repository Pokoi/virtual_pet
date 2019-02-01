///
/// Created by Jesus 'Pokoidev' Villar, jesusferminvillar@gmail.com (www.pokoidev.com)
/// On December 2018
/// Copyright © 2018 Pokoidev. Creative Commons License:
/// Attribution 4.0 International (CC BY 4.0)
///

package com.pokoidev.virtual_pet;

public class Pet {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region PARAMETERS

    //······························································································
    // public


    //······························································································
    //private

    /**
     * The minimum value of the parameters
     */
    private static final byte MIN_PARAMETER_VALUE = 0;

    /**
     * The maximum value of the parameters
     */
    private static final byte MAX_PARAMETER_VALUE = 1;

    /**
     * Minimum value to have a needed
     */
    public static final byte ALERT_VALUE = 1;

    /**
     * The hunger level of the creature
     */
    public int hunger;

    /**
     * The thirst level of the creature
     */
    public int thirst;

    /**
     * The dirt level of the creature
     */
    public int dirt;

    /**
     * The sleep level of the creature
     */
    public int sleepy;


    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region METHODS

    //······························································································
    // public


    Pet(){

        hunger = thirst = dirt= sleepy = MIN_PARAMETER_VALUE;
    }


    /**
     * Method that manages the events when the user taps on Eat Button
     */
   void Eat(){

        //Eating decreasses hunger
        hunger = ModifyParameter(this.hunger, -1);

    }

    /**
     * Method that manages the events when the user taps on Drink Button
     */
    void Drink(){

        //Drinking decreasses thirst
        thirst = ModifyParameter(this.thirst, -1);
    }

    /**
     * Method that manages the events when the user taps on Sleep Button
     */
    void Sleep(){

        //Sleeping decreasses sleep
        sleepy = ModifyParameter(this.sleepy, -1);
    }

    /**
     * Method that manages the events when the user taps on Wash Button
     */
    void Wash(){

        //Washing decreasses dirt
        dirt = ModifyParameter(this.dirt, -1);

    }

    /**
     * Method that controls what parameter is more needed     *
     */
    public String CheckStatus(){

        String to_return = "none";

        if(hunger >= ALERT_VALUE){
            to_return = "hunger";
        }
        else if (thirst >= ALERT_VALUE){
            to_return = "thirst";
        }
        else if(dirt >= ALERT_VALUE){
            to_return = "dirt";
        }
        else if(sleepy >= ALERT_VALUE){
            to_return = "sleepy";
        }

        return to_return;
    }

    //······························································································
    //private

    /**
     * Method that modify a parameter returning the modification
     */
    private int ModifyParameter(int initial_value, int amount){

        int value_to_return = initial_value + amount;

        if(value_to_return > MAX_PARAMETER_VALUE){
            value_to_return = MAX_PARAMETER_VALUE;
        }
        else if(value_to_return <MIN_PARAMETER_VALUE){
            value_to_return = MIN_PARAMETER_VALUE;
        }

        return value_to_return;
    }


    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

}
