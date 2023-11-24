package com.m08.dataunitconversor;

import java.util.Locale;

import java.util.ArrayList;

/**
 * Represents a unit for data conversion.
 */
public class Unit {
    private final String abbreviation_JOO;
    private final String name_JOO;
    private final double factor_JOO;
    private static final ArrayList<Unit> units_JOO = new ArrayList<>();

    /**
     * Constructs a new Unit with the given properties
     *
     * @param abbreviation_JOO The unit's abbreviation
     * @param name_JOO         The unit's name
     * @param factor_JOO       The conversion factor to a base unit
     */
    public Unit(String abbreviation_JOO, String name_JOO, double factor_JOO) {
        this.name_JOO = name_JOO;
        this.abbreviation_JOO = abbreviation_JOO;
        this.factor_JOO = factor_JOO;
    }

    public String getAbbreviation_JOO() { return abbreviation_JOO; }

    public String getName_JOO() { return name_JOO; }

    /**
     * Convert a value to another unit
     *
     * @param inputValue_JOO   The value to be converted
     * @param targetUnit_JOO   The target unit for conversion
     * @return The converted value as a formatted string
     */
    public String convertTo(String inputValue_JOO, Unit targetUnit_JOO) {
        try {
            double value_JOO = Double.parseDouble(inputValue_JOO);

            // Check if the source and target units are the same
            if (this == targetUnit_JOO) return formatValue(value_JOO);

            // Check for invalid cases: division by zero or non-integer input
            if (targetUnit_JOO.factor_JOO == 0 || this.factor_JOO == 0 || (value_JOO % 1) != 0) return "Invalid input";

            // Calculate the conversion and format the result
            return formatValue(value_JOO * (this.factor_JOO / targetUnit_JOO.factor_JOO));
        } catch (NumberFormatException | ArithmeticException e) { return "Invalid input"; }
    }

    /**
     * Format a converted value as a string
     *
     * @param convertedValue_JOO The converted value
     * @return The formatted value as a string
     */
    String formatValue(double convertedValue_JOO) {
        // Check if the converted value is very large or very small, and use scientific notation
        if (Math.abs(convertedValue_JOO) >= 1000 || Math.abs(convertedValue_JOO) < 0.01) return String.format(Locale.GERMAN, "%.2e", convertedValue_JOO);

        // Check if the converted value is an integer, and format it without decimal places
        else if (convertedValue_JOO == (int) convertedValue_JOO) return String.format(Locale.GERMAN, "%.0f", convertedValue_JOO);

        // Format the converted value with two decimal places
        else return String.format(Locale.GERMAN, "%.2f", convertedValue_JOO);
    }


    /**
     * Get a list of sample units for data conversion
     *
     * @return A list of sample units
     */
    public static ArrayList<Unit> getSampleUnits() {
        units_JOO.add(new Unit("b", "bit", 1));
        units_JOO.add(new Unit("B", "byte", 8));
        units_JOO.add(new Unit("KB", "kilobyte", Math.pow(2,10)));
        units_JOO.add(new Unit("MB", "megabyte", Math.pow(2,20)));
        units_JOO.add(new Unit("GB", "gigabyte", Math.pow(2,30)));
        units_JOO.add(new Unit("TB", "terabyte", Math.pow(2,40)));
        units_JOO.add(new Unit("PB", "petabyte", Math.pow(2,50)));
        units_JOO.add(new Unit("EB", "exabyte", Math.pow(2,60)));
        units_JOO.add(new Unit("ZB", "zettabyte", Math.pow(2,70)));
        units_JOO.add(new Unit("YB", "yottabyte", Math.pow(2,80)));
        units_JOO.add(new Unit("BB", "brontobyte", Math.pow(2,90)));
        units_JOO.add(new Unit("GeB", "geopbyte", Math.pow(2,100)));

        return units_JOO;
    }

    /**
     * Get the names of sample units
     *
     * @return A list of unit names
     */
    public static ArrayList<String> getSampleUnitNames() {
        ArrayList<String> unitNames_JOO = new ArrayList<>();

        // Iterate through the list of sample units and add their names to the 'unitNames' list
        for (Unit unit_JOO: units_JOO) unitNames_JOO.add(unit_JOO.name_JOO);
        return unitNames_JOO;
    }

    /**
     * Get a unit by its name
     *
     * @param name_JOO The name of the unit to retrieve
     * @return The unit with the specified name, or a default unit if not found
     */
    public static Unit getUnitByName(String name_JOO) {
        if (name_JOO != null) {

            // Iterate through the list of sample units and check if a unit with the specified name exists
            for (Unit unit_JOO : getSampleUnits()) {
                if (unit_JOO.name_JOO.equals(name_JOO)) return unit_JOO;
            }
        }

        // If no matching unit is found, return a default unit with empty values
        return new Unit("", "No unit selected", 0);
    }
}
