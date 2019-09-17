/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalProject;

import java.time.LocalDateTime;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Bob
 */
public class Clock extends Application {
    
    private Pane hour1;
    private Pane hour2;
    private Pane minute1;
    private Pane minute2;
    private Pane second1;
    private Pane second2;
    private Pane colon1;
    private Pane colon2;
    private Pane day1;
    private Pane day2;
    private Pane month1;
    private Pane month2;
    private Pane year1;
    private Pane year2;
    private Pane year3;
    private Pane year4;
    private Pane hifen1;
    private Pane hifen2;
    private Timeline timeline;
    private Timeline timeline2;
    private LocalDateTime now = LocalDateTime.now();
    private SevenSegment sec1seg = new SevenSegment(100);
    private SevenSegment sec2seg = new SevenSegment(100);
    private SevenSegment min1seg = new SevenSegment(100);
    private SevenSegment min2seg = new SevenSegment(100);
    private SevenSegment hour1seg = new SevenSegment(100);
    private SevenSegment hour2seg = new SevenSegment(100);
    private SevenSegment colon1seg = new SevenSegment(70);
    private SevenSegment colon2seg = new SevenSegment(70);
    private SevenSegment day1seg = new SevenSegment(50);
    private SevenSegment day2seg = new SevenSegment(50);
    private SevenSegment month1seg = new SevenSegment(50);
    private SevenSegment month2seg = new SevenSegment(50);
    private SevenSegment year1seg = new SevenSegment(50);
    private SevenSegment year2seg = new SevenSegment(50);
    private SevenSegment year3seg = new SevenSegment(50);
    private SevenSegment year4seg = new SevenSegment(50);
    private SevenSegment hifen1seg = new SevenSegment(50);
    private SevenSegment hifen2seg = new SevenSegment(50);
    private HBox time;
    private HBox date = new HBox(-2*year1seg.getHeight());
    private int counterSec = now.getSecond();
    private int counterMin = now.getMinute();
    private int counterHour = now.getHour();
    private int counter;
    private CheckBox check;
    private boolean isMilitaryTime = true;
    private HBox secondContainer = new HBox(10);
    private VBox mainPane = new VBox(10); 
    private Text am = new Text("AM");
    private Text pm = new Text("PM");
    private Pane ampm = new Pane();
    private int [] monthsOfYear1 = {31,28,31,30,31,30,31,31,30,31,30,31};
    private int [] monthsOfYear2 = {31,29,31,30,31,30,31,31,30,31,30,31};
    private int counterMonth = now.getMonthValue();
    private int counterDay = now.getDayOfMonth();
    private int counterYear = now.getYear();
    private int counterHourDays = now.getHour();
    private int counterHourK = now.getHour();
    private VBox ampmContain = new VBox();
    private int checkX = 0;
    private HBox topBox = new HBox(25);
    private ToggleGroup group = new ToggleGroup();
    private int colorIndex = 0;
    private Color[] colorList = {
        Color.BLUE,
        Color.RED,
        Color.BLACK,
        Color.GREEN
    };
    private RadioButton blue = new RadioButton("Blue");
    private RadioButton red = new RadioButton("Red");
    private RadioButton black = new RadioButton("Black");
    private RadioButton green = new RadioButton("Green");
    
