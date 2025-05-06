Note: You can use any IDE of your choice. It's not compulsory to write your code on hackerrank.
Make sure of:
1. Code Structure
2. Code correctness
3. Your code will be executed on multiple instances, so make it thread safe, if required.

Problem Statement:
1. Imagine a City on a 1 dimension plane where N cabs are available.
2. A passenger wishes to travel from point A to point B.
3. Implement an algorithm to assist the passenger to
   3.1 Book the nearest available cab
   3.2 End the ride

KeyNotes
1. The city, being in the 1 dimension plane, scatters over the range [1,2,3,.., K],
2. The search radius for the cab is R, where R>0
3. The number of Cabs within the City is N, where N>=1
4. Cost of Travel is Rs. 10/- per km.
5. RK < N.

Implement following methods
1. Initialize - Initialize the Plane and randomly assign Cabs over it.
2. Book the Cab - This method takes care of booking the Cab for a given passenger id. Implement the logic to book the nearest available cab within radius R.
   If booking was a success, print the Tripld.
   Print: Cab booked. Trip id : <trip_id>, cab id : <cab_id>.
3. End the Ride - implement the logic to end the ride
   Print: Ride ended for driver id <id>, pickup point < starting_point>, drop <drop_point>. Total fare for the ride <fare>

Add On(Try only if you have time left):
1. How would you build "Book Any" feature.