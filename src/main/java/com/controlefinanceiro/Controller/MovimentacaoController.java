package com.controlefinanceiro.Controller;

import com.controlefinanceiro.Model.Movimentacao;
import com.controlefinanceiro.Repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;


    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity salvar(@RequestBody Movimentacao movimentacao){
        movimentacaoRepository.salvar(movimentacao);
        return ResponseEntity.status(200).body(movimentacao.getId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Movimentacao>> buscarTodas() {
        return ResponseEntity.status(200).body(movimentacaoRepository.buscarTodas());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Movimentacao> buscarTodas(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body(movimentacaoRepository.buscarPelo(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
        public ResponseEntity remover(@PathVariable("id") int id) {
            movimentacaoRepository.remover(id);
            return ResponseEntity.status(200).body("Removido com Sucesso!");
        }

}
