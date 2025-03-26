import java.sql.DriverManager

fun main() {
    val conn = DriverManager.getConnection("jdbc:sqlite:test.db")
    val stmt = conn.createStatement()

    stmt.execute("""
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY,
            name TEXT
        )
    """.trimIndent())

    stmt.executeUpdate("INSERT INTO users(name) VALUES ('Alicja')")

    val rs = stmt.executeQuery("SELECT * FROM users")
    //while (rs.next()) {
    //    println("User: ${rs.getInt("id")} - ${rs.getString("name")}")
    //}

    System.out.println("Hello World!")

    conn.close()
}
