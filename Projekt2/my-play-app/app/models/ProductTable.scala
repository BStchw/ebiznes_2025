package models
import slick.jdbc.SQLiteProfile.api._
import slick.lifted.{ProvenShape, Tag}

case class Product(id: Int, name: String, description: String, price: BigDecimal)

class Products(tag: Tag) extends Table[Product](tag, "products") {
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("name")
  def description: Rep[String] = column[String]("description")
  def price: Rep[BigDecimal] = column[BigDecimal]("price")

  def * : ProvenShape[Product] = (id, name, description, price) <> (Product.tupled, Product.unapply)
}