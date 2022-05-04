package bot

import com.petersamokhin.vksdk.core.client.VkApiClient
import com.petersamokhin.vksdk.core.model.VkSettings
import com.petersamokhin.vksdk.http.VkOkHttpClient

class ConfigurableVkLongPollBot(
    groupId: Int,
    accessToken: String,
    config: VkApiClient.() -> Unit
) : VkLongPollBot {

    private val client = VkApiClient(
        groupId,
        accessToken,
        VkApiClient.Type.Community,
        VkSettings(VkOkHttpClient())
    ).apply(config)

    override suspend fun startLongPolling() {
        client.startLongPolling()
    }
}