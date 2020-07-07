## Digital Assessment Hire

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
				

### link to API Collection on postman
[link to postman collection for API](https://www.getpostman.com/collections/bafddaa7707fb270d817)


- Twilio was used to notify a customer whenever an actions like add new account or update customer info is done.


**###Note###**
- Authentication can simply be removed by deleting the auth folder, and commenting out the **spring-boot-starter-security** dependency in the pom.xml.

- Springfox-swagger2 was also used to generate documentation for API. The port used is 3000; therefore the Swagger API doc can be access via
[link](http://localhost:3000/swagger-ui.html) on a local machine. The doc is only accessible if authentication is removed.
					

					
					
				
