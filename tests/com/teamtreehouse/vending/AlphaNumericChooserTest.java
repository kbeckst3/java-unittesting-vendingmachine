package com.teamtreehouse.vending;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//@RunWith(Arquillian.class)
public class AlphaNumericChooserTest {
    private AlphaNumericChooser chooser;

//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(AlphaNumericChooser.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
        chooser = new AlphaNumericChooser(10, 10);
    }

    @Test
    public void validInputReturnsProperLocation() throws Exception {
        AlphaNumericChooser.Location loc = chooser.locationFromInput("B4");

        assertEquals("proper row ",1, loc.getRow());
        assertEquals("proper column ",3, loc.getColumn());

    }

    @Test(expected = InvalidLocationException.class)
    public void choosingInputLargerThanRange() throws Exception {
        chooser.locationFromInput("Z8");
    }
    @Test
    public void constructingLargerThanAlphabetNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maximum rows supported is 26");
        new AlphaNumericChooser(27, 10);
    }
}
