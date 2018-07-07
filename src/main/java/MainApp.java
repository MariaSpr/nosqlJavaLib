
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
                cass.openConnection("127.0.0.1", 0, "testKS");
//                cass.scan("cats");
//                cass.project("cats", "name", "age", "color");
            QueryCriteria queryCriteria1 = new QueryCriteria(1, "age", "GTE");
            QueryCriteria queryCriteria2 = new QueryCriteria(4, "age", "LT");
            QueryCriteria queryCriteria3 = new QueryCriteria("white", "color", "EQ");
            cass.filter("cats", "AND", queryCriteria1, queryCriteria2);
//            Mongo mongo = new Mongo();
//            mongo.openConnection("localhost", 27017, "testDB");

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
