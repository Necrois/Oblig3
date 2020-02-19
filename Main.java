package no.ntnu.idata.iir.Joachiam.oblig3;

import java.time.LocalDate;

/**
 * This main-class is used to make a simple test of the functionality of the MemberArchive class
 * and the classes BasicMember, SilverMmeber and GoldMember.
 */
public class Main {
  public static void main(String[] args) {
   // Create an instance of MemberArchive
    MemberArchive archive = new MemberArchive();

    // Add some Basic Members, and register points. Some of the members
    // will be registered with points that qualify them for Silver og Gold
    Personals pers1 = new Personals("Arne", "Styve", "asdf@gmail.no", "qwerty");
    Personals pers2 = new Personals("Ole", "Jensen", "ole@gmail.no", "ytrewq");
    Personals pers3 = new Personals("Lise", "Pedersen", "lise@gmail.no", "zaq123");
    Personals pers4 = new Personals("Kari", "Lid", "kari@gmail.no", "123qaz");
    Personals pers5 = new Personals("Leif", "Øvrebust", "leif@gmail.no", "jadda");

    // Add all persons as members in the archive
    int memberNo1 = archive.addMember(pers1, LocalDate.of(2019, 3, 10));
    archive.registerPoints(memberNo1, 10000);

    int memberNo2 = archive.addMember(pers2, LocalDate.of(2019, 3, 10));
    archive.registerPoints(memberNo2, 30000);

    int memberNo3 = archive.addMember(pers3, LocalDate.of(2019, 3, 10));
    archive.registerPoints(memberNo3, 80000);

    int memberNo4 = archive.addMember(pers4, LocalDate.of(2019, 3, 10));
    archive.registerPoints(memberNo4, 77000);

    int memberNo5 = archive.addMember(pers5, LocalDate.of(2019, 3, 10));
    archive.registerPoints(memberNo2, 35000);

    // Upgrade members that are qualified. Here, members 2 and 5 should be
    // qualified for Silver. Member 3 and 4 should be qualified for Gold

    archive.checkMembers(LocalDate.now());

    // Check that member 2 has been upgraded to Silver
    if (archive.findMember(memberNo2) instanceof SilverMember)
    {
      System.out.println("Member 2 was upgraded to Silver - test OK");
    } else
    {
      System.out.println("Member 2 should have been upgraded to Silver, but was not - test FAILED");
    }

    // Check that member 4 has been upgraded to Gold
    if (archive.findMember(memberNo4) instanceof GoldMember)
    {
      System.out.println("Member 4 was upgraded to Gold - test OK");
    } else
    {
      System.out.println("Member 4 should have been upgraded to Gold, but was not - test FAILED");
    }


  }
}
