package com.example.application.views.example;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.cookieconsent.CookieConsent.Position;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

import com.vaadin.componentfactory.EnhancedDateRangePicker;

@Route(value = "example", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Example")
public class ExampleView extends VerticalLayout {
    public ExampleView() {
    	this.setSizeFull();
    	this.setPadding(true);
    	
    	// add two rows
    	add(createSimpleDatePicker(), createList());            	
    }

    private Component createSimpleDatePicker() {
    	HorizontalLayout row = new HorizontalLayout();
    	
    	// range datepicker
        EnhancedDateRangePicker datePicker = new EnhancedDateRangePicker();
        
        // button component
        Button dateButton = new Button("Get Range Date");
        dateButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				if (datePicker.getValue() != null) {
					Notification.show("Date from: " + 
									  datePicker.getValue().getStartDate().toString() + " - "
									  + "Date to: " +
									  datePicker.getValue().getEndDate().toString());
				}
			}
		});
        
        // add components to the horizontal layout
        row.add(datePicker, dateButton);
        
        return row;        
    }
    
    private Component createList() {
    	HorizontalLayout row = new HorizontalLayout();
    	
    	ListBox<String> listBox = new ListBox<>();
    	listBox.setItems("Option one", "Option two", "Option three");
    	listBox.setValue("Option one");
    	listBox.addValueChangeListener(new ValueChangeListener<ValueChangeEvent<String>>() {
			@Override
			public void valueChanged(ValueChangeEvent<String> event) {
				Notification.show(event.getValue());				
			}
		});
    	
    	Button stringButton = new Button("Get Value");
    	stringButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				Notification.show(listBox.getValue(), 2000, Notification.Position.TOP_END);				
			}
		});
    	
        row.add(listBox, stringButton);
        
        return row;    
    }
}
