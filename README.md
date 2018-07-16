Your exercise is to implement a simple stream processing service that also exposes an HTTP interface.

You are provided with a blackbox executable that spits out an infinite stream of lines of event data encoded in JSON. You can download it here:

* Linux - https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-linux-amd64

* Mac OS X - https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-macosx-amd64

* Windows - https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-windows-amd64.exe

Please note that the stream might sometimes encounter errors and output corrupt JSON lines.

Service Requirements
It should consume the output of the generator and gather the following stats:
A count of events by event type.
A count of words encountered in the data field of the events. (e.g. “the” → 32, “me” → 5)
It should expose these stats in an HTTP interface


Important Notes:
We are looking for simple readable code which is not over-engineered
The architecture of your service should obviously decouple the data processing, HTTP handling, be testable, etc.
You can implement this exercise in either Java or Scala
The task should take no more than 2-3 hours.
In the submission's README please note 3 things you would improve in your submission.


To install you just need to do a maven install.
Commamd line to launch: java -jar StreamService-0.0.1-SNAPSHOT.jar bigpanda.StreamService -gen <generatorPath>

When the program is running, to get the count of event for an event type enter http://localhost:8080/eventType?type=<eventType> (for example http://localhost:8080/eventType?type=baz) and to get the count of a word enter http://localhost:8080/eventWord?word=<word> (for example http://localhost:8080/eventWord?word=lorem)
  
  Improvements:
  - Create interface class 
  - Divide in subproject Model, Http and Processing to have different jar
  - Add tests
  
