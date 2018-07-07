import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.json.simple.JSONArray;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Projections.*;


public class Mongo implements IDBOps {

    private MongoDatabase db = null;
    private MongoClient mongoClient;
    private boolean isConnected = false;

    @Override
    public void openConnection(String node, Integer port, String databaseName) {
        // connect
//        mongoClient = new MongoClient("localhost", 27017);
        mongoClient = new MongoClient(node, port);
        db = mongoClient.getDatabase(databaseName);
        isConnected = true;

    }

    @Override
    public JSONArray scan(String col) {

        // check connection

        MongoCollection collection = db.getCollection(col);
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

        MongoCursor<Document> cursor = collection.find().projection(projDoc).iterator();

        while(cursor.hasNext()) {
            Document doc = cursor.next();
            jsonArray.add(doc);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONArray filter(String col, String operator, QueryCriteria... queryCriteria) {

        MongoCollection collection = db.getCollection(col);
        JSONArray jsonArray = new JSONArray();
        Document queryDoc = getQueryDoc(operator, queryCriteria);

        MongoCursor<Document> cursor = collection.find(queryDoc).iterator();

        while(cursor.hasNext()) {
            Document doc = cursor.next();
            jsonArray.add(doc);
        }
        System.out.println(jsonArray);

        return jsonArray;
    }

    public Document getQueryDoc(String operator, QueryCriteria... queryCriteria) {

        Document query = null;
        BasicDBList criteria = new BasicDBList();

        for(QueryCriteria crit: queryCriteria) {
            Document criterion = null;

            switch(crit.getOperator()) {
                case "EQ":
                    criterion = new Document(crit.getField(), new Document("$eq",crit.getValue()));
                    break;
                case "GT":
                    criterion = new Document(crit.getField(), new Document("$gt",crit.getValue()));
                    break;
                case "LT":
                    criterion = new Document(crit.getField(), new Document("$lt",crit.getValue()));
                    break;
                case "GTE":
                    criterion = new Document(crit.getField(), new Document("$gte",crit.getValue()));
                    break;
                case "LTE":
                    criterion = new Document(crit.getField(), new Document("$lte",crit.getValue()));
                    break;
                case "NE":
                    criterion = new Document(crit.getField(), new Document("$ne",crit.getValue()));
                    break;
                default: System.out.println("Invalid Operator");
            }
            criteria.add(criterion);
        }

        if(operator == "AND") {
            query = new Document("$and", criteria);
        } else if(operator == "OR") {
            query = new Document("$or", criteria);
        }

        return query;
    }

}
