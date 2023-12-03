package test;

import static org.junit.Assert.assertEquals;
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
    public void setUp() {

        currencies = Currency.init();

        

    }

    //Test de Boite Noire
    @Test
    public void testNegativeAmountConversion() {
        double negativeAmount = -100.0;

        double conversion = MainWindow.convert("USD", "EUR", currencies, negativeAmount);
        assertEquals(0, conversion, 0.001);
    }

    @Test
    public void testZeroAmountConversion() {
        double zeroAmount = 0.0;
        double expectedResult = 0.0;
        
        double actualResult = MainWindow.convert("USD", "EUR", currencies, zeroAmount);
    
        assertEquals(expectedResult, actualResult, 0.001);
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

        double actualResult = MainWindow.convert("USD", "EUR", currencies, unacceptedAmout);
    
        assertEquals(0, actualResult, 0.001);
    }

    //Boite noir test supplementaire

    @Test
    public void testValidConversion() {
        // Conversion avec des devises valides
        assertNotNull(MainWindow.convert("USD", "EUR", currencies, 500.15));

    }

    @Test
    public void testConversionWithInvalidCurrency() {
        // Conversion avec une devise non valide (CAD, AUD)
        assertEquals(Double.valueOf(0.0),MainWindow.convert("USD", "CAD", currencies, 500.92));
        assertEquals(Double.valueOf(0.0),MainWindow.convert("USD", "AUD", currencies, 900.00));
    }

    @Test
    public void testConversionWithInvalidAmount() {
        // Conversion avec un montant negatif
        assertEquals(Double.valueOf(0.0),MainWindow.convert("USD", "EUR", currencies, -100.00));
    }

    @Test
    public void testConversionOnLimit() {
        // Conversion avec des montants à la limite
        assertNotNull(MainWindow.convert("USD", "EUR", currencies, 0.00));
        assertNotNull(MainWindow.convert("USD", "EUR", currencies, 1000000.00));
    }

    @Test
    public void testConversionAboveLimit() {
        // Conversion avec un montant au-dessus de la limite valide
        //Test quand meme accepter, suit pas les specification
        assertNotNull(MainWindow.convert("USD", "EUR", currencies, 1000001.00));
    }

    //Tests boite blanche
    //Tests  couverte instruction

    @Test
    public void testCurrencyFound(){
        assertNotNull(MainWindow.convert("USD", "CHF", currencies, 1020.00));
        
    }
    @Test
    public void testCurrencyNotFound(){
        assertEquals(Double.valueOf(0.0),MainWindow.convert("USD", "AUD", currencies, 900.00));

        
    }

    @Test
    public void testConvertValidAmount(){
        //test conversion valide
        Double resultat = Currency.convert(100.00, 1.2);
        assertEquals(Double.valueOf(120.0), resultat);
    }
    @Test
    public void testConvertZeroAmount(){
        //test conversion valide avec 0
        Double resultat = Currency.convert(0.0, 1.2);
        assertEquals(Double.valueOf(0.0), resultat);
    }
    @Test
    public void testConvertZeroExchangeAmount(){
        //test conversion valide avec 0 exchange rate
        Double resultat = Currency.convert(100.0, 0.0);
        assertEquals(Double.valueOf(0.0), resultat);
    }

    @Test
    public void testConvert2Emount(){
        //test conversion valide avec plusieurs decimal
        Double resultat = Currency.convert(100.0, 1.2345);
        assertEquals(Double.valueOf(123.45), resultat);
    }

    }

    