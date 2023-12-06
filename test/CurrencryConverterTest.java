package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;
import org.junit.Assert.*;
import currencyConverter.Currency;
import currencyConverter.CurrencyConverter;
import currencyConverter.MainWindow;

public class CurrencryConverterTest {
    private ArrayList<Currency> currencies;

    @Before
    public void setUp() {;
        currencies = Currency.init();

    }

    //Test de Boite Noire

    @Test
    public void testValidConversion() {
        // Conversion avec des devises valides
        assertNotNull(MainWindow.convert("US Dollar", "Euro", currencies, 500.15));

    }

    @Test
    public void testNegativeAmountConversion() {
        double negativeAmount = -100.0;
        double expectedResult = 0.0;
        double actualResult = MainWindow.convert("US Dollar", "Euro", currencies, negativeAmount);
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void testZeroAmountConversion() {
        double zeroAmount = 0.0;
        double result = MainWindow.convert("US Dollar", "Euro", currencies, zeroAmount);
        assertNotNull(result);
    }

    @Test
    public void testExtremeAmountConversion(){
        double amount = 1000000.0;
        double expectedResult = 930000.0;
        double actualResult = MainWindow.convert("US Dollar", "Euro", currencies, amount);
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void testUnAcceptedAmoutConversion(){
        double unacceptedAmout = 10000000.0;
        double result = MainWindow.convert("US Dollar", "Euro", currencies, unacceptedAmout);
        assertNotNull(result);
    }

    @Test
    public void testConversionInvalidCurrency() {
        // Conversion avec une devise non valide (CAD, AUD)
        assertEquals(Double.valueOf(0.0),MainWindow.convert("US Dollar", "Ca Dollar", currencies, 500.92));
        assertEquals(Double.valueOf(0.0),MainWindow.convert("US Dollar", "Au Dollar", currencies, 900.00));
    }

    @Test
    public void testConvert2Emount(){
        //test conversion valide avec plusieurs decimal
        Double resultat = Currency.convert(100.0, 1.2345);
        assertEquals(Double.valueOf(123.45), resultat);
    }

    @Test
    public void testConvertZeroExchangeAmount(){
        //test conversion valide avec 0 exchange rate
        Double resultat = Currency.convert(100.0, 0.0);
        assertEquals(Double.valueOf(0.0), resultat);
    }

    @Test
    public void testConvertZeroAmount(){
        //test conversion valide avec 0
        Double resultat = Currency.convert(0.0, 1.2);
        assertEquals(Double.valueOf(0.0), resultat);
    }

    @Test
    public void testConvertValidAmount(){
        //test conversion valide
        Double resultat = Currency.convert(100.00, 1.2);
        assertEquals(Double.valueOf(120.0), resultat);
    }


    //Tests boite blanche
    @Test
    public void testsBoiteBlanche(){
        double zeroAmount = 0.0;

        //MainWindow.convert test
        //Valid Currency 1 and 2 (it tests the whole function)
        double result = MainWindow.convert("US Dollar", "Chinese Yuan Renminbi", currencies, 1020.00);
        System.out.println(result);
        assertNotEquals(zeroAmount, result, 0.001);

        //Valid Currency 2 but Invalid Currency 1 (it passes in zero if)
        result = MainWindow.convert("US Dol", "Chinese Yuan Renminbi", currencies, 1020.00);
        System.out.println(result);
        assertEquals(zeroAmount,result,0.001);

        //Valid Currency 1 but Invalid Currency 2 (it passes in the first if)
        result = MainWindow.convert("US Dollar", "Chinese", currencies, 1020.00);
        System.out.println(result);
        assertEquals(zeroAmount,result,0.001);

        //Currency.convert test
        //Valid Amount and ExchangeValue (it tests the whole function *the only path*)
        result = Currency.convert(100.00, 1.2);
        System.out.println(result);
        assertNotEquals(zeroAmount, result, 0.001);
    }









    }

    