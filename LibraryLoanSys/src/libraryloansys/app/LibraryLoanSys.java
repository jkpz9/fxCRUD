package libraryloansys.app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import libraryloansys.controller.Controller;
import libraryloansys.dao.BookDAO;
import libraryloansys.dao.impl.DerbyBookDAO;
import libraryloansys.service.Library;

/**
 *
 * @author hayes96
 */
public class LibraryLoanSys extends Application {
    
    private Library buildLib()
    {
        return new Library(new DerbyBookDAO());
    }
    
    private Controller buildController(Stage stage)
    {
        return new Controller(buildLib(),stage);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        loader.setControllerFactory(t -> buildController(primaryStage));
        
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
    
    public static void main(String[] args) throws Exception {
        launch(args);
        
//        BookDAO bookDAO = new DerbyBookDAO();
//        
//        bookDAO.connect();
//        
//        Library lib = new Library(bookDAO);
//        
//        lib.addNewBook("Book 1", "KING HAPPER_HELEN VJ", 2015);
//        lib.addNewBook("Book 2", "ALICE RXS_HELEN VJ", 2016);
//        lib.addNewBook("Book 3", "KING HAPPER_HELEN VJ", 2017);
//        lib.addNewBook("Book 5", "ALICE RXS_HELEN VJ", 2016);
//        lib.addNewBook("Book 10", "JONH DOE_HELEN PRX", 2018);
//        lib.addNewBook("Book 100", "ALICE RXS_HELEN VJ", 2016);
//        lib.addNewBook("Book 102", "KING HAPPER_HELEN VJ", 2016);
//        lib.addNewBook("Book 105", "KING DIXON_ALICE RXS", 2018);
//        lib.addNewBook("Book 108", "KING DIXON_HELEN VJ", 2014);
//        lib.addNewBook("Book 112", "KING DIXON_HELEN VJ", 2013);
//        lib.addNewBook("Book 124", "KING DIXON_HELEN VJ", 2015);
//        lib.addNewBook("Book 145", "KING HAPPER_HELEN VJ", 2013);
//        
//        lib.close();
//        System.exit(0);
        
    }
    
}
