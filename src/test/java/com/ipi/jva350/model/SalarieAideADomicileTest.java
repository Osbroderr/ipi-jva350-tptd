package com.ipi.jva350.model;

import static org.mockito.ArgumentMatchers.contains;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SalarieAideADomicileTest {
    @Test
    void testALegalementDroitADesCongesPayesNonInitialise() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile();
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res);
    }

    @Test
    void testALegalementDroitADesCongesPayesTrue() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne", LocalDate.now(), LocalDate.now(), 0, 0, 145, 1,
                1);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(true, res);
    }

    @Test
    void testALegalementDroitADesCongesPayesJoursTravaillesCasAuLimite() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne", LocalDate.now(), LocalDate.now(), 0, 0, 10, 1,
                1);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(true, res);

        // When :
        aide.setJoursTravaillesAnneeNMoins1(9);
        ;
        boolean res1 = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res1);
    }

    // @Test
    // void testcalculeJoursDeCongeDecomptesPourPlage() {
    // // Given :
    // SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
    // LocalDate.now(), LocalDate.now(), 0, 0, 10, 1,
    // 1);
    // // When :
    // Set<LocalDate> res =
    // aide.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2021, 01, 21),
    // LocalDate.now());
    // // Then :
    // Assertions.assertEquals(true, res.isEmpty());
    // }
}
