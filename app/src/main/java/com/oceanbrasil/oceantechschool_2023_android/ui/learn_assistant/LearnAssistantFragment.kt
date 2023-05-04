package com.oceanbrasil.oceantechschool_2023_android.ui.learn_assistant

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oceanbrasil.oceantechschool_2023_android.databinding.FragmentLearnAssistantBinding

class LearnAssistantFragment : Fragment() {

    private var _binding: FragmentLearnAssistantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(LearnAssistantViewModel::class.java)

        _binding = FragmentLearnAssistantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Carrega os temas no spinner (dropdown)
        loadThemes()

        // Quando clicar no botão ask, realiza a pergunta no ViewModel
        prepareAsk(viewModel)

        // Qualquer mudança na variável answer do ViewModel, executa esse código
        prepareAnswer(viewModel)

        return root
    }

    private fun loadThemes() {
        val programmingTopics = listOf(
            "Lógica de programação",
            "Variáveis, tipos de dados e operadores",
            "Estruturas de controle de fluxo (if, else, while, for, switch)",
            "Funções e procedimentos",
            "Arrays e listas",
            "Programação orientada a objetos básica",
            "Introdução à linguagem de programação Java, Python ou C++",
            "Introdução ao desenvolvimento de aplicativos para dispositivos móveis (Android)",
            "Introdução ao desenvolvimento web (HTML, CSS e JavaScript)",
            "Banco de dados básico (conceitos e SQL básico)",
            "Introdução ao Git e controle de versão",
            "Carreira em programação e habilidades necessárias para começar"
        )

        val adapter =
            ArrayAdapter(requireActivity(), R.layout.simple_spinner_item, programmingTopics)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

    private fun prepareAsk(viewModel: LearnAssistantViewModel) {
        binding.ask.setOnClickListener {
            // Pega o tema selecionado no spinner (dropdown)
            val theme = binding.spinner.selectedItem?.toString() ?: ""

            // Pega a pergunta digitada no EditText
            val question = binding.question.text.toString()

            if (theme.isEmpty() || question.isEmpty()) {
                binding.question.error = "Preencha os campos"

                return@setOnClickListener
            }

            // Realiza a pergunta no ViewModel
            viewModel.ask(theme, question)
        }
    }

    private fun prepareAnswer(viewModel: LearnAssistantViewModel) {
        viewModel.answer.observe(viewLifecycleOwner) {
            // Atualiza o texto da resposta na tela
            binding.answer.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
