import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface MongoDbClient {
    MongoCollection<Document> getCollection(String databaseName, String collectionName);
}
