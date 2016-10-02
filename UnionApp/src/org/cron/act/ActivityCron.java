package org.cron.act;

import org.repository.RepositoryDelegate.RepositoryDelegator;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivityCron {
	@Autowired
	RepositoryDelegator repositoryDelegator;
	private String beforeLimit="1";
	
	public void doIt() {
		System.out.println("Activity cron starts !!");
		repositoryDelegator.deleteActivityCron(beforeLimit);;
    }

	public RepositoryDelegator getRepositoryDelegator() {
		return repositoryDelegator;
	}

	public void setRepositoryDelegator(RepositoryDelegator repositoryDelegator) {
		this.repositoryDelegator = repositoryDelegator;
	}

		
}
