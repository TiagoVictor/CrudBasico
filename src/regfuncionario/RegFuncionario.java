package regfuncionario;

import DAO.FuncionarioDAO;
import java.util.Scanner;
import modelo.Funcionario;


public class RegFuncionario {

    
    public static void main(String[] args) {
        Funcionario f = new Funcionario();
        FuncionarioDAO fd = new FuncionarioDAO();        
        int op=1000;
        Scanner scn = new Scanner(System.in);
        
        System.out.println("-----CADASTRAMENTO DE FUNCIONARIO-----");
        do{
            System.out.println("Insira a opção desejada");
            System.out.println("1-Cadastrar");
            System.out.println("2-Exibir funcionarios cadastrados");
            System.out.println("3-Atualizar um funcionario");
            System.out.println("4-Deletar um funcionario");
            System.out.println("0-Para sair do sistema");
            try{
            op = scn.nextInt();
            } catch(Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            scn.nextLine();
            
            switch(op){
                case 1: 
                    System.out.println("Cadastrar");
                    System.out.println("Insira o nome do funcionario: ");
                    f.setNome(scn.nextLine());
                    System.out.println("Insira o id: ");
                    f.setId(scn.nextInt());
                    try{
                        fd.inserir(f);
                    } catch (Exception e){
                        System.out.println("Erro: " + e.getMessage());
                    }

                break;
                case 2: 
                    System.out.println("Exibir");
                   
                    for(Funcionario func : fd.listar() ){
                        System.out.println(func.getId());
                        System.out.println(func.getNome());
                        System.out.println("------------");
                    }
                break;
                case 3: 
                    System.out.println("Atualizar");
                    System.out.println("Insira o id do funcionario que voce quer atualizar");
                    f.setId(scn.nextInt());
                    System.out.println("Insira o novo nome");
                    f.setNome(scn.next());
                    try{
                    fd.atualizar(f);
                    } catch (Exception e){
                        System.out.println("Erro: " + e.getMessage());
                    }
                break;
                case 4:  
                    System.out.println("Deletar");
                    System.out.println("Insira o id do funcionario que voce quer deletar");
                    f.setId(scn.nextInt());
                    try{
                        fd.deletar(f);
                    } catch (Exception e) {
                        System.out.println("Erro: " +e.getMessage());
                    }
                break;
            }
            
        }while(op !=0);
    }
    
}
