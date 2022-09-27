package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage stg;
	@Override
	public void start(Stage primaryStage) {
		try {
			stg= primaryStage;
			primaryStage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
			Scene scene = new Scene(root,650,430);
			primaryStage.setTitle("Library System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxml) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	}
	
	public void changeSize(int w, int h ) throws IOException{
		stg.setWidth(w);
		stg.setHeight(h);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
