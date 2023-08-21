package org.example.DAO;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer> {
    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Customer element) throws SQLException {
        request = "INSERT INTO customer (first_name, last_name, phone) values (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS); // "Return..." Récupere l'ID
        statement.setString(1, element.getFirstName());
        statement.setString(2, element.getLastName());
        statement.setString(3, element.getPhone());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys(); //Id généré automatiquement
        if(resultSet.next()) {
            element.setId(resultSet.getInt(1)); //l'element de customer va récupéré l'id de la BDD génér automatiquement
        }
        return nbRow == 1; //Si nbRow = 1 c'est qu'il a fait le job "ajouter client ou save"
    }

    @Override
    public boolean update(Customer element) throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("customer dao"); //Pas de modification pour le customer (message possible)
    }

    @Override
    public boolean delete(Customer element) throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("customer dao");//Pas de modification pour le customer (message possible)
    }

    @Override
    public Customer get(int id) throws SQLException { //Résupére u client par rapport a son id
        Customer customer = null; //Initiatisation du customer à null
        request = "SELECT * FROM customer where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) { // On verifie le resultat de la requete
            customer = new Customer(resultSet.getInt("id"), //:Le customer à null devient un vrai customer avec les parametres
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("phone")
            );
        }
        return customer; //Si l'id selectionnée est trop grand par exemple, le return de customer sera null
    }

    @Override
    public List<Customer> get() throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("customer dao");
    }
}