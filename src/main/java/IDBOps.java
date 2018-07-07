import com.mongodb.BasicDBList;
import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import org.json.simple.JSONArray;

public interface IDBOps {

    void openConnection(String node, Integer port, String databaseName);
    JSONArray scan(String col);
    JSONArray project(String col, String... fields);
    JSONArray filter(String col, String operator, QueryCriteria... queryCriteria);
}
