package com.edgar.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogItem {
	private String name;
	private String description;
	private int rating;
}
