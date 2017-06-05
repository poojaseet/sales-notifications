Readme:
# sales-notifications


A sample message notifications processing app which process messages and generates report.

Technologies used:

java
Maven
junit

Project Execution
Import The project into any IDE, and run the ApplictaionManager file(which contains, main class). 
NotificationManagerTest runs the testCase's for processing message.(Junit) 


Work flow and Assumtions:

All data to be in memory.

Long is used instead of BigDecimal as no decimal point calculation is involved And all calculations are done on pence.

IO operations done via console from Main/ByteOutputStream to TDD. NotifcationManager receives message from any source and process.
Invalid message is also considered as processed and affects processed message counts.

Sale value can be negative.

Maximum Capacity and interval report generation is by default is 50 and 10 respectively, if not passed by user.

Final Adjustment report displays the different adjustment operation performed on each product. 

example : If 2 ADD adjustment is done on apple one for 20p and other for 10p, 
then adjustment report will display :  apple|20|Add|10|Add

Message examples

apple at 11p
10 sales of oranges at 40p each
Multiply 2p apples



