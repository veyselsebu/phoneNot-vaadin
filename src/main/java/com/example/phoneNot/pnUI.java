package com.example.phoneNot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.GridRowDragger;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class pnUI extends UI {

	private VerticalLayout root;

	@Autowired
	pnLayout pnlayout;

	private TextField nameTask;
	private TextField surnameTask;
	private TextField phoneNumberTask;
	private Grid<PhoneBook> grid;
	private Grid<PhoneBook> grid2;
	private List<PhoneBook> dataList;
	private List<PhoneBook> dataList1;
	private PhoneBook selectData;
	private GridRowDragger<PhoneBook> leftToRight;
	private GridRowDragger<PhoneBook> rightToLeft;

	@Override
	protected void init(VaadinRequest request) {

		dataList1 = new ArrayList();
		setupLayout();
		addHeader();
		addNames();
		addForm1();
		addForm2();
		addForm3();
		addSaveChange();

	}

	private void addSaveChange() {
		HorizontalLayout saveChangeLayout = new HorizontalLayout();
		saveChangeLayout.setWidth("80%");
		Button saveChange = new Button("Save Change");
		saveChangeLayout.addComponentsAndExpand(saveChange);

		saveChange.addClickListener(click -> {

			for (PhoneBook a : dataList1) {
				pnlayout.delet(a);
			}
			updateScrean();
		});

		root.addComponent(saveChangeLayout);
	}

	private void addNames() {
		HorizontalLayout namesLayout = new HorizontalLayout();
		namesLayout.setWidth("80%");
		Label name = new Label("name : ");
		Label surname = new Label("surname :");
		Label Phoneno = new Label("Phone number :");

		namesLayout.addComponentsAndExpand(name, surname, Phoneno);

		root.addComponent(namesLayout);
	}

	private void setupLayout() {
		root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(root);
	}

	private void addHeader() {

		Label header = new Label("Phone Book");
		header.addStyleName(ValoTheme.LABEL_H1);
		root.addComponent(new Image(null,
				new ClassResource("https://cdn4.iconfinder.com/data/icons/social-icons-6/40/phone-512.png")));
		root.addComponent(header);
	}

	private void addForm1() {
		HorizontalLayout form1Layout = new HorizontalLayout();
		form1Layout.setWidth("80%");
		nameTask = new TextField();
		surnameTask = new TextField();
		phoneNumberTask = new TextField();
		form1Layout.addComponentsAndExpand(nameTask, surnameTask, phoneNumberTask);
		root.addComponent(form1Layout);
	}

	private void addForm2() {
		HorizontalLayout form2Layout = new HorizontalLayout();
		form2Layout.setWidth("80%");

		Button create = new Button("add");
		Button delete = new Button("delete");
		Button update = new Button("update");
		create.setIcon(VaadinIcons.PLUS);
		create.addStyleName(ValoTheme.BUTTON_PRIMARY);
		delete.setIcon(VaadinIcons.FILE_REMOVE);
		delete.addStyleName(ValoTheme.BUTTON_DANGER);
		update.setIcon(VaadinIcons.FILE_REFRESH);
		update.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		form2Layout.addComponentsAndExpand(create, delete, update);
		create.addClickListener(click -> {
			pnlayout.add(new PhoneBook(nameTask.getValue(), surnameTask.getValue(), phoneNumberTask.getValue()));
			nameTask.clear();
			surnameTask.clear();
			phoneNumberTask.clear();
			updateScrean();
		});
		delete.addClickListener(click -> {
			if (selectData != null) {
				pnlayout.delet(selectData);
				selectData = null;
				nameTask.clear();
				surnameTask.clear();
				phoneNumberTask.clear();
				updateScrean();
			}
		});
		update.addClickListener(click -> {
			if (selectData != null) {
				selectData.setName(nameTask.getValue());
				selectData.setSurname(surnameTask.getValue());
				selectData.setPhoneNumber(phoneNumberTask.getValue());
				pnlayout.add(selectData);
				selectData = null;
				nameTask.clear();
				surnameTask.clear();
				phoneNumberTask.clear();
				updateScrean();
			}

		});
		root.addComponent(form2Layout);
	}

	private void addForm3() {

		HorizontalLayout form3Layout = new HorizontalLayout();
		form3Layout.setWidth("80%");
		grid = new Grid<>(PhoneBook.class);
		grid2 = new Grid<>(PhoneBook.class);

		leftToRight = new GridRowDragger<>(grid, grid2);
		rightToLeft = new GridRowDragger<>(grid2, grid);
		dataList = pnlayout.datas();
		grid.setItems(dataList);
		grid2.setItems(dataList1);
		grid.addComponentColumn(this::deleteButton);
		form3Layout.addComponentsAndExpand(grid, grid2);
		grid.addItemClickListener(click -> {
			for (PhoneBook a : grid.getSelectedItems()) {
				selectData = a;
				nameTask.setValue(a.getName());
				surnameTask.setValue(a.getSurname());
				phoneNumberTask.setValue(a.getPhoneNumber());
			}
		});

		leftToRight.getGridDragSource()
				.addDragStartListener(event -> rightToLeft.getGridDropTarget().setDropEffect(DropEffect.NONE));
		leftToRight.getGridDragSource()
				.addDragEndListener(event -> rightToLeft.getGridDropTarget().setDropEffect(null));
		rightToLeft.getGridDragSource()
				.addDragStartListener(event -> leftToRight.getGridDropTarget().setDropEffect(DropEffect.NONE));
		rightToLeft.getGridDragSource()
				.addDragEndListener(event -> leftToRight.getGridDropTarget().setDropEffect(null));

		root.addComponent(form3Layout);
	}

	private void updateScrean() {
		dataList = pnlayout.datas();
		grid.setItems(dataList);
		grid2.setItems(dataList1);

	}
	
	private Button updateButton(PhoneBook a) {
	Button b=new Button("update");
	b.addClickListener(click->{
		pnlayout.add(a);
		updateScrean();
	});
	
	return b;
	}	
	
	private Button deleteButton(PhoneBook a) {
	Button b=new Button("delete");
	b.addClickListener(click->{
		pnlayout.delet(a);
		updateScrean();
	});
	
	return b;
	}
	
}
