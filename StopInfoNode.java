

public class StopInfoNode {
		public int number;
		public String code;
		public String name;
		public String description;
		public double latitude;
		public double longitude;
		public String zone;
		public String url;
		public String locationType;
		public String parentStation;
		public StopInfoNode next;
		
		StopInfoNode()
    	{
    		
    	}
		
		StopInfoNode(int number, String code, String name, String description, double latitude, 
	        		double longitude, String zone, String url, String locationType, String parentStation)
		{
			this.number = number;
			this.code = code;
			this.name = name;
			this.description = description;
			this.latitude = latitude;
			this.longitude = longitude;
			this.zone = zone;
			this.url = url;
			this.locationType = locationType;
			this.parentStation = parentStation;
		}
    	
    	@Override
    	public String toString()
    	{
    		return "Name: " + name + ".\n" + 
					"Number: " + number + ".\n" + 
					"Code: " + code + ".\n" + 
					"Description: " + description + ".\n" + 
					"Latititude: " + latitude + ".\n" + 
					"Longitude: " + longitude + ".\n" + 
					"Zone: " + zone + ".\n" + 
					"URL: " + url + ".\n" +
					"Location Type: " + locationType + ".\n" +
					"Parent Station: " + parentStation + ".\n";
    	}
	}