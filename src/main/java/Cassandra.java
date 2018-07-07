import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.json.simple.JSONArray;

public class Cassandra implements IDBOps {

    private String db = null;
    private Cluster cluster;
    private Session session;
    private boolean isConnected = false;
//    private String node = "127.0.0.1";


    @Override
    public void openConnection(String node, Integer port, String databaseName) {

        cluster= Cluster.builder().addContactPoint(node).build();
        session = cluster.connect();

        db = databaseName;
        session.execute("CREATE KEYSPACE IF NOT EXISTS "+db+" WITH REPLICATION={'class':'SimpleStrategy', 'replication_factor': 1};");
        session.execute("USE "+db);
        isConnected = true;
    }

    @Override
    public JSONArray scan(String col) {
        JSONArray jsonArray = new JSONArray();

        String query = "SELECT JSON * from "+db+"."+col;

        for(Row row : session.execute(query)) {
            jsonArray.add(JSON.parse(row.getString("[json]")));
        }

        System.out.println(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONArray project(String col, String... fields) {
        JSONArray jsonArray = new JSONArray();

        StringBuilder sb = new StringBuilder();
        for(String field: fields) {
            if(sb.length()!=0) {
                sb.append(", ");
            }
            sb.append(field);
        }

        String fieldStr = sb.toString();
        String query = "SELECT JSON "+fieldStr+" from "+db+"."+col;

        for(Row row : session.execute(query)) {
            jsonArray.add(JSON.parse(row.getString("[json]")));
        }

        System.out.println(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONArray filter(String col, String operator, QueryCriteria... queryCriteria) {
        JSONArray jsonArray = new JSONArray();
        String query = null;

        if(operator == "OR") {
            System.out.println("Invalid operator");
        } else {
            String criteria = getQueryString(queryCriteria);
            query = "SELECT JSON * FROM "+db+"."+col+" WHERE "+criteria+" ALLOW FILTERING";
            for(Row row : session.execute(query)) {
                jsonArray.add(JSON.parse(row.getString("[json]")));
            }

            System.out.println(jsonArray);
        }
        return jsonArray;
    }

    public String getQueryString(QueryCriteria... queryCriteria) {
        StringBuilder query = new StringBuilder();

        for(QueryCriteria crit: queryCriteria) {
                if(query.length()!=0) {
                    query.append(" AND ");
                }

                if(crit.getValue() instanceof String) {
                    switch(crit.getOperator()) {
                        case "EQ":
                            query.append(crit.getField()+" = "+"'"+crit.getValue()+"'");
                            break;
                        case "GT":
                            query.append(crit.getField()+" > "+"'"+crit.getValue()+"'");
                            break;
                        case "LT":
                            query.append(crit.getField()+" < "+"'"+crit.getValue()+"'");
                            break;
                        case "GTE":
                            query.append(crit.getField()+" >= "+"'"+crit.getValue()+"'");
                            break;
                        case "LTE":
                            query.append(crit.getField()+" <= "+"'"+crit.getValue()+"'");
                            break;
                        case "NE":
                            System.out.println("Not Supported Operator");
                            break;
                        default: System.out.println("Invalid Operator");
                    }
                } else {
                    switch(crit.getOperator()) {
                        case "EQ":
                            query.append(crit.getField()+" = "+crit.getValue());
                            break;
                        case "GT":
                            query.append(crit.getField()+" > "+crit.getValue());
                            break;
                        case "LT":
                            query.append(crit.getField()+" < "+crit.getValue());
                            break;
                        case "GTE":
                            query.append(crit.getField()+" >= "+crit.getValue());
                            break;
                        case "LTE":
                            query.append(crit.getField()+" <= "+crit.getValue());
                            break;
                        case "NE":
                            System.out.println("Not Supported Operator");
                            break;
                        default: System.out.println("Invalid Operator");
                    }
                }


        }
        return query.toString();
    }
}
