import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({MagazinTest.class, CentruComercialTest.class})
@IncludeTags({"UnitTest1", "UnitTest2"})
public class SuitaTeste {
}
