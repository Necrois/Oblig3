package no.ntnu.idata.iir.Joachiam.oblig3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Superclass for all types of bonus members.
 * Holds all information that is common to all categories of bonus members,
 * including common functionality.
 *
 * @author ntnu
 * @version 2020-01-31
 */
public abstract class BonusMember implements Comparable<BonusMember> {
  private final int memberNo;
  private final Personals personals;
  private final LocalDate enrolledDate;
  protected int bonusPoints = 0;

  // Constants defining the bonus point factor
  protected static final float FACTOR_SILVER = 1.2F;
  protected static final float FACTOR_GOLD = 1.5F;


  /**
   * Constructs an instance of BonusMember.
   *
   * @param memberNo     member number
   * @param personals    personals information
   * @param enrolledDate date enrolled as a member
   */
  public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate) {
    if (memberNo < 0) {
      throw new IllegalArgumentException("Member number cannot be negative: " + memberNo);
    }
    if (personals == null || enrolledDate == null) {
      throw new IllegalArgumentException("Parameter personals or enrolledDate was null");
    }
    this.memberNo = memberNo;
    this.personals = personals;
    this.enrolledDate = enrolledDate;
  }

  /**
   * Returns the member number.
   *
   * @return the member number
   */
  public int getMemberNo() {
    return this.memberNo;
  }

  /**
   * Returns an instance of the Personals-class
   * holding all personal information of the person
   * related to this membership.
   *
   * @return the personal information of the member
   */
  public Personals getPersonals() {
    return this.personals;
  }

  /**
   * Returns the date enrolled in the bonus program.
   *
   * @return the date enrolled in the bonus program
   */
  public LocalDate getEnrolledDate() {
    return this.enrolledDate;
  }

  /**
   * Returns the number of bonus points earned so far.
   *
   * @return the number of bonus points earned so far.
   */
  public int getPoints() {
    return this.bonusPoints;
  }

  /**
   * Checks if the member is qualified to be upgraded, by returning
   * the currently earned bonus points if the membership is less than 365
   * days old. If the member was enrolled more than 365 days ago,
   * zero is returned, indicating no qualifying bonus points.
   *
   * @param date the date to chack against. Usually todays date.
   * @return 0 if not qualified for upgrade. If qualified, the number of
   *         currently earned bonus points is returned.
   */
  public int findQualificationPoints(LocalDate date) {
    int qualifyingPoints;
    if (ChronoUnit.DAYS.between(this.enrolledDate, date) <= 365) {
      qualifyingPoints = this.bonusPoints;
    } else {
      qualifyingPoints = 0;
    }

    return qualifyingPoints;
  }

  /**
   * Checks if the password provided by the parameter is valid.
   * If valid <code>true</code> is returned, otherwise <code>false</code>
   * is returned.
   *
   * @param password password to check/verify
   * @return <code>true</code> if password is correct, <code>false</code> if not.
   */
  public boolean okPassword(String password) {
    return personals.okPassword(password);
  }

  /**
   * Register bonus points.
   *
   * //@param newPoints the bonuspoints to be added
   */
  //public void registerPoints(int newPoints) {
    //this.bonusPoints += newPoints;


  @Override
  public int compareTo(BonusMember bonusMember){
    int sign = 0;
    if(this.bonusPoints > bonusMember.getPoints()){
      sign = -1;
    }
    if(this.bonusPoints < bonusMember.getPoints()){
      sign = 1;
    }
    return sign;
  }

  public abstract void registerPoints(int points);

}
