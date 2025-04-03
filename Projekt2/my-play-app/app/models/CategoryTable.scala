package models

import slick.jdbc.SQLiteProfile.api._
import slick.lifted.{ProvenShape, Tag}

case class Category(id: Int, name: String)

class Categories(tag: Tag) extends Table[Category](tag, "categories") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * : ProvenShape[Category] = (id, name) <> (Category.tupled, Category.unapply)
}
