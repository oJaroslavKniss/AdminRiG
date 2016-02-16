package com.example.jarrig;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("jarrig")
public class JarrigUI extends UI {
	
	RigComponents components = new RigComponents();
	@Override
	protected void init(VaadinRequest request) {
		setContent(components);
	}

}