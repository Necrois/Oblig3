package no.ntnu.idata.iir.Joachiam.oblig3;

import java.time.LocalDate;

/**
 * Represents a bonus member at silver level.
 */
public class SilverMember extends BonusMember {
  /**
   * Constructs an instance of SilverMember.
   *
   * @param memberNo     member number
   * @param personals    personals information
   * @param enrolledDate date enrolled as a member
   */
  public SilverMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
    super(memberNo, personals, enrolledDate);
    // Call the super-version of register points, since the member should not
    // get the extra factor on already earned points.

  }

  @Override
  public String getMembershipLevel() {
    return "Silver";
  }

  @Override
  public void registerPoints(int points) {
    float silverBonus = points * FACTOR_SILVER;
    int pointsToAdd = Math.round(silverBonus);

    this.bonusPoints += pointsToAdd;
  }
}