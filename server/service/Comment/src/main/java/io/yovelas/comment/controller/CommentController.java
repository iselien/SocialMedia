package io.yovelas.comment.controller;

import com.mongodb.client.*;
import io.yovelas.entity.Comment;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mongodb.client.model.Filters.eq;


@RestController
public class CommentController {

    @GetMapping("/")
    public String comment(){

        MongoClient mongoClient = MongoClients.create();

        MongoDatabase mediacloud = mongoClient.getDatabase("mediacloud");
        MongoCollection<Document> comment = mediacloud.getCollection("comment");
        FindIterable<Document> documents = comment.find();
        MongoCursor<Document> iterator = comment.find(eq("_id",1.0)).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(documents);







        // Creating a collection
        mediacloud.createCollection("sampleCollection");
        System.out.println("Collection created successfully");

        // Retrieving a collection
        MongoCollection<Document> collection = mediacloud.getCollection("sampleCollection");
        System.out.println("Collection sampleCollection selected successfully");

        Document document = new Document("title", "MongoDB")
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.tutorialspoint.com/mongodb/")
                .append("by", "tutorials point");

        //Inserting document into the collection
        collection.insertOne(document);
        System.out.println("Document inserted successfully");


        return documents.toString();
    }
}
