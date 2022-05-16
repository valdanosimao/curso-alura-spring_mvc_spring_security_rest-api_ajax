package br.com.alura.mvc.mudi.controller;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private final PedidoRepository pedidoRepository;
	
	public HomeController(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@GetMapping
	public String home(Model model, Principal principal) {
		//Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 10);
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, (Pageable) paginacao);
		model.addAttribute("pedidos", pedidos);
		return "home";
	}

}
