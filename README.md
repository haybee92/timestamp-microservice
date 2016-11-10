# timestamp-microservice

A Java implementation of the challenge found here https://www.freecodecamp.com/challenges/timestamp-microservice

User stories:
1) I can pass a string as a parameter, and it will check to see whether that string contains either a unix timestamp or a natural language date (example: January 1, 2016)
2) If it does, it returns both the Unix timestamp and the natural language form of that date.
3) If it does not contain a date or Unix timestamp, it returns the current Unix timestamp and natural language date

Example usage:

Request==>  https://timestamp-ms.herokuapp.com/December%2015,%202015
Response==> { "unix": 1450137600, "natural": "December 15, 2015" }

================================================================================

Request==>  https://timestamp-ms.herokuapp.com/1450137600
Response==> { "unix": 1450137600, "natural": "December 15, 2015" }

================================================================================

Request==>  https://timestamp-ms.herokuapp.com/invalid
Response==> {"errorCode":"02","errorMessage":"Parameter not in correct format"}
