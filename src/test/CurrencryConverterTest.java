package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import currencyConverter.Currency;
import currencyConverter.MainWindow;

import junit.runner.Version;


public class CurrencryConverterTest {
    private ArrayList<Currency> currencies;

    @Before
    public void setUp() {;
        currencies = Currency.init();
        System.out.println(Version.id());
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
        double zeroAmount = 0.0;
        double unacceptedAmout = 10000000.0;
        double actualResult = MainWindow.convert("US Dollar", "Euro", currencies, unacceptedAmout);
        assertEquals(zeroAmount, actualResult, 0.001);
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
    public void testConvertNegativeExchangeAmount(){
        //test conversion valide avec 0 exchange rate
        Double resultat = Currency.convert(100.0, -1.2);
        assertEquals(Double.valueOf(0.0), resultat);
    }

    @Test
    public void testConvertZeroAmount(){
        //test conversion valide avec 0
        Double resultat = Currency.convert(0.0, 1.2);
        assertEquals(Double.valueOf(0.0), resultat);
    }
    
    @Test
    public void testConvertNegativeAmount(){
        //test conversion valide avec 0
        Double resultat = Currency.convert(-100.0, 1.2);
        assertEquals(Double.valueOf(0.0), resultat);
    }

    @Test
    public void testConvertExtremeAmount(){
        //test conversion valide avec 0
        Double resultat = Currency.convert(1000000.0, 1.2);
        assertEquals(Double.valueOf(1200000.0), resultat);
    }
    @Test
    public void testConvertUnAcceptedAmoutAmount(){
        //test conversion valide avec 0
        Double resultat = Currency.convert(10000000.0, 1.2);
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
        String[] curren = new String[]{
            "US Dollar",
            "Euro",
            "British Pound",
            "Swiss Franc",
            "Chinese Yuan Renminbi",
            "Japanese Yen"
        };

        double result;


        //MainWindow.convert test
        //Valid Currency 1 and 2 (it tests all the currency options)
        for (int i = 0; i < curren.length; i++) {
            for (int j = 0; j < curren.length; j++) {
                System.out.println(curren[i]+ " / "+ curren[j]);
                result = MainWindow.convert(curren[i], curren[j], currencies, 1000.00);
                assertNotNull(result);
            }
        }

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

    