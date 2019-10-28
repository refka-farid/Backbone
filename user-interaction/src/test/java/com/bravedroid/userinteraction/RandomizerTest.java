package com.bravedroid.userinteraction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RandomizerTest {
    private Randomizer.LoopWithForRandomizerVersionOne SUT1;
    private Randomizer.LoopWithForRandomizerVersionTwo SUT2;
    private Randomizer.LoopWithWhileRandomizerVersionOne SUT3;
    private Randomizer.LoopWithDoWhileRandomizer SUT4;
    private Randomizer.LoopWithWileRandomizerVersionTwo SUT5;

    @Before
    public void setUp() {
        SUT1 = new Randomizer.LoopWithForRandomizerVersionOne();
        SUT2 = new Randomizer.LoopWithForRandomizerVersionTwo();
        SUT3 = new Randomizer.LoopWithWhileRandomizerVersionOne();
        SUT4 = new Randomizer.LoopWithDoWhileRandomizer();
        SUT5 = new Randomizer.LoopWithWileRandomizerVersionTwo();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRandomIndexNegativeNumberAsBound() {
        int randomIndex1 = SUT1.getRandomIndex(-2);
        int randomIndex2 = SUT2.getRandomIndex(-2);
        int randomIndex3 = SUT3.getRandomIndex(-2);
        int randomIndex4 = SUT4.getRandomIndex(-2);
        int randomIndex5 = SUT5.getRandomIndex(-2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getRandomIndexZeroAsBound() {
        int randomIndex1 = SUT1.getRandomIndex(0);
        int randomIndex2 = SUT2.getRandomIndex(0);
        int randomIndex3 = SUT3.getRandomIndex(0);
        int randomIndex4 = SUT4.getRandomIndex(0);
        int randomIndex5 = SUT5.getRandomIndex(0);
    }

    @Test
    public void getRandomIndexTakeTwoAsBoundShouldNotBeEqualsToOldIndex() {
        SUT1.setOldIndex(1);
        SUT2.setOldIndex(1);
        SUT3.setOldIndex(1);
        SUT4.setOldIndex(1);
        SUT5.setOldIndex(1);

        int randomIndex1 = SUT1.getRandomIndex(2);
        int randomIndex2 = SUT2.getRandomIndex(2);
        int randomIndex3 = SUT3.getRandomIndex(2);
        int randomIndex4 = SUT4.getRandomIndex(2);
        int randomIndex5 = SUT5.getRandomIndex(2);

        assertNotEquals(1, randomIndex1);
        assertNotEquals(1, randomIndex2);
        assertNotEquals(1, randomIndex3);
        assertNotEquals(1, randomIndex4);
        assertNotEquals(1, randomIndex5);
    }

    @Test
    public void getRandomIndexCorrectBound() {
        SUT1.setOldIndex(1);
        SUT2.setOldIndex(1);
        SUT3.setOldIndex(1);
        SUT4.setOldIndex(1);
        SUT5.setOldIndex(1);

        int randomIndex1 = SUT1.getRandomIndex(1);
        int randomIndex2 = SUT2.getRandomIndex(1);
        int randomIndex3 = SUT3.getRandomIndex(1);
        int randomIndex4 = SUT4.getRandomIndex(1);
        int randomIndex5 = SUT5.getRandomIndex(1);

        assertEquals(0, randomIndex1);
        assertEquals(0, randomIndex2);
        assertEquals(0, randomIndex3);
        assertEquals(0, randomIndex4);
        assertEquals(0, randomIndex5);
    }
}
