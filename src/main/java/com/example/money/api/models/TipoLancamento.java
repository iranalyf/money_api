package com.example.money.api.models;

public enum TipoLancamento {

	RECEITA("Receita"), DESPESA("Despesa");

	private String descricao;

	TipoLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
