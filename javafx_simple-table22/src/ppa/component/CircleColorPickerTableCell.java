package ppa.component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValueBase;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleColorPickerTableCell<T> extends TableCell<T, Circle> {
	
	private Circle circle;
	private ColorPicker colorPicker;
	
    public CircleColorPickerTableCell() {
        setAlignment(Pos.CENTER);
        this.circle = new Circle(10.0);
        this.circle.setVisible(true);
        this.colorPicker=new ColorPicker((Color)this.circle.getFill());
        
    }
    
    public Circle getCircle(){
    	return this.circle;
    }

    
    @Override
    protected void updateItem(Circle value, boolean isEmpty) {
        super.updateItem(value, isEmpty);
        if (isEmpty) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(value);
        }
    }

//    @Override
//    public void startEdit() {
//        super.startEdit();
//        final ColorPicker colorPicker = new ColorPicker();
//        colorPicker.setValue(Color.CORAL);
//        colorPicker.setOnAction(t -> circle.setFill(colorPicker.getValue()));
//        setGraphic(colorPicker);
//        
//    }
    @Override
    public void startEdit() {
        super.startEdit();
        //ColorPicker colorPicker = new ColorPicker((Color) this.circle.getFill());
        setGraphic(colorPicker);
        EventHandler<KeyEvent> handler = key -> {
            if (key.getCode() == KeyCode.ENTER) {
                //commitEdit(colorPicker.getValue();
            	this.circle.setFill(colorPicker.getValue());
            	cancelEdit();
            	setGraphic(this.circle);
            } else if (key.getCode() == KeyCode.ESCAPE) {
            	setGraphic(this.circle);
                cancelEdit();
            }
        };
        colorPicker.setOnKeyTyped(handler);
        onKeyPressedProperty().setValue(handler);
        this.circle.onKeyPressedProperty().setValue(handler);
    }
    
//    @Override
//    public void commitEdit(Circle newValue) {
//        super.commitEdit(newValue);
//    }
    
}
