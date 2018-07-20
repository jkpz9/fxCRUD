package libraryloansys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryloansys.model.Book;
import libraryloansys.optional.BookSearchType;
import libraryloansys.service.Library;

public class Controller {
    
    @FXML
    private ChoiceBox<BookSearchType> choiceBox;
    
    @FXML
    private ListView<Book> listView;
    
    private Library model;
    
    public Controller(Library model, Stage stage) 
    {
        this.model = model; 
        
        stage.setOnCloseRequest(e -> model.close());
    }
    
    public void initialize()
    {
        choiceBox.getItems().setAll(BookSearchType.values());
        choiceBox.getSelectionModel().selectFirst();
    }
    
    
    public void onSearch(ActionEvent e)
    {
        String param = ((TextField)e.getSource()).getText();
        
        boolean setAll = listView.getItems().setAll(model.search(choiceBox.getValue(), param));
    }
    
    public void onLoan()
    {
        model.loanBook(listView.getSelectionModel().getSelectedItems().get(0).getId());
    }
    
    
    public void onReturn()
    {
        model.returnBook(listView.getSelectionModel().getSelectedItems().get(0).getId());
    }
    
}
