package com.example.money.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.money.api.models.Lancamento;
import com.example.money.api.models.Pessoa;
import com.example.money.api.repository.LancamentoRepository;
import com.example.money.api.repository.PessoaRepository;
import com.example.money.api.service.exception.PessoaInexistenOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = this.pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenOuInativaException();
		}
		return this.lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}
}
