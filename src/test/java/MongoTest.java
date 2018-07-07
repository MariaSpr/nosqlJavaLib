import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MongoTest {
    private String node;
    private Integer port;
    private String databaseName;
    private Mongo mongo = new Mongo();

    @Before
    public void setUp() throws Exception {
        node = "localhost";
        port= 27017;
        databaseName = "testDB";
        mongo.openConnection(node, port, databaseName);

    }

    @Test
    public void openConnection() {
    }

    @Test
    public void scan() {
        /* cats collection: id, name, fur, age */
        mongo.scan("cats");
    }

    @Test
    public void project() {
        mongo.project("cats", "name", "age");
    }

    @Test
    public void filter() {
        QueryCriteria queryCriteria1 = new QueryCriteria(2, "age", "GT");
        QueryCriteria queryCriteria2 = new QueryCriteria(4, "age", "LTE");
            mongo.filter("cats", "AND", queryCriteria1, queryCriteria2 );
    }
}