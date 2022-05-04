package bot

interface VkLongPollBot {

    /**
     * Starts the long polling
     *
     */
    suspend fun startLongPolling()

}