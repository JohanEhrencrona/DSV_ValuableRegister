//johan ehrencrona joeh2789

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Main extends Application {
    private BorderPane root = new BorderPane();
    private ArrayList<Valuable> allValuables = new ArrayList<>();
    private HBox bottomHBox = new HBox(5);


    @Override
    public void start(Stage primaryStage) {
        Label center = new Label("Värdesaker");
        center.setAlignment(Pos.TOP_CENTER);
        center.setMaxWidth(Double.MAX_VALUE);
        root.setTop(center);

        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomHBox.setMaxWidth(Double.MAX_VALUE);
        root.setBottom(bottomHBox);
        showMenuItems();

        Scene scene = new Scene(root, 550,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Sakregister");

    }

    private void showMenuItems(){
        bottomHBox.getChildren().add(new Label("Ny:"));
        MenuButton menuButton = new MenuButton("Välj en värdesak:");
        MenuItem smycke = new MenuItem("Smycke");
        MenuItem aktie = new MenuItem("Aktie");
        MenuItem apparat = new MenuItem("Apparat");
        menuButton.getItems().addAll(smycke, aktie, apparat);
        Button showButton = new Button("Visa");
        Button crashButton = new Button("Börskrasch");
        bottomHBox.getChildren().addAll(menuButton,showButton,crashButton);


        TextArea textArea = new TextArea();
        textArea.setMaxWidth(Double.MAX_VALUE);
        root.setLeft(textArea);


        final ToggleGroup group = new ToggleGroup();
        RadioButton nameRadioButton = new RadioButton("Namn");
        RadioButton valueRadioButton = new RadioButton("Värde");
        nameRadioButton.setToggleGroup(group);
        valueRadioButton.setToggleGroup(group);
        nameRadioButton.setSelected(true);


        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(30, 50, 50, 10));
        vbox.getChildren().add(new Label("Sortering"));
        vbox.getChildren().addAll(nameRadioButton,valueRadioButton);
        root.setRight(vbox);

        smycke.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Nytt Smycke");
                alert.setHeaderText("");

                GridPane grid = new GridPane();

                Label nameLabel = new Label("Namn");
                nameLabel.setStyle("-fx-font-weight: bold");
                TextField nameTextField = new TextField();
                grid.add(nameLabel, 1,1);
                grid.add(nameTextField, 2,1);

                Label valueLabel = new Label("Stenar:");
                valueLabel.setStyle("-fx-font-weight: bold");
                TextField valueTextField = new TextField();
                grid.add(valueLabel, 1,2);
                grid.add(valueTextField, 2,2);

                HBox goldHBox = new HBox();
                Label checkBoxLabel = new Label(" Av Guld");
                CheckBox goldCheck = new CheckBox();
                goldHBox.getChildren().addAll(goldCheck,checkBoxLabel);
                grid.add(goldHBox,2,3);

                alert.getDialogPane().setContent(grid);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    if (!nameTextField.getText().isEmpty() && (valueTextField.getText().matches("[0-9]+") && !valueTextField.getText().isEmpty())){
                        String material;
                        int stones = Integer.parseInt(valueTextField.getText().trim());
                        if (goldCheck.isSelected()) {
                            material = "Guld";
                        } else {
                            material = "Silver";
                        }
                        allValuables.add(new Jewellery(nameTextField.getText(), stones, material));
                    }
                    else{
                        errorInput();
                    }
                }
            }
        });

        aktie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ny aktie");
                alert.setHeaderText("");

                GridPane grid = new GridPane();

                Label nameLabel = new Label("Namn:");
                nameLabel.setStyle("-fx-font-weight: bold");
                TextField nameTextField = new TextField();
                grid.add(nameLabel, 1,1);
                grid.add(nameTextField, 2,1);

                Label numberLabel = new Label("Antal:");
                numberLabel.setStyle("-fx-font-weight: bold");
                TextField numberTextField = new TextField();
                grid.add(numberLabel, 1,2);
                grid.add(numberTextField, 2,2);

                Label valueLabel = new Label("Pris:");
                valueLabel.setStyle("-fx-font-weight: bold");
                TextField valueTextField = new TextField();
                grid.add(valueLabel, 1,3);
                grid.add(valueTextField, 2,3);

                alert.getDialogPane().setContent(grid);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    if (!nameTextField.getText().isEmpty() && !valueTextField.getText().isEmpty() && !numberTextField.getText().isEmpty() && (valueTextField.getText().matches("([0-9]*)\\.([0-9]*)") ||valueTextField.getText().matches("([0-9]*)")) && numberTextField.getText().matches("[0-9]+")){
                        int quantity = Integer.parseInt(numberTextField.getText().trim());
                        double rate = Double.parseDouble(valueTextField.getText().trim());
                        allValuables.add(new Stock(nameTextField.getText(), quantity, rate));
                    }
                    else{
                        errorInput();
                    }
                }
            }
        });

        apparat.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ny apparat");
                alert.setHeaderText("");

                GridPane grid = new GridPane();

                Label nameLabel = new Label("Namn:");
                nameLabel.setStyle("-fx-font-weight: bold");
                TextField nameTextField = new TextField();
                grid.add(nameLabel, 1,1);
                grid.add(nameTextField, 2,1);

                Label valueLabel = new Label("Pris:");
                valueLabel.setStyle("-fx-font-weight: bold");
                TextField valueTextField = new TextField();
                grid.add(valueLabel, 1,3);
                grid.add(valueTextField, 2,3);

                Label conditionLabel = new Label("Skick:");
                conditionLabel.setStyle("-fx-font-weight: bold");
                TextField conditionTextField = new TextField();
                grid.add(conditionLabel, 1,2);
                grid.add(conditionTextField, 2,2);

                alert.getDialogPane().setContent(grid);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    if (!nameTextField.getText().isEmpty() && !valueTextField.getText().isEmpty() && !conditionTextField.getText().isEmpty() && conditionTextField.getText().matches("([0-9]*)") && valueTextField.getText().matches("[0-9]+")){
                        int condition = Integer.parseInt(conditionTextField.getText().trim());
                        double value = Double.parseDouble(valueTextField.getText().trim());
                        allValuables.add(new Appliance(nameTextField.getText(), value, condition));
                    }
                    else{
                        errorInput();
                    }
                }
            }
        });

        showButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t){
                StringBuilder valuablesString = new StringBuilder("");
                checkSort(valueRadioButton,nameRadioButton);
                for (Valuable valuable : allValuables) {
                    if(valuable instanceof Jewellery) {
                        valuablesString.append(String.format("%s värde: %s av: %s antal ädelstenar: %s \n", valuable.getName(), valuable.getValuePlusVAT(), ((Jewellery) valuable).getMaterial(), ((Jewellery) valuable).getJewels()));
                    }
                    if (valuable instanceof Stock){
                        valuablesString.append(String.format("%s värde: %s antal: %s kurs: %s \n", valuable.getName(), valuable.getValuePlusVAT(), ((Stock) valuable).getQuantity(), ((Stock) valuable).getRate()));
                    }
                    if (valuable instanceof Appliance){
                        valuablesString.append(String.format("%s värde: %s inköpspris: %s skick: %s \n", valuable.getName(), valuable.getValuePlusVAT(), ((Appliance) valuable).getPrice(), ((Appliance) valuable).getWear()));
                    }
                }
                textArea.setText(String.valueOf(valuablesString));
            }
        });

        crashButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t){
                StringBuilder valuablesString = new StringBuilder("");
                for (Valuable valuable : allValuables) {
                    if (valuable instanceof Stock){
                        ((Stock) valuable).setRate(0);
                    }
                }
            }
        });
    }

    public void checkSort(RadioButton valueRadioButton, RadioButton nameRadioButton){
        if (valueRadioButton.isSelected()) {
            Collections.sort(allValuables, new ValueSort().reversed());
        }
        else if (nameRadioButton.isSelected()){
            Collections.sort(allValuables, new NameSort());
        }
    }

    public static void main(String[] args) {

        Application.launch(args);

    }

    private void errorInput(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fel!");
        error.setHeaderText("");
        error.setContentText("Felaktig inmatning!");
        error.showAndWait();
    }

    private void bottomButtons(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fel!");
        error.setHeaderText("");
        error.setContentText("Felaktig inmatning!");
        error.showAndWait();
    }


}



