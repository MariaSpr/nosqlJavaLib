
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
            Mongo mongo = new Mongo();
            mongo.openConnection();

            Scanner input = new Scanner(System.in);
            System.out.print("Enter a collection: ");
            String col = input.nextLine();
            mongo.scan(col);

            Scanner inputField = new Scanner(System.in);
            System.out.print("Enter a Field: ");
            String field = input.nextLine();
            mongo.project(col, field);




//            List<String> dbs = mongo.listDatabaseNames().into(new LinkedList<>());
//            System.out.println(dbs);


        } catch (Exception e) {
            System.out.println(e);
        }



    }
}
