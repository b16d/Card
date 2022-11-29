package acl.domain;

public record Card(int id,String title, String information) {

	public String toCSV() {
		return id + "," + title + "," + information;
	}

}
