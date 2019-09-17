/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalProject;


import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

/**
 *
 * @author Bob
 */
public class SevenSegment{
    //private BorderPane pane = new BorderPane();
    private Pane pane = new Pane();
    private Pane vPane = new Pane();
    private Polygon pol;
    private Polygon pol2;
    private Polygon pol3;
    private Polygon pol4;
    private Polygon pol5;
    private Polygon pol6;
    private Polygon hex;
    private double length;
    private double height; 
    private Circle cir1 = new Circle();
    private Circle cir2 = new Circle();
    
    public SevenSegment(double length)
    {
        this.length = length;
        this.height = length * (.2);
    }
    
    public Pane makeNumber()
    {
        this.pol = new Polygon(new double [] {height,0,length + height,0,(length*0.75)+height,height,length*.25 + height,height});
        this.pol2 = new Polygon(new double [] {0,0,0,length,height,length*0.75,height,length*0.25});
        this.pol3 = new Polygon(new double [] {0,length,0,length+length,height,length*0.75+length,height,length*0.25+length});
        this.pol4 = new Polygon(new double [] {length + height*2,0, length + height*2,length,(length-height) + height*2,length*0.75,(length-height) + height*2,length*0.25});
        this.pol5 = new Polygon(new double [] {length + height*2,length, length + height*2,length+length,(length-height) + height*2,length*0.75+length,(length-height) + height*2,length*0.25 +length});
        this.pol6 = new Polygon(new double [] {height,length+length,length+height,length+length,length*0.75+height,length-height+length,length*0.25+height,length-height+length});
        this.hex = new Polygon(new double [] {height, height+(length-height), length*0.25+height, length-height, length*0.75+height, length-height, length+height, height+(length-height), length*0.75+height, height*2+(length-height), length*0.25+height, height*2+(length-height)});
        
        pol.setFill(Color.BLUE);
        pol2.setFill(Color.BLUE);
        pol3.setFill(Color.BLUE);
        pol4.setFill(Color.BLUE);
        pol5.setFill(Color.BLUE);
        pol6.setFill(Color.BLUE);
        hex.setFill(Color.BLUE);
        
        pane.getChildren().addAll(pol, pol2, pol3, pol4, pol5, pol6, hex);
       
        Scale scale = new Scale();
        scale.xProperty().bind(pane.widthProperty().divide(140));
        scale.yProperty().bind(pane.heightProperty().divide(200));
        pol.getTransforms().add(scale);
        pol2.getTransforms().add(scale);
        pol3.getTransforms().add(scale);
        pol4.getTransforms().add(scale);
        pol5.getTransforms().add(scale);
        pol6.getTransforms().add(scale);
        hex.getTransforms().add(scale);
        
        
        
        return pane;
    }
    public Pane makeColon()
    {
        
        this.pol = new Polygon(new double [] {height,0,length + height,0,(length*0.75)+height,height,length*.25 + height,height});
        this.pol2 = new Polygon(new double [] {0,0,0,length,height,length*0.75,height,length*0.25});
        this.pol3 = new Polygon(new double [] {0,length,0,length+length,height,length*0.75+length,height,length*0.25+length});
        this.pol4 = new Polygon(new double [] {length + height*2,0, length + height*2,length,(length-height) + height*2,length*0.75,(length-height) + height*2,length*0.25});
        this.pol5 = new Polygon(new double [] {length + height*2,length, length + height*2,length+length,(length-height) + height*2,length*0.75+length,(length-height) + height*2,length*0.25 +length});
        this.pol6 = new Polygon(new double [] {height,length+length,length+height,length+length,length*0.75+height,length-height+length,length*0.25+height,length-height+length});
        this.hex = new Polygon(new double [] {height, height+(length-height), length*0.25+height, length-height, length*0.75+height, length-height, length+height, height+(length-height), length*0.75+height, height*2+(length-height), length*0.25+height, height*2+(length-height)});
        
        pol.setFill(Color.WHITE);
        pol2.setFill(Color.WHITE);
        pol3.setFill(Color.WHITE);
        pol4.setFill(Color.WHITE);
        pol5.setFill(Color.WHITE);
        pol6.setFill(Color.WHITE);
        hex.setFill(Color.WHITE);
        
        cir1 = new Circle(height*3.5, length/2-height, height);
        cir2 = new Circle(height*3.5, length+height*3, height);
        cir1.setFill(Color.BLUE);
        cir2.setFill(Color.BLUE);
        
        
        
        cir1.radiusProperty().bind(vPane.widthProperty().divide(10));
        cir1.radiusProperty().bind(vPane.heightProperty().divide(10));
        
        cir1.centerXProperty().bind(vPane.widthProperty().divide(2));
        cir1.centerYProperty().bind(vPane.heightProperty().divide(10));
        
        cir2.radiusProperty().bind(vPane.widthProperty().divide(10));
        cir2.radiusProperty().bind(vPane.heightProperty().divide(10));
        
        cir2.centerXProperty().bind(vPane.widthProperty().divide(2));
        cir2.centerYProperty().bind(vPane.heightProperty().divide(1.25));
        
        Scale scale = new Scale();
        scale.xProperty().bind(pane.widthProperty().divide(140));
        scale.yProperty().bind(pane.heightProperty().divide(200));
        pol.getTransforms().add(scale);
        pol2.getTransforms().add(scale);
        pol3.getTransforms().add(scale);
        pol4.getTransforms().add(scale);
        pol5.getTransforms().add(scale);
        pol6.getTransforms().add(scale);
        hex.getTransforms().add(scale);
        
        vPane.getChildren().addAll(pol, pol2, pol3, pol4, pol5, pol6, hex,cir1,cir2);
        
        
        
        return vPane;
    }
    public void setColonFill(Color n)
    {
        cir1.setFill(n);
        cir2.setFill(n);
        
    }
    public void setHifenFill(Color n)
    {
        hex.setFill(n);
    }
    
    public Polygon getPol1()
    {
        return pol;
    }
    public Polygon getPol2()
    {
        return pol2;
    }
    public Polygon getPol3()
    {
        return pol3;
    }
    public Polygon getPol4()
    {
        return pol4;
    }
    public Polygon getPol5()
    {
        return pol5;
    }
    public Polygon getPol6()
    {
        return pol6;
    }
    public Polygon getHex()
    {
        return hex;
    }
    public double getHeight()
    {
        return this.height;
    }
    
    
}
