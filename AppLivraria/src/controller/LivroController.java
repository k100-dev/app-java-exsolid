package controller;

import model.Livro;
import repository.LivroRepository;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final List<Livro> livros = new ArrayList<>();
    private int contador = 1;

    @PostMapping
    public Livro criar(@RequestBody Livro livro) {
        livro.setId(contador++);
        livros.add(livro);
        return livro;
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable int id) {
        return livros.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }
}