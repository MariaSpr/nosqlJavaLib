
import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args){
        try {

                Cassandra cass = new Cassandra();
                cass.openConnection();
                cass.scan("cats");
//            Mongo mongo = new Mongo();
//            mongo.openConnection();

//            Scanner input = new Scanner(System.in);
//            System.out.print("Enter a collection: ");
//            String col = input.nextLine();
//            mongo.scan(col);

//            Scanner inputField = new Scanner(System.in);
//            System.out.print("Enter a Field: ");
//            String field = input.nextLine();
//            mongo.project(col, field);

//            QueryCriteria queryCriteria1 = new QueryCriteria(2, "age", "GT");
//            QueryCriteria queryCriteria2 = new QueryCriteria(4, "age", "LTE");
//            mongo.filter(col, "AND", queryCriteria1, queryCriteria2 );
//
//            QueryCriteria stringQuery = new QueryCriteria("Svouras", "name", "EQ");
//            QueryCriteria stringQuery2 = new QueryCriteria("Fystiki", "name", "EQ");
//
//            mongo.filter(col, "OR", stringQuery, stringQuery2 );




        } catch (Exception e) {
            System.out.println(e);
        }



    }
}
