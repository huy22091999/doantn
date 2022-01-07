package com.huyphungkien.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="tbl_categorys")
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	@Column(name="name",length = 100,columnDefinition = "nvarchar(100) not null")
	private String name;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "parent_id")
	private Set<Category> categories;

	public Category getParent() {
		return parent;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}

