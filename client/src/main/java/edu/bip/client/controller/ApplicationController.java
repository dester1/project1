package edu.bip.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.bip.client.Application;
import edu.bip.client.entity.AuthorEntity;
import edu.bip.client.entity.BookEntity;
import edu.bip.client.entity.PublishingEntity;
import edu.bip.client.utils.HTTPUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;



public class ApplicationController {

    public static String api = "http://localhost:2825/api/v1/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public static ObservableList<AuthorEntity> authorsData = FXCollections.observableArrayList();
    public static ObservableList<PublishingEntity> publishersData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    public boolean redacting = false;

    //книги:
    @FXML
    public TableView<BookEntity> tableBooks;

    @FXML
    private TableColumn<BookEntity, String> bookName;
    @FXML
    private TableColumn<BookEntity, String> bookAuthor;
    @FXML
    private TableColumn<BookEntity, String> bookPublisher;
    @FXML
    private TableColumn<BookEntity, String> bookYear;
    @FXML
    private TableColumn<BookEntity, String> bookChapter;

    //авторы:
    @FXML
    private TableView<AuthorEntity> tableAuthors;

    @FXML
    private TableColumn<AuthorEntity, String> authorLastname;
    @FXML
    private TableColumn<AuthorEntity, String> authorName;
    @FXML
    private TableColumn<AuthorEntity, String> authorSurname;

    //издатели:
    @FXML
    private TableView<PublishingEntity> tablePublishers;

    @FXML
    private TableColumn<PublishingEntity, String> publisherCity;
    @FXML
    private TableColumn<PublishingEntity, String> publisherPublisher;

    @FXML
    private void initialize() throws Exception {
        getDataBooks();
        getDataAuthors();
        getDataPublishers();
        updateTableBooks();
        updateTableAuthors();
        updateTablePublishers();
    }

    private void updateTableBooks() throws Exception {
        bookName.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publishing"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("kind"));
        tableBooks.setItems(booksData);
    }
    private void updateTableAuthors() throws Exception {
        authorName.setCellValueFactory(new PropertyValueFactory<AuthorEntity, String>("name"));
        authorSurname.setCellValueFactory(new PropertyValueFactory<AuthorEntity, String>("surname"));
        authorLastname.setCellValueFactory(new PropertyValueFactory<AuthorEntity, String>("lastname"));
        tableAuthors.setItems(authorsData);
    }
    private void updateTablePublishers() throws Exception {
        publisherPublisher.setCellValueFactory(new PropertyValueFactory<PublishingEntity, String>("publisher"));
        publisherCity.setCellValueFactory(new PropertyValueFactory<PublishingEntity, String>("city"));
        tablePublishers.setItems(publishersData);
    }

    @FXML
    private void click_newBook() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        Application.showPersonEditDialog(tempBook, booksData.size()-1);
        addBook(tempBook);
    }
    @FXML
    private void click_newPublisher() throws IOException {
        PublishingEntity tempPublishing = new PublishingEntity();
        publishersData.add(tempPublishing);
        Application.showPublisherEditDialog(tempPublishing, publishersData.size()-1);
        addPublishing(tempPublishing);
    }

    @FXML
    private void click_removeBook() throws IOException {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println(selectedPerson.getId());
            System.out.println(http.delete(api, selectedPerson.getId()));
            booksData.remove(selectedPerson);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутсвует выбраный пользователь!");
            alert.setContentText("Пожалуйста, выберите пользователя из таблицы");
            alert.showAndWait();
        }
    }
    @FXML
    private void click_removePublisher() throws IOException {
        PublishingEntity selectedPublisher = tablePublishers.getSelectionModel().getSelectedItem();
        if (selectedPublisher != null) {
            System.out.println(selectedPublisher.getId());
            System.out.println(http.delete(api+"publisher/", selectedPublisher.getId()));
            publishersData.remove(selectedPublisher);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутсвует выбраный издатель!");
            alert.setContentText("Пожалуйста, выберите издателя из таблицы.");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_editBook() {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) Application.showPersonEditDialog(selectedPerson, booksData.indexOf(selectedPerson));
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутсвует выбраная книга!");
            alert.setContentText("Пожалуйста, выберите книгу из таблицы.");
            alert.showAndWait();
        }
    }
    @FXML
    private void click_editPublisher() {
        PublishingEntity selectedPublisher = tablePublishers.getSelectionModel().getSelectedItem();
        if (selectedPublisher != null) Application.showPublisherEditDialog(selectedPublisher, publishersData.indexOf(selectedPublisher));
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутсвует выбраный издатель!");
            alert.setContentText("Пожалуйста, выберите издателя из таблицы.");
            alert.showAndWait();
        }
    }

    public static void getDataBooks() throws Exception {
        String res = http.get(api,"book/all");
        System.out.println(res);

        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");

        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    public static void getDataAuthors() throws Exception {
        String res = http.get(api,"author/all");
        System.out.println(res);

        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");

        for (int i = 0; i < dataArr.size(); i++) {
            AuthorEntity newAuthor = gson.fromJson(dataArr.get(i).toString(), AuthorEntity.class);
            authorsData.add(newAuthor);
        }
    }

    public static void getDataPublishers() throws Exception {
        String res = http.get(api,"publisher/all");
        System.out.println(res);

        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");

        for (int i = 0; i < dataArr.size(); i++) {
            PublishingEntity newPublisher = gson.fromJson(dataArr.get(i).toString(), PublishingEntity.class);
            publishersData.add(newPublisher);
        }
    }

    public static void addBook(BookEntity book) throws IOException {
        System.out.println(book.toString());
        book.setId(null);
        http.post(api+"book/add", gson.toJson(book).toString());
    }

    public static void addPublishing(PublishingEntity publishing) throws IOException {
        System.out.println(publishing.toString());
        publishing.setId(null);
        http.post(api+"publisher/add", gson.toJson(publishing).toString());
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(api+"book/update", gson.toJson(book).toString());
    }
    public static void updateAuthor(AuthorEntity author) throws IOException {
        http.put(api+"author/update", gson.toJson(author).toString());
    }
    public static void updatePublisher(PublishingEntity publishing) throws IOException {
        http.put(api+"publisher/update", gson.toJson(publishing).toString());
    }
}