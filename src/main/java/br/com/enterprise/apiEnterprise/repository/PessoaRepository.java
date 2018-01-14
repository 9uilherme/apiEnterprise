package br.com.enterprise.apiEnterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.enterprise.apiEnterprise.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
