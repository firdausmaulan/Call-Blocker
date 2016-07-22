package nutech.call.blocker;

// Model class for database table blacklist
public class Blacklist {

	// Two mapping fields for the database table blacklist 
	public long id;
	public String phoneNumber;
	
	// Default constructor
	public Blacklist() {
		 
	}
	
	// To easily create Blacklist object, an alternative constructor
	public Blacklist(final String phoneMumber) {
		 this.phoneNumber = phoneMumber;
	}
	
	// Overriding the default method to compare between the two objects bu phone number
	@Override
	public boolean equals(final Object obj) {
		
		// If passed object is an instance of Blacklist, then compare the phone numbers, else return false as they are not equal
		if(obj.getClass().isInstance(new Blacklist()))
		{
			// Cast the object to Blacklist
			final Blacklist bl = (Blacklist) obj;
			
			// Compare whether the phone numbers are same, if yes, it defines the objects are equal
			if(bl.phoneNumber.equalsIgnoreCase(this.phoneNumber))
				return true; 
		}
		return false;
	}
}
