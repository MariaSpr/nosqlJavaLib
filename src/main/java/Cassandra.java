import org.json.simple.JSONArray;

public class Cassandra implements IDBOps {
    @Override
    public void openConnection() {

    }

    @Override
    public JSONArray scan(String col) {
        return null;
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
