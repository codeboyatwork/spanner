package spanner_demo.spanner;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;
import com.google.cloud.spanner.Statement;

/**
 * A quick start code for Cloud Spanner. It demonstrates how to setup the Cloud Spanner client and
 * execute a simple query using it against an existing database.
 */
public class Insert {
  public static void main(String... args) throws Exception {
	long keyvalue = 0;
    String nametouse = "";
    if (args.length != 2) {
      System.err.println("Usage: QuickStartSample <instance_id> <database_id>");
      return;
    }
    // Instantiates a client
    SpannerOptions options = SpannerOptions.newBuilder().build();
    Spanner spanner = options.getService();

    // Name of your instance & database.
    String instanceId = args[0];
    String databaseId = args[1];
    try {
      // Creates a database client
      DatabaseClient dbClient =
          spanner.getDatabaseClient(DatabaseId.of(options.getProjectId(), instanceId, databaseId));
      
      // Queries the database
      try (ResultSet resultSet = dbClient.singleUse().executeQuery(Statement.of("select MAX(id_column) from my_table"))) {
          System.out.println("\n\nResults:");
          // Prints the results
          while (resultSet.next()) {
            keyvalue = resultSet.getLong(0);
            keyvalue += 1;
            nametouse = "name" + keyvalue;
          }
        }  
      final long tempkey = keyvalue;
      final String tempname = nametouse;
      dbClient
      .readWriteTransaction()
      .run(transaction -> {
        String sql =
            "INSERT INTO my_table (id_column, name_column) "
                + " VALUES ("+tempkey+",'"+tempname+"')";
        long rowCount = transaction.executeUpdate(Statement.of(sql));
        System.out.printf("%d record inserted.\n", rowCount);
        return null;
      });
    } finally {
      // Closes the client which will free up the resources used
      spanner.close();
    }
  }
}
