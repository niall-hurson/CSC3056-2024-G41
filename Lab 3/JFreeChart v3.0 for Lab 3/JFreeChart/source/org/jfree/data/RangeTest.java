package org.jfree.data;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        try {
            Range result = Range.combine(new Range(0, 1), null);
            assertEquals("Combining a non-null range with a null range should return the non-null range", 0, result.getLowerBound(), 0.0001);
            assertEquals("Combining a non-null range with a null range should return the non-null range", 1, result.getUpperBound(), 0.0001);
        } catch (NullPointerException e) {
            fail("An unexpected NullPointerException occurred: " + e.getMessage());
        }
    }


    @Test
    public void testCombineBothNonNull() {
        Range result = Range.combine(new Range(0, 1), new Range(0, 3));
        assertEquals("Combining two non-null ranges should return a combined range with lower bound from the first range", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining two non-null ranges should return a combined range with upper bound from the second range", 3, result.getUpperBound(), 0.0001);
    }
    
    //3 new tests for lab 3
    @Test
    public void testCombineFirstNullSecondNotNull() {
        Range result = Range.combine(null, new Range(0, 1));
        assertNotNull("Combining a null range with a non-null range should not return null", result);
        assertEquals("Combining a null range with a non-null range should return the non-null range's lower "
        		+ "bound", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining a null range with a non-null range should return the non-null range's upper "
        		+ "bound", 1, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineSecondNullFirstNotNull() {
        Range result = Range.combine(new Range(0, 1), null);
        assertNotNull("Combining a non-null range with a null range should not return null", result);
        assertEquals("Combining a non-null range with a null range should return the non-null range's lower "
        		+ "bound", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining a non-null range with a null range should return the non-null range's upper "
        		+ "bound", 1, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineBothBoundariesEqual() {
        Range result = Range.combine(new Range(0, 1), new Range(1, 2));
        assertEquals("Combining two ranges with equal upper and lower bounds should return a combined "
        		+ "range with the same bounds", 0, result.getLowerBound(), 0.0001);
        assertEquals("Combining two ranges with equal upper and lower bounds should return a combined "
        		+ "range with the same bounds", 2, result.getUpperBound(), 0.0001);
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
    
    //new tests for shift lab 3
    @Test
    public void testShiftZeroDeltaNoZeroCrossing() {
        Range result = Range.shift(new Range(0, 2), 0.0, false);
        assertEquals("Shifting a range [0, 2] by a delta of zero should return the original range [0, 2] "
        		+ "when zero crossing is not allowed",
                0, result.getLowerBound(), 0.0001);
        assertEquals("Shifting a range [0, 2] by a delta of zero should return the original range [0, 2] "
        		+ "when zero crossing is not allowed",
                2, result.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testShiftPositiveDeltaNoZeroCrossing() {
        Range result = Range.shift(new Range(-1, 1), 2.0, false);
        assertEquals("Shifting a range [-1, 1] by a positive delta should return a shifted range [1, 3] "
        		+ "when zero crossing is not allowed",
                1, result.getLowerBound(), 0.0001);
        assertEquals("Shifting a range [-1, 1] by a positive delta should return a shifted range [1, 3] "
        		+ "when zero crossing is not allowed",
                3, result.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testShiftNegativeDeltaZeroCrossing() {
        Range result = Range.shift(new Range(-1, 1), -2.0, true);
        assertEquals("Shifting a range [-1, 1] by a negative delta should return a shifted range [-3, -1] "
        		+ "when zero crossing is allowed",
                -3, result.getLowerBound(), 0.0001);
        assertEquals("Shifting a range [-1, 1] by a negative delta should return a shifted range [-3, -1] "
        		+ "when zero crossing is allowed",
                -1, result.getUpperBound(), 0.0001);
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
    
    //3 new tests for expand
    @Test
    public void testExpandZeroLowerMargin() {
        Range result = Range.expand(new Range(2, 6), 0, 0.5);
        assertEquals("Expanding range with zero lower margin should not change lower bound", 2.0, 
        		result.getLowerBound(), 0.0001);
        assertEquals("Expanding range with zero lower margin should adjust upper bound correctly", 9.0, 
        		result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandZeroUpperMargin() {
        Range result = Range.expand(new Range(2, 6), 0.25, 0);
        assertEquals("Expanding range with zero upper margin should adjust lower bound correctly", 1.5, 
        		result.getLowerBound(), 0.0001);
        assertEquals("Expanding range with zero upper margin should not change upper bound", 6.0, 
        		result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandLargeMargins() {
        Range result = Range.expand(new Range(2, 6), 1.0, 2.0);
        assertEquals("Expanding range with large margins should adjust lower bound correctly", 0.0, 
        		result.getLowerBound(), 0.0001);
        assertEquals("Expanding range with large margins should adjust upper bound correctly", 18.0, 
        		result.getUpperBound(), 0.0001);
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
    
    //lab 3 methods to improve coverage 
    @Test
    public void testIntersectsLowerEqualsUpper() {
        rangeObjectUnderTest = new Range(1, 3);
        Assert.assertEquals("Lower bound equals upper bound, should return true", true, 
        		rangeObjectUnderTest.intersects(3, 4));
        Assert.assertEquals("Lower bound equals upper bound, should return false", false, 
        		rangeObjectUnderTest.intersects(0, 1));
    }

    @Test
    public void testIntersectsUpperEqualsLower() {
        rangeObjectUnderTest = new Range(2, 5);
        Assert.assertEquals("Upper bound equals lower bound, should return true", true, 
        		rangeObjectUnderTest.intersects(1, 2));
        Assert.assertEquals("Upper bound equals lower bound, should return false", false, 
        		rangeObjectUnderTest.intersects(5, 6));
    }

    @Test
    public void testIntersectsBothBoundsEqual() {
        rangeObjectUnderTest = new Range(3, 3);
        Assert.assertEquals("Both bounds are equal, should return true", true, 
        		rangeObjectUnderTest.intersects(3, 4));
        Assert.assertEquals("Both bounds are equal, should return false", false, 
        		rangeObjectUnderTest.intersects(0, 2));
    }

    @Test
    public void testIntersectsUpperLessThanRangeUpper() {
        rangeObjectUnderTest = new Range(1, 3);
        Assert.assertEquals("Upper bound is less than range upper bound, should return true", true, 
        		rangeObjectUnderTest.intersects(0, 2));
    }

    @Test
    public void testIntersectsBothConditionsTrue() {
        rangeObjectUnderTest = new Range(2, 5);
        Assert.assertEquals("Upper bound is less than range upper bound and upper is greater than or "
        		+ "equal to lower, should return true", true, rangeObjectUnderTest.intersects(3, 4));
    }
    
    @Test
    public void testIntersectsUpperLessThanRangeUpperAndGreaterThanOrEqualToLower() {
        rangeObjectUnderTest = new Range(2, 5);
        Assert.assertEquals("Upper bound is less than range upper bound and upper is greater than or "
        		+ "equal to lower, should return true", true, rangeObjectUnderTest.intersects(4, 5));
    }
    
    @Test
    public void testIntersectsLowerEqualToThisLowerAndUpperGreaterThanThisLower() {
        rangeObjectUnderTest = new Range(2, 5);
        Assert.assertTrue("Should return true when lower bound is equal to this.lower and upper bound is "
        		+ "greater than this.lower", rangeObjectUnderTest.intersects(2, 3));
    }

    @Test
    public void testIntersectsLowerGreaterThanThisLowerAndUpperLessThanThisUpper() {
        rangeObjectUnderTest = new Range(2, 5);
        Assert.assertTrue("Should return true when lower bound is greater than this.lower and upper bound "
        		+ "is less than this.upper", rangeObjectUnderTest.intersects(3, 4));
    }

    @Test
    public void testIntersectsLowerGreaterThanThisLowerAndUpperEqualToThisUpper() {
        rangeObjectUnderTest = new Range(2, 5);
        Assert.assertTrue("Should return true when lower bound is greater than this.lower and upper bound "
        		+ "is equal to this.upper", rangeObjectUnderTest.intersects(3, 5));
    }
    
    @Test
    public void testIntersectsGivenRangeContainsThisRange() {
        rangeObjectUnderTest = new Range(2, 4);
        Assert.assertTrue("Should return true when the given range contains this range", 
        		rangeObjectUnderTest.intersects(1, 5));
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
    
    @Test
    public void testConstrainValueOutsideRange() {
        rangeObjectUnderTest = new Range(-1, 1);
        assertEquals("Constraining a value outside range should return the closest bound", -1, rangeObjectUnderTest.constrain(-100), 0.0001);
        assertEquals("Constraining a value outside range should return the closest bound", 1, rangeObjectUnderTest.constrain(100), 0.0001);
    }
    
    //expand to include Tests
    @Test
    public void testExpandToIncludeNullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        Assert.assertEquals("Expanding a null range should return a range with both bounds set to the input value", 
                     new Range(5.0, 5.0), result);
    }
    
    @Test
    public void testExpandToIncludeValueBelowRange() {
        Range range = new Range(0.0, 10.0);
        Range result = Range.expandToInclude(range, -5.0);
        Assert.assertEquals("Expanding with a value below the range should set the lower bound to the input value", 
                     new Range(-5.0, 10.0), result);
    }
    
    @Test
    public void testExpandToIncludeValueAboveRange() {
        Range range = new Range(0.0, 10.0);
        Range result = Range.expandToInclude(range, 15.0);
        Assert.assertEquals("Expanding with a value above the range should set the upper bound to the input value", 
                     new Range(0.0, 15.0), result);
    }
    
    @Test
    public void testExpandToIncludeValueWithinRange() {
        Range range = new Range(0.0, 10.0);
        Range result = Range.expandToInclude(range, 5.0);
        Assert.assertEquals("Expanding with a value within the range should return the same range", 
                     range, result);
    }
    
    //Equals Method Tests
    @Test
    public void testEqualsWithSameRange() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(0.0, 10.0);
        assertTrue("Ranges with the same bounds should be equal", range1.equals(range2));
    }
    
    @Test
    public void testEqualsWithDifferentRanges() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(0.0, 20.0);
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(range2));
    }
    
    @Test
    public void testEqualsWithDifferentLowerBound() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(1.0, 10.0);
        assertFalse("Ranges with different lower bounds should not be equal", range1.equals(range2));
    }
    
    @Test
    public void testEqualsWithDifferentUpperBound() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(0.0, 20.0);
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(range2));
    }
    
    @Test
    public void testEqualsWithNull() {
        Range range = new Range(0.0, 10.0);
        assertFalse("Range should not be equal to null", range.equals(null));
    }
    
    @Test
    public void testEqualsWithNonRangeObject() {
        Range range = new Range(0.0, 10.0);
        assertFalse("Range should not be equal to a non-Range object", range.equals("Not a range"));
    }
    
    //toString tests
    @Test
    public void testToStringWithPositiveBounds() {
        Range range = new Range(0.0, 10.0);
        Assert.assertEquals("String representation of the range should be 'Range[0.0,10.0]'", "Range[0.0,10.0]", range.toString());
    }
    
    @Test
    public void testToStringWithNegativeBounds() {
        Range range = new Range(-5.0, -2.5);
        Assert.assertEquals("String representation of the range should be 'Range[-5.0,-2.5]'", "Range[-5.0,-2.5]", range.toString());
    }
    
    @Test
    public void testToStringWithZeroBounds() {
        Range range = new Range(0.0, 0.0);
        Assert.assertEquals("String representation of the range should be 'Range[0.0,0.0]'", "Range[0.0,0.0]", range.toString());
    }
    
    @Test
    public void testToStringWithIntegerBounds() {
        Range range = new Range(1, 5);
        Assert.assertEquals("String representation of the range should be 'Range[1.0,5.0]'", "Range[1.0,5.0]", range.toString());
    }

    
    //hash code tests
    @Test
    public void testHashCodeConsistency() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(0.0, 10.0);
        
        Assert.assertEquals("Hash codes should be consistent for equal ranges", range1.hashCode(), range2.hashCode());
    }
    
    @Test
    public void testHashCodeDifferentRanges() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(-5.0, 5.0);
        
        // Assuming the ranges are not equal, hash codes should ideally be different
        Assert.assertEquals("Hash codes should be different for different ranges", false, range1.equals(range2));
        Assert.assertEquals("Hash codes should be different for different ranges", range1.hashCode() != range2.hashCode());
    }

}
