package com.app.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	
	@ManyToMany
	@JoinTable(
	        name = "customer_books",
	        joinColumns = @JoinColumn(name = "customer_id"),
	        inverseJoinColumns = @JoinColumn(name = "book_id"))
			private List<Book> books;
}
