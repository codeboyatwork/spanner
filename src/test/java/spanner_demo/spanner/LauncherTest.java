package spanner_demo.spanner;

import junit.framework.TestCase;

public class LauncherTest extends TestCase {
	public static final String INSTANCE_ID = "my-spanner-td";
	public static final String DATABASE_ID = "demo_spanner";

	public void test_select() throws Exception {
		App.main(INSTANCE_ID, DATABASE_ID);
	}
	public void test_insert() throws Exception {
		Insert.main(INSTANCE_ID, DATABASE_ID);
	}
}
