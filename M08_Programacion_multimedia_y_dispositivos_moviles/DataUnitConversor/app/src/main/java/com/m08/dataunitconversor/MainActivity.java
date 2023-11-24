package com.m08.dataunitconversor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dataunitconversor.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView textViewUnit1_JOO;  // TextView to display the first selected unit
    private TextView textViewUnit2_JOO;  // TextView to display the second selected unit
    private Button buttonLaunchUnit1_JOO;  // Button for launching the first unit conversion
    private Button buttonLaunchUnit2_JOO;  // Button for launching the second unit conversion
    private Spinner selectorUnit1_JOO;  // Spinner to select the first unit for conversion
    private Spinner selectorUnit2_JOO;  // Spinner to select the second unit for conversion
    private EditText textInputUnit1_JOO;  // EditText for inputting the value to be converted
    private TextView textViewResult1_JOO;  // TextView to display the result of the unit conversion
    private ImageView imageUnit_JOO;  // ImageView for displaying an image associated with the conversion result

    @Override
    protected void onCreate(Bundle savedInstanceState_JOO) {
        super.onCreate(savedInstanceState_JOO);

        // Set the content view to the "main" layout defined in XML.
        // There are two main.xml, but one is for land and the other for vertical disposition
        setContentView(R.layout.main);

        // Initialize sample units created on the Unit class
        Unit.getSampleUnits();

        // Initialize UI elements
        textViewUnit1_JOO = findViewById(R.id.textViewUnit1);
        textViewUnit2_JOO = findViewById(R.id.textViewUnit2);

        buttonLaunchUnit1_JOO = findViewById(R.id.buttonLaunchUnit1);
        buttonLaunchUnit2_JOO = findViewById(R.id.buttonLaunchUnit2);

        selectorUnit1_JOO = findViewById(R.id.selectorUnit1);
        selectorUnit2_JOO = findViewById(R.id.selectorUnit2);

        textInputUnit1_JOO = findViewById(R.id.textInputUnit1);

        textViewResult1_JOO = findViewById(R.id.textViewResult1);

        imageUnit_JOO = findViewById(R.id.imageUnit);
        imageUnit_JOO.setImageResource(R.drawable.p1); // To change set the image on every view create

        // Creating an adapter for the unit selection spinners
        ArrayList<String> unitNames_JOO = new ArrayList<>();
        unitNames_JOO.add(""); // For making the first selectable unit blank
        unitNames_JOO.addAll(Unit.getSampleUnitNames()); // Adding before the rest of the units
        ArrayAdapter<String> unitNameAdapter_JOO = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, unitNames_JOO);

        selectorUnit1_JOO.setAdapter(unitNameAdapter_JOO);
        selectorUnit2_JOO.setAdapter(unitNameAdapter_JOO);

        // Setting up onClick listeners for the first conversion button
        buttonLaunchUnit1_JOO.setOnClickListener(view_JOO -> performConversion(view_JOO, true));

        // Setting up onClick listener for the second conversion button
        buttonLaunchUnit2_JOO.setOnClickListener(view_JOO -> performConversion(view_JOO, false));
    }

    /**
     * Performs a unit conversion based on the selected units and input value
     *
     * @param view_JOO The view to which the conversion is related
     * @param unit1ToUnit2_JOO A boolean flag indicating the direction of the conversion
     *   If true, the conversion is from unit1 to unit2; otherwise, it's from unit2 to unit1
     */
    private void performConversion(View view_JOO, boolean unit1ToUnit2_JOO) {
        String input1_JOO = selectorUnit1_JOO.getSelectedItem().toString();
        String input2_JOO = selectorUnit2_JOO.getSelectedItem().toString();
        String textInput_JOO = textInputUnit1_JOO.getText().toString();

        // Display a Snackbar message based on input and conversion parameters

        Unit unit1_JOO = Unit.getUnitByName(input1_JOO);
        Unit unit2_JOO = Unit.getUnitByName(input2_JOO);

        // Set labels for units based on conversion direction
        if (unit1ToUnit2_JOO) {
            appearSnackbar(view_JOO, input1_JOO, input2_JOO, textInput_JOO);
            textViewUnit1_JOO.setText(unit1_JOO.getAbbreviation_JOO());
            textViewUnit2_JOO.setText(unit2_JOO.getAbbreviation_JOO());
        } else {
            appearSnackbar(view_JOO, input2_JOO, input1_JOO, textInput_JOO);
            textViewUnit1_JOO.setText(unit2_JOO.getAbbreviation_JOO());
            textViewUnit2_JOO.setText(unit1_JOO.getAbbreviation_JOO());
        }

        // Perform the unit conversion and display the result
        String result_JOO = unit1ToUnit2_JOO ? unit1_JOO.convertTo(textInput_JOO, unit2_JOO) : unit2_JOO.convertTo(textInput_JOO, unit1_JOO);
        textViewResult1_JOO.setText(result_JOO);

        // Change the image based on the conversion result
        changeImage(result_JOO);
    }

    /**
     * Changes the image displayed based on the result of the unit conversion
     *
     * @param result_JOO The result of the unit conversion, which determines the image to be shown
     */
    private void changeImage(String result_JOO) {
        if (result_JOO.equals("Invalid input")) {
            // If the result is "Invalid input," set the image to p10
            imageUnit_JOO.setImageResource(R.drawable.p10);
        // If the result is decimal apply the else content
        } else if (result_JOO.contains(",")) {
            // If the result contains an exponent (e.g., 2.5e3), parse and determine the image based on the exponent value
            if (result_JOO.contains("e")) {
                String[] exponentParts_JOO = result_JOO.split("e[\\+\\-]");
                int exponent_JOO = Integer.parseInt(exponentParts_JOO[1]);

                // Change the image based on the exponent value
                if (1 <= exponent_JOO && exponent_JOO < 4) imageUnit_JOO.setImageResource(R.drawable.p3);
                else if (4 <= exponent_JOO && exponent_JOO < 8) imageUnit_JOO.setImageResource(R.drawable.p4);
                else if (8 <= exponent_JOO && exponent_JOO < 11) imageUnit_JOO.setImageResource(R.drawable.p5);
                else if (11 <= exponent_JOO && exponent_JOO < 14) imageUnit_JOO.setImageResource(R.drawable.p6);
                else if (14 <= exponent_JOO && exponent_JOO < 17) imageUnit_JOO.setImageResource(R.drawable.p7);
                else if (17 <= exponent_JOO && exponent_JOO < 21) imageUnit_JOO.setImageResource(R.drawable.p8);
                else imageUnit_JOO.setImageResource(R.drawable.p9);
            } else {
                // If there's a comma but no exponent, set the image to p2
                imageUnit_JOO.setImageResource(R.drawable.p2);
            }
        } else {
            // If none of the above conditions are met, set the image to p1
            imageUnit_JOO.setImageResource(R.drawable.p1);
        }
    }

    /**
     * Displays a Snackbar with a message based on input and conversion parameters
     *
     * @param view_JOO The view to which the Snackbar should be attached
     * @param input1_JOO The first selected unit for conversion
     * @param input2_JOO The second selected unit for conversion
     * @param textInput_JOO The input value for conversion
     */
    private void appearSnackbar(View view_JOO, String input1_JOO, String input2_JOO, String textInput_JOO) {
        String snackbarText_JOO;

        // Create a Snackbar
        Snackbar snackbar_JOO = Snackbar.make(view_JOO, null, Snackbar.LENGTH_LONG);
        snackbar_JOO.setTextColor(Color.BLACK);

        // Get the view of the Snackbar and set its background color to red pastel
        View snackbarView_JOO = snackbar_JOO.getView();
        snackbarView_JOO.setBackgroundColor(Color.rgb(255,189,189));

        // If either unit is not selected, show an error message
        if (input1_JOO.equals("") || input2_JOO.equals("")) {snackbarText_JOO = "Error. You must select the units to convert.";}
        // If no input value is provided, show an error message.
        else if (textInput_JOO.equals("")) {snackbarText_JOO = "Error. You must introduce a value to convert.";}
        // If the input contains a comma or period, show an error message
        else if (input1_JOO.contains(",") || input1_JOO.contains(".")) {snackbarText_JOO = "Error. Your input must not contain decimal places.";}
        // If none of the error conditions are met, set a success message and change its background color to green pastel
        else {snackbarView_JOO.setBackgroundColor(Color.rgb(225,247,213)); snackbarText_JOO = "Converted "+input1_JOO+" to "+input2_JOO;}

        // Set the text of the Snackbar and display it
        snackbar_JOO.setText(snackbarText_JOO);
        snackbar_JOO.show();
    }
}
