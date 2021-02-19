package resources;
// enum is special class in java which has collection of constants or methods
public enum APIResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	//declare global resource name that inside the class
	
	private String resource;
	
	//when we think that all the methods declaration is completed then put semcolon at the end of the method ";"
	
	
	//declare the constructor with the parameter string which accepts one parameter it is similarly like how we declared AddPlaceAPI accepts one string as parameter
	
	APIResources(String resource)
	{
		this.resource = resource;
	}
	
	// scope the above method "resource" is local to APIResources for that need to declare another method to return resource
	
	
	public String getResource() 
	{
		return resource;
	}
	

}
