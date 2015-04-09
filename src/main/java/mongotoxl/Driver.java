package mongotoxl;

public class Driver {
	public static void main(String args[]){
		if(args.length < 2){
			System.out.println("Usage: java -jar MongoToXL-0.0.1-SNAPSHOT.jar mongodb://user:pass@host:port/db.collection [output].xls");
		}
		String mongouri = args[0];
		MongoReader reader = new MongoReader(mongouri);
		reader.connect();
		if (reader.getMongoClient() == null){
			System.out.println("Unable to connect to monggo db uri " + mongouri);
			return;
		}
		reader.query();		
		XlsWriter writer = new XlsWriter(args[1]);
		writer.setReader(reader);
		try{
			writer.writeData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
