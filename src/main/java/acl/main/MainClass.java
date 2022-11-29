package acl.main;

import acl.controler.CardControleur;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import io.javalin.apibuilder.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class MainClass {

	public static void main(String[] args) {
		
		String deprecatedDocsPath = "/swagger-docs";


		
    	  Javalin.create(config -> {
              OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
              openApiConfiguration.getInfo().setTitle("Javalin Cards example");
              openApiConfiguration.setDocumentationPath(deprecatedDocsPath);
             
      		SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
    		swaggerConfiguration.setUiPath("/swagger"); 
    		swaggerConfiguration.setDocumentationPath(deprecatedDocsPath);
    		//config.plugins.register(new SwaggerPlugin(swaggerConfiguration));
    		
    		
              config.plugins.register(new OpenApiPlugin(openApiConfiguration));
              config.plugins.register(new SwaggerPlugin(swaggerConfiguration));
           //   config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));
          }).routes(() -> {
        	  path("Card All", () -> {
        		  get(CardControleur::getAllCards);


                  path("{cardId}", () -> {
                      get(CardControleur::getCardById);
                      put(CardControleur::updateCard);
                      delete(CardControleur::removeCard);
                  });
              });
          }).start(7002);

          System.out.println("Check out ReDoc docs at http://localhost:7002/redoc");
          System.out.println("Check out Swagger UI docs at http://localhost:7002/swagger");
      }

    
}