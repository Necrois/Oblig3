package no.ntnu.idata.iir.Joachiam.oblig3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
/**
 * Holds the archive of all bonus members.
 */
public class MemberArchive  {
  // Constants defining the bonus point limits for becoming silver- and gold members
  public static final int SILVER_LIMIT = 25000;
  public static final int GOLD_LIMIT = 75000;

  // Since members have a unique member number, HashMap is ideal for
  // storing the members. Use member number for key
  private HashMap<Integer, BonusMember> members;

  // Random number generator to be used for member numbers
  private static final Random randomGenerator = new Random();

  /**
   * Constructs a member archive with no members.
   */
  public MemberArchive() {
    this.members = new HashMap<>();
  }

  /**
   * Returns the number of bonus points earned by the member whos memebr number
   * and password is given by the parameters.
   * If no member is found matching the member number, or
   * the password is wrong, -1 is returned.
   *
   * @param memberNumber the member number of the member to look up
   * @param password     the password of the member to look up
   * @return earned bonus points of the member in question.
   *         -1 if no member found matching the membernumber or if wrong password.
   */
  public int findPoints(int memberNumber, String password) {
    int pointsFound;

    BonusMember foundMember = findMember(memberNumber);

    if ((foundMember == null) || !foundMember.okPassword(password)) {
      pointsFound = -1;
    } else {
      pointsFound = foundMember.getPoints();
    }
    return pointsFound;
  }

  /**
   * Register new bonus points on the member given by the parameter.
   * If the registering of bonus points was successfull, <code>true</code>
   * is returned. If not successfull, due to no members
   * matching the member number, <code>false</code> is returned.
   *
   * @param memberNumber the member number of the member ot register bonus points on
   * @param bonusPoints  bonus points to register
   * @return <code>true</code> if successfully registered, <code>false</code>
   *         not successful.
   */
  public boolean registerPoints(int memberNumber, int bonusPoints) {
    boolean success = false;
    BonusMember member = this.findMember(memberNumber);
    if (member != null) {
      member.registerPoints(bonusPoints);
      success = true;
    }

    return success;
  }

  /**
   * Adds a new member to the archive with the personals and enrollment date
   * given by the parameters. The new member is registered as a Basic Member.
   * The member is given a unique random member number.
   * The member number is returned.
   *
   * @param pers         personals of the memebr to add
   * @param dateEnrolled the enrollment date of the membership
   * @return the assigned membership number
   */
  public int addMember(Personals pers, LocalDate dateEnrolled) {
    int memberNo = this.findAvailableNo();
    BasicMember basicMember = new BasicMember(memberNo, pers, dateEnrolled);
    this.members.put(memberNo, basicMember);
    return memberNo;
  }

  /**
   * Iterates through all the members in the archive and upgrades the membership
   * if the member qualifies for upgrade.
   * A member qualifies for upgrade if he enrolled less than 365 days ago,
   * and have earned enough bonuspoints (25 000 for silver, 75 000 for gold)
   */
  public void checkMembers(LocalDate currentDate) {

    for (Integer memberNo : this.members.keySet()) {
      // Handle the basic members
      BonusMember member = this.members.get(memberNo);
      if (member instanceof BasicMember) {
        if (member.findQualificationPoints(currentDate) >= GOLD_LIMIT) {
          GoldMember goldMember = new GoldMember(
              member.getMemberNo(),
              member.getPersonals(),
              member.getEnrolledDate(),
              member.getPoints());
          this.members.replace(memberNo, goldMember);
        } else if (member.findQualificationPoints(currentDate) >= SILVER_LIMIT) {
          SilverMember silverMember = new SilverMember(
              member.getMemberNo(),
              member.getPersonals(),
              member.getEnrolledDate(),
              member.getPoints());
          this.members.replace(memberNo, silverMember);
        }
      }

      // Handel silver members
      if ((member instanceof SilverMember)
          && (member.findQualificationPoints(currentDate) >= GOLD_LIMIT)) {
        GoldMember goldMember = new GoldMember(
            member.getMemberNo(),
            member.getPersonals(),
            member.getEnrolledDate(),
            member.getPoints());
        this.members.replace(memberNo, goldMember);
      }

    }
  }

  /**
   * Upgrades a member from old membership to new membership as given by the parameters.
   * Takes two parameters; the first is the member to be removed from the archive,
   * the second the member to replace the removed member.
   *
   * @param oldMembership the member to remove from the archive
   * @param newMembership the member to replace the removed member
   */
  private void upgradeMember(BonusMember oldMembership, BonusMember newMembership) {
    BonusMember memberRemoved = this.members.remove(oldMembership.getMemberNo());
    if (memberRemoved != null) {
      this.members.put(newMembership.getMemberNo(), newMembership);
    }
  }

  /**
   * Returns the member matching the membernumber given by the parameter.
   * If no member found, <code>null</code> is returned.
   *
   * @param memberNumber the member number of the member to search for
   * @return the member matching the member number.
   *         If no member found, <code>null</code> is returned.
   */
  public BonusMember findMember(int memberNumber) {
    return this.members.get(memberNumber);
  }

  /**
   * Generates a new unique membership number. The number can be any number
   * between 1 and Interger.MAX_VALUE.
   *
   * @return a unique random membership number.
   */
  private int findAvailableNo() {
    int newMembershipNumber = 0;
    boolean foundAvailabelNumber = false;

    while (!foundAvailabelNumber || (newMembershipNumber == 0)) {
      newMembershipNumber = randomGenerator.nextInt(Integer.MAX_VALUE);
      if (!this.members.containsKey(newMembershipNumber)) {
        foundAvailabelNumber = true;
      }
    }
    return newMembershipNumber;
  }

  /**
   * Stream sorting values
   * returning number of member in order of points
   *
   *
   */
public Stream<BonusMember> stream() { return this.members.values().stream(); }

public void forEach(Consumer<? super BonusMember> action)  { this.members.values().forEach(action); }

BonusMember getBonusMember(int number) { return this.members.get(number); }

//@Override
  //public Iterator<BonusMember> iterator() { return members.values().iterator(); }


}
