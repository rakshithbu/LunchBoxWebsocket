import com.fasterxml.jackson.databind.JsonNode;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.Base64;

public class DatabaseOperations {

public void insertIntoProducts(Connection connection, JsonNode jsonString) throws SQLException {
     String SQL_INSERT = "INSERT INTO products (product_name, product_stock, product_price, product_time,product_image) VALUES (?,?,?,?,?)";

     try {
         byte[] decodedByte = Base64.getDecoder().decode(jsonString.get("productImage").asText().split(",")[1]);
         Blob blob = new SerialBlob(decodedByte);

         PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);

         preparedStatement.setString(1, jsonString.get("productName").asText());
         preparedStatement.setInt(2, jsonString.get("productStock").asInt());
         preparedStatement.setDouble(3, jsonString.get("productPrice").asDouble());
         preparedStatement.setInt(4, jsonString.get("productPrice").asInt());
         preparedStatement.setBlob(5, blob);
         int row = preparedStatement.executeUpdate();
     }catch (Exception e){
         e.printStackTrace();
     }
}

}
