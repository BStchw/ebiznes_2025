package models
import slick.jdbc.SQLiteProfile.api._
import slick.lifted.{ProvenShape, Tag}

case class Product(id: Int, name: String, description: String, price: BigDecimal, categoryId: Int)

class Products(tag: Tag) extends Table[Product](tag, "products") {
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("name")
  def description: Rep[String] = column[String]("description")
  def price: Rep[BigDecimal] = column[BigDecimal]("price")
  def categoryId = column[Int]("category_id")

  def categoryFK = foreignKey("category_fk", categoryId, TableQuery[Categories])(_.id)

  def * = (id, name, description, price, categoryId) <> (Product.tupled, Product.unapply)
}