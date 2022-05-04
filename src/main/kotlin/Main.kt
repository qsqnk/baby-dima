import bot.ConfigurableVkLongPollBot
import database.Messages
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import repository.MessageRepositoryImpl


fun main(): Unit = runBlocking {
    Database.connect(
        driver = Configuration.Database.DRIVER,
        url = Configuration.Database.URL,
        user = Configuration.Database.USER,
        password = Configuration.Database.PASSWORD
    )
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Messages)
    }

    val repository = MessageRepositoryImpl()

    val longPollBot = ConfigurableVkLongPollBot(
        groupId = Configuration.Vk.GROUP_ID,
        accessToken = Configuration.Vk.ACCESS_TOKEN
    ) {
        onMessage {
            val msg = it.message

            repository.addMessage(
                msg.peerId.toString(),
                "idk how to obtain sender name using this api",
                msg.text
            )

            launch {
                sendMessage {
                    peerId = msg.peerId
                    message = repository
                        .getMessagesById(msg.peerId.toString())
                        .joinToString("\n")
                }.execute()
            }
        }
    }

    longPollBot.startLongPolling()
}