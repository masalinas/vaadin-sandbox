package com.example.application.views.checkbox;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.example.application.data.Department;
import com.example.application.services.DepartmentService;
import com.example.application.views.main.MainView;

@Route(value = "checkbox", layout = MainView.class)
@PageTitle("Checkbocx View")
public class CheckboxView extends VerticalLayout {
	DepartmentService departmentService;
	
	public CheckboxView(DepartmentService departmentService) {
		this.departmentService = departmentService;
		
		add(CreateCheckbox(),CreateCheckboxGroup(), CreateCheckboxGroupWithDepartments(), CreateRadioButtonGroupWithDepartments());
	}
	
	private Component CreateCheckbox() {
		HorizontalLayout row = new HorizontalLayout();
		
		Checkbox checkbox = new Checkbox();
		checkbox.setLabel("Option");
		checkbox.setValue(false);
		checkbox.setIndeterminate(true);
				
		/*checkbox.addValueChangeListener(new ValueChangeListener<ValueChangeEvent<Boolean>>() {
			@Override
			public void valueChanged(ValueChangeEvent<Boolean> event) {
				if (event.getValue() == true)
					Notification.show("Is True");
				else
					Notification.show("Is False");				
			}
		});*/
		
		//checkbox.addValueChangeListener(event -> ShowNotification(event));
		checkbox.addValueChangeListener(this::ShowNotification);
		
		row.add(checkbox);
		
		return row;
	}
	
	private void ShowNotification(ValueChangeEvent<Boolean> event) {
		if (event.getValue() == true)
			Notification.show("Is True");
		else
			Notification.show("Is False");	
	}
	
	private Component CreateCheckboxGroup() {
		HorizontalLayout row = new HorizontalLayout();
		
		CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
		
		checkboxGroup.setLabel("Label");
		checkboxGroup.setItems("Option one", "Option two", "Option three");
		//checkboxGroup.setValue(Collections.singleton("Option one"));
		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		/*checkboxGroup.addValueChangeListener(new ValueChangeListener<ValueChangeEvent<?>>() {

			@Override
			public void valueChanged(ValueChangeEvent<?> event) {
				// TODO Auto-generated method stub
				
			}
		})*/
		//checkboxGroup.addValueChangeListener(event -> ShowNotificationGroup(event));
		checkboxGroup.addValueChangeListener(this::ShowNotificationGroup);
		
		row.add(checkboxGroup);
		
		return row;
	}
	
	private void ShowNotificationGroup(ComponentValueChangeEvent<CheckboxGroup<String>, Set<String>> event) {
		if (!event.getValue().isEmpty()) {
			Iterator<String> it = event.getValue().iterator();
			
		    while(it.hasNext()){
		    	Notification.show(it.next());		        
		    }		     			
		}
	}
	
	private Component CreateCheckboxGroupWithDepartments() {
		HorizontalLayout row = new HorizontalLayout();
		
		CheckboxGroup<Department> checkboxGroup = new CheckboxGroup<>();
		
		checkboxGroup.setLabel("Label");
		checkboxGroup.setItems(departmentService.findAll());
		checkboxGroup.setItemLabelGenerator(Department::getSizeToString);
		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		
		checkboxGroup.addValueChangeListener(new ValueChangeListener<ValueChangeEvent<Set<Department>>>() {
			@Override
			public void valueChanged(ValueChangeEvent<Set<Department>> event) {
				Iterator<Department> it = event.getValue().iterator();
				
			    while(it.hasNext()){
			    	Notification.show(it.next().getSizeToString());		        
			    }			   		
			}
		});
		
		row.add(checkboxGroup);
		
		return row;
	}
	
	private Component CreateRadioButtonGroupWithDepartments() {
		HorizontalLayout row = new HorizontalLayout();
		
		RadioButtonGroup<Department> radioButtonGroup = new RadioButtonGroup<>();
		
		radioButtonGroup.setLabel("Label");
		radioButtonGroup.setItems(departmentService.findAll());
		radioButtonGroup.setRenderer(new TextRenderer<>(Department::getName));
		radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
		
		radioButtonGroup.addValueChangeListener(new ValueChangeListener<ValueChangeEvent<Department>>() {
			@Override
			public void valueChanged(ValueChangeEvent<Department> event) {				
			    Notification.show(event.getValue().getName());		        			    			   		
			}
		});
		
		row.add(radioButtonGroup);
		
		return row;
	}
	
}
