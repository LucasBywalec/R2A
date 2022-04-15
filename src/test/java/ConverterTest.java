import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    Converter converter;

    @Before
    public void setClass(){
        converter = new Converter();
    }

    @Test
    public void isArabicValidatorReturningProperValue(){
        assertTrue(converter.isValidArabic("111"));
        assertTrue(converter.isValidArabic("149817500098149184"));
        assertFalse(converter.isValidArabic("001"));
        assertFalse(converter.isValidArabic("5123M1"));
    }

    @Test
    public void isRomanNumeralRecognizingRomanNumbersCorrect(){
        assertTrue(converter.isRomanNumeral("I"));
        assertFalse(converter.isRomanNumeral("Y"));
        assertFalse(converter.isRomanNumeral("."));
    }

    @Test
    public void isRomanValidatorReturningProperValue(){
        assertTrue(converter.isValidRoman("X"));
        assertTrue(converter.isValidRoman("XX"));
        assertTrue(converter.isValidRoman("MCX"));
        assertFalse(converter.isValidRoman("MMMM"));
    }

    @Test
    public void singleNumberValueInRoman(){
        assertEquals(50, converter.numberValueInArabic("L"));
        assertEquals(-1, converter.numberValueInArabic("Y"));
    }

    @Test
    public void isRomanConvertedToArabic(){
        assertEquals("1900", converter.romanToArabic("MCM"));
    }

    @Test
    public void isArabicConvertedToRoman(){
        assertEquals("MCM", converter.arabicToRoman("1900"));
    }

}
