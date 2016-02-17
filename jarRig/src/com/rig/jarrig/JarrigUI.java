package com.rig.jarrig;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("jarrig")
public class JarrigUI extends UI {

    RigComponents components = new RigComponents();

    @Override
    protected void init(VaadinRequest request) {
	setContent(components);
    }

}