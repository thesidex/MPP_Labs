package probC;

/** Instances of Tax are types of tax, with constant values as vals */
public enum Tax {
	FICA(.23), State(.05), Local(.01), Medicare(.03), SocialSecurity(.075);

	private double val;

	private Tax(double val) {
		this.val = val;
	}

	public double getVal() {
		return val;
	}

}
