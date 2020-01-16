/*
 * TMStatusDisplay.java
 * www.bouthier.net
 *
 * The MIT License :
 * -----------------
 * Copyright (c) 2001 Christophe Bouthier
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package net.bouthier.treemapSwing;

import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;


/**
 * The TMStatusDisplay abstract class defines view displaying 
 * the status of an activity.
 * It should be subclassed by classes that implements a way to display 
 * the status of the activity.
 *
 * @author Christophe Bouthier [bouthier@loria.fr]
 * @version 2.5
 */
abstract class TMStatusDisplay 
	extends JPanel {


    /* --- Constructor --- */

    /**
     * Constructor.
     *
     * @param layout    the layout manager of the JPanel.
     */
    TMStatusDisplay(LayoutManager layout) {
        super(layout);
        setPreferredSize(new Dimension(200, 50));
        setSize(new Dimension(200, 50));
        setMaximumSize(new Dimension(200, 50));
    }

    /* --- Increment --- */

    /**
     * Increments the status display. 
     */
    abstract void increment();

    /* --- Clone --- */

    /**
     * Do a deep clone of the TMStatusDisplay.
     *
     * @return    a deep clone
     */
    abstract TMStatusDisplay deepClone();

}
