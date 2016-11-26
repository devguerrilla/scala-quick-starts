passwordValidator
=================
A quick-start example of a plain Dropwizard microservice written in only Scala. This example accepts an HTTP GET call to validate a password against current policy, returning "OK" if the password is valid or a description of the policy if it is invalid.

Run the service with:-

	sbt "run server etc/passwordValidator.yaml"
	
It can be tested with curl:-

	$ curl "http://localhost:8080/validatePassword?policy=simple&password=invalid"
	Password must be at least six characters with at least one upper-case and one lower-case character
	
	$ curl "http://localhost:8080/validatePassword?policy=simple&password=IAmValid"
	OK

A "fat jar" for deployment can be generated with:-

	$ sbt assembly

See [http://www.devguerrilla.com/notes/2016/11/scala-quick-start-developing-a-microservice-with-dropwizard](http://www.devguerrilla.com/notes/2016/11/scala-quick-start-developing-a-microservice-with-dropwizard) and [http://www.devguerrilla.com/notes/2016/11/ scala-quick-start-making-dropwizard-configuration-more-scala-friendly/](http://www.devguerrilla.com/notes/2016/11/ scala-quick-start-making-dropwizard-configuration-more-scala-friendly/‎) for more details.
