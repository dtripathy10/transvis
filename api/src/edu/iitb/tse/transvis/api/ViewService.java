/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.transvis.api;

import javax.tools.FileObject;

/**
 *
 * @author dtripathy10
 */
public interface ViewService {

    boolean isRendered(FileObject file);

    boolean isUpToDate(FileObject file);

    void view(FileObject file);
}
