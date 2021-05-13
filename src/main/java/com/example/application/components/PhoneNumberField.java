package com.example.application.components;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberField extends CustomField<String> {
    private final Select countryCode = new Select();
    private final TextField areaCode = new TextField();
    private final TextField subscriberCode = new TextField();

    public PhoneNumberField() {
        setLabel("Phone number");
        setHelperText(
              "Please, provide your phone number with all the details");
        countryCode.setItems("+358", "+46", "+34");
        countryCode.getStyle().set("width", "6em");
        countryCode.setPlaceholder("Code");
        areaCode.setPattern("[0-9]*");
        areaCode.setPreventInvalidInput(true);
        areaCode.setMaxLength(4);
        areaCode.setPlaceholder("Area");
        areaCode.getStyle().set("width", "5em");
        subscriberCode.setPattern("[0-9]*");
        subscriberCode.setPreventInvalidInput(true);
        subscriberCode.setMaxLength(8);
        subscriberCode.setPlaceholder("Subscriber");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(countryCode, areaCode, subscriberCode);
        
        add(horizontalLayout);
    }

    @Override
    protected String generateModelValue() {
        return countryCode.getValue() + "#" + areaCode.getValue() + "#" + subscriberCode.getValue();
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
        if (newPresentationValue != null) {
        	String[] values = newPresentationValue.split(" ");
        	
        	countryCode.setValue(values[0]);
            areaCode.setValue(values[1]);
            subscriberCode.setValue(values[2]);
        }
    }
}