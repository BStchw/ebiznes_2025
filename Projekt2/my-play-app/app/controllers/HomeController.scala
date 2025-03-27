package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repositories.ProductRepository
import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                val controllerComponents: ControllerComponents,
                                productRepo: ProductRepository

                              )(implicit ec: ExecutionContext) extends BaseController {

  productRepo.init()

  def index = Action {
    Ok("Strona główna działa!")
  }
}
