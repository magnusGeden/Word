package com.mycompany.programword;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable{

    /*
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        
    }
    */
    
    @FXML
    Button SaveBtn;
    
 
    
    @FXML
    ColorPicker ColorMenue;
    
    @FXML
    ColorPicker TextBgColor;
    
    @FXML
    TextArea Text;
    
    @FXML
    MenuButton ColorMenueText;
    
    @FXML
    MenuButton AllNotes;
    
    
    
    ArrayList<TekstClass> tekst = new ArrayList<TekstClass>();
    
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        ArrayList<TekstClass> _TekstClass = new ArrayList<>();
        _TekstClass = GetAllQuestions();
        for(int i = 0; i < _TekstClass.size(); i++)
           
        {
           
        }
        //qMenu.getSelectionModel().select(0);
        System.out.println("Starting...");
        Text.setStyle("-fx-wrap-text:true;");
        Reload();
    }
    
    @FXML
    public void SaveText(){
    
        DbHolder.INSTANCE.InsertTekst(Text.getText());
        Reload();
    }
    
    
    
       
   
    
    public void Reload()
    {
     
     AllNotes.getItems().clear();
        ArrayList<TekstClass> l = GetAllQuestions();
        for(int i = 0 ; i<l.size();i++) 
        {
            MenuItem mi = new MenuItem(Integer.toString(l.get(i).ID));
            mi.setOnAction(eh ->{
                LoadText(Integer.parseInt(mi.getText()));
            });
            AllNotes.getItems().add(mi);
            
        }
     
        
        
    }
    
    /**
     *
     * @param event
     */
    public void changeColor (ActionEvent event)
    {
        
        Text.setStyle("-fx-control-inner-background:#"+TextBgColor.getValue().toString().substring(2)+";");
        //Text.setStyle("-fx-highlight-Arrays.fill: #000000;");
   
    
    }
    public void changeBackColor(ActionEvent event)
    {
        Color myColor = ColorMenue.getValue();
        Text.setBackground(new Background(new BackgroundFill(myColor, null, null)));
        System.out.println(ColorMenue.getValue().toString().substring(2));
        Text.setStyle("-fx-text-fill: #" + ColorMenue.getValue().toString().substring(2)+";");
    
    }
    /*
     public void changeColor(ActionEvent event)
    {
        ColorMenueText.setOnAction(new EventHandler<ActionEvent>() {
        });
        @Override
        public void handle (ActionEvent event){
        Text.setFill(ColorMenueText.getValue());
    
    }
        /*
        Color myColor = ColorMenueText.getValue();
        Text.setFill(Color.RED);
  
    }
*/
   
   public void LoadText(int id)
             {
                 
                 
                 String sql = "SELECT * FROM WordTextfiles WHERE `ID`="+id +";";
        try {
            ResultSet rs = DbHolder.INSTANCE.Exec(sql);
            Text.setText(rs.getString("Tekst"));
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        
             }
    
     ArrayList<TekstClass> GetAllQuestions()
    {
        String sql = "SELECT * FROM `WordTextfiles`";
        ArrayList<TekstClass> list = new ArrayList<TekstClass>();
        try {
            ResultSet rs = DbHolder.INSTANCE.Exec(sql);
            while(rs.next())
            {
                list.add(new TekstClass(rs.getString("Tekst"),rs.getInt("ID")));
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
     
     
    
    
}
