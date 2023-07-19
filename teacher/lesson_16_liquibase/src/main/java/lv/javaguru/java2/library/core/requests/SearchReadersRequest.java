package lv.javaguru.java2.library.core.requests;

public class SearchReadersRequest {

	private String firstName;
	private String lastName;

	public SearchReadersRequest() { }

	public SearchReadersRequest(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isFirstNameProvided() {
		return this.firstName != null && !this.firstName.isEmpty();
	}

	public boolean isLastNameProvided() {
		return this.lastName != null && !this.lastName.isEmpty();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
