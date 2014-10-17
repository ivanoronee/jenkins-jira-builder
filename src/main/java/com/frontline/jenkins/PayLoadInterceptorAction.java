/**
 * 
 */
package com.frontline.jenkins;

import hudson.model.Job;
import hudson.model.Project;

import java.util.List;

import jenkins.model.Jenkins;

/**
 * @author frontline
 *
 */
public class PayLoadInterceptorAction {	

	@SuppressWarnings("rawtypes")
	public static Project getMatchingJob(String issueId) {
		List<Project> projects = Jenkins.getInstance().getAllItems(
				Project.class);
		for (Project project : projects) {
			if (project instanceof Job) {
				if (project.getName().contains(issueId)) {
					return (Project) project;
				}
			}
		}
		return null;
	}	
}
