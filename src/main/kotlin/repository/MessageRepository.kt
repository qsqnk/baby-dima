package repository

interface MessageRepository {

    fun addMessage(senderId: String, senderName: String, text: String)

    fun getMessagesById(id: String): List<String>

    fun getMessagesByName(name: String): List<String>

}