import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

import com.fasterxml.jackson.annotation.JsonProperty

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.setup.Environment

class PasswordValidatorConfiguration extends Configuration {
  @JsonProperty var rule : String = _
  @JsonProperty var help : String = _
}

@Path("/validatePassword")
class PasswordValidatorResource(rule: String, help: String) {
  @GET def validate(@QueryParam("password") password: String) : String = {
    if(password.matches(rule)) 
      "OK" 
    else 
      help
  }
}

class PasswordValidatorApplication extends Application[PasswordValidatorConfiguration] {
  def run(configuration: PasswordValidatorConfiguration, environment: Environment) : Unit = {
    environment.jersey().register(new PasswordValidatorResource(configuration.rule, configuration.help))
  }
}

object PasswordValidatorApplication {
  def main(args: Array[String]) {
    new PasswordValidatorApplication().run(args:_*)
  }
} 