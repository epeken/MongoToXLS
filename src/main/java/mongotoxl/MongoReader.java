package mongotoxl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoReader {
	private String uri; //mongodb://user:pass@host:port/db.collection
	private MongoClient mongoClient;
	
	private DB db;
	private DBCollection collection;
	private Set keySet;
	private ArrayList<ArrayList> list = new ArrayList<ArrayList>();
	
	public ArrayList getList(){
		return this.list;
	}
	
	public Set getKeySet() {
		return keySet;
	}

	public MongoReader(String uri){
		this.uri = uri;
	}
	
	public void connect(){
		try{
			MongoClientURI mongoClientURI = new MongoClientURI(uri);
			this.mongoClient = new MongoClient(mongoClientURI);
			this.db = mongoClient.getDB(mongoClientURI.getDatabase());
			this.collection = db.getCollection(mongoClientURI.getCollection());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void query(){
		DBCursor docs = this.collection.find();
		DBObject doc = null;
		if(docs.hasNext()){
			doc = docs.next();
			this.keySet = doc.keySet();					
		}else{
			System.out.println("No records found.");
		    return;
		}
		
		ArrayList<String> tmpList = new ArrayList<String>();
		for(int i=0;i<keySet.size();i++){
			tmpList.add(doc.get((keySet.toArray()[i]).toString()).toString());
		}
		this.list.add(tmpList);
		
		
		while(docs.hasNext()){
			doc = docs.next();
			tmpList = new ArrayList<String>();
			for(int i=0;i<keySet.size();i++){
				tmpList.add(doc.get((keySet.toArray()[i]).toString()).toString());
			}
			this.list.add(tmpList);
		}
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

}
