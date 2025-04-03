package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._
import models.Category
import repositories.CategoryRepository

@Singleton
class CategoryController @Inject()(
                                    val controllerComponents: ControllerComponents,
                                    categoryRepository: CategoryRepository
                                  )(implicit ec: ExecutionContext) extends BaseController {

  // JSON format
  implicit val categoryFormat: OFormat[Category] = Json.format[Category]

  // GET /categories
  def getAllCategories: Action[AnyContent] = Action.async {
    categoryRepository.all().map { categories =>
      Ok(Json.toJson(categories))
    }
  }

  // POST /categories
  def createCategory: Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Category].fold(
      errors => Future.successful(BadRequest(Json.obj("error" -> "Invalid JSON"))),
      category => categoryRepository.create(category).map { _ =>
        Created(Json.obj("status" -> "Category created"))
      }
    )
  }

  // DELETE /categories/:id
  def deleteCategory(id: Int): Action[AnyContent] = Action.async {
    categoryRepository.delete(id).map { rowsAffected =>
      if (rowsAffected > 0) Ok(Json.obj("status" -> "Deleted"))
      else NotFound(Json.obj("error" -> "Category not found"))
    }
  }
}
