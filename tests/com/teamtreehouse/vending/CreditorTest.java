package com.teamtreehouse.vending;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//@RunWith(Arquillian.class)
public class CreditorTest {
    private Creditor creditor;
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(Creditor.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }

    @Before
    public void setUp() throws Exception {
        creditor = new Creditor();
    }

    @Test
    public void addingFundsIncrementsAvailableFunds() {
        creditor.addFunds(25);
        creditor.addFunds(25);

        assertEquals(50, creditor.getAvailableFunds());
    }

    @Test
    public void afterRefundAvailableFundsIsZero() {
        creditor.addFunds(10);
        
        creditor.refund();
        
        assertEquals(0, creditor.getAvailableFunds());
    }

    @Test
    public void refundReturnsAllAvailableFunds(){
        creditor.addFunds(10);

        int refund = creditor.refund();

        assertEquals(10, refund);

    }
}
