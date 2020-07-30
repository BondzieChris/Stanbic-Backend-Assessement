## Stanbic Digital Backend Assessment Hire

### Database Schema
*Customers Table*
| Field | Type | Null | key | 
| ------| ------ | ----- | ----- |
| id | bigint(20) | NO | PRI  |
| name |varchar(255) | NO |   |
| email | varchar(255) | NO | UNI  |
| phone_number | varchar(255) | NO | UNI  |
| date_of_birth | date | NO |   |
| created_at | date | YES	 |   |


*Accounts Table*
| Field | Type | Null | key | 
| ------| ------ | ----- | ----- |			
|id |	bigint(20)  |	NO  |	PRI	|
|account_number| bigint(20) | YES   |	UNI	|
|created_at|	date    |	YES |		|
|current_balance|	double  |	NO	|	    |
|opening_amount|	double|	NO  	|	    |
|customer_id|	bigint(20)  |	YES|	MUL	|
				

### Link to API Collection on postman
[https://www.getpostman.com/collections/bafddaa7707fb270d817](https://www.getpostman.com/collections/bafddaa7707fb270d817)


- Twilio is used to notify a customer whenever actions like adding new account or updating customer's information is done. Because a Twilio free account was used, only verified numbers can receive notifications. Please contact **0540953694** to verify your phone number.


##Note
- Authentication can simply be removed by deleting the auth folder, and commenting out the **spring-boot-starter-security** dependency in the pom.xml.

- Springfox-swagger2 was also used to generate documentation for API. The port used was 3000; therefore the Swagger API doc can be access via
[http://localhost:3000/swagger-ui.html](http://localhost:3000/swagger-ui.html) on a local machine. The doc is only accessible if authentication is removed.
					

					
					
				
