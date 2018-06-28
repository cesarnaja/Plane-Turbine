import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import javafx.animation.KeyFrame;


public class NewPlaneTurbine extends BorderPane{

	// ----------- Properties for plane turbine
	private StackPane turbineHolder = new StackPane();
	private StackPane bladeRotorHolder = new StackPane();
	private Circle outerShell  = new Circle();
	private Circle innerShell = new Circle();	
	private Circle rotor = new Circle();
	private Polygon blade = new Polygon();
		
	// ----------- Properties for status bar
	private BorderPane statusPaneHolder = new BorderPane();
	private Rectangle powerBar = new Rectangle();
	private Text rate = new Text();
	private HBox ratePane = new HBox();
	private VBox powerPane = new VBox(); 
	DoubleProperty startY = new SimpleDoubleProperty(0);
	
	// ----------- Other
	private final double TURBINEPANEWIDTH = 400; 
	private final double TURBINEPANEHEIGHT = 400;
	private Timeline animation = new Timeline();
	
	
	// ----------- Constructor
	public NewPlaneTurbine() 
	{
		//Background color for program
		//super.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)");
		super.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #c9e0e8, #1a2d66)");
		
		
		turbineHolder = paintTurbine();
		
		statusPaneHolder = paintStatusBar();
		statusPaneHolder.setMaxWidth(200);
		statusPaneHolder.setMaxHeight(TURBINEPANEHEIGHT);
					
		super.setCenter(turbineHolder);
		super.setRight(statusPaneHolder);
		
		
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(50) , e -> rotate()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	
	// ------------- Methods
	private StackPane paintTurbine() 
	{	
		StackPane holder = new StackPane();
		holder.setMaxWidth(TURBINEPANEWIDTH);
		holder.setMaxHeight(TURBINEPANEHEIGHT);
		
		// ********* Paint Outer Shell ***************	
		outerShell = paintOuterShell();		
		holder.getChildren().add(outerShell);
		
		// ********* Paint Inner Shell ***************	
		innerShell = paintInnerShell();		
		holder.getChildren().add(innerShell);
		
		// *********** Paint Rotor / Blades **********
		
		bladeRotorHolder = paintBladeRotor();
		holder.getChildren().add(bladeRotorHolder);
		return holder; 
	}
	private StackPane paintBladeRotor() 
	{
		StackPane bladeRotorHolder = new StackPane();
		Pane rotorBladePane = new Pane();
		
		Circle circle = new Circle(TURBINEPANEWIDTH / 2, TURBINEPANEHEIGHT / 2, 113);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(2);
		
		//Lets draw the line object
		
		double midPointX;
		double midPointY;
		
		//**********************************************************
		for(int i = 0; i < 360 ; i += 12) 
		{
					
			double x = circle.getCenterX() + circle.getRadius() * Math.cos(i * Math.PI / 180);
			double y = circle.getCenterY() + circle.getRadius() * Math.sin(i * Math.PI / 180);
			
			double nextX = circle.getCenterX() + circle.getRadius() * Math.cos((i + 10.8) * Math.PI / 180);
			double nextY = circle.getCenterY() + circle.getRadius() * Math.sin((i + 10.8) * Math.PI / 180);
			
			

			midPointX =((8 * nextX) + (1 * circle.getCenterX())) / 9;
			midPointY =((8 * nextY) + (1 * circle.getCenterY())) / 9;
			
			
			blade = new Polygon();
			
			ObservableList<Double> list = blade.getPoints();
			list.add(x);
			list.add(y);
			list.add(midPointX);
			list.add(midPointY);
			list.add(circle.getCenterX());
			list.add(circle.getCenterY());
			

			blade.setFill(Color.rgb(121, 140, 147));
			blade.setStroke(Color.BLACK);
			
			rotorBladePane.getChildren().add(blade);			
		}
		
		File file = new File("src/Images/Spiral.jpg");
		if(file.exists()) 
		{
			Image image = new Image("Images/Spiral.jpg");
			
			rotor.setRadius(27.5);
			rotor.setCenterX(TURBINEPANEWIDTH / 2);
			rotor.setCenterY(TURBINEPANEHEIGHT / 2);
			rotor.setStroke(Color.BLACK);
			rotor.setFill(new ImagePattern(image));
		}
		else 
		{
			System.out.println(file.getName() + " does not exist");
		}	
			
		rotorBladePane.getChildren().add(rotor);
		
		bladeRotorHolder.getChildren().add(rotorBladePane);
		
		return bladeRotorHolder;
	}
	private Circle paintOuterShell() 
	{
		Circle tempOuterShell = new Circle();
					
		Stop[] stops = { new Stop(0, Color.DARKSLATEGRAY), new Stop(1, Color.ALICEBLUE)}; 		
		RadialGradient rg1 = new RadialGradient(0, 0, 0, 0, -53, false, CycleMethod.REPEAT, stops);
		
		tempOuterShell.setFill(rg1);
		tempOuterShell.setRadius(150);
		tempOuterShell.setStroke(Color.BLACK);
		tempOuterShell.setStrokeWidth(1.5);
		
		return tempOuterShell;	
	}
	private Circle paintInnerShell() 
	{
		Circle tempInnerShell = new Circle();
		InnerShadow innerShadow = new InnerShadow();
		innerShadow.setOffsetX(15);
		innerShadow.setOffsetY(2);
		innerShadow.setColor(Color.web("0x3b596d"));
		
		tempInnerShell.setRadius(115);
		tempInnerShell.setStroke(Color.BLACK);
		tempInnerShell.setStrokeWidth(2);
		tempInnerShell.setEffect(innerShadow);
		tempInnerShell.setFill(Color.rgb(35, 35, 40));
		
		return tempInnerShell;
	}
	private BorderPane paintStatusBar() 
	{
		BorderPane statusBar = new BorderPane();
			
		// Rate Status ON TOP
		rate.setFont(Font.font("AppleMyungjo", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
		
		ratePane.getChildren().add(rate);
		//ratePane.setStyle("-fx-background-color : lightblue ; -fx-border-color: black");
		ratePane.setPadding(new Insets(20, 90, 20, 90));
		
		statusBar.setTop(ratePane);
			
		//The Power Level ON CENTER
		
		//powerPane.setStyle("-fx-background-color : lightcyan ; -fx-border-color: black");
		powerPane.setPadding(new Insets(15));
		powerPane.setAlignment(Pos.BOTTOM_CENTER);
		powerPane.setSpacing(8);

		powerBar.setWidth(50);
		powerBar.heightProperty().bind(animation.rateProperty().multiply(1.5));
		powerBar.setStroke(Color.BLACK);
		
		powerPane.getChildren().add(powerBar);
		
		statusBar.setCenter(powerPane);
		return statusBar; 	
	}		
	public void play() 
	{
		animation.play();
	}
	public void pause() 
	{
		animation.pause();
	}
	public void increaseSpeed() 
	{
		animation.setRate(animation.getRate() < 200 ? animation.getRate() + 1.0 : 200);
	}
	public void decreaseSpeed() 
	{
		animation.setRate(animation.getRate() > 1 ? animation.getRate() - 1.0 : 1);
	}
	
	public void rotate() 
	{		
		int x = 1;
		bladeRotorHolder.setRotate(bladeRotorHolder.getRotate() - x);
		x += 1;		
		
		rate.setText("Speed : " + String.format("%4.0f", animation.getCurrentRate()));
		
		
		startY.bind(animation.currentRateProperty().multiply(0.01));
		
		Stop[] stops = { new Stop(0, Color.DARKSLATEGRAY), new Stop(1, Color.ALICEBLUE)}; 
		LinearGradient lg1 = new LinearGradient(0, startY.doubleValue(), 0, 0, true, CycleMethod.NO_CYCLE, stops);
		
		powerBar.setFill(lg1);
		rate.setFill(lg1);
				
	}

	
}
