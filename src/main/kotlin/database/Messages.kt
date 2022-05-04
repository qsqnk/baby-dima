package database

import org.jetbrains.exposed.sql.Table

private const val MAX_VK_ID_LENGTH = 20
private const val MAX_NAME_LENGTH = 50

object Messages : Table() {

    val id = varchar("id", MAX_VK_ID_LENGTH)

    val name = varchar("name", MAX_NAME_LENGTH)

    val message = text("message")

}