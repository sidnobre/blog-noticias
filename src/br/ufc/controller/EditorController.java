package br.ufc.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.fabrica.FabricaDeConexoes;
import br.ufc.dao.noticia.SecaoDAO;
import br.ufc.dao.usuario.JornalistaDAO;
import br.ufc.model.noticia.Secao;
import br.ufc.model.usuario.Jornalista;

@Controller
public class EditorController {
	
	@RequestMapping("menu_editor")
	public String menuEditor(){
		return "usuario/editor/menuEditor";
	}
	
	@RequestMapping("cadastro_jornalista")
	public String cadastroJornalista(){
		return "usuario/editor/FormJornalista";
	}
	@RequestMapping("cadastro_classificado")
	public String cadastroClassificado() {
		return "usuario/editor/FormClassificado";
	}
	
	@RequestMapping("cadastro_secao")
	public String cadastroSecao() {
		return "usuario/editor/FormSecao";
	}
	
	@RequestMapping("cadastraJornalista")
	public String cadastrarJornalista(@Valid Jornalista jornalista, BindingResult result) {
		if(result.hasErrors()){
			return "usuario/editor/FormClassificado";
			}
		FabricaDeConexoes fc = new FabricaDeConexoes();
		Connection conn = fc.getConexao();
		JornalistaDAO jDAO = new JornalistaDAO(conn);
		jDAO.inserir(jornalista);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return "redirect:editor/menu_editor";
	}
	
	@RequestMapping("cadastraSecao")
	public String cadastrarSecao(@Valid Secao secao, BindingResult result) {
		if(result.hasErrors()){
			return "usuario/editor/FormSecao";
			}
		FabricaDeConexoes fc = new FabricaDeConexoes();
		Connection conn = fc.getConexao();
		SecaoDAO sDAO = new SecaoDAO(conn);
		sDAO.inserir(secao);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return "redirect:editor/menu_editor";
	}
	
}

