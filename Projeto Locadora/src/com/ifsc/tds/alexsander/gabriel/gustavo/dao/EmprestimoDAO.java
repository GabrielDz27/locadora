package com.ifsc.tds.alexsander.gabriel.gustavo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Emprestimo;


public class EmprestimoDAO implements DAO<Emprestimo> {

		private ClienteDAO clienteDAO;

		private FilmeDAO filmeDAO;

		public EmprestimoDAO() {
			this.clienteDAO = new ClienteDAO();
			this.filmeDAO = new FilmeDAO();
		}

		@Override
		public Object get(Long id) {
			Emprestimo emprestimo = null;
			String sql = "select * from emprestimo where id = ?";

			// Recupera a conex�o com o banco
			Connection conexao = null;

			// Criar uma prepara��o da consulta
			PreparedStatement stm = null;

			// Criar uma classe que guarde o retorno da opera��o
			ResultSet rset = null;

			try {

				conexao = new Conexao().getConnection();

				stm = conexao.prepareStatement(sql);
				stm.setInt(1, id.intValue());
				rset = stm.executeQuery();

				while (rset.next()) {
					emprestimo = new Emprestimo();

					// atribui campo para atributo
					emprestimo.setId(rset.getLong("id"));
					emprestimo.setData_emprestimo(rset.getDate("data_emprestimo"));
					emprestimo.setData_Entrega(rset.getDate("data_entrega"));
					emprestimo.setObs(rset.getString("obs"));
					emprestimo.setValorEmprestimo(rset.getInt("valorEmprestimo"));

					// buscando as chaves estrangeiras
					emprestimo.setCliente(this.clienteDAO.get(rset.getLong("fk_cliente")));
					emprestimo.setFilme(this.filmeDAO.get(rset.getLong("fk_filme")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stm != null) {
						stm.close();
					}

					if (conexao != null) {
						conexao.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return emprestimo;
		}

		@Override
		public List<Emprestimo> getAll() {
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

			String sql = "select * from emprestimo";

			// Recupera a conex�o com o banco
			Connection conexao = null;

			// Criar uma prepara��o da consulta
			PreparedStatement stm = null;

			// Criar uma classe que guarde o retorno da opera��o
			ResultSet rset = null;

			try {

				conexao = new Conexao().getConnection();

				stm = conexao.prepareStatement(sql);
				rset = stm.executeQuery();

				while (rset.next()) {
					Emprestimo emprestimo = new Emprestimo();

					// atribui campo para atributo
					emprestimo.setId(rset.getLong("id"));
					emprestimo.setData_emprestimo(rset.getDate("data_emprestimo"));
					emprestimo.setData_Entrega(rset.getDate("data_entrega"));
					emprestimo.setObs(rset.getString("obs"));
					emprestimo.setValorEmprestimo(rset.getInt("valorEmprestimo"));

					// buscando as chaves estrangeiras
					emprestimo.setCliente(this.clienteDAO.get(rset.getLong("fk_cliente")));
					emprestimo.setFilme(this.filmeDAO.get(rset.getLong("fk_filme")));

					emprestimos.add(emprestimo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stm != null) {
						stm.close();
					}

					if (conexao != null) {
						conexao.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return emprestimos;
		}

		@Override
		public int save(Emprestimo emprestimo) {
			String sql = "insert into emprestimo (valorEmprestimo, data_Emprestimo, data_entrega, fk_filme, fk_cliente, obs)" + " values (?, ?, ?, ?, ?, ?)";

			// Recupera a conex�o com o banco
			Connection conexao = null;

			// Criar uma prepara��o da consulta
			PreparedStatement stm = null;

			try {

				conexao = new Conexao().getConnection();

				stm = conexao.prepareStatement(sql);
				stm.setInt(1, emprestimo.getValorEmprestimo());
				stm.setDate(2, emprestimo.getData_emprestimo());
				stm.setDate(3, emprestimo.getData_Entrega());
				stm.setLong(4, emprestimo.getCliente().getId());
				stm.setLong(5, emprestimo.getFilme().getId());
				stm.setString(6, emprestimo.getObs());

				stm.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stm != null) {
						stm.close();
					}

					if (conexao != null) {
						conexao.close();
					}
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return 0;
		}

		@Override
		public boolean update(Emprestimo emprestimo, String[] params) {
			String sql = "update emprestimo set valorEmprestimo = ?, data_Emprestimo = ? , data_entrega= ?, fk_filme= ?, fk_cliente = ?, obs = ? where id = ?";

			// Recupera a conex�o com o banco
			Connection conexao = null;

			// Criar uma prepara��o da consulta
			PreparedStatement stm = null;

			try {
				conexao = new Conexao().getConnection();

				stm = conexao.prepareStatement(sql);
				stm.setInt(1, emprestimo.getValorEmprestimo());
				stm.setDate(2, emprestimo.getData_emprestimo());
				stm.setDate(3, emprestimo.getData_Entrega());
				stm.setLong(4, emprestimo.getFilme().getId());
				stm.setLong(5, emprestimo.getCliente().getId());
				stm.setString(6, emprestimo.getObs());
				stm.setLong(7, emprestimo.getId());

				stm.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stm != null) {
						stm.close();
					}

					if (conexao != null) {
						conexao.close();
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		@Override
		public boolean delete(Emprestimo emprestimo) {
			String sql = "delete from emprestimo where id = ?";

			// Recupera a conex�o com o banco
			Connection conexao = null;

			// Criar uma prepara��o da consulta
			PreparedStatement stm = null;

			try {
				conexao = new Conexao().getConnection();

				stm = conexao.prepareStatement(sql);
				stm.setLong(1, emprestimo.getId());
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stm != null) {
						stm.close();
					}

					if (conexao != null) {
						conexao.close();
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
	}
}
