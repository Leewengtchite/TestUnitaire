/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package MoneyTest;

import emse.test.Money;
import emse.test.MoneyBag;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leewt
 */
public class MoneyBagTest {
    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;
    
    
    
    public MoneyBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        f12CHF = new Money(12,"CHF");
        f14CHF = new Money(14,"CHF");
        f7USD = new Money(7,"USD");
        f21USD = new Money(21,"USD");
        fMB1 = new MoneyBag(f12CHF,f7USD);
        fMB2 = new MoneyBag(f14CHF,f21USD);
        
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testBagEquals(){
        assertTrue(!fMB1.equals(null));
        assertEquals(fMB1,fMB1);
        assertTrue(!fMB1.equals(f12CHF));
        assertTrue(!f12CHF.equals(fMB1));
        assertTrue(!fMB1.equals(fMB2));
    }
     @Test
     // ajout de money et Money mais avec des devises differentes doit donc retourner un moneyBag 
     public void testMixedSimpleAdd(){
         // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
    Money bag[] = { f12CHF, f7USD };
    MoneyBag expected = new MoneyBag(bag);
    assertEquals(expected, f12CHF.add(f7USD));
     }
      //Test pour ajouter  un MoneyBag  a un simple Money avec une devise differente ce qui aurait pour but de creer un MoneyBag plus large
         @Test
public void testBagSimpleAdd() {
    // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
    MoneyBag bag = new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"));
    Money m = new Money(3,"EUR");
    MoneyBag expected = new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"),new Money(3,"EUR"));
    assertEquals(expected, m.add(bag));
}
// Test pour ajouter un simple Money à un MoneyBag dont ce simple Money aura la meme devise que l un des Money dans MoneyBag donc l addition s effectue correctement
    @Test
    public void testSimpleBagAdd() {
    MoneyBag bag = new MoneyBag(new Money(12,"CHF"), new Money(7,"USD"));
    Money m = new Money(8,"CHF");
    MoneyBag expected = new MoneyBag(new Money(20,"CHF"),new Money(7,"USD"));
    assertEquals(expected, bag.add(m));
}
    @Test
    // Test Pour ajouter deux MoneyBag avec toutes des devises differentes
    public void Bagbag(){
        MoneyBag bag = new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"));
        MoneyBag bag2= new MoneyBag(new Money(3,"EUR"),new Money(4,"JPY"));
        MoneyBag expected = new MoneyBag(new Money(12,"CHF"),new Money(7,"USD"),new Money(3,"EUR"),new Money(4,"JPY")) ;
        assertEquals(expected,bag.add(bag2));
    }
    
    @Test
    // test d ajout de deux MoneyBag avec un seul element MOney avec les memes devises devrait retoiurner un money avec la somme des deux MoneyBag
    public void simplification(){
        MoneyBag bag = new MoneyBag(new Money(2,"CHF"));
        MoneyBag bag2 = new MoneyBag(new Money(2,"CHF"));
        Money expected = new Money(4,"CHF");
        assertEquals(expected,bag.add(bag2));
         
    }
    
   @Test
public void simplification14(){
    MoneyBag bag = new MoneyBag(new Money(12,"CHF"), new Money(7,"USD"));
    Money m = new Money(-12, "CHF");
    Money expected = new Money(7,"USD");
    assertEquals(expected, bag.add(m));
}
     

   
}
