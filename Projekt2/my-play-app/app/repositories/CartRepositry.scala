package repositories

import models.{CartItem, CartItems}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

@Singleton
class CartRepository @Inject()(
                                protected val dbConfigProvider: DatabaseConfigProvider
                              )(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val cartItems = TableQuery[CartItems]

  def all(): Future[Seq[CartItem]] = db.run(cartItems.result)

  def add(item: CartItem): Future[Int] = db.run(cartItems += item)

  def remove(id: Int): Future[Int] =
    db.run(cartItems.filter(_.id === id).delete)

  def clear(): Future[Int] =
    db.run(cartItems.delete)

  def init(): Future[Unit] =
    db.run(cartItems.schema.createIfNotExists)
}
