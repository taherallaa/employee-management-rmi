import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.mongodb.client.FindIterable;


import static com.mongodb.client.model.Filters.eq;

public class EmployeeImp extends UnicastRemoteObject implements Employee {
    private static final String MONGO_URI = "mongodb://admin:pass@localhost:27017"; // Replace with your connection URI
    private static final String DATABASE_NAME = "mydatabase"; // Replace with your database name
    private static final String COLLECTION_NAME = "Employee"; // Replace with your collection name
    private final MongoClient mongoClient;

    public EmployeeImp() throws RemoteException{
        super();
        mongoClient= MongoClients.create(MONGO_URI);
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase(EmployeeImp.DATABASE_NAME).getCollection(COLLECTION_NAME);
    }

    @Override
    public String addEmployee(String name, String email, double salary, String department) throws RemoteException {
        MongoCollection<Document> collection = getCollection();
        Document employeeDoc = new Document()
                .append("name", name)
                .append("email", email)
                .append("salary", salary)
                .append("department", department);
        InsertOneResult insertResult = collection.insertOne(employeeDoc);
        if (insertResult.getInsertedId() == null)
            return "Failed to add Employee";
        return "Employee added successfully";
    }

    @Override
    public String updateEmployeeSalary(String employeeID, String salary) throws RemoteException {
        MongoCollection<Document> collection = getCollection();

        if (employeeID == null || employeeID.isBlank() || salary == null || salary.isBlank()) {
            return "Invalid employee ID or salary provided.";
        }

        Document filter = new Document("_id", new ObjectId(employeeID));
        Document update = new Document("$set", new Document("salary", Double.parseDouble(salary)));
        Document updatedDocument = collection.findOneAndUpdate(filter, update);

        if (updatedDocument != null) {
            return "Employee salary updated successfully.";
        } else {
            return "No employee found with ID: " + employeeID;
        }
    }

    @Override
    public String updateEmployeeDepartment(String employeeID, String department) throws RemoteException {
        MongoCollection<Document> collection = getCollection();

        if (employeeID == null || employeeID.isBlank() || department == null || department.isBlank()) {
            return "Invalid employee ID or salary provided.";
        }

        Document filter = new Document("_id", new ObjectId(employeeID));
        Document update = new Document("$set", new Document("department", department));
        Document updatedDocument = collection.findOneAndUpdate(filter, update);

        if (updatedDocument != null) {
            return "Employee department updated successfully.";
        } else {
            return "No employee found with ID: " + employeeID;

        }
    }

    @Override
    public String updateEmployeeEmail(String employeeID, String email) throws RemoteException {
        MongoCollection<Document> collection = getCollection();

        if (employeeID == null || employeeID.isBlank() || email == null || email.isBlank()) {
            return "Invalid employee ID or salary provided.";
        }

        Document filter = new Document("_id", new ObjectId(employeeID));
        Document update = new Document("$set", new Document("email", email));
        Document updatedDocument = collection.findOneAndUpdate(filter, update);

        if (updatedDocument != null) {
            return "Employee email updated successfully.";
        } else {
            return "No employee found with ID: " + employeeID;
        }
    }

    @Override
    public String deleteEmployee(String employeeID) throws RemoteException {
        MongoCollection<Document> collection = getCollection();
        Bson query = Filters.eq("_id", employeeID);
        DeleteResult deleteResult = collection.deleteOne(query);

        System.out.println(deleteResult);

        if (deleteResult.getDeletedCount() == 1) {
            return "Employee with ID " + employeeID + " was deleted.";
        } else {
            return "Employee with ID " + employeeID + " was not found or not deleted.";
        }
    }

    @Override
    public String deleteEmployees() throws RemoteException {
        MongoCollection<Document> collection = getCollection();
        DeleteResult deleteResult = collection.deleteMany(new Document());
        long deletedCount = deleteResult.getDeletedCount();

        return "All employees " + (deletedCount > 0 ? "were deleted successfully" : " were not deleted");
    }

    @Override
    public List<Document> getEmployees() throws RemoteException {
        List<Document> employeeList = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> documents = collection.find();
        documents.into(employeeList);

        return employeeList;
    }

    @Override
    public Document getEmployee(String employeeID) throws RemoteException {
        MongoCollection<Document> collection = getCollection();
        Document filter = new Document("_id", new ObjectId(employeeID));
        Document document =  collection.find(filter).first();
        if (document == null ){
            document = new Document("message", "Employee not found");
            return document;
        }
        return document;
    }
}
