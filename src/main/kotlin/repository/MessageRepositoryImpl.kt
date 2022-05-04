package repository

import database.Messages
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class MessageRepositoryImpl : MessageRepository {

    override fun addMessage(senderId: String, senderName: String, text: String): Unit = transaction {
            Messages
                .insert {
                    it[id] = senderId
                    it[name] = senderName
                    it[message] = text
                }
        }

    override fun getMessagesById(id: String): List<String> = transaction {
        Messages
            .select { Messages.id eq id }
            .map { it[Messages.message] }
    }

    override fun getMessagesByName(name: String): List<String> = transaction {
        Messages
            .select { Messages.name eq name }
            .map { it[Messages.message] }
    }
}