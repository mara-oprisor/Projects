# Dream Travel
## Description
For this project, I have created a Desktop Application which allows the user to book a room, leave a review and see all reviews. I implemented it using Java Swing and it is connected to a PostgreSQL database using a JDBC connection. The project uses an MVC pattern, the parts of the project being clearly divided.

## How to use the application
There are 2 types of users who can use the application: clients and admins. Each of them has a different menu and can perform different actions through this app. Initially all people are users, but using inheritance, they are divided into clients and admins, by choosing the type of user when logging in.
The page which opens when the user opens the application, lets him choose whether he wants to sign up or login. Only clients can sign up, the account of the admins must be created from the database, because the admin’s account is associated with a hotel. When the client signs up, if the username chosen was already used before, an error will occur and user has to choose another username or go to the login page. Also, the password must be at least 5 characters long. When logging in, if the credentials don’t match, an error message will appear on the screen.
### Client
The client menu includes 5 buttons, each of them allows him to perform a different action. 
1.	The user can view his details (read them from the database), can delete them, can insert informations if there are none in the database or he can update the already existing ones. Age must be an integer, otherwise an exception is thrown. If there is no information about the user in the database, when clicking on the “View Details button” all fields will remain empty except for the age one which will contain a 0.
2.	The user can view all reviews left by other users. These reviews are extracted from the database. He can filter them by the name of the hotel and sort them by rating. Moreover, he can add a review. He has to select the hotel, write a short description and select the rating given to it.
3.	The user can book a room at one of the five hotels. First, he is presented all 5 hotels and can click on the button “View Photos” to see more photos from each hotel. If he clicks on the “Book a room” button, he will book a room at the hotel near which was the button. When booking, the client will choose the type of room, enter the checking date and checkout date. These dates must have the structure “dd.MM.yyyy”, otherwise will generate an error message. If the check-in date is after the check-out date, the price calculated will be negative. If there are no more rooms available of the type chosen, an error message will appear on the screen and the user should choose a different hotel or a different type of room.
4.	The user can see all his reviews and for which hotel was left.
5.	The user can see al his bookings, the hotel chosen, the room type, the check-in date, the check-out date and the price of the room which he booked (is calculated as the price of the room from the database multiplied by the number of nights).

### Admin
The admin menu contains 3 buttons, each of them allows him to perform a different action.
1.	The first button takes the user to the details form. This one works the same as for the client.
2.	The admin can see all the bookings that were made to his hotel (that is the reason why each admin has associated a hotel).
3.	The admin can see all the reviews that were made to his hotel.
To exit the application the user must click the exit button “X”. 
