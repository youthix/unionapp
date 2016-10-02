package org.cron.meet;

import org.repository.RepositoryDelegate.RepositoryDelegator;
import org.springframework.beans.factory.annotation.Autowired;

public class MeetingCron {
	@Autowired
	RepositoryDelegator repositoryDelegator;
	private String beforeLimit="1";
	
	public void doIt() {
		System.out.println("Meeting cron starts !!");
		repositoryDelegator.deleteMeetingCron(beforeLimit);;
    }

	public RepositoryDelegator getRepositoryDelegator() {
		return repositoryDelegator;
	}

	public void setRepositoryDelegator(RepositoryDelegator repositoryDelegator) {
		this.repositoryDelegator = repositoryDelegator;
	}

		
}
