package no.ntnu.idata.iir.Joachiam.oblig3;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BonusMemberTest {
    private LocalDate testDate;
    private Personals ole;
    private Personals tove;

    @BeforeEach
    void setUp() {
        this.testDate = LocalDate.of(2008, 2, 10);
        this.ole = new Personals("Ole", "Olsen",
                "ole.olsen@dot.com", "ole");
        this.tove = new Personals("Tove", "Hansen",
                "tove.hansen@dot.com", "tove");
    }

    /**
     * Tests the calculation of points for  a basic member Ole.
     * User should not be qualified for the upgrade.
     *
     */

    @Test
    void testBasicMemberOle() {
        BasicMember b1 = new BasicMember(100, ole,
                LocalDate.of(2006, 2, 15));
        b1.registerPoints(30000);
        System.out.println("Test nr 1: No qualification points");
        assertEquals(0, b1.findQualificationPoints(testDate));
        assertEquals(30000, b1.getPoints());
        System.out.println("Test nr 2: Adding 15 000 points, still no qualification points");
        b1.registerPoints(15000);
        assertEquals(0, b1.findQualificationPoints(testDate));
        assertEquals(45000, b1.getPoints());
    }

    /**
     * Tests a member that should be qualified for a silver and later gold upgrade.
     *
     *
     */

    @Test
    void testBasicMemberTove() {
        BasicMember b2 = new BasicMember(110, tove,
                LocalDate.of(2007, 5, 3));
        b2.registerPoints(30000);
        System.out.println("Test nr 3: Tove should qualify");
        assertEquals(30000, b2.findQualificationPoints(testDate));
        assertEquals(30000, b2.getPoints());
        System.out.println("Test nr 4: Tove as silver member");
        SilverMember b3 = new SilverMember(b2.getMemberNo(), b2.getPersonals(),
                b2.getEnrolledDate(), b2.getPoints());
        b3.registerPoints(50000);
        assertEquals(90000, b3.findQualificationPoints(testDate));
        assertEquals(90000, b3.getPoints());
        System.out.println("Test nr 5: Tove as gold member");
        GoldMember b4 = new GoldMember(b3.getMemberNo(), b3.getPersonals(),
                b3.getEnrolledDate(), b3.getPoints());
        b4.registerPoints(30000);
        assertEquals(135000, b4.findQualificationPoints(testDate));
        assertEquals(135000, b4.getPoints());
        System.out.println("Test nr 6: Changed test date on Tove");
        testDate = LocalDate.of(2008, 10, 12);
        // assertEquals( 0, b4.findQualificationPoints(testDate)); Should be 135000??
        assertEquals(0, b4.findQualificationPoints(testDate));
        assertEquals(135000, b4.getPoints());
    }



    @org.junit.jupiter.api.Test
    void getMemberNo() {
    }

    @org.junit.jupiter.api.Test
    void getPersonals() {
    }

    @org.junit.jupiter.api.Test
    void getEnrolledDate() {
    }

    @org.junit.jupiter.api.Test
    void getPoints() {
    }

    @org.junit.jupiter.api.Test
    void findQualificationPoints() {
    }

    @org.junit.jupiter.api.Test
    void okPassword() {
    }

    @org.junit.jupiter.api.Test
    void getMembershipLevel() {
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
    }

    @org.junit.jupiter.api.Test
    void registerPoints() {
    }

    /**
     * Tests the password for both members.
     *
     *
     */

    @Test
    void testPasswords() {
        System.out.println("Test nr 7: Trying wrong password on Ole");
        assertFalse(ole.okPassword("000"));
        System.out.println("Test nr 8: Trying correct password on Tove.");
        assertTrue(tove.okPassword("tove"));
    }



}