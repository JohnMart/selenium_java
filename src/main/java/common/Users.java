package common;

public enum Users {
	
	//List of usernames/roles for BlueSource.
	BASE("kevin.hedgecock"),
	MANAGER("top.bottom"),
	DEPTHEAD("virginia.vestal"),
	COMPANYADMIN("company.admin"),
	FAKEUSER("fake.user");
	
	private final String user;
	
	//Constructor to set local user to incoming argument.
	private Users(String user) {
		this.user = user;
	}
	
	//Return the username asked for.
	public String getUsername() {
		return user;
	}

}
