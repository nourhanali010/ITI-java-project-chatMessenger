package serverjavaproject;

import DAOImplementation.UserDAOImpl;
import DBManeger.DBManeger;
import DTOModel.UserDTO;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class UserTableController implements Initializable {

    @FXML
    private TableColumn<UserDTO, Integer> id;
    @FXML
    private TableColumn<UserDTO, String> name;
    @FXML
    private TableColumn<UserDTO, String> email;
    @FXML
    private TableColumn<UserDTO, String> gender;
    @FXML
    private TableColumn<UserDTO, Integer> age;
    @FXML
    private TableColumn<UserDTO, String> status;
    @FXML
    private TableView<UserDTO> table;

    public ArrayList<UserDTO> user;
    public ObservableList<UserDTO> data;
    public ArrayList<UserDTO> onlineUsers;

    DBManeger dbm;
    UserDAOImpl udi;
    Connection con;
    
    @FXML
    private Button refre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        

        dbm = new DBManeger();
        dbm.DBStartConnection();
        con = dbm.getConnection();
        udi = new UserDAOImpl();
        
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserDTO, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserDTO, String> event) {
                UserDTO newU = event.getRowValue();
                newU.setUserName(event.getNewValue());                
                udi.tableUpdate(con, newU);
            }
        });
        
        
        gender.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserDTO, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserDTO, String> event) {
                UserDTO newU = event.getRowValue();
                newU.setUserGender(event.getNewValue());                
                udi.tableUpdate(con, newU);
            }
        });
        
        status.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserDTO, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserDTO, String> event) {
                UserDTO newU = event.getRowValue();
                newU.setUserStatus(event.getNewValue());                
                udi.tableUpdate(con, newU);
            }
        });

        refresh();

    }
    
     /**
     * List results of data retrieved from user table
     */
    @FXML
    private void refresh() {
        if (user != null) {
            user.clear();
            data.clear();
        }
        user = udi.getAllUsers(con);


        data = FXCollections.observableArrayList(user);

        table.setEditable(true);

        id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        id.setCellFactory(new Callback<TableColumn<UserDTO, Integer>, TableCell<UserDTO, Integer>>() {
            @Override
            public TableCell<UserDTO, Integer> call(TableColumn<UserDTO, Integer> col) {
                return new TableCell<UserDTO, Integer>() {
                    @Override
                    protected void updateItem(Integer duration, boolean empty) {
                        super.updateItem(duration, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(Integer.toString(duration));
                        }
                    }
                };
            }
        });

        name.setCellValueFactory(new PropertyValueFactory<>("userName"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());

        email.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        gender.setCellValueFactory(new PropertyValueFactory<>("userGender"));
        gender.setCellFactory(TextFieldTableCell.forTableColumn());

        age.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("userAge"));
        age.setCellFactory(new Callback<TableColumn<UserDTO, Integer>, TableCell<UserDTO, Integer>>() {
            @Override
            public TableCell<UserDTO, Integer> call(TableColumn<UserDTO, Integer> col) {
                return new TableCell<UserDTO, Integer>() {
                    @Override
                    protected void updateItem(Integer duration, boolean empty) {
                        super.updateItem(duration, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(Integer.toString(duration));
                        }
                    }
                };
            }
        });

        status.setCellValueFactory(new PropertyValueFactory<>("userStatus"));
        status.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(data);
    }


}
