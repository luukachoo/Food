package com.example.food.presentation.screen.chatbot

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.food.R
import com.example.food.databinding.FragmentChatBotBinding
import com.example.food.presentation.common.base.BaseFragment
import com.example.food.presentation.common.helper.Listener
import com.example.food.presentation.common.helper.Observer
import com.example.food.presentation.event.chatbot.ChatBotFragmentEvent
import com.example.food.presentation.model.Message
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatBotFragment : BaseFragment<FragmentChatBotBinding>(FragmentChatBotBinding::inflate),
    Listener, Observer {

    private val chatRecyclerAdapter by lazy { ChatbotFragmentRecyclerAdapter() }
    private val viewModel: ChatBotFragmentViewModel by viewModels()
    override fun init() {
        listeners()
        observers()
        setUpRecycler()
    }

    override fun listeners(): Unit = with(binding) {
        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        buttonSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                viewModel.onEvent(ChatBotFragmentEvent.SendMessage(message))
                etMessage.text!!.clear()
            }
        }

        etMessage.doOnTextChanged { text, _, _, _ ->
            buttonSend.isEnabled = !text.isNullOrEmpty()
            val colorStateList = if (text.isNullOrEmpty()) {
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.grey))
            } else {
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.orange))
            }
            buttonSend.backgroundTintList = colorStateList
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.chatbotAnswer.collect { answer ->
                    addMessageToRecyclerView(
                        Message(
                            id = chatRecyclerAdapter.currentList.size + 1,
                            text = answer.answerText ?: "No information :((",
                            type = Message.MessageType.BOT
                        )
                    )
                }
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.chatbotSendMessage.collect { userMessage ->
                    addMessageToRecyclerView(
                        Message(
                            id = chatRecyclerAdapter.currentList.size + 1,
                            text = userMessage.text,
                            type = Message.MessageType.USER
                        )
                    )
                }
            }
        }
    }

    private fun addMessageToRecyclerView(message: Message) {
        val currentMessages = mutableListOf<Message>()
        currentMessages.apply {
            addAll(chatRecyclerAdapter.currentList)
            add(message)
        }
        chatRecyclerAdapter.submitList(currentMessages)
    }


    private fun setUpRecycler() = with(binding) {
        rvChat.adapter = chatRecyclerAdapter
    }
}