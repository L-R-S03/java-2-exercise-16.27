/*
 * Project: Java 2 Programming exercise 27
 * Name: Lauren Smith
 * Date: 1/28/21
 * Description: Displays country flag and description. Country changed through
  * options in a ComboBox
 */
package pkg1627;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application 
{
    //makes string array of country names so it can be accessed in all methods
    String[] titles={"Canada","China","Denmark","France","Germany","India",
       "Norway","United Kingdom","United States"}; 
    //makes an array for the images of flags and loads them in 
    ImageView[] flags={new ImageView("canada.png"),
        new ImageView("china.png"),
        new ImageView("denmark.png"),new ImageView("france.png"),
        new ImageView("germany.png"),new ImageView("india.png"),
        new ImageView("norway.png"),new ImageView("uk.png"),
        new ImageView("us.png")};
    
    
    //makes a descriptionPane     
    DescriptionPane descPane=new DescriptionPane();  
    //makes a ComboBox to hold strings which will hold country names
    ComboBox <String> cbo=new ComboBox(); 
    //makes an empty String array for country descriptions which will be filed in
    //later 
    String[] descriptions=new String[9]; 
        
        @Override
    public void start(Stage primaryStage) throws FileNotFoundException 
    {
        //makes two BorderPanes, one for the combobox and another to combine everything
        BorderPane main=new BorderPane();
        BorderPane paneForCombo= new BorderPane(); 
        //sets items in ComboBox to the array of country names 
        ObservableList<String> items=FXCollections.observableArrayList(titles); 
        //adds a new label to the left of the comboBox pane to say 
        //select a country
        paneForCombo.setLeft(new Label("Select a Country: ")); 
        //sets comboBox to the center of the comboBox pane 
        paneForCombo.setCenter(cbo); 
        //sets comboBox to the top of the main pane
        main.setTop(paneForCombo); 
        //sets pane for country description to the middle of the main pane
        main.setCenter(descPane); 
        //adds all the country names to the comboBox
        cbo.getItems().addAll(items); 
        //runs read method to load in country descriptions 
        read(); 
        cbo.setPrefWidth(400); 
        //makes the country selected in ComboBox when first loaded be canada
        cbo.setValue("Canada"); 
        //runs selection method for index of canada so right information is disokayed
        //when first loaded
        selection(0); 
        //lambda that goes off when selection in comboBox changes
        cbo.setOnAction(e -> {
            //in try catch because something could go wrong with reading the file
            //in from earlier 
            try {
                //runs selection method with passing the value of the ComboBox
                //current index selected
                selection(items.indexOf(cbo.getValue()));
            //if file isn't found display file not found error 
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        
        
       
        
        Scene scene = new Scene(main, 800, 250);
        primaryStage.setTitle("Countries");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    
        
    //method to read country description files and add them in the descriptions
    //array
    public void read() 
       {
           //string to hold file data
           String desc=""; 
           //loops through all the files
           for(int i=0; i<descriptions.length; i++) 
           {
               //empties description string when new file happens
               desc=""; 
               try { 
                   //sets scanner to read file named description followed by index
                   Scanner scan=new Scanner(new File("description"+i+".txt"));
                   while(scan.hasNext()) 
                   {
                       //adds all read data to description string
                       desc+=scan.next()+" "; 
                   } 
                   //sets string to descriptions array at the same index
                   descriptions[i]=desc; 
                //catch in case file isn't found
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    //method used for when comboBox selected is changed
    public void selection(int index) throws FileNotFoundException 
    {
        //sets title of country to index of selection in comboBox
        descPane.setTitle(titles[index]); 
        //sets the flag to the same index as comboBox in flag array
        descPane.setImageView(flags[index]); 
        //sets description displayed to index of ComboBox in descriptions array
        descPane.setDesc(descriptions[index]); 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
