package CheckersTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * ImplementationTests encapsulates all tests dealing with implementation.
 * Thanks to CSE331 for this thingy
 */

@RunWith(Suite.class)
@SuiteClasses({MainTest.class
			, GameBoardTest.class
			, CheckersModelTest.class/* list classes here */ })
public final class ImplementationTests
{
  //this class is a placeholder for the suite, so it has no members.
}