import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

import com.fasterxml.jackson.module.scala.DefaultScalaModule

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment


class PasswordPolicy {
  var rule : String = _
  var help : String = _
}

class PasswordValidatorConfiguration extends Configuration {
  var policies : Map[String, PasswordPolicy] = _   
}

@Path("/validatePassword")
class PasswordValidatorResource(policies : Map[String,PasswordPolicy]) {
  @GET def validate(@QueryParam("policy") policy: String, 
                    @QueryParam("password") password: String) : String = {
    if(password.matches(policies(policy).rule)) 
      "OK" 
    else 
      policies(policy).help
  }
}

class PasswordValidatorApplication extends Application[PasswordValidatorConfiguration] {
  def run(configuration: PasswordValidatorConfiguration, environment: Environment) : Unit = {
    environment.jersey().register(new PasswordValidatorResource(configuration.policies))
  }
  
  override def initialize(bootstrap: Bootstrap[PasswordValidatorConfiguration]) : Unit = {
    bootstrap.getObjectMapper().registerModule(new DefaultScalaModule)
  }  
}

object PasswordValidatorApplication {
  def main(args: Array[String]) {
    new PasswordValidatorApplication().run(args:_*)
  }
} 