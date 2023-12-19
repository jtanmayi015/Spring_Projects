package com.app.Entity;

import javax.persistence.Entity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
@Entity

public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String storeName;
	
	@OneToMany(mappedBy = "seller")
	private List<Book> books;
}
