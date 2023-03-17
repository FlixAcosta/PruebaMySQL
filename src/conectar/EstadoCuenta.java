
package conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class EstadoCuenta extends Conectar{
    
    public boolean consultarD(NuevoDeposito deposito){
        
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = getConexion();
      
        
        try{
            String sql = "SELECT * FROM bancoppel.movimientos_cuenta_cliente WHERE numero_cliente=1;";
            ps = con.prepareStatement(sql);
            ps.execute();
            
            rs = ps.getResultSet();
            
            while(rs.next()){
             int num_cliente = Integer.parseInt(rs.getString(1));
             String tipo_mov = rs.getString(2);
             float monto_mov = Float.parseFloat(rs.getString(3));
             
                System.out.println(num_cliente+" "+tipo_mov+" "+monto_mov);
            }
            
            ps.execute();
            
           
        }catch (SQLException ex) {
            Logger.getLogger(InsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode());
            if(ex.getErrorCode() == 1062){
            JOptionPane.showMessageDialog(null, "Error: usuario existente en la base de datos");
            }
            
        }
    
        
        
        return false;
    }
    
}
