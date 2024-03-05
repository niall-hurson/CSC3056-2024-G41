package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RangeTest {
    private Range rangeObjectUnderTest;

    public static void assertEquals(String message,
                                     double expected,
                                     double actual,
                                     double delta) {
    }

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-1, 1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCentralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0", 0, rangeObjectUnderTest.getCentralValue(),
                0.000000001d);
    }

    // Method 1: combine
    @Test
    public void testCombineBothNull() {
        Range result = Range.combine(null, null);
        assertNull("Combining two null ranges should return null", result);
    }

    @Test
    public void testCombineFirstNull() {
        Range result = Range.combine(null, new Range(0, 1));
        assertEquals("Combining a null range with a non-null range should return the non-null range", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining a null range with a non-null range should return the non-null range", 1, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineSecondNull() {
        Range result = Range.combine(new Range(0, 1), null);
        assertEquals("Combining a non-null range with a null range should return the non-null range", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining a non-null range with a null range should return the non-null range", 1, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineBothNonNull() {
        Range result = Range.combine(new Range(0, 1), new Range(0, 3));
        assertEquals("Combining two non-null ranges should return a combined range with lower bound from the first range", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining two non-null ranges should return a combined range with upper bound from the second range", 3, result.getUpperBound(), 0.0001);
    }

    // Method 2: shift
    @Test
    public void testShiftBaseNull() {
        try {
            Range result = Range.shift(null, 1.5);
            assertNull("Shifting a null range should return null", result);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Error message should match", "Invalid base object", e.getMessage());
        } catch (NullPointerException e) {
            assertNull("Shifting a null range should return null", e.getMessage());
        }
    }

    @Test
    public void testShiftPositiveDelta() {
        Range result = Range.shift(new Range(0, 2), 1.5);
        assertEquals("Shifting a range [0, 2] by a positive delta should return a shifted range [1.5, 3.5]", 1.5, result.getLowerBound(), 0.0001);
        assertEquals("Shifting a range [0, 2] by a positive delta should return a shifted range [1.5, 3.5]", 3.5, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testShiftZeroDelta() {
        Range result = Range.shift(new Range(0, 2), 0.0);
        assertEquals("Shifting a range [0, 2] by a delta of zero should return the original range [0, 2]", 0, result.getLowerBound(), 0.0001);
        assertEquals("Shifting a range [0, 2] by a delta of zero should return the original range [0, 2]", 2, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testShiftNegativeDelta() {
        Range result = Range.shift(new Range(0, 2), -1.5);
        assertEquals("Shifting a range [0, 2] by a negative delta should return a shifted range [-1.5, 0.5]", -1.5, result.getLowerBound(), 0.0001);
        assertEquals("Shifting a range [0, 2] by a negative delta should return a shifted range [-1.5, 0.5]", 0.5, result.getUpperBound(), 0.0001);
    }
    
 // Method 3: expand
    @Test
    public void testExpandPositiveMargins() {
        Range result = Range.expand(new Range(2, 6), 0.25, 0.5);
        assertEquals("Expanded range with positive margins should adjust lower bound correctly", 1.0, result.getLowerBound(), 0.0001);
        assertEquals("Expanded range with positive margins should adjust upper bound correctly", 8.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandNegativeMargins() {
        Range result = Range.expand(new Range(2, 6), -0.25, -0.5);
        assertEquals("Expanded range with negative margins should adjust lower bound correctly", 3.0, result.getLowerBound(), 0.0001);
        assertEquals("Expanded range with negative margins should adjust upper bound correctly", 4.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandZeroMargins() {
        Range result = Range.expand(new Range(2, 6), 0, 0);
        assertEquals("Expanding range with zero margins should not change lower bound", 2.0, result.getLowerBound(), 0.0001);
        assertEquals("Expanding range with zero margins should not change upper bound", 6.0, result.getUpperBound(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpandNullRange() {
        Range result = Range.expand(null, 0.25, 0.5);
    }

    //method 4 Intersects(double, double)
    @Test
    public void testIntersectsTrue() {
        rangeObjectUnderTest = new Range(-1, 1);
        Assert.assertEquals("Intersecting ranges should return true", true, rangeObjectUnderTest.intersects(-1, 0.5));
        Assert.assertEquals("Intersecting ranges should return true", true, rangeObjectUnderTest.intersects(0, 1.0));
    }
    
    @Test
    public void testIntersectsFalse() {
    	rangeObjectUnderTest = new Range(0, 3);
        Assert.assertEquals("Non-intersecting ranges should return false", false, rangeObjectUnderTest.intersects(1.5, 2.0));
        Assert.assertEquals("Non-intersecting ranges should return false", false, rangeObjectUnderTest.intersects(0.0, 1.0));
    }
    
    //method 5 constrain
    @Test
    public void testConstrainValueWithinRange() {
        rangeObjectUnderTest = new Range(-1, 1);
        assertEquals("Constraining a value within range should return the same value", 0, rangeObjectUnderTest.constrain(0), 0.0001);
        assertEquals("Constraining a value within range should return the same value", -1, rangeObjectUnderTest.constrain(-1), 0.0001);
        assertEquals("Constraining a value within range should return the same value", 1, rangeObjectUnderTest.constrain(1), 0.0001);
    }
    
    @Test
    public void testConstrainValueBelowRange() {
    	rangeObjectUnderTest = new Range(-1, 1);
        assertEquals("Constraining a value below range should return the lower bound", -1, rangeObjectUnderTest.constrain(-2), 0.0001);
        assertEquals("Constraining a value below range should return the lower bound", -1, rangeObjectUnderTest.constrain(-10), 0.0001);
    }
    
    @Test
    public void testConstrainValueAboveRange() {
    	rangeObjectUnderTest = new Range(-1, 1);
        assertEquals("Constraining a value above range should return the upper bound", 1, rangeObjectUnderTest.constrain(2), 0.0001);
        assertEquals("Constraining a value above range should return the upper bound", 1, rangeObjectUnderTest.constrain(10), 0.0001);
    }



}
