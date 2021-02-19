package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	//create the method addPlaceLoad()
	
	//paste all the test data into belo addplaceload method
	
	public AddPlace addPlaceLoad(String name, String lanuguage, String address) {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(lanuguage);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLng(33.427362);
		l.setLat(-38.383494);
		p.setLocation(l);
		
		return p;
		
		
	}
	
	public String deletePlacePayload(String place_id) 
	{
		return "{\r\n\"place_id\":\""+place_id+"\"\r\n}";

}
}
