package spanner_demo.spanner;

import junit.framework.TestCase;

public class LauncherTest extends TestCase {
	public static final String INSTANCE_ID = "my-spanner-td";
	public static final String DATABASE_ID = "demo_spanner";

	public void test_spanner() throws Exception {
		App.main(INSTANCE_ID, DATABASE_ID);
	}
}
