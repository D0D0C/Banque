package org.example.DAO;

        import jdk.jshell.spi.ExecutionControl;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.List;

//Correction Cours avec commentaire
public abstract class BaseDAO<T> {
    protected Connection _connection;
    protected PreparedStatement statement;
    protected String request;
    protected ResultSet resultSet;

    protected BaseDAO(Connection connection) {
        _connection = connection;
    } //Connection à la construction


    //Méthode "CRUD"
    // tte classe héritée sera abstract car l'objet baseDAO' est abstract également
    public abstract boolean save(T element) throws SQLException; //La couche service traitera des exceptions
    public abstract boolean update(T element) throws SQLException, ExecutionControl.NotImplementedException;
    //NotImplementedException : cette méthode n'est pas implementée, obligée de la mettre mais rien dedans
    public abstract boolean delete(T element) throws SQLException, ExecutionControl.NotImplementedException;
    //Supprimer un element de la BDD
    public abstract T get(int id) throws SQLException, ExecutionControl.NotImplementedException;
    //Récupérer un seul element de la BDD
    public abstract List<T> get() throws SQLException, ExecutionControl.NotImplementedException;
    //Tout récupérer de la base de données.
}