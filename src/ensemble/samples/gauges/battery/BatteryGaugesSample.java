/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemble.samples.gauges.battery;

import ensemble.Sample;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.gauge.Battery;
import jfxtras.labs.scene.control.gauge.SimpleBattery;


/**
 *
 * @author hansolo
 */
public class BatteryGaugesSample extends Sample{
    private final Battery BATTERY;
    private final SimpleBattery SIMPLE_BATTERY;
    private double charge;

    public BatteryGaugesSample() {
        super(600, 600);

        // Create some controls
        BATTERY = new Battery();
        BATTERY.setPrefSize(200, 200);
        charge = BATTERY.getChargingLevel();

        SIMPLE_BATTERY = new SimpleBattery();

        Button decrease = new Button("decrease charge");
        decrease.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                charge -= 0.05;
                BATTERY.setChargingLevel(charge);
                SIMPLE_BATTERY.setChargingLevel(charge);
                charge = BATTERY.getChargingLevel();
            }
        });

        Button increase = new Button("increase charge");
        increase.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                charge += 0.05;
                BATTERY.setChargingLevel(charge);
                SIMPLE_BATTERY.setChargingLevel(charge);
                charge = BATTERY.getChargingLevel();
            }
        });

        final ToggleGroup buttonGroup = new ToggleGroup();
        final RadioButton plug = new RadioButton("Plug indicator");
        plug.setToggleGroup(buttonGroup);
        plug.setSelected(true);
        plug.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (plug.isSelected()) {
                    BATTERY.setChargeIndicator(Battery.ChargeIndicator.PLUG);
                    SIMPLE_BATTERY.setChargeIndicator(SimpleBattery.ChargeIndicator.PLUG);
                }
            }
        });
        final RadioButton flash = new RadioButton("Flash indicator");
        flash.setToggleGroup(buttonGroup);
        flash.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flash.isSelected()) {
                    BATTERY.setChargeIndicator(Battery.ChargeIndicator.FLASH);
                    SIMPLE_BATTERY.setChargeIndicator(SimpleBattery.ChargeIndicator.FLASH);
                }
            }
        });

        final CheckBox charging = new CheckBox("charging");
        charging.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BATTERY.setCharging(charging.isSelected());
                SIMPLE_BATTERY.setCharging(charging.isSelected());
            }
        });

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(BATTERY, 1, 1);
        pane.add(SIMPLE_BATTERY, 2, 1);
        GridPane.setRowSpan(BATTERY, 5);
        pane.add(decrease, 3, 1);
        pane.add(increase, 3, 2);
        pane.add(plug, 3, 3);
        pane.add(flash, 3, 4);
        pane.add(charging, 3, 5);

        getChildren().add(pane);
    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }
}