package teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.ChecklistDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.ProcedimentoDAO;
import br.unitins.censohgp.dao.TipoDAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Procedimento;
import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.Usuario;

public class Teste {

	public static void main(String[] args) {

		List<Procedimento> procList = new ArrayList<Procedimento>();
		Checklist check = new Checklist();
		ChecklistDAO dao = new ChecklistDAO();
		ProcedimentoDAO proc = new ProcedimentoDAO();
		Procedimento procedimento = new Procedimento();
		PacienteDAO paciDao = new PacienteDAO();
		UsuarioDAO usuDao = new UsuarioDAO();
		TipoDAO tipoDao = new TipoDAO();
		Tipo tipo = tipoDao.findId(1);
		Usuario usu = null;
		Usuario usu1 = null;
		String senha = Util.hashSHA256("123456");
		try {
			usuDao.getConnection();
			usu.setId(1);
			usu.setNome("sdfdsf");
			usu.setEmail("victor@gmail.com");
			usu.setAtivo(true);
			usu.setMatricula("234234");
			usu.setSenha1(senha);
			usu.setSenha2(senha);
			usu.setTipo(tipo);
			usuDao.create(usu);
		} 
		catch (SQLException e) {
			usuDao.rollbackConnection(); 
		}
		usuDao.closeConnection();

		try {
			dao.getConnection();
			Paciente paci = paciDao.findById(1);
			usu1 = usuDao.findId(1);
			procList = proc.findAll();
			procedimento = procList.get(0);
			check.setPaciente(paci);
//			check.setProcedimento(procedimento);
//			check.setUsuario(usu);
			dao.create(check);
			System.out.println("Feito.");
		} catch (SQLException e) {
			System.out.println("errado.");
			e.printStackTrace();

		}

		dao.closeConnection();
	}
}
