package br.unitins.censohgp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.model.Usuario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/administrador/*", "/faces/usuario/*"})
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		// imprime o endereco da pagina
		String endereco = servletRequest.getRequestURI();
		System.out.println(endereco);
		if (endereco.equals("/CensoHGP/faces/login.xhtml")) {
			chain.doFilter(request, response);
			return;
		}

		// retorna a sessao corrente (false - para nao criar uma nova sessao)
		HttpSession session = servletRequest.getSession(false);

		Usuario usuario = null;
		if (session != null)
			usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (usuario == null) {
			((HttpServletResponse) response).sendRedirect("/CensoHGP/faces/login.xhtml");
			return;
		} else {
			// nesse local podemos trabalhar as permissoes por pagina
			System.out.println(usuario.getTipo().getId());
			if (usuario.getTipo().getId().equals(1)) {
				System.out.println("entrou aqui");

				if (endereco.equals("/CensoHGP/faces/usuario/index.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/alterarpaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/buscarpaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/cadastropaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/checklistpaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/consultadepartamento.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/listabuscapaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/usuario/transferencia.xhtml")) {

					System.out.println("a aqui");
					// segue o fluxo
					((HttpServletResponse) response).sendRedirect("/CensoHGP/faces/login.xhtml");
					return;
				}
				chain.doFilter(request, response);
				return;

			}
			if (usuario.getTipo().getId().equals(2)) {
				System.out.println("entrou aqui");
				if (endereco.equals("/CensoHGP/faces/administrador/index.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/alterarpaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/buscarpaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/buscarusuario.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/cadastrardepartamento.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/cadastrousuario.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/cadastropaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/consultadepartamento.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/listabuscapaciente.xhtml")
						|| endereco.equals("/CensoHGP/faces/administrador/transferencia.xhtml")) {

					System.out.println("a aqui");
					// segue o fluxo
					((HttpServletResponse) response).sendRedirect("/CensoHGP/faces/login.xhtml");
					return;
				}

				chain.doFilter(request, response);
				return;

			}

			((HttpServletResponse) response).sendRedirect("/CensoHGP/faces/login.xhtml");
			// segue o fluxo
			chain.doFilter(request, response);
			return;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}