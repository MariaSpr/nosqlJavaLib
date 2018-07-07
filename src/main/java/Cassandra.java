import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.mongodb.util.JSON;
import org.json.simple.JSONArray;

public class Cassandra implements IDBOps {

    private String db = null;
    private Cluster cluster;
    private Session session;
    private String node = "127.0.0.1";


    @Override
    public void openConnection() {

        cluster= Cluster.builder().addContactPoint(node).build();
        session = cluster.connect();

        db = "testKS";
        session.execute("CREATE KEYSPACE IF NOT EXISTS "+db+" WITH REPLICATION={'class':'SimpleStrategy', 'replication_factor': 1};");
        session.execute("USE "+db);

    }

    @Override
    public JSONArray scan(String col) {
        JSONArray jsonArray = new JSONArray();

        String query = "SELECT JSON * from "+db+"."+col;

        for(Row row : session.execute(query)) {
            System.out.println(row.getString("[json]"));
            jsonArray.add(JSON.parse(row.getString("[json]")));
        }

        System.out.println(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONArray project(String col, String... fields) {
        return null;
    }

    @Override
    public JSONArray filter(String col, String operator, QueryCriteria... queryCriteria) {
        return null;
    }
}
