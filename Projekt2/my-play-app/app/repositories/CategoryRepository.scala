package repositories

import models.{Category, Categories}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

@Singleton
class CategoryRepository @Inject()(
                                    protected val dbConfigProvider: DatabaseConfigProvider
                                  )(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val categories = TableQuery[Categories]

  def all(): Future[Seq[Category]] = db.run(categories.result)

  def create(category: Category): Future[Int] =
    db.run(categories += category)

  def findById(id: Int): Future[Option[Category]] =
    db.run(categories.filter(_.id === id).result.headOption)

  def init(): Future[Unit] =
    db.run(categories.schema.createIfNotExists)

  def delete(id: Int): Future[Int] =
    db.run(categories.filter(_.id === id).delete)
}
