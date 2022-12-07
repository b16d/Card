package acl.main;

import acl.controler.CardControler;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class MainClass {

	public static void main(String[] args) {
		
		String deprecatedDocsPath = "/swagger-docs";



    	  Javalin.create(config -> {
              OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
              openApiConfiguration.getInfo().setTitle("Card Service");
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
        		  get(CardControler::getAllCards);


                  /*path("{cardId}", () -> {
                      get(CardControler::getCardById);
                      put(CardControler::updateCard);
                      delete(CardControler::removeCard);
                  });*/
              });
          }).exception(Exception.class, (e, ctx) -> {
              ctx.status(401).json("Server Exception: " + e);

          }).start(7002);

          System.out.println("Check out ReDoc docs at http://localhost:7002/redoc");
          System.out.println("Check out Swagger UI docs at http://localhost:7002/swagger");
      }

    
}