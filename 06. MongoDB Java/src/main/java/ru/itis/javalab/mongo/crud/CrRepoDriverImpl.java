package ru.itis.javalab.mongo.crud;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.mongo.models.A;

import java.util.Optional;

@Component
public class CrRepoDriverImpl implements CrRepo<A, String> {

    private final MongoCollection<Document> collection;

    public CrRepoDriverImpl(@Value("${spring.data.mongodb.database}") String db) {
        collection = MongoClients.create()
                .getDatabase(db)
                .getCollection("a");
    }

    @Override
    public Optional<A> read(String id) {
        var query = new Document("_id", new ObjectId(id));
        var a = collection.find(query).first();

        return Optional.ofNullable(a)
                .map(row -> new A(row.getString("id"), row.getDouble("value")));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String create(A a) {
        return collection.insertOne(new Document("value", a.getValue()))
                .getInsertedId()
                .asObjectId()
                .getValue()
                .toString();
    }
}
