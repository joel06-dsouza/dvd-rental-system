package in.mindcraft.trialDVD.Staff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_list")
public class ActorsInfo {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "fid")
	    private Long id;
	 
	 
	 @Column(name="actors")
	 private String actors;


	public ActorsInfo() {
		super();
	}


	public ActorsInfo(Long id, String actors) {
		super();
		this.id = id;
		this.actors = actors;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getActors() {
		return actors;
	}


	public void setActors(String actors) {
		this.actors = actors;
	}
	 
	 
	 
	 
	
}