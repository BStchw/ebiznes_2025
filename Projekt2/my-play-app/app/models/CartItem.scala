package models

import slick.jdbc.SQLiteProfile.api._
import slick.lifted.{ProvenShape, Tag}

case class CartItem(id: Int, productId: Int, quantity: Int)

class CartItems(tag: Tag) extends Table[CartItem](tag, "cart_items") {
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def productId: Rep[Int] = column[Int]("product_id")
  def quantity: Rep[Int] = column[Int]("quantity")

  def productFK = foreignKey("fk_product", productId, TableQuery[Products])(_.id)

  def * : ProvenShape[CartItem] = (id, productId, quantity) <> (CartItem.tupled, CartItem.unapply)
}
