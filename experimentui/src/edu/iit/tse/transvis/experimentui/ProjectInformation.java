/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui;

import java.util.List;
import org.netbeans.api.project.Project;

/**
 *
 * @author dtripathy10
 */
public class ProjectInformation {
    static Project currentProject;
    static List<Project> listOfOpenProject;

    public static Project getCurrentProject() {
        return currentProject;
    }

    public static List<Project> getListOfOpenProject() {
        return listOfOpenProject;
    }

    public static void setCurrentProject(Project currentProject1) {
        currentProject = currentProject1;
    }

    public static void setListOfOpenProject(List<Project> listOfOpenProject1) {
        listOfOpenProject = listOfOpenProject1;
    }

}
