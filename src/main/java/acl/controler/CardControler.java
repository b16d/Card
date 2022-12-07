package acl.controler;

import acl.domain.Card;
import acl.service.CardService;
import io.javalin.http.Context;
import io.javalin.openapi.*;

public class CardControler {

	static CardService service;

	public CardControler() {
		service = new CardService();
	}

	@OpenApi(
		    summary = "Get all cards",
		    operationId = "getAllCards",
		    path = "/api/v1/path/Card",
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
			path = "/api/v1/GetCardById",
			methods = HttpMethod.GET,
			tags = {"Card"},
			pathParams = {@OpenApiParam(name = "cardId", type = Integer.class, description = "The card ID")},
			responses = {
					@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
			}
	)
	public static void getCardById(Context ctx) {
		var card = service.getCardById(ctx.getClass());
		ctx.json();
	}

	private static int validPathParamCardId(Context ctx) {
		return ctx.pathParamAsClass("cardId", Integer.class).check(id -> id > 0, "ID must be greater than 0").get();
	}
	@OpenApi(
			summary = "Update Card",
			operationId = "updateCard",
			path = "/api/v1/UpdateCard",
			methods = HttpMethod.PUT,
			tags = {"Card"},
			pathParams = {@OpenApiParam(name = "Card", type = Card.class, description = "Card to update")},
			responses = {
					@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
			}
	)
	public static void updateCard(Context ctx) {

	}

	@OpenApi(
			summary = "Remove Card",
			operationId = "remove Card",
			path = "/api/v1/RemoveCard",
			methods = HttpMethod.DELETE,
			tags = {"Card"},
			pathParams = {@OpenApiParam(name = "Card to delete", type = Card.class, description = "Card to remove")},
			responses = {
					@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Card[].class)})
			}
	)
	public static void removeCard(Context ctx) {

	}
}
