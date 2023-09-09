package JavaFx.SubComponents.newExeTab;

import Defenitions.EnvPropertyDefinitionDTO;
import Defenitions.EnvironmentDefinitionDTO;
import JavaFx.SubComponents.body.BodyController;
import definition.property.api.PropertyType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NewExeScreenController {
    private BodyController bodyController;

    @FXML
    private ListView<String> entList;

    @FXML
    private TextField entLabel;

    @FXML
    private Spinner<Integer> popSpinner;

    @FXML
    private ListView<String> envList;

    @FXML
    private TextField envLabel;

    @FXML
    private TextField envValue;

    @FXML
    private TextField rangeLabel;

    @FXML
    private TextField typeLabel;

    @FXML
    private Button saveEntPopBtn;

    @FXML
    private Button saveEnvVarBtn;

    @FXML
    private Button startBtn;

    Map<String,Integer> entValMap = new HashMap<>();
    Map<String,String> envValMap = new HashMap<>();


    public void setBodyController(BodyController bodyController) {
        this.bodyController = bodyController;
    }

    public void initSpinner() {
        Integer maxPop = bodyController.getMaxPop();
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxPop, 0);
        popSpinner.setValueFactory(spinnerValueFactory);
    }

    private void validateAndSetValue(SpinnerValueFactory<Integer> spinnerValueFactory,String selectedItem) {
        String newValue = popSpinner.getEditor().getText();
        if (!newValue.matches("\\d*")) {
            bodyController.showAlert("Invalid Input!", Alert.AlertType.ERROR,"Error");
            popSpinner.getValueFactory().setValue(0);
        } else {
            if (newValue.isEmpty()) {
                //ignore
            } else {
                // Input is valid, update the spinner's value
                Integer intValue = Integer.parseInt(newValue);
                if (bodyController.checkPopulation(intValue,selectedItem)) {
                    spinnerValueFactory.setValue(intValue);
                } else {
                    bodyController.showAlert("Value out of range!\nSpace left: " +
                            bodyController.getSpaceLeft(selectedItem), Alert.AlertType.ERROR,"Error");
                    popSpinner.getValueFactory().setValue(0);
                }
            }
        }
    }


    public void onPopSaveBtnClicked()
    {
        String selectedItem = entList.getSelectionModel().getSelectedItem();
        validateAndSetValue(popSpinner.getValueFactory(),selectedItem);
        bodyController.setEntPop(selectedItem,popSpinner.getValue());
        entValMap.put(selectedItem, popSpinner.getValue());
        bodyController.showAlert(selectedItem + ": Population is Set to:" + popSpinner.getValue(), Alert.AlertType.INFORMATION,"Success");

    }

    public void initEntList() {
        Collection<String> EntitiesDTO = bodyController.getEntityDTO().keySet();
        entList.getItems().addAll(EntitiesDTO);
        EntitiesDTO.forEach(entName ->{
            entValMap.put(entName,0);
        });
    }

    public void initEnvList() {
        Collection<String> EnvDTO = bodyController.getEnvDTO().getEnvProps().keySet();
        envList.getItems().addAll(EnvDTO);
        EnvDTO.forEach(envName ->{
            envValMap.put(envName,"");
        });
    }

    public void initNewExeScreen() {
        clearNewExeScreen();
        initEntList();
        initEnvList();
        initSpinner();
    }

    public void clearNewExeScreen() {
        entList.getItems().clear();
        envList.getItems().clear();
    }

    public void selectedEnt() {
        String selectedItem = entList.getSelectionModel().getSelectedItem();
        entLabel.setText(selectedItem);
        popSpinner.setDisable(false);
        popSpinner.getValueFactory().setValue(entValMap.get(selectedItem));
    }

    public void selectedEnv() {
        String selectedItem = envList.getSelectionModel().getSelectedItem();
        EnvPropertyDefinitionDTO envVarDTO = bodyController.getEnvDTO().getEnvProps().get(selectedItem);
        envLabel.setText(selectedItem);
        typeLabel.setText(envVarDTO.getType().toString());
        if (envVarDTO.getRange() != null)
            rangeLabel.setText(envVarDTO.getRange().getRangeFrom() + "-" + envVarDTO.getRange().getRangeTo());
        else
            rangeLabel.setText("No Range To Display");
        envValue.setDisable(false);
        envValue.setText(envValMap.get(selectedItem));

    }
    public void onSaveEnvVarClicked()
    {
        String selectedItem = envList.getSelectionModel().getSelectedItem();
        String userEnvInput  = envValue.getText().trim();
        EnvPropertyDefinitionDTO envVarDTO = bodyController.getEnvDTO().getEnvProps().get(selectedItem);
        Object userValue;
        switch (envVarDTO.getType())
        {
            case DECIMAL:
                if(bodyController.isValidInput(envVarDTO,userEnvInput))
                {
                    userValue = PropertyType.DECIMAL.parse(userEnvInput);
                    bodyController.addEnvVarToActiveEnv(userValue,envVarDTO.getName());
                    envValMap.put(selectedItem,userEnvInput);
                    bodyController.showAlert(selectedItem + "is Set!\nValue is:" + userEnvInput, Alert.AlertType.INFORMATION,"Success");
                }
                else
                {
                    bodyController.showAlert("Invalid Input!", Alert.AlertType.ERROR,"Error");
                }
                break;
            case FLOAT:
                if(bodyController.isValidInput(envVarDTO,userEnvInput))
                {
                    userValue = PropertyType.FLOAT.parse(userEnvInput);
                    bodyController.addEnvVarToActiveEnv(userValue,envVarDTO.getName());
                    envValMap.put(selectedItem,userEnvInput);
                    bodyController.showAlert(selectedItem + "is Set!\nValue is:" + userEnvInput, Alert.AlertType.INFORMATION,"Success");
                }
                else
                {
                    bodyController.showAlert("Invalid Input!", Alert.AlertType.ERROR,"Error");
                }
                break;
            case BOOLEAN:
                if(bodyController.isValidInput(envVarDTO,userEnvInput))
                {
                    userValue = PropertyType.BOOLEAN.parse(userEnvInput);
                    bodyController.addEnvVarToActiveEnv(userValue,envVarDTO.getName());
                    envValMap.put(selectedItem,userEnvInput);
                    bodyController.showAlert(selectedItem + "is Set!\nValue is:" + userEnvInput, Alert.AlertType.INFORMATION,"Success");
                }
                else
                {
                    bodyController.showAlert("Invalid Input!", Alert.AlertType.ERROR,"Error");
                }
                break;
            case STRING:
                if(bodyController.isValidInput(envVarDTO,userEnvInput))
                {
                    bodyController.addEnvVarToActiveEnv(userEnvInput,envVarDTO.getName());
                    envValMap.put(selectedItem,userEnvInput);
                    bodyController.showAlert(selectedItem + "is Set!\nValue is:" + userEnvInput, Alert.AlertType.INFORMATION,"Success");
                }
                else
                {
                    bodyController.showAlert("Invalid Input!", Alert.AlertType.ERROR,"Error");
                }
                break;
        }

    }
    public void onStartBtnClicked()
    {
        EnvironmentDefinitionDTO myEnvDef = bodyController.getEnvDTO();
        for(String envName: myEnvDef.getEnvProps().keySet())
            bodyController.initRandomEnvVars(envName);
        bodyController.runSimulation();
    }
    public void onClearBtnClicked()
    {
        entList.getSelectionModel().clearSelection();
        envList.getSelectionModel().clearSelection();
        popSpinner.setDisable(true);
        envValue.setDisable(true);
        popSpinner.getValueFactory().setValue(0);
        entLabel.clear();
        envLabel.clear();
        envValue.clear();
        typeLabel.clear();
        rangeLabel.clear();
        initNewExeScreen();
    }
}
