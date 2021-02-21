package com.java.test.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/17- 16:34
 */
public class MongoTest1 {

    private MongoClient mongoClient=null;
    private  MongoCollection<Document> stuCollection = null;

    @Before
    public void init(){
        //1、MongoClient(host，port)
        mongoClient = new MongoClient("127.0.0.1", 27017);
        //2、获取你要操作的库
        MongoDatabase db = mongoClient.getDatabase("k10");
        //3、找到需要添加数据的集合
        stuCollection = db.getCollection("students");
    }

    /**
     * 往MongoDB中添加多条数据
     */
    @Test
    public void insertMany(){
        Document doc1 = new Document();
        doc1.append("stuName","lisi");
        doc1.append("age",38);
        doc1.append("gender","女");

        Document doc2 = new Document();
        doc2.append("stuName","wangwu");
        doc2.append("age",88);
        doc2.append("gender","男");

        Document doc3 = new Document();
        doc3.append("stuName","wangermazi");
        doc3.append("age",28);
        doc3.append("gender","女");

        stuCollection.insertMany(Arrays.asList(doc1,doc2,doc3));
    }

    //查询所有与分页操作
    @Test
    public void get(){
        FindIterable<Document> docIt = stuCollection.find();
        docIt.skip(0);
        docIt.limit(10);
        MongoCursor<Document> iterator = docIt.iterator();
        iterator.forEachRemaining(temp-> System.out.println(temp));
    }

    //查询k10库中students表中age>=38
    @Test
    public void getByCondi1(){
        //db.students.find({"age":{$gte:38}})
        //条件第1种方式
        //Document doc1 = new Document("$gte",38);
        //Document doc2 = new Document("age",doc1);
        //条件第2中方式
        //Document doc2 = Document.parse("{\"age\":{$gte:38}}");
        //条件第3种方式
        Bson doc2 = Filters.gte("age",38);
        FindIterable<Document> docIterable = stuCollection.find(doc2);
        docIterable.iterator().forEachRemaining(temp-> System.out.println(temp));
    }
    //查询k10库中students表中18<age<=88
    @Test
    public void getByCondi2(){
        //Document doc1 = Document.parse("{$and:[{\"age\":{$gt:18}},{\"age\":{$lte:88}}]}");
        Bson bson1 = Filters.gt("age",18);
        Bson bson2 = Filters.lte("age",88);
        Bson bson = Filters.and(bson1,bson2);
        FindIterable<Document> docIterable = stuCollection.find(bson);
        docIterable.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    @Test
    public void remove(){
        Bson bson1 = Filters.eq("age",18);
        Bson bson2 = Filters.eq("gender","女");
        Bson bson = Filters.and(bson1,bson2);
        DeleteResult deleteResult = stuCollection.deleteMany(bson);
        System.out.println(deleteResult);
    }

    /**
     * 修改多条数据
     */
    @Test
    public void update(){
        Document bson1 = Document.parse("{\"gender\":\"男\"}");
        Document bson2 = Document.parse("{$set:{\"stuName\":\"admin\",\"age\":99}}");
        UpdateResult updateResult = stuCollection.updateMany(bson1, bson2);
        System.out.println(updateResult);
    }

    @Test
    public void testLamda(){
        //List、Set
        //Map
        List<String> aList = Arrays.asList("a", "b", "c");
        // aList.forEach(temp->System.out.println(temp));
        Map<String,Object> aMap = new HashMap<>();
        aMap.put("stuName","wangermazi");
        aMap.put("gender","男");
        aMap.forEach((k,v)-> System.out.println(k+"="+v));
    }


    @After
    public void close(){
        //5、关闭流
        mongoClient.close();
    }
}
