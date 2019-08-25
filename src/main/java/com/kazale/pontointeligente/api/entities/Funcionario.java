package com.kazale.pontointeligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kazale.pontointeligente.api.enums.PerfilEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -3960436649365666213L;

	public Funcionario() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@Column(name = "valor_hora", nullable = false)
	private BigDecimal valorHora;
	
	@Transient
	public Optional<BigDecimal> getValorHora(){
		return Optional.ofNullable(valorHora);
	}
	
	@Column(name = "qtde_horas_trabalho_dia", nullable = false)
	private Float qtdeHorasTrabalhoDia;
	
	@Transient
	public Optional<Float> getQtdeHorasTrabalhoDiaOpt(){
		return Optional.ofNullable(qtdeHorasTrabalhoDia);
	}
	
	@Column(name = "qtde_horas_almoco", nullable = false)
	private Float qtdeHorasAlmoco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private PerfilEnum perfil;
	
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha 
				+ ", cpf=" + cpf + ", valorHora=" + valorHora + ", qtdeHorasTrabalhoDia=" + qtdeHorasTrabalhoDia 
				+ ", qtdeHorasAlmoco=" + qtdeHorasAlmoco + ", perfil=" + perfil + ", dataCriacao=" + dataCriacao 
				+ ", dataAtualizacao=" + dataAtualizacao + ", empresa=" + empresa + "]";
	}
	
}