    @Override
    public void start(Stage primaryStage) {
       setUpNumbers();
       initalizeDots();
       mainPane.setStyle("-fx-background:white;");
       
       setRadio();
       setUpDate();
       
       
       setUpAmPm(now.getHour());
       mainPane.getChildren().addAll(topBox,time,date, secondContainer);
       clockDuration();
       Scene scene = new Scene(mainPane);
        
        
        
       primaryStage.setTitle("Digital Clock");
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
    public void clockDuration()
    {
        //Setting up standard time
        if(counterHour > 12)
        {
            counterHour -= 12;
        }
        else if(counterHour == 0)
        {
            counterHour = 12;
        }
        counter = now.getSecond();
        timeline = new Timeline();
        timeline2 = new Timeline();
        KeyFrame  k1 = new KeyFrame(
                Duration.millis(1000), e -> {
                    if(check.isSelected() == false)
                    {
                        if(counterSec == 59)
                        {
                            counterSec = 0;
                            if(counterMin == 59)
                            {
                                counterMin = 0;
                                counterHourDays++;
                                if(counterHour == 12)
                                {
                                    counterHour = 1;
                                }
                                else
                                    counterHour++;
                            }
                            else
                                counterMin++;
                        }
                        else
                            counterSec++;
                    }
                    else
                    {
                        if(counterSec == 59)
                        {
                            counterSec = 0;
                            if(counterMin == 59)
                            {
                                counterMin = 0;
                                counterHourDays++;
                                if(counterHour == 23)
                                {
                                    counterHour = 0;
                                }
                                else
                                    counterHour++;
                            }
                            else
                                counterMin++;
                        }
                        else
                            counterSec++;
                    }
                    setNumber(counterSec/10, sec1seg);
                    setNumber(counterSec%10, sec2seg);
                    setNumber(counterMin/10, min1seg);
                    setNumber(counterMin%10, min2seg);
                    setNumber(counterHour/10, hour1seg);
                    setNumber(counterHour%10, hour2seg);

                    
                });
        //bottom circles
        KeyFrame  k2 = new KeyFrame(
                Duration.millis(1000), e -> {

                    if(counterSec == 60)
                    {
                        counterSec = 1;
                        secondContainer.getChildren().clear();
                        setUpSeconds(counterSec);
                        
                    }
                    else
                    {
                        secondContainer.getChildren().clear();
                        setUpSeconds(counterSec);
                    }
                });
        //switching between 24 hr and standard time
        KeyFrame  k3 = new KeyFrame(
                Duration.millis(100), e -> {
                    
               
                    if(check.isSelected() == true)
                    {
                        LocalDateTime tempHour = LocalDateTime.now();
                        
                        if(isMilitaryTime == true)
                        {
                           if(tempHour.getHour() == 0)
                           {
                                counterHour = 0;
                           }
                           if(tempHour.getHour() > 12)
                           {
                              counterHour += 12;
                           }
                           isMilitaryTime = false; 
                           am.setFill(Color.OLDLACE);
                           pm.setFill(Color.OLDLACE);
                        }
                    }
                    else if(check.isSelected() == false)
                    {
                        isMilitaryTime = true;
                        if(counterHourDays >= 12)
                        {
                            am.setFill(Color.OLDLACE);
                            pm.setFill(colorList[colorIndex]);
                        }
                        else
                        {
                            am.setFill(colorList[colorIndex]);
                            pm.setFill(Color.OLDLACE);
                        }  
                        if(counterHour > 12)
                        {
                            counterHour -= 12;
                        }
                        else if(counterHour == 0)
                        {
                            counterHour = 12;
                        }
                    }
                    setNumber(counterHour/10, hour1seg);
                    setNumber(counterHour%10, hour2seg);
              });
        //date
        KeyFrame  k4 = new KeyFrame(
                Duration.millis(100), e -> {
                    int temp [];
                    if(counterYear%4 == 0)
                        temp = monthsOfYear2;
                    else
                        temp = monthsOfYear1;
                    if(counterHourDays == 24)
                    {
                        counterDay++;
                        counterHourDays = 0;
                        if(counterDay > temp[counterMonth-1])
                        {
                            counterDay = 1;
                            counterMonth++;
                            if(counterMonth > 12)
                            {
                                counterMonth = 1;
                                counterYear++;
                            }
                        }
                    }
                    setNumber(counterMonth/10, month1seg);
                    setNumber(counterMonth%10, month2seg);
                    setNumber(counterDay/10, day1seg);
                    setNumber(counterDay%10, day2seg);
                    setNumber(counterYear/1000, year1seg);
                    setNumber((counterYear%1000)/100, year2seg);
                    setNumber(((counterYear%1000)%100)/10,year3seg);
                    setNumber(((counterYear%1000)%100)%10,year4seg);
                });
        //color change
        KeyFrame k5 = new KeyFrame (
                Duration.millis(100), e -> {
                    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                        public void changed(ObservableValue<? extends Toggle> ob, Toggle O, Toggle n)
                        {
                            if(blue.isSelected())
                            {
                                colorIndex = 0;
                                colon1seg.setColonFill(colorList[colorIndex]);
                                colon2seg.setColonFill(colorList[colorIndex]);
                                hifen1seg.setHifenFill(colorList[colorIndex]);
                                hifen2seg.setHifenFill(colorList[colorIndex]);
                                if(check.isSelected() == true)
                                {
                                   am.setFill(Color.OLDLACE);
                                   pm.setFill(Color.OLDLACE);
                                }
                                else if(counterHourK >12)
                                {
                                    am.setFill(Color.OLDLACE);
                                    pm.setFill(colorList[colorIndex]);
                                }
                                else
                                {
                                    am.setFill(colorList[colorIndex]);
                                    pm.setFill(Color.OLDLACE);
                                }
                                
                                
                            }
                            if(red.isSelected())
                            {
                                colorIndex = 1;
                                colon1seg.setColonFill(colorList[colorIndex]);
                                colon2seg.setColonFill(colorList[colorIndex]);
                                hifen1seg.setHifenFill(colorList[colorIndex]);
                                hifen2seg.setHifenFill(colorList[colorIndex]);
                                if(check.isSelected() == true)
                                {
                                   am.setFill(Color.OLDLACE);
                                   pm.setFill(Color.OLDLACE);
                                }
                                else if(counterHourK >12)
                                {
                                    am.setFill(Color.OLDLACE);
                                    pm.setFill(colorList[colorIndex]);
                                }
                                else
                                {
                                    am.setFill(colorList[colorIndex]);
                                    pm.setFill(Color.OLDLACE);
                                } 
                                
                                
                            }
                            if(black.isSelected())
                            {
                                colorIndex = 2;
                                colon1seg.setColonFill(colorList[colorIndex]);
                                colon2seg.setColonFill(colorList[colorIndex]);
                                hifen1seg.setHifenFill(colorList[colorIndex]);
                                hifen2seg.setHifenFill(colorList[colorIndex]);
                                if(check.isSelected() == true)
                                {
                                   am.setFill(Color.OLDLACE);
                                   pm.setFill(Color.OLDLACE);
                                }
                                else if(counterHourK >12)
                                {
                                    am.setFill(Color.OLDLACE);
                                    pm.setFill(colorList[colorIndex]);
                                }
                                else
                                {
                                    am.setFill(colorList[colorIndex]);
                                    pm.setFill(Color.OLDLACE);
                                }
                                
                                
                                
                            }
                            if(green.isSelected())
                            {
                                colorIndex = 3;
                                colon1seg.setColonFill(colorList[colorIndex]);
                                colon2seg.setColonFill(colorList[colorIndex]);
                                hifen1seg.setHifenFill(colorList[colorIndex]);
                                hifen2seg.setHifenFill(colorList[colorIndex]);
                                if(check.isSelected() == true)
                                {
                                   am.setFill(Color.OLDLACE);
                                   pm.setFill(Color.OLDLACE);
                                }
                                else if(counterHourK >12)
                                {
                                    am.setFill(Color.OLDLACE);
                                    pm.setFill(colorList[colorIndex]);
                                }
                                else
                                {
                                    am.setFill(colorList[colorIndex]);
                                    pm.setFill(Color.OLDLACE);
                                }
                                
                                
                                
                            }
                        }
                    });
                }
        );

