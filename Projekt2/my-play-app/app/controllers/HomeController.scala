package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repositories.ProductRepository
import repositories.CategoryRepository
import repositories.CartRepository
import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                val controllerComponents: ControllerComponents,
                                productRepo: ProductRepository,
                                categoryRepo: CategoryRepository,
                                cartRepo: CartRepository

                              )(implicit ec: ExecutionContext) extends BaseController {

  productRepo.init()
  categoryRepo.init()
  cartRepo.init()

  def index = Action {
    Ok("Strona główna działa!")
  }
}
