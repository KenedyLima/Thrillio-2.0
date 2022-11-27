package com.thrillio.constants;

public enum BookGenre {

	ART("Art"), FICTION("Fiction"), HISTORY("History"), BIOGRAPHY("Biography"), CHILDREN("Children"),
	MYSTERY("Mystery"), PHILOSOPHY("Phylosophy"), ROMANCE("Romance"), TECHNICAL("Technical"), SELF_HELP("Self help"),
	RELIGION("Religion");

	
	
	private String name;

	BookGenre(String string) {
		this.name = string;
	}

	public String getName() {
		return name;
	}

}
