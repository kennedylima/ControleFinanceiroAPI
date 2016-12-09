package com.controlefinanceiro.Controller;

import com.controlefinanceiro.Model.Categoria;
import com.controlefinanceiro.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Categoria>> buscarTodas() {
        return ResponseEntity.status(200).body(categoriaRepository.buscarTodas());
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity salvar(@RequestBody Categoria categoria){
        categoriaRepository.salvar(categoria);
        return ResponseEntity.status(200).body(categoria.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    private Categoria buscarPelo(@PathVariable("id") int id){
        return categoriaRepository.buscarPelo(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    private ResponseEntity remover(@PathVariable("id") int id){
        categoriaRepository.remover(id);
        return  ResponseEntity.status(200).body("Removido Com Sucesso!");
    }
}
