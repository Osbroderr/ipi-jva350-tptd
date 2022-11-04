package com.ipi.jva350.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @Test
    void testcalculeJoursDeCongeDecomptesPourPlageEgal() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.now(), LocalDate.now(), 0, 0, 10, 1,
                1);
        // When :
        LinkedHashSet<LocalDate> res = aide.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2022, 7, 01),
                LocalDate.of(2022, 7, 02));

        // Then :
        LinkedHashSet<LocalDate> expected = new LinkedHashSet<>();
        expected.add(LocalDate.of(2022, 7, 1));
        expected.add(LocalDate.parse("2022-07-02"));

        Assertions.assertEquals(expected, res);
    }

    @ParameterizedTest(name = "numeroSecu {0} est valide : {1}")
    @CsvSource({
            "'2022-07-01','2022-07-02', 2"
    })
    void testcalculeJoursDeCongeDecomptesPourPlageEgalParama(String debut, String fin, double expectedNbJCongeDec) {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.now(), LocalDate.now(), 0, 0, 10, 1,
                1);
        // When :
        LinkedHashSet<LocalDate> res = aide.calculeJoursDeCongeDecomptesPourPlage(LocalDate.parse(debut),
                LocalDate.parse(fin));

        // Then :

        Assertions.assertEquals(expectedNbJCongeDec, res.size());
    }
}
