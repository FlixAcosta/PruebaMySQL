
package conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class InsertSQL extends Conectar {
    
    public boolean insertarD(NuevaCuenta cuenta){
        
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO bancoppel.detalle_cuenta_cliente (numero_cliente, nombre_cliente, saldo_cliente) VALUES(?,?,?)";
    try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cuenta.getNumero_cliente());
            ps.setString(2, cuenta.getNombre_cliente());
            ps.setFloat(3, cuenta.getSaldo_cliente());            
            ps.execute();        
            
            return true;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode());
            if(ex.getErrorCode() == 1062){
            JOptionPane.showMessageDialog(null, "Error: usuario existente en la base de datos");
            }
            
        }
        
       return false;
    }
    
}
