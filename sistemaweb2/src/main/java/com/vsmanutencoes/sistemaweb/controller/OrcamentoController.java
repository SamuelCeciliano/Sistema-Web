package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Orcamento;
import com.vsmanutencoes.sistemaweb.models.Servico;
import com.vsmanutencoes.sistemaweb.models.SolicitacaoOrcamento;
import com.vsmanutencoes.sistemaweb.models.Equipamento;
import com.vsmanutencoes.sistemaweb.models.ServicoOrcamento;
import com.vsmanutencoes.sistemaweb.service.OrcamentoService;
import com.vsmanutencoes.sistemaweb.service.SolicitacaoOrcamentoService;
import com.vsmanutencoes.sistemaweb.models.StatusSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private SolicitacaoOrcamentoService solicitacaoOrcamentoService;

    // Método para exibir a listagem de orçamentos
    @GetMapping
    public String listarOrcamentos(Model model) {
        List<Orcamento> orcamentos = orcamentoService.listarOrcamentos();
        model.addAttribute("orcamentos", orcamentos);
        return "orcamentos"; // Página de listagem de orçamentos
    }

    // Página para criar um novo orçamento
    @GetMapping("/new")
    public String criarOrcamentoForm(Model model) {
        model.addAttribute("orcamento", new Orcamento());
        return "orcamento-form"; // Página de formulário para criar um novo orçamento
    }

    // Salvar o orçamento
    @PostMapping("/save")
    public String salvarOrcamento(@RequestParam("solicitacaoId") Long solicitacaoId, @ModelAttribute Orcamento orcamento) {
        // Pega a solicitação de orçamento pelo ID
        SolicitacaoOrcamento solicitacaoOrcamento = solicitacaoOrcamentoService.buscarSolicitacaoPorId(solicitacaoId);

        // Aqui, podemos configurar o orçamento com as informações da solicitação
        orcamento.setCliente(solicitacaoOrcamento.getCliente());
        orcamento.setEquipamentos(solicitacaoOrcamento.getEquipamentos());

        // Converte os serviços da solicitação de orçamento para o orçamento
        orcamento.setServicos(converterServicos(solicitacaoOrcamento.getEquipamentos()));

        // O status do orçamento pode ser setado como 'PENDENTE' ou conforme sua necessidade
        orcamento.setStatus(StatusSolicitacao.PENDENTE);
        
        // Configura a data de criação (ou o valor que for necessário)
        orcamento.setDataCriacao(LocalDate.now());

        // Calcular o valor total do orçamento (essa lógica pode ser ajustada conforme necessidade)
        BigDecimal valorTotal = calcularValorTotal(orcamento.getServicos());
        orcamento.setValorTotal(valorTotal);

        // Salva o orçamento no banco de dados
        orcamentoService.salvarOrcamento(orcamento);

        return "redirect:/orcamentos"; // Redireciona para a página de listagem de orçamentos
    }

    // Método auxiliar para calcular o valor total do orçamento
    private BigDecimal calcularValorTotal(List<ServicoOrcamento> servicos) {
        BigDecimal total = BigDecimal.ZERO;
        for (ServicoOrcamento servico : servicos) {
            BigDecimal preco = servico.getPrecoUnitario().multiply(BigDecimal.valueOf(servico.getQuantidadeHoras()));
            total = total.add(preco);
        }
        return total;
    }

    // Método auxiliar para converter os serviços da solicitação
    private List<ServicoOrcamento> converterServicos(List<Equipamento> equipamentos) {
        // Converte os equipamentos em serviços para o orçamento
        // Aqui você pode implementar a lógica de como mapear os equipamentos para os serviços
        List<ServicoOrcamento> servicos = new ArrayList<>();
        for (Equipamento equipamento : equipamentos) {
            for (Servico servico : equipamento.getServicos()) {
                ServicoOrcamento servicoOrcamento = new ServicoOrcamento();
                servicoOrcamento.setServico(servico);
                servicos.add(servicoOrcamento);
            }
        }
        return servicos;
    }

    // Excluir orçamento
    @PostMapping("/delete/{id}")
    public String excluirOrcamento(@PathVariable("id") Long id) {
        orcamentoService.excluirOrcamento(id);
        return "redirect:/orcamentos";
    }

    // Detalhes do orçamento
    @GetMapping("/{id}")
    public String exibirDetalhesOrcamento(@PathVariable("id") Long id, Model model) {
        Orcamento orcamento = orcamentoService.buscarOrcamento(id);
        model.addAttribute("orcamento", orcamento);
        return "orcamento/detalhes"; // Página de detalhes do orçamento
    }
}
