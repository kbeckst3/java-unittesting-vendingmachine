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
public class BinTest {
    private Bin bin;
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(Bin.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        bin = new Bin(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void restockingWithDifferentItemsIsNotAllowed() {
        bin.restock("Cheetos", 1, 100, 50);
        bin.restock("Doritos", 1, 100, 50);
    }

    @Test
    public void whenBinIsEmptyItGivesZero() {
        assertEquals(0, bin.getItemPrice());
    }

    @Test
    public void whenEmptyNameIsNull() {
        assertNull(bin.getItemName());
    }
    @Test
    public void overStockingNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("There are only 10 spots left");

        bin.restock("Fritos", 25090, 100, 50 );
    }
}