        timeline.getKeyFrames().addAll(k1,k2, k3,k4, k5);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
    }
    public void setRadio()
    {
        
        check = new CheckBox("24-Hour Format");
        blue.setToggleGroup(group);
        blue.setSelected(true);
        red.setToggleGroup(group);
        black.setToggleGroup(group);
        green.setToggleGroup(group);
        
        topBox.getChildren().addAll(check, blue, red, green,black);
    }
    
    public void setNumber(int i, SevenSegment s)
    {
        if(i == 0)
        {
            s.getHex().setFill(Color.OLDLACE);
            s.getPol3().setFill(colorList[colorIndex]);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol2().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
        }
        else if(i == 1)
        {
            s.getPol1().setFill(Color.OLDLACE);
            s.getPol2().setFill(Color.OLDLACE);
            s.getPol3().setFill(Color.OLDLACE);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol6().setFill(Color.OLDLACE);
            s.getHex().setFill(Color.OLDLACE);
            s.getPol5().setFill(colorList[colorIndex]);
        }
        else if(i == 2)
        {
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol3().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
            s.getPol2().setFill(Color.OLDLACE);
            s.getPol5().setFill(Color.OLDLACE);
        }
        else if(i == 3)
        {
            
            s.getPol5().setFill(colorList[colorIndex]);
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
            s.getPol2().setFill(Color.OLDLACE);
            s.getPol3().setFill(Color.OLDLACE);   
        }
        else if(i == 4)
        {
            s.getPol2().setFill(colorList[colorIndex]);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
            s.getPol1().setFill(Color.OLDLACE);
            s.getPol3().setFill(Color.OLDLACE);
            s.getPol6().setFill(Color.OLDLACE);
        }
        else if(i == 5)
        {
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol2().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
            s.getPol3().setFill(Color.OLDLACE);
            s.getPol4().setFill(Color.OLDLACE);
        }
        else if(i == 6)
        {
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol2().setFill(colorList[colorIndex]);
            s.getPol3().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
            s.getPol4().setFill(Color.OLDLACE);
        }
        else if(i == 7)
        {
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getPol2().setFill(Color.OLDLACE);
            s.getPol3().setFill(Color.OLDLACE);
            s.getPol6().setFill(Color.OLDLACE);
            s.getHex().setFill(Color.OLDLACE);
        }
        else if(i == 8)
        {
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol2().setFill(colorList[colorIndex]);
            s.getPol3().setFill(colorList[colorIndex]);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
        }
        else if(i == 9)
        {
            s.getPol1().setFill(colorList[colorIndex]);
            s.getPol2().setFill(colorList[colorIndex]);
            s.getPol3().setFill(Color.OLDLACE);
            s.getPol4().setFill(colorList[colorIndex]);
            s.getPol5().setFill(colorList[colorIndex]);
            s.getPol6().setFill(colorList[colorIndex]);
            s.getHex().setFill(colorList[colorIndex]);
        }
    }
    public void setUpNumbers()
    {
       this.second1 = sec1seg.makeNumber();
       this.second2 = sec2seg.makeNumber();
       
       this.minute1 = min1seg.makeNumber();
       this.minute2 = min2seg.makeNumber();
       
       this.hour1 = hour1seg.makeNumber();
       this.hour2 = hour2seg.makeNumber();
       
       this.colon1 = colon1seg.makeColon();
       this.colon2 = colon2seg.makeColon();
       
       time = new HBox(this.hour1seg.getHeight(),ampm,hour1,hour2,colon1, minute1, minute2,colon2,second1, second2);
    }
    public void setUpDate()
    {
        
        this.month1 = month1seg.makeNumber();
        this.month2 = month2seg.makeNumber();
        
        this.day1 = day1seg.makeNumber();
        this.day2 = day2seg.makeNumber();
        
        this.year1 = year1seg.makeNumber();
        this.year2 = year2seg.makeNumber();
        this.year3 = year3seg.makeNumber();
        this.year4 = year4seg.makeNumber();
        
        this.hifen1 = hifen1seg.makeNumber();
        this.hifen2 = hifen2seg.makeNumber();
        
        hifen1seg.getPol1().setFill(Color.WHITE);
        hifen1seg.getPol2().setFill(Color.WHITE);
        hifen1seg.getPol3().setFill(Color.WHITE);
        hifen1seg.getPol4().setFill(Color.WHITE);
        hifen1seg.getPol5().setFill(Color.WHITE);
        hifen1seg.getPol6().setFill(Color.WHITE);
        hifen1seg.getHex().setFill(colorList[colorIndex]);
        
        hifen2seg.getPol1().setFill(Color.WHITE);
        hifen2seg.getPol2().setFill(Color.WHITE);
        hifen2seg.getPol3().setFill(Color.WHITE);
        hifen2seg.getPol4().setFill(Color.WHITE);
        hifen2seg.getPol5().setFill(Color.WHITE);
        hifen2seg.getPol6().setFill(Color.WHITE);
        hifen2seg.getHex().setFill(colorList[colorIndex]);
        
        
        date.getChildren().addAll(month1,month2,hifen1, day1, day2,hifen2, year1, year2, year3, year4);
        date.setAlignment(Pos.CENTER);
    }
    public void setUpSeconds(int n)
    {
        int i = 1;

        while(i < 60)
        {
           
           if(i <= n)
           { 
                Circle testCir = new Circle(7, colorList[colorIndex]);
                testCir.radiusProperty().bind(Bindings.min(mainPane.widthProperty().divide(70),
                                         mainPane.heightProperty().divide(70)));
                secondContainer.getChildren().add(testCir);
           }  
           else
           {
                Circle testCir = new Circle(7, Color.OLDLACE);
                testCir.radiusProperty().bind(Bindings.min(mainPane.widthProperty().divide(70),
                                         mainPane.heightProperty().divide(70)));
                secondContainer.getChildren().add(testCir);
           }
           i++;
        }    
        secondContainer.prefWidthProperty().bind(time.widthProperty());
        secondContainer.setPadding(new Insets(0, 0, 20, 0));
    }
    public void initalizeDots()
    {
        int i = 1;
        int x = now.getSecond();
        
        while (i < 60)
        {
            if(i <= counterSec)
           { 
                Circle testCir = new Circle(7, colorList[colorIndex]);
                testCir.radiusProperty().bind(Bindings.min(mainPane.widthProperty().divide(70),
                                         mainPane.heightProperty().divide(70)));
                secondContainer.getChildren().add(testCir);
           }  
           else
           {
                Circle testCir = new Circle(7, Color.OLDLACE);
                testCir.radiusProperty().bind(Bindings.min(mainPane.widthProperty().divide(70),
                                         mainPane.heightProperty().divide(70)));
                secondContainer.getChildren().add(testCir);
           }
            i++;
        }
        secondContainer.prefWidthProperty().bind(time.widthProperty());
        secondContainer.setPadding(new Insets(0, 0, 20, 0));
    }
    public void setUpAmPm(int hour)
    {
        if(counterHourDays >= 12)
        {
            am.setFill(Color.OLDLACE);
            pm.setFill(colorList[colorIndex]);
        }
        else
        {
            am.setFill(colorList[colorIndex]);
            pm.setFill(Color.OLDLACE);
        }
        am.setFont(Font.font("Arial", 30));
        pm.setFont(Font.font("Arial", 30));
        ampm.getChildren().addAll(am, pm);


        Scale scale = new Scale();
        scale.xProperty().bind(ampm.widthProperty().divide(50));
        scale.yProperty().bind(ampm.heightProperty().divide(200));
        am.getTransforms().add(scale);
        pm.getTransforms().add(scale);

        am.xProperty().bind(hour1.widthProperty().divide(10));
        am.yProperty().bind(hour1.heightProperty().divide(1.25));

        pm.xProperty().bind(hour1.widthProperty().divide(10));
        pm.yProperty().bind(hour1.heightProperty().divide(3));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
