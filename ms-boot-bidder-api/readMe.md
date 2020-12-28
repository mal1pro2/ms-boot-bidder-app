## Item Bids APP
 

This is a spring boot rest web service & expose the multiple endpoints for creating items, auction & bids as well.
Also work with json request & response from APIs.



##Bid Rules:

• If the reserve price has not been met, current bid should be set to the maximum of the current bid and the max auto-bid amount – an exception should be returned letting the bidder know they have not met the reserve.

• Once the reserve price has been met, max auto-bid amount becomes the max amount bidder is willing to pay but not necessarily the amount they must pay. As new bids are submitted for an item, bidder with the highest max auto-bid amount must only pay $1.00 more than the next highest bidder. It is important to remember the bid leaders max auto-bid amount in case future bids are submitted for the item. Any time a bidder has been outbid, an event/exception should be broadcast notifying them that they’ve been outbid. 


## Instructions

1. If code is available on GIT repo then clone project code using below GIT command:

	`git clone <prject git repo URL>`

2. Go to project directory & install dependencies & build project using maven command: 

	`mvn clean install`
	
3. Run project unit tests usingmaven command: 
	
	 `mvn test`
	 
4. Go to the target directory under application directory & run application server via maven: 

	`mvn spring-boot:run` or  `java -jar ***.jar`
	
5. First step to create Items with API : 

	URL: `http://localhost:8080/items`
	
	`POST /items HTTP/1.1 Host: localhost:8080 Content-Type: application/json Content-Length: 41`
				
	{
	    "description":"kty description"
	} 
	
6. Second step to create AuctionItems with created Item details :

	URL: `http://localhost:8080/auctionItems`
	
	`POST /auctionItems HTTP/1.1 Host: localhost:8080 Content-Type: application/json Content-Length: 127`

	{
	    "reservePrice": 16600.00,
	    "item":  { 
	        "itemId" : 1,
	        "description": "ABCXYZ description"
	    }
	}
		
7. Third step to create Bid with created AuctionItem id:

	URL: `http://localhost:8080/bids`
	
	`POST /bids HTTP/1.1 Host: localhost:8080 Content-Type: application/json Content-Length: 99`
	
	{
	    "auctionItemId":1,
	    "maxAutoBidAmount":"11000.00",
	    "bidderName":"por Dealership"
	}
	
8. To run application with different environment, need to set property 

	`spring.profiles.active=dev` into `application.properties` under `**\src\main\resources\`.
	
9. Swagger UI URL [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

10. Build jar is runnable jar so we can easily deploy on any cloud server as well.

## DEV Tools

The Application developed with dev components: 

1. Spring Boot 2 
2. Java 8 
3. Maven,
4. Swagger.,
5. H2 DB

## API Endpoints 

GET : http://localhost:8080/items

GET : http://localhost:8080/items/{itemId}

POST : http://localhost:8080/items

GET : http://localhost:8080/auctionItems

GET : http://localhost:8080/auctionItems/{auctionItemID}

POST : http://localhost:8080/auctionItems

GET : http://localhost:8080/bids

POST : http://localhost:8080/bids

POST : http://localhost:8080/bids/outbid?id=<bidId>









   