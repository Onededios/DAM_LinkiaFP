package com.m08.dataunitconversor;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class UnitUnitTest {
    @Test
    public void testConvertTo() {
        ArrayList<Unit> sampleUnits = Unit.getSampleUnits();

        Unit bit_JOO = new Unit("b", "bit", 1);
        Unit byte_JOO = new Unit("B", "byte", 8);
        Unit kilobyte_JOO = new Unit("KB", "kilobyte", Math.pow(2, 10));
        Unit megabyte_JOO = new Unit("MB", "megabyte", Math.pow(2, 20));
        Unit gigabyte_JOO = new Unit("GB", "gigabyte", Math.pow(2, 30));
        Unit terabyte_JOO = new Unit("TB", "terabyte", Math.pow(2, 40));
        Unit petabyte_JOO = new Unit("PB", "petabyte", Math.pow(2, 50));
        Unit exabyte_JOO = new Unit("EB", "exabyte", Math.pow(2, 60));
        Unit zettabyte_JOO = new Unit("ZB", "zettabyte", Math.pow(2, 70));
        Unit yottabyte_JOO = new Unit("YB", "yottabyte", Math.pow(2, 80));
        Unit brontobyte_JOO = new Unit("BB", "brontobyte", Math.pow(2, 90));
        Unit geopbyte_JOO = new Unit("GeB", "geopbyte", Math.pow(2, 100));

        // For bit to byte
        String result_JOO = bit_JOO.convertTo("1", byte_JOO);
        assertEquals("0,13", result_JOO);

        // For bit to kilobyte
        result_JOO = bit_JOO.convertTo("2", kilobyte_JOO);
        assertEquals("1,95e-03", result_JOO);

        // For bit to megabyte
        result_JOO = bit_JOO.convertTo("3", megabyte_JOO);
        assertEquals("2,86e-06", result_JOO);

        // For bit to gigabyte
        result_JOO = bit_JOO.convertTo("4", gigabyte_JOO);
        assertEquals("3,73e-09", result_JOO);

        // For bit to terabyte
        result_JOO = bit_JOO.convertTo("5", terabyte_JOO);
        assertEquals("4,55e-12", result_JOO);

        // For bit to petabyte
        result_JOO = bit_JOO.convertTo("6", petabyte_JOO);
        assertEquals("5,33e-15", result_JOO);

        // For bit to exabyte
        result_JOO = bit_JOO.convertTo("7", exabyte_JOO);
        assertEquals("6,07e-18", result_JOO);

        // For bit to zettabyte
        result_JOO = bit_JOO.convertTo("8", zettabyte_JOO);
        assertEquals("6,78e-21", result_JOO);

        // For bit to yottabyte
        result_JOO = bit_JOO.convertTo("9", yottabyte_JOO);
        assertEquals("7,44e-24", result_JOO);

        // For bit to brontobyte
        result_JOO = bit_JOO.convertTo("10", brontobyte_JOO);
        assertEquals("8,08e-27", result_JOO);

        // For bit to geopbyte
        result_JOO = bit_JOO.convertTo("11", geopbyte_JOO);
        assertEquals("8,68e-30", result_JOO);

        // Test invalid input
        result_JOO = bit_JOO.convertTo("abc", byte_JOO);
        assertEquals("Invalid input", result_JOO);

        // Test number with comma
        result_JOO = bit_JOO.convertTo("1,2", byte_JOO);
        assertEquals("Invalid input", result_JOO);

        // Test number with dot
        result_JOO = bit_JOO.convertTo("1.3", byte_JOO);
        assertEquals("Invalid input", result_JOO);

        // Test negative number
        result_JOO = bit_JOO.convertTo("-1", byte_JOO);
        assertEquals("-0,13", result_JOO);
    }

    @Test
    public void testGetUnitByName() {
        // Create sample units
        Unit.getSampleUnits();

        // Test getting a unit by name
        Unit unit_JOO = Unit.getUnitByName("bit");
        assertNotNull(unit_JOO);
        assertEquals("bit", unit_JOO.getName_JOO());

        // Test getting a unit with an invalid name
        unit_JOO = Unit.getUnitByName("invalidUnit");
        assertNotNull(unit_JOO);
        assertEquals("No unit selected", unit_JOO.getName_JOO());
    }

    @Test
    public void testFormatValue() {
        Unit unit_JOO = new Unit("test", "testUnit", 1);

        // Test formatting for large value
        String result_JOO = unit_JOO.formatValue(12345.67);
        assertEquals("1,23e+04", result_JOO);

        // Test formatting for small value
        result_JOO = unit_JOO.formatValue(0.00123);
        assertEquals("1,23e-03", result_JOO);

        // Test formatting for integer value
        result_JOO = unit_JOO.formatValue(42);
        assertEquals("42", result_JOO);

        // Test formatting for value with two decimal places
        result_JOO = unit_JOO.formatValue(3.14);
        assertEquals("3,14", result_JOO);
    }
}
