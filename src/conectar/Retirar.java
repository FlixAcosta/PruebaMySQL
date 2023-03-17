
package conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Retirar extends Conectar {
    
    public boolean retirarD(NuevoDeposito deposito){
        
        
         PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs;
        Connection con = getConexion();
        float saldo_actual=0;
        
        try{
            String sql = "INSERT INTO movimientos_cuenta_cliente (numero_cliente, tipo_movimiento, monto_movimiento) VALUES(?,'retiro',?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, deposito.getNumero_cliente());
            ps.setFloat(2, deposito.getMonto_movimiento());
            ps.execute();
            
            String sql2 = "SELECT saldo_cliente FROM bancoppel.detalle_cuenta_cliente WHERE numero_cliente="+deposito.getNumero_cliente()+"";
            ps2 = con.prepareStatement(sql2);
            ps2.execute();
            rs = ps2.getResultSet();
            
            while(rs.next()){
            saldo_actual = Float.parseFloat(rs.getString(1));
            }
            System.out.println(saldo_actual);
            System.out.println(deposito.getMonto_movimiento());
            
            float nuevo_saldo = saldo_actual - deposito.getMonto_movimiento();
            
            System.out.println(nuevo_saldo);
            
            if(saldo_actual < deposito.getMonto_movimiento()){
            
            JOptionPane.showMessageDialog(null, "Fondos insuficientes");
            return false;
            }
            
            String sql3 = "UPDATE bancoppel.detalle_cuenta_cliente SET saldo_cliente="+nuevo_saldo+" WHERE numero_cliente="+deposito.getNumero_cliente()+"";
            ps3 = con.prepareStatement(sql3);
            ps3.execute();
            
            JOptionPane.showMessageDialog(null,"Saldo actual:$"+nuevo_saldo);
        return true;
        }catch (SQLException ex) {
            Logger.getLogger(InsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode());
            if(ex.getErrorCode() == 1062){
            JOptionPane.showMessageDialog(null, "Error: usuario existente en la base de datos");
            }
            if(ex.getErrorCode() == 1452){
            JOptionPane.showMessageDialog(null, "Error: usuario NO existente en la base de datos");
            }
            
            
        }
        
        return false;
    }
    
}
