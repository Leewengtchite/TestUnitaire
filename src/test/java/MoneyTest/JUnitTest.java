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
public class JUnitTest {
    private Money m12CHF;
    private Money m14CHF;
    
    
    
    public JUnitTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
         /////////////////////////////////////seconde test unitaire//////////////////////////////////////
     public void testEquals(){
//                 Money m12CHF = new Money(12,"CHF");// creation de donnees 
//                 Money m14CHF = new Money(14,"CHF");
                 
                 assertTrue(!m12CHF.equals(null));
                 assertEquals(m12CHF,m12CHF);
                 assertEquals(m12CHF,new Money(12,"CHF"));
                 assertTrue(!m12CHF.equals(m14CHF));

     }
     
     
     ////////////////////////////first test unitaire///////////////////////////////////////
     @Test
      public void testSimpleAdd(){
//        Money m12CHF = new Money(12,"CHF");// creation de donnees 
//        Money m14CHF = new Money(14,"CHF");
        Money expected = new Money(26,"CHF");
        Money result = (Money) m12CHF.add(m14CHF);// execution de la methode testee
        assertTrue(expected.equals(result));//comparaison
    }
      
   
     
}
