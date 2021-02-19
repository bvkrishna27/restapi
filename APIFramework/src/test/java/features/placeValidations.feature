Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name   |language|address  		    |
	|AAhouse|English |World cross center|
#	|BBhouse|Spanish |Blue cross center |

# this keyword is used to comment the script in the cucumber in the feature file
@DeletePlace
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"