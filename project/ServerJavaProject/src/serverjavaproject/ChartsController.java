/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjavaproject;

import DAOImplementation.UserDAOImpl;
import DBManeger.DBManeger;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Aya
 */
public class ChartsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private PieChart genderPie;
   
   @FXML
    private PieChart statusPie;
    
    
    
    @FXML
    private NumberAxis yAxis;

    @FXML
    private CategoryAxis xAxis ;
   
    
    @FXML
    private BarChart<String,Number>ageChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //male_female_Pie
       int male,female,online,offline,age1,age2,age3;
       
        DBManeger mgr = new DBManeger();
        mgr.DBStartConnection();
        Connection con1 = mgr.getConnection();
        UserDAOImpl obj1 = new UserDAOImpl();
        UserDAOImpl obj2 = new UserDAOImpl();
        UserDAOImpl obj3 = new UserDAOImpl();
        
        male=obj1.countMale(con1);
        //System.out.print(male);
        female=obj1.countFemale(con1);
        //System.out.print(female);
        
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Male", male),
                new PieChart.Data("Female", female));
        
         genderPie.setData(pieChartData);
        
  
        
        age1=obj2.countAge1(con1);
        age2=obj2.countAge2(con1);
        age3=obj2.countAge3(con1);
        
        xAxis.setLabel("Age");
        //yAxis.setLabel("Number");
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2018");

        dataSeries1.getData().add(new XYChart.Data("15-20", age1));
        dataSeries1.getData().add(new XYChart.Data("20-28"  , age2));
        dataSeries1.getData().add(new XYChart.Data("29-35"  , age3));
        ageChart.getData().add(dataSeries1);
        

        
        online=obj3.countOnline(con1);
        offline=obj3.countOffline(con1);
        
        ObservableList<PieChart.Data> pieChartData1 =
                FXCollections.observableArrayList(
                new PieChart.Data("Online",online),
                new PieChart.Data("Offline",offline));
         statusPie.setData(pieChartData1); 
    }  
    }    
    

