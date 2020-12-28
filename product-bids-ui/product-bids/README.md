## Item Bids UI
 

This is a Angular 11 & bootstrap 4 based project. User first need to create items then auctionItems & then Bids.
Bidder immediately will notify that he/she has meet bid or not.

##Bid Rules:

• If the reserve price has not been met, current bid should be set to the maximum of the current bid and the max auto-bid amount – an exception should be returned letting the bidder know they have not met the reserve.

• Once the reserve price has been met, max auto-bid amount becomes the max amount bidder is willing to pay but not necessarily the amount they must pay. As new bids are submitted for an item, bidder with the highest max auto-bid amount must only pay $1.00 more than the next highest bidder. It is important to remember the bid leaders max auto-bid amount in case future bids are submitted for the item. Any time a bidder has been outbid, an event/exception should be broadcast notifying them that they’ve been outbid. 

## Run server

Run Backend API: Goto backend api directory & run command : 

1:- mvn spring-boot:run

2:- Goto target directory under api directory & run commands:
	1: mvn clean install
	2: java -jar **.jar
	
Run Front-end UI:

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

Step 1: First create Items with description.
Step 2: After Item creation , create auctionItems with reserved amount.
Step 3: After AuctionItem creation , Bidder can create bid & can outbid any time.