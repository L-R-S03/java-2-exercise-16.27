/*
 * Project: Java 2 Programming exercise 27
 * Name: Lauren Smith
 * Date: 1/28/21
 * Description: DescriptionPane class to display text area, image and a label
 */

package pkg1627;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

//class used to display text area with description and image of flag
public class DescriptionPane extends BorderPane {
   //label for country name and text area for flag description
    private Label lblImage=new Label(); 
    
    private TextArea desc=new TextArea(); 
    
    public DescriptionPane() 
    {
        //sets image to be at top with a perferred size
        lblImage.setContentDisplay(ContentDisplay.TOP);
        lblImage.setPrefSize(200,100); 
        
        //makes text area not editable and able to have wrapped text
        desc.setWrapText(true); 
        desc.setEditable(false); 
        
        //adds ScrollPane so text area can be scrolled
        ScrollPane scrollPane=new ScrollPane(desc); 
        //sets positioning of items in DescriptionPane
        setLeft(lblImage); 
        setCenter(scrollPane); 
        setPadding(new Insets(5,5,5,5)); 
       
    } 
    //setters for title,description,image 
    public void setTitle(String title) 
    {
        lblImage.setText(title); 
    } 
    
    public void setDesc(String text) 
    {
        desc.setText(text); 
    }
    
    public void setImageView(ImageView icon) 
    {
        lblImage.setGraphic(icon); 
    }
}
