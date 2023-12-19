package pojos;
import javax.persistence.*;
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(statergy)
	private Long id;

}
