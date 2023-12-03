package org.example.Controller;

import java.sql.SQLException;

public interface IControllers {
    public void getAll() throws ClassNotFoundException, SQLException;
    public void setNew() throws ClassNotFoundException, SQLException;
    public void setNewCommand(String newCommand, int id) throws ClassNotFoundException, SQLException;
    public boolean getAnimId(int id) throws ClassNotFoundException, SQLException;
    public void getAllOrderBy() throws ClassNotFoundException, SQLException;


}
