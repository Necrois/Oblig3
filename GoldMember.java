package no.ntnu.idata.iir.Joachiam.oblig3;

import java.time.LocalDate;

/**
 * Represents a bonus member at gold level.
 */
public class GoldMember extends BonusMember {
  /**
   * Constructs an instance of GoldMember.
   *
   * @param memberNo     member number
   * @param personals    personals information
   * @param enrolledDate date enrolled as a member
   */
  public GoldMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
    super(memberNo, personals, enrolledDate);
    // Call the super-version of register points, since the member should not
    // get the extra factor on already earned points.

  }

  @Override
  public void registerPoints(int points){
    float goldBonus = points * FACTOR_GOLD;
    int pointsToAdd = Math.round(goldBonus);

    this.bonusPoints += pointsToAdd;
  }
}
