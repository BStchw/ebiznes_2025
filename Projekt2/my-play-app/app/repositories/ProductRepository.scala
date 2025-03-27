package repositories

import models.{Product, Products}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

@Singleton
class ProductRepository @Inject()(
                                   protected val dbConfigProvider: DatabaseConfigProvider
                                 )(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val products = TableQuery[Products]

  def all(): Future[Seq[Product]] = db.run(products.result)

  def create(product: Product): Future[Int] =
    db.run(products += product)

  def findById(id: Int): Future[Option[Product]] =
    db.run(products.filter(_.id === id).result.headOption)

  def init(): Future[Unit] =
    db.run(products.schema.createIfNotExists)

  def update(id: Int, updated: Product): Future[Int] =
    db.run(products.filter(_.id === id).update(updated))

  def delete(id: Int): Future[Int] =
    db.run(products.filter(_.id === id).delete)

}
