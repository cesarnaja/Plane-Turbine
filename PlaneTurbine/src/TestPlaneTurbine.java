import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;


public class TestPlaneTurbine extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) 
	{
		NewPlaneTurbine planeTurbine = new NewPlaneTurbine();
		
		planeTurbine.setOnKeyPressed(e -> 
		{
			planeTurbine.play();
			if(e.getCode() == KeyCode.UP) 
			{	
				planeTurbine.increaseSpeed();
			}
			else if(e.getCode() == KeyCode.DOWN) 
			{
				planeTurbine.decreaseSpeed();
			}
			
		});
		planeTurbine.setOnKeyReleased(e -> 
		{
			planeTurbine.decreaseSpeed();
		});
		planeTurbine.requestFocus();
		
		
		
		Scene scene = new Scene(planeTurbine , 760 , 440);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Plane Turbine");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		planeTurbine.requestFocus();
	}

}



