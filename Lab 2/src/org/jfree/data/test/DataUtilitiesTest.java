package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class DataUtilitiesTest extends TestCase {
	private Values2D values2D;
    private KeyedValues keyedValuesAllPos;
    private KeyedValues keyedValuesAllNeg;
    private KeyedValues keyedValuesPosAndNeg;




	@Before
	public void setUp() {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
		
		DefaultKeyedValues keyedValuesAllPos = new DefaultKeyedValues();
		keyedValuesAllPos.addValue("0", 5);
		keyedValuesAllPos.addValue("1", 9);
		keyedValuesAllPos.addValue("2", 2);
	    this.keyedValuesAllPos = keyedValuesAllPos;
	    
	    DefaultKeyedValues keyedValuesAllNeg = new DefaultKeyedValues();
	    keyedValuesAllNeg.addValue("0", -5);
	    keyedValuesAllNeg.addValue("1", -9);
	    keyedValuesAllNeg.addValue("2", -2);
	    this.keyedValuesAllNeg = keyedValuesAllNeg;
	    
	    DefaultKeyedValues keyedValuesPosAndNeg = new DefaultKeyedValues();
	    keyedValuesPosAndNeg.addValue("0", -5);
	    keyedValuesPosAndNeg.addValue("1", 9);
	    keyedValuesPosAndNeg.addValue("2", -2);
	    this.keyedValuesPosAndNeg = keyedValuesPosAndNeg;
	}

	public static void assertEquals(java.lang.String message, double expected, double actual, double delta) {
	}

	@After
	public void tearDown() {
		values2D = null;
	}

	//method 1 - column total
	@Test
	public void testValidDataAndColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0),
				0.0000001d);
	}
	
	@Test
	public void testInvalidColumnIndexColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(values2D, -1); // Column index exceeding the range.
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testNullDataColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// method 2 - calculateRowTotal
	@Test
	public void testValidDataAndRowTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateRowTotal(values2D, 1),
				0.0000001d);
	}

	@Test
	public void testInvalidRowIndexRowTotal() {
		try {
			DataUtilities.calculateRowTotal(values2D, 3); // Row index exceeding the range.
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testNullDataRowTotal() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	//method 3 --create number array
    @Test
    public void testValidDataAllPosCreateNumberArray() {
        double[] validData = {1.0, 2.5, 3.7};
        Number[] result = DataUtilities.createNumberArray(validData);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match input length", validData.length, result.length);
        for (int i = 0; i < validData.length; i++) {
            assertNotNull("Element should not be null", result[i]); // Add null check
            assertEquals("Element value should match", validData[i], result[i].doubleValue(), 0.000001d);
        }
    }
    
    @Test
    public void testValidDataPosAndNegCreateNumberArray() {
        double[] validData = {-2, 0, 5};
        Number[] result = DataUtilities.createNumberArray(validData);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match input length", validData.length, result.length);
        for (int i = 0; i < validData.length; i++) {
            assertNotNull("Element should not be null", result[i]); // Add null check
            assertEquals("Element value should match", validData[i], result[i].doubleValue(), 0.000001d);
        }
    }
    
    @Test
    public void testValidDataAllNegCreateNumberArray() {
        double[] validData = {-3, -6, 12};
        Number[] result = DataUtilities.createNumberArray(validData);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match input length", validData.length, result.length);
        for (int i = 0; i < validData.length; i++) {
            assertNotNull("Element should not be null", result[i]); // Add null check
            assertEquals("Element value should match", validData[i], result[i].doubleValue(), 0.000001d);
        }
    }


    // Test for null array of double primitives
    @Test
    public void testNullDataCreateNumberArray() {
        try {
            DataUtilities.createNumberArray(null);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }
    
    //method 4
 // Test for valid non-null array of double arrays
    @Test
    public void testValidDataAllPosCreateNumberArray2D() {
        double[][] validData = {{1.0, 2.5}, {3.7, 4.2}};
        Number[][] result = DataUtilities.createNumberArray2D(validData);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of rows should match", validData.length, result.length);
        for (int i = 0; i < validData.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]); // Add null check for row
            assertEquals("Number of columns in row " + i + " should match", validData[i].length, result[i].length);
            for (int j = 0; j < validData[i].length; j++) {
                assertNotNull("Element [" + i + "][" + j + "] should not be null", result[i][j]); // Add null check for element
                assertEquals("Element value should match", validData[i][j], result[i][j].doubleValue(), 0.000001d);
            }
        }
    }
    
    @Test
    public void testValidDataAllNegCreateNumberArray2D() {
        double[][] validData = {{-1.0, -2.5}, {-3.7, -4.2}};
        Number[][] result = DataUtilities.createNumberArray2D(validData);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of rows should match", validData.length, result.length);
        for (int i = 0; i < validData.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]); // Add null check for row
            assertEquals("Number of columns in row " + i + " should match", validData[i].length, result[i].length);
            for (int j = 0; j < validData[i].length; j++) {
                assertNotNull("Element [" + i + "][" + j + "] should not be null", result[i][j]); // Add null check for element
                assertEquals("Element value should match", validData[i][j], result[i][j].doubleValue(), 0.000001d);
            }
        }
    }
    
    @Test
    public void testValidDataPosAndNegCreateNumberArray2D() {
        double[][] validData = {{1.0, -2.5}, {-3.7, 4.2}};
        Number[][] result = DataUtilities.createNumberArray2D(validData);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of rows should match", validData.length, result.length);
        for (int i = 0; i < validData.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]); // Add null check for row
            assertEquals("Number of columns in row " + i + " should match", validData[i].length, result[i].length);
            for (int j = 0; j < validData[i].length; j++) {
                assertNotNull("Element [" + i + "][" + j + "] should not be null", result[i][j]); // Add null check for element
                assertEquals("Element value should match", validData[i][j], result[i][j].doubleValue(), 0.000001d);
            }
        }
    }


    // Test for null array of double arrays
    @Test
    public void testNullDataCreateNumberArray2D() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }
    
    //method 5 
    @Test
    public void testValidDataAllPosGetCumulativePercentages() {
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValuesAllPos);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of items should match", keyedValuesAllPos.getItemCount(), result.getItemCount());
        double totalValue = 16; // Sum of all values (5 + 9 + 2)
        double cumulativeSum = 0;
        for (int i = 0; i < keyedValuesAllPos.getItemCount(); i++) {
            cumulativeSum += keyedValuesAllPos.getValue(i).doubleValue();
            double expectedPercentage = cumulativeSum / totalValue;
            assertEquals("Cumulative percentage should match", expectedPercentage, result.getValue(i).doubleValue(), 0.000001d);
        }
    }
    
    @Test
    public void testValidDataAllNegGetCumulativePercentages() {
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValuesAllNeg);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of items should match", keyedValuesAllNeg.getItemCount(), result.getItemCount());
        double totalValue = 16; // Sum of all values (5 + 9 + 2)
        double cumulativeSum = 0;
        for (int i = 0; i < keyedValuesAllNeg.getItemCount(); i++) {
            cumulativeSum += keyedValuesAllNeg.getValue(i).doubleValue();
            double expectedPercentage = cumulativeSum / totalValue;
            assertEquals("Cumulative percentage should match", expectedPercentage, result.getValue(i).doubleValue(), 0.000001d);
        }
    }
    
    @Test
    public void testValidDataPosAndNegGetCumulativePercentages() {
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValuesPosAndNeg);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of items should match", keyedValuesPosAndNeg.getItemCount(), result.getItemCount());
        double totalValue = 16; // Sum of all values (5 + 9 + 2)
        double cumulativeSum = 0;
        for (int i = 0; i < keyedValuesPosAndNeg.getItemCount(); i++) {
            cumulativeSum += keyedValuesPosAndNeg.getValue(i).doubleValue();
            double expectedPercentage = cumulativeSum / totalValue;
            assertEquals("Cumulative percentage should match", expectedPercentage, result.getValue(i).doubleValue(), 0.000001d);
        }
    }

    @Test
    public void testNullDataGetCumulativePercentages() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }
	
}