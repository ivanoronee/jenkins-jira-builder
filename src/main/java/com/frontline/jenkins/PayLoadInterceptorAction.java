/**
 * 
 */
package com.frontline.jenkins;

import hudson.model.TopLevelItem;
import hudson.model.AbstractProject;
import hudson.model.Job;

import java.util.List;

import jenkins.model.Jenkins;

/**
 * @author frontline
 *
 */
public class PayLoadInterceptorAction {

	@SuppressWarnings("rawtypes")
	public static AbstractProject getMatchingJob(String issueId) {
		List<TopLevelItem> projects = Jenkins.getInstance().getItems();
		for (TopLevelItem project : projects) {
			if (project instanceof Job && issueId != null) {
				if (project.getName().contains(issueId)) {
					return (AbstractProject) project;
				}
			}	
		}
		return null;
	}
}
