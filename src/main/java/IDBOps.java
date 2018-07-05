import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import org.json.simple.JSONArray;

public interface IDBOps {

    void openConnection();
    JSONArray scan(String col);
    JSONArray project(String col, String... fields);
}
