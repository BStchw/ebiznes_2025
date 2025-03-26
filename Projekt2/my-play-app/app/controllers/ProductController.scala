package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action {
    Ok("Lista produktów")
  }

  def details(id: Int) = Action {
    Ok(s"Szczegóły produktu o ID: $id")
  }
}
