package controllers;

import colors.CMYKColor;
import colors.ColorObject;
import colors.ColorType;
import converters.NeededColorsConverter;
import exceptions.ColorOutOfBoundsException;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * The type Main controller.
 */
public class MainController extends Controller {

    @FXML
    private Label titleLabel;


    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField tvC;

    @FXML
    private TextField tvM;

    @FXML
    private TextField tvYcmyk;

    @FXML
    private TextField tvK;

    @FXML
    private TextField tvX;

    @FXML
    private TextField tvL;

    @FXML
    private TextField tvYxyz;

    @FXML
    private TextField tvZ;

    @FXML
    private TextField tvA;

    @FXML
    private TextField tvB;

    @FXML
    private Slider slC;

    @FXML
    private Slider slM;

    @FXML
    private Slider slYcmyk;

    @FXML
    private Slider slK;

    @FXML
    private Slider slX;

    @FXML
    private Slider slYxyz;

    @FXML
    private Slider slZ;

    @FXML
    private Slider slL;

    @FXML
    private Slider slA;

    @FXML
    private Slider slB;

    private NeededColorsConverter commonConverter = null;
    private final ArrayList<TextField> cmykTv = new ArrayList<>();
    private final ArrayList<TextField> xyzTv = new ArrayList<>();
    private final ArrayList<TextField> labTv = new ArrayList<>();
    private final ArrayList<Slider> cmykSl = new ArrayList<>();
    private final ArrayList<Slider> xyzSl = new ArrayList<>();
    private final ArrayList<Slider> labSl = new ArrayList<>();


    @Override
    public void init() {
        initTVs();
        initSls();

        colorPicker.setOnAction(inputMethodEvent -> {
            try {
                commonConverter.setColor(colorPicker.getValue());
                refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            commonConverter = new NeededColorsConverter();
            commonConverter.setColor(Color.WHITE);
            refresh();
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void initTVs() {
        cmykTv.add(tvC);
        cmykTv.add(tvM);
        cmykTv.add(tvYcmyk);
        cmykTv.add(tvK);

        xyzTv.add(tvX);
        xyzTv.add(tvYxyz);
        xyzTv.add(tvZ);

        labTv.add(tvL);
        labTv.add(tvA);
        labTv.add(tvB);

        for (TextField textField : cmykTv)
            textField.setOnAction(inputMethodEvent -> updateValueFromTv(textField, ColorType.CMYK));
        for (TextField textField : xyzTv)
            textField.setOnAction(inputMethodEvent -> updateValueFromTv(textField, ColorType.XYZ));
        for (TextField textField : labTv)
            textField.setOnAction(inputMethodEvent -> updateValueFromTv(textField, ColorType.LAB));
    }

    private void initSls() {
        cmykSl.add(slC);
        cmykSl.add(slM);
        cmykSl.add(slYcmyk);
        cmykSl.add(slK);

        xyzSl.add(slX);
        xyzSl.add(slYxyz);
        xyzSl.add(slZ);

        labSl.add(slL);
        labSl.add(slA);
        labSl.add(slB);

        for (Slider slider : cmykSl)
            slider.setOnMouseReleased(dragEvent -> updateValueFromSlider(slider,ColorType.CMYK));
        for (Slider slider : xyzSl)
            slider.setOnMouseReleased(dragEvent -> updateValueFromSlider(slider,ColorType.XYZ));
        for (Slider slider : labSl)
            slider.setOnMouseReleased(dragEvent -> updateValueFromSlider(slider,ColorType.LAB));
    }

    private void updateValueFromSlider(Slider slider, ColorType colorType){
        int index = switch (colorType){
            case CMYK -> cmykSl.indexOf(slider);
            case XYZ -> xyzSl.indexOf(slider);
            case LAB -> labSl.indexOf(slider);
            default -> 0;
        };
       updateValue(index,slider.getValue(),colorType);
    }

    private void updateValueFromTv(TextField textField,ColorType colorType){
        int index = switch (colorType){
            case CMYK -> cmykTv.indexOf(textField);
            case XYZ -> xyzTv.indexOf(textField);
            case LAB -> labTv.indexOf(textField);
            default -> 0;
        };

        try {
            updateValue(index, Double.parseDouble(textField.getText()), colorType);
        } catch (NumberFormatException e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void updateValue(int index, double value,ColorType colorType){
        try {
            var color = commonConverter.getWithType(colorType);
            if(color.isCountedPrecisely())
                showErrorMessage("Color "+colorType.name() +" counted precisely");
            color.setCoordinate(index,value);
            commonConverter.setColorObject(color);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                updatePicker();
                refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void refresh() {
        try {
            showErrorMessage("");
            updateXyz();
            updateCmyk();
            updateLab();
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void updateCmyk() throws Exception {
        CMYKColor cmykColor = commonConverter.getCmykColor();
        if(cmykColor.isCountedPrecisely())
            showErrorMessage("Color Cmyk counted precisely");
        updateColor(cmykColor, cmykTv, cmykSl);
    }

    private void updateXyz() throws Exception {
        try {
            var color = commonConverter.getXyzColor();
            if(color.isCountedPrecisely())
                showErrorMessage("Color XYZ counted precisely");
            updateColor(color, xyzTv, xyzSl);
        } catch (ColorOutOfBoundsException e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void updateLab() throws Exception {
        var color = commonConverter.getLabColor();
        if(color.isCountedPrecisely())
            showErrorMessage("Color Lab counted precisely");
        updateColor(color, labTv, labSl);
    }

    private void updateColor(ColorObject colorObject, ArrayList<TextField> tvFields, ArrayList<Slider> sliders) {
        var coordinates = colorObject.getDoubleCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            tvFields.get(i).setText(String.valueOf(coordinates[i]));
            sliders.get(i).setValue(coordinates[i]);
        }
    }

    private void updatePicker() throws Exception{
        var rgbColor = commonConverter.getRgbColor();
        if(rgbColor.isCountedPrecisely())
            showErrorMessage("Color for picker counted precisely");
        var coordinates = rgbColor.getIntCoordinates();
        colorPicker.setValue(Color.rgb(coordinates[0],coordinates[1],coordinates[2]));
    }

    @Override
    public void close() {

    }
}
