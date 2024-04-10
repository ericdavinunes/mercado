package br.com.ericnunes.mercado.controllers;

import br.com.ericnunes.mercado.entities.Produto;
import br.com.ericnunes.mercado.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    // GET /produtos => retornar todos os produtos
    @GetMapping
    public List<Produto> listarTodosOsProdutos() {
        return repository.findAll();
    }

    // GET /produtos/id => retornar o produto com o id especificado
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST /produtos => adicionar um novo produto
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto novo) {
        return repository.save(novo);
    }

    // DELETE /produtos/id => remover o produto com o id especificado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterarProdutoPorId(@PathVariable Long id, @RequestBody Produto novosDados) {
        try {
            // 1 - Pegar produto do banco de dados pelo id
            Produto produtoAntigo = repository.findById(id).get();
            // 2 - Alterar os dados
            produtoAntigo.setNome(novosDados.getNome());
            produtoAntigo.setValor(novosDados.getValor());
            produtoAntigo.setMarca(novosDados.getMarca());
            // 3 - Salvar
            return new ResponseEntity<>(repository.save(produtoAntigo), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
