package br.com.ericnunes.mercado.repositories;

import br.com.ericnunes.mercado.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
