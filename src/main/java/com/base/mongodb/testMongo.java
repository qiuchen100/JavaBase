package com.base.mongodb;

import com.mongodb.MongoClient;
import org.bson.types.BSONTimestamp;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class testMongo {
    public static void main(String[] args) {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.base");
        final Datastore datastore = morphia.createDatastore(new MongoClient("localhost"), "study");
        datastore.ensureIndexes();
        Query<User> query = datastore.createQuery(User.class);
        for (User user : query.asList()) {
            if (user.bizData.get("sendTime") instanceof Date) {
                Date date = (Date) user.bizData.get("sendTime");
                SimpleDateFormat myFmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(myFmt.format(date));
            }

        }
    }
}
