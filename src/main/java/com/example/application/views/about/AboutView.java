package com.example.application.views.about;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import com.example.application.components.PhoneNumberField;
import com.example.application.views.main.MainView;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends HorizontalLayout {

    public AboutView() {
        addClassName("about-view");
        
        createPhoneComponent();
    }
    
    private void createPhoneComponent() {
    	PhoneNumberField phoneNumberField = new PhoneNumberField();
    	
    	phoneNumberField.setValue("+99 88999999 1234666");
    	
    	add(phoneNumberField);
    	
    	Button button = new Button("Get Phone");
    	button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				Notification.show(phoneNumberField.getValue());				
			}
		});
    	
    	add(button);
    }

}
