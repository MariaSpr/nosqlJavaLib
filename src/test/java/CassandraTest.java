import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CassandraTest {
    private String node;
    private Integer port;
    private String databaseName;
    private Cassandra cass = new Cassandra();

    @Before
    public void setUp() throws Exception {
        node = "127.0.0.1";
        port= 9072;
        databaseName = "testKS";
        cass.openConnection(node, port, databaseName);

    }

    @Test
    public void openConnection() {
    }

    @Test
    public void scan() {
        cass.scan("cats");
    }

    @Test
    public void project() {
        cass.project("cats", "name", "age");
    }

    @Test
    public void filter() {
        QueryCriteria queryCriteria1 = new QueryCriteria(2, "age", "GT");
        QueryCriteria queryCriteria2 = new QueryCriteria(4, "age", "LTE");
        cass.filter("cats", "AND", queryCriteria1, queryCriteria2 );
    }
}