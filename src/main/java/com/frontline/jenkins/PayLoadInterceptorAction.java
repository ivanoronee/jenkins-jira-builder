/**
 * 
 */
package com.frontline.jenkins;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.Project;

import java.util.List;

import jenkins.model.Jenkins;

import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.HttpResponses;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.WebMethod;

/**
 * @author frontline
 *
 */
@Extension
public class PayLoadInterceptorAction {

	/**
	 * get a request from webHook and parse the jobs to be built.
	 * 
	 * @param issueId
	 *            the id of the issue that triggered the webHook.
	 * @return
	 */
	@WebMethod(name = { "jira-builder-build" })
	HttpResponse doBuildJob(@QueryParameter String issueId) {

		@SuppressWarnings("rawtypes")
		Project project = getMatchingJob(issueId);
		if (project != null) {
			project.scheduleBuild2(0);
		}
		return HttpResponses.plainText("Buld triggered?");
	}

	public HttpResponse doStart() {
		return HttpResponses.redirectTo(".");
	}

	@SuppressWarnings("rawtypes")
	public Project getMatchingJob(String issueId) {
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
