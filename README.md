# sales-notifications
Readme:

This repo contains a sample message notifications processing app which process message.

Technologies used:

java
Maven
junit

Work flow and Assumtions:

All data to be in memory.
IO via console/ByteOutputStream
Sale value can be negative.
Maximum Capacity and interval report generation is by default is 50 and 10 respectively, if not passed by user.
-- For Sale calculation used long instead of BigDecimal, as all calculations are done on pence.
-- Final Adjustment report displays the different adjustment operation performed on each product. 
example : If 2 ADD adjustment is done on apple one for 20p and other for 10p, 
then adjustment report will display :  apple|20|Add|10|Add

Message examples

apple at 11p
10 sales of oranges at 40p each
Multiply 2p apples


