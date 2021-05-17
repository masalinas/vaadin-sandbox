package com.example.application.views.grid;

import com.example.application.data.Department;
import com.example.application.services.DepartmentService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "grid", layout = MainView.class)
@PageTitle("Grid")
@CssImport(
	    themeFor = "vaadin-grid",
	    value = "./themes/vaadin-sandbox/components/my-grid-view.css"
)
public class GridView extends HorizontalLayout {
	private DepartmentService departmentService;
	private Grid<Department> grid;
	
	public GridView(DepartmentService departmentService) {
		super();
		
		this.departmentService = departmentService;
		
		setSizeFull();
		setPadding(true);
		 
	    addClassName("grid-view");
	     
	    add(createFilters());
	    add(createGrid());
	}
	 
	private Component createFilters( ) {
		 return new HorizontalLayout();
	}
	 
	private Component createGrid() {
		 grid = new Grid<Department>(Department.class);
		 //grid.addClassName("department-grid");
		 grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, 
					   GridVariant.LUMO_NO_ROW_BORDERS, 
					   GridVariant.LUMO_ROW_STRIPES);
		 
		 try {
			 // bind the Department collection to the grid
			 grid.setItems(this.departmentService.findAll());
			
			 grid.setColumns("name", "description", "size");
			 			 			 
			 // configure the grid columns from entity 			 
			 //grid.addColumn(Department::getName);
			 //grid.addColumn(Department::getSize);
			 
			 //grid.getColumns().get(0).setHeader("Nombre");
			 //grid.getColumns().get(1).setHeader("Size");
			 grid.getColumnByKey("name").setHeader("Nombre").setWidth("200px").setFlexGrow(0).setSortable(false);			 
			 grid.getColumnByKey("description").setHeader("Description").setFlexGrow(1);
			 grid.getColumnByKey("size").setHeader("Size").setFlexGrow(0).setWidth("90px").setTextAlign(ColumnTextAlign.END);
			 grid.getColumnByKey("size").setClassNameGenerator(department -> department.getSize() > 6 ? "warn": null);
			 
			 grid.getColumns().forEach(column -> {
				 column.setSortable(false);
			 });
		 } catch (Exception e) {
			Notification.show(e.getLocalizedMessage());
		 } finally {
			return grid;
		 }		 
	 }
}
