package com.kazale.pontointeligente.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1876899865987567L;

	
	
}
