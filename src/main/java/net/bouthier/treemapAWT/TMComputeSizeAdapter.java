/*
 * TMComputeSizeAdapter.java
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

package net.bouthier.treemapAWT;


/**
 * The TMComputeSizeAdapter class implements a 
 * adapter for the TMComputeSize interface for users
 * of the TMModelNode interface.
 *
 * @author Christophe Bouthier [bouthier@loria.fr]
 * @version 2.5
 */
public abstract class TMComputeSizeAdapter 
	implements TMComputeSize {

    /**
     * DO NOT OVERLOAD.
     */
    public final boolean isCompatibleWith(TMNode node) {
        if (node instanceof TMNodeEncapsulator) {
            TMNodeEncapsulator n = (TMNodeEncapsulator) node;
            return isCompatibleWithObject(n.getNode());
        } else {
            return false;
        }
    }

    /**
     * DO NOT OVERLOAD.
     */
    public final float getSize(TMNode node) 
    	throws TMExceptionBadTMNodeKind {

        if (isCompatibleWith(node)) {
            TMNodeEncapsulator n = (TMNodeEncapsulator) node;
            return getSizeOfObject(n.getNode());
        } else {
            throw new TMExceptionBadTMNodeKind(this, node);
        }
    }

    /**
     * TO BE IMPLEMENTED.
     */
    public abstract boolean isCompatibleWithObject(Object node);

    /**
     * TO BE IMPLEMENTED.
     */
    public abstract float getSizeOfObject(Object node);

}
