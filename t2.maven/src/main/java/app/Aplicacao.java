package app;

import java.util.List;
import java.util.Scanner;

import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {

	public static void main(String[] args) throws Exception {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Scanner sc = new Scanner(System.in);
		int opcao;
		
		do {
			System.out.println("1. Listar usuários");
			System.out.println("2. Inserir usuário");
			System.out.println("3. Excluir usuário");
			System.out.println("4. Atualizar usuário");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = sc.nextInt();
			sc.nextLine(); 

			switch (opcao) {
				case 1:
					System.out.println("\n\nListar usuários");
					List<Usuario> usuarios = usuarioDAO.get();
					if (usuarios.isEmpty()) {
						System.out.println("Nenhum usuário cadastrado.");
					} else {
						for (Usuario u : usuarios) {
							System.out.println(u.toString());
						}
					}
					break;
				
				case 2:
					System.out.println("\n\nInserir usuário");
					System.out.print("Digite o código: ");
					int codigo = sc.nextInt();
					sc.nextLine();
					System.out.print("Digite o login: ");
					String login = sc.nextLine();
					System.out.print("Digite a senha: ");
					String senha = sc.nextLine();
					System.out.print("Digite o sexo (M/F): ");
					char sexo = sc.next().charAt(0);
					
					Usuario novoUsuario = new Usuario(codigo, login, senha, sexo);
					if (usuarioDAO.insert(novoUsuario)) {
						System.out.println("Usuário inserido com sucesso!");
					} else {
						System.out.println("Falha na inserção do usuário.");
					}
					break;
				
				case 3:
					System.out.println("\n\nExcluir usuário");
					System.out.print("Digite o código do usuário a ser excluído: ");
					int codigoExcluir = sc.nextInt();
					if (usuarioDAO.delete(codigoExcluir)) {
						System.out.println("Usuário excluído com sucesso!");
					} else {
						System.out.println("Falha na exclusão do usuário. Verifique o código.");
					}
					break;
				
				case 4:
					System.out.println("\n\nAtualizar usuário");
					System.out.print("Digite o código do usuário a ser atualizado: ");
					int codigoAtualizar = sc.nextInt();
					sc.nextLine();
					
					Usuario usuarioAtualizar = usuarioDAO.get(codigoAtualizar);
					if (usuarioAtualizar != null) {
						System.out.println("Usuário encontrado: " + usuarioAtualizar.getLogin());
						System.out.print("Digite o novo login: ");
						String novoLogin = sc.nextLine();
						System.out.print("Digite a nova senha: ");
						String novaSenha = sc.nextLine();
						System.out.print("Digite o novo sexo (M/F): ");
						char novoSexo = sc.next().charAt(0);
						
						usuarioAtualizar.setLogin(novoLogin);
						usuarioAtualizar.setSenha(novaSenha);
						usuarioAtualizar.setSexo(novoSexo);

						if (usuarioDAO.update(usuarioAtualizar)) {
							System.out.println("Usuário atualizado com sucesso!");
						} else {
							System.out.println("Falha na atualização do usuário.");
						}
					} else {
						System.out.println("Usuário não encontrado.");
					}
					break;
				
				case 5:
					System.out.println("Saindo do programa...");
					break;
					
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
			
		} while (opcao != 5);
		
		sc.close();
		usuarioDAO.close();
		System.out.println("Conexão fechada.");
	}
}
