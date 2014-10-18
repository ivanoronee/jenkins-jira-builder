/**
 * 
 */
package com.frontline.jenkins;

import hudson.Extension;
import hudson.model.RootAction;
import hudson.model.AbstractProject;
import hudson.model.Project;

import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.HttpResponses;
import org.kohsuke.stapler.QueryParameter;

/**
 * @author frontline
 *
 */
@Extension
public class Root implements RootAction {

	public String getIconFileName() {
		return "gear.png";
	}

	public String getDisplayName() {
		return "Jira Builder";
	}

	public String getUrlName() {
		return "jira-builder";
	}
	
	public HttpResponse doBuildJob(@QueryParameter String issueId) {

		@SuppressWarnings("rawtypes")
		AbstractProject project = PayLoadInterceptorAction.getMatchingJob(issueId);
		if (project != null && project.isBuildable()) {
			project.scheduleBuild2(0);
		}
		return HttpResponses.plainText("Buld triggered: "+issueId);
	}
}
