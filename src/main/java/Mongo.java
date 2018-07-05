import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.json.simple.JSONArray;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import static com.mongodb.client.model.Projections.*;


public class Mongo implements IDBOps {

    private MongoDatabase db = null;
    private MongoClient mongoClient;

    @Override
    public void openConnection() {
        // connect
        MongoClient mongo = new MongoClient("localhost", 27017);
        db = mongo.getDatabase("testDB");

    }

    @Override
    public JSONArray scan(String col) {

        // check connection

        MongoCollection collection = db.getCollection(col);
//        db.getCollection(col).find().projection().iterator();
        MongoCursor<Document> cursor = collection.find().iterator();
        JSONArray jsonArray = new JSONArray();
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            jsonArray.add(doc);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONArray project(String col, String... fields) {

        MongoCollection collection = db.getCollection(col);
        JSONArray jsonArray = new JSONArray();

        Document projDoc = new Document();
        for (String field: fields) {
            projDoc.append(field, 1);
            projDoc.append("_id", 0);
        }
        // don't return default id key
//        System.out.println(projDoc.toJson());
        MongoCursor<Document> cursor = collection.find().projection(projDoc).iterator();
//        DBCursor cursor = collection.find().projection(fields(include("name"),excludeId()));

        while(cursor.hasNext()) {
            Document doc = cursor.next();
            jsonArray.add(doc);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
}
