package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Product
import repositories.ProductRepository
import scala.concurrent.ExecutionContext
import play.api.libs.json._
import models.Product
import scala.concurrent.Future



@Singleton
class ProductController @Inject()(
                                   val controllerComponents: ControllerComponents,
                                   productRepo: ProductRepository
                                 )(implicit ec: ExecutionContext) extends BaseController {

  implicit val productWrites: Writes[Product] = Json.writes[Product]

  implicit val productFormat: Format[Product] = Json.format[Product]


  def index = Action.async {
    productRepo.all().map { products =>
      Ok(Json.toJson(products))
    }
  }

  def details(id: Int) = Action {
    Ok(s"Szczegóły produktu o ID: $id")
  }

  def create = Action(parse.json).async { implicit request =>
    request.body.validate[Product].fold(
      errors => Future.successful(BadRequest(Json.obj("error" -> "Invalid JSON"))),
      product => {
        productRepo.create(product).map(_ => Created("Produkt dodany"))
      }
    )
  }

  def update(id: Int) = Action(parse.json).async { implicit request =>
    request.body.validate[Product].fold(
      errors => Future.successful(BadRequest(Json.obj("error" -> "Invalid JSON"))),
      product => {
        productRepo.update(id, product).map {
          case 0 => NotFound("Nie znaleziono produktu")
          case _ => Ok("Zaktualizowano")
        }
      }
    )
  }

  def delete(id: Int) = Action.async {
    productRepo.delete(id).map {
      case 0 => NotFound("Nie znaleziono produktu")
      case _ => Ok("Usunięto")
    }
  }

}
