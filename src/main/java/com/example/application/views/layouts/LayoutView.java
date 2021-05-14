package com.example.application.views.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.login.AbstractLogin.ForgotPasswordEvent;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import com.example.application.views.main.MainView;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.data.User;
import com.example.application.services.UserService;

@Route(value = "layout", layout = MainView.class)
@PageTitle("Layout")
public class LayoutView extends VerticalLayout {
	@Autowired
	private UserService userService;
	private FormLayout userLayout;
	private Binder<User> binder = new Binder<User>();
	
	private TextField title;
	private TextField firstName;
	private TextField lastName;
	
	private User user;
	
	public LayoutView() {
		super();
		
    	this.setSizeFull();
    	this.setPadding(false);	
    	    	
		// add two rows
		//add(createSplitLayout());
		//add(createLoginLayout());
		add(createFormLayout());
		
		//this.userService = userService;
		binder.bind(title, User::getTitle, User::setTitle);
		binder.bind(firstName, User::getFirstName, User::setFirstName);
		binder.bind(lastName, User::getLastName, User::setLastName);
		
    	binder.bindInstanceFields(this);
	}
	
	private Component createSplitLayout() {
		SplitLayout layout = new SplitLayout(
		        new Label("First content component"),
		        new Label("Second content component"));
		layout.setOrientation(Orientation.VERTICAL);
		layout.setSplitterPosition(80);
		layout.setPrimaryStyle("background", "red");
		layout.setSecondaryStyle("background", "green");
		
		layout.setSizeFull();
		
		return layout;
	}
	
	private Component createLoginLayout() {
		LoginForm component = new LoginForm();	
		component.setForgotPasswordButtonVisible(false);
		component.addLoginListener(e -> {
		    boolean isAuthenticated = authenticate(e);
		    if (isAuthenticated) {		    	
		    	UI.getCurrent().navigate("checkbox");		        
		    } else {
		        component.setError(true);
		    }
		});

		return component;		
	}
	
	private boolean authenticate(LoginEvent e) {
		if (e.getUsername().equals("masalinas") && e.getPassword().equals("!Thingtrack2010"))
			return true;
		
		return false;
		
	}
	
	private Component createFormLayout() {
		userLayout = new FormLayout();
		userLayout.setWidth("700px");
				
		title = new TextField("Title");
		firstName = new TextField("First name"); 
		lastName = new TextField("Last name");
						
		userLayout.add(title, firstName, lastName);
		
		userLayout.setResponsiveSteps(
		        new ResponsiveStep("1px", 1),
		        new ResponsiveStep("600px", 2),
		        new ResponsiveStep("700px", 3));
				
		Button button = new Button("Load User");
		button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				user = userService.getMockUser();
				
				binder.readBean(user);				
			}
		});
		
		add(button);
		
		Button button02 = new Button("Get User");
		button02.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				
				try {
					binder.writeBean(user);
					
					Notification.show(user.getFirstName());
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				
			}
		});
		
		add(button02);
		
		return userLayout;
	}
}
