package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import models.CartItem
import repositories.CartRepository

@Singleton
class CartController @Inject()(
                                val controllerComponents: ControllerComponents,
                                cartRepo: CartRepository
                              )(implicit ec: ExecutionContext) extends BaseController {

  implicit val cartItemFormat: OFormat[CartItem] = Json.format[CartItem]

  // GET /cart
  def getCart: Action[AnyContent] = Action.async {
    cartRepo.all().map { items =>
      Ok(Json.toJson(items))
    }
  }

  // POST /cart
  def addToCart: Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[CartItem].fold(
      errors => Future.successful(BadRequest(Json.obj("error" -> "Invalid JSON"))),
      item => cartRepo.add(item).map(_ => Created(Json.obj("status" -> "Added to cart")))
    )
  }

  // DELETE /cart/:id
  def removeFromCart(id: Int): Action[AnyContent] = Action.async {
    cartRepo.remove(id).map { deleted =>
      if (deleted > 0) Ok(Json.obj("status" -> "Removed"))
      else NotFound(Json.obj("error" -> "Not found"))
    }
  }

  // DELETE /cart
  def clearCart: Action[AnyContent] = Action.async {
    cartRepo.clear().map(_ => Ok(Json.obj("status" -> "Cart cleared")))
  }
}
