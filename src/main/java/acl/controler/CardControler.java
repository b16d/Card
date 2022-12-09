package acl.controler;

import acl.domain.Card;
import acl.service.CardService;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.openapi.*;

public class CardControler {

	static CardService service = new CardService();

	public CardControler() {
		//service =  new CardService();
	}

	@OpenApi(
		    summary = "Get all cards",
		    operationId = "getAllCards",
		    path = "/api/v1/card",
		    methods = HttpMethod.GET,
		    tags = {"Card"},
		    responses = {
		        @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
		    }
		)
	public static void getAllCards(Context ctx) {
		ctx.json(service.getAllCards());
	}

	@OpenApi(
			summary = "Get one card",
			operationId = "getCardById",
			path = "/api/v1/getCardById",
			methods = HttpMethod.GET,
			tags = {"Card"},
			queryParams = {@OpenApiParam(name = "cardId", type = Integer.class, description = "The card ID")},
			responses = {
					@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
			}
	)
	public static void getCardById(Context ctx) {
		Card card = service.getCardById(validPathParamCardId(ctx));
		if (card == null) {
			throw new NotFoundResponse("Card not found");
		} else {
			ctx.json(card);
		}
	}

	private static int validPathParamCardId(Context ctx) {
		return Integer.parseInt(ctx.queryParam("cardId")); //Catch by exception mapping ;)
	}
	@OpenApi(
			summary = "Update Card",
			operationId = "updateCard",
			path = "/api/v1/updateCard",
			methods = HttpMethod.PUT,
			tags = {"Card"},
			requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Card.class)}),
			responses = {
					@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
			}
	)
	public static void updateCard(Context ctx) {
		Card card = ctx.bodyAsClass(Card.class);
		service.updateCard(card);
	}

	@OpenApi(
			summary = "Remove Card",
			operationId = "remove Card",
			path = "/api/v1/removeCard",
			methods = HttpMethod.DELETE,
			tags = {"Card"},
			queryParams = {@OpenApiParam(name = "cardId", type = Integer.class, description = "The card ID")},
			responses = {
					@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
			}
	)
	public static void removeCard(Context ctx) {
		int cardId = validPathParamCardId(ctx);
		service.removeCard(cardId);
	}
}
