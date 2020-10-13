package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Funcionario;


public class FuncionarioDAO {
    private Connection con;
    private PreparedStatement cmd;
    
    public int inserir(Funcionario funcionario) {
        try{
            String sql = "INSERT INTO tb_funcionario (id_funcionario,nm_funcionario) VALUES (?,?)";
            con = Conexao.conectar();
            cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cmd.setInt(1, funcionario.getId());
            cmd.setString(2, funcionario.getNome());
            
            if (cmd.executeUpdate() > 0) {
                ResultSet rs = cmd.getGeneratedKeys();
                return rs.next() ? rs.getInt(1) : -1;
            }
            return -1;
        }catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return -1;
        } finally {
            Conexao.desconectar(con);
        }
    }
    public int atualizar(Funcionario funcionario){
        try{
            String sql = "UPDATE tb_funcionario SET nm_funcionario=? WHERE id_funcionario =?";
            con = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            cmd.setString(1, funcionario.getNome());
            cmd.setInt(2, funcionario.getId());
            
            if (cmd.executeUpdate() > 0){
                return funcionario.getId();
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return -1;
        } finally{
            Conexao.desconectar(con);
        }
    }
    public int deletar(Funcionario funcionario){
         try{
             String sql = "DELETE FROM tb_funcionario WHERE id_funcionario=?";
             con = Conexao.conectar();
             cmd = con.prepareStatement(sql);
             cmd.setInt(1, funcionario.getId());
             
             if(cmd.executeUpdate() > 0){
                 return funcionario.getId();
             }
             return -1;
         } catch (SQLException e) {
             System.out.println("Erro: " + e.getMessage());
             return -1;
         } finally {
             Conexao.desconectar(con);
         }
    }
    public List<Funcionario> listar(){
        try{
            String sql = "SELECT id_funcionario,nm_funcionario FROM tb_funcionario";
            con = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            List<Funcionario> lista = new ArrayList<>();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nm_funcionario"));
                lista.add(f);
            }
            return lista;
        } catch (SQLException e) {
        System.out.println("Erro: " + e.getMessage());
        return null;
    } finally{
            Conexao.desconectar(con);
            }
    }
    
    
}
