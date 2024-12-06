package com.oprojetodev.thymeleaf.controller.produto;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oprojetodev.thymeleaf.model.Produto;
import com.oprojetodev.thymeleaf.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;

	public ProdutoController(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView model = new ModelAndView("/produtos/lista");
		model.addObject("produtos", produtoRepository.findAll());
		return model;
	}

	@DeleteMapping("/{id}")
	public String deleta(@PathVariable(name = "id") String id) {
		produtoRepository.deleteById(id);
		return "redirect:/produtos";
	}

	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView model = new ModelAndView("/produtos/novo");
		model.addObject("produto", new ProdutoForm());
		return model;
	}

	@PostMapping
	public String adiciona(ProdutoForm form) {

		Produto p = new Produto();
		p.setId(UUID.randomUUID().toString());
		p.setNome(form.getNome());
		produtoRepository.save(p);

		return "redirect:/produtos";
	}

	@GetMapping("/{id}")
	public ModelAndView edita(@PathVariable(name = "id") String id) {

		Optional<Produto> op = produtoRepository.findById(id);
		Produto p = op.get();

		ProdutoForm form = new ProdutoForm();
		form.setId(p.getId());
		form.setNome(p.getNome());

		ModelAndView model = new ModelAndView("/produtos/edita");
		model.addObject("produto", form);
		return model;
	}

	@PutMapping("/{id}")
	public String atualiza(@PathVariable(name = "id") String id, ProdutoForm form) {

		Optional<Produto> op = produtoRepository.findById(id);
		Produto p = op.get();
		p.setNome(form.getNome());
		produtoRepository.save(p);

		return "redirect:/produtos";
	}

}