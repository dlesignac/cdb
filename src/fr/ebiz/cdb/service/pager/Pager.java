package fr.ebiz.cdb.service.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pager<T> {

	private List<T> entities;
	private int limit = 10;
	private int offset = -1;
	
	public Pager(List<T> entities) {
		this.entities = entities;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
		this.offset = -1;
	}
	
	public boolean hasNext() {
		return this.offset + 1 <= (this.entities.size() - 1) / this.limit;
	}
	
	public boolean hasPrevious() {
		return this.offset - 1 >= 0;
	}
	
	public List<T> next() throws PagerIndexOutOfBoundsException {
		if (!this.hasNext()) {
			throw new PagerIndexOutOfBoundsException();
		}
		
		this.offset++;
		
		List<T> result = entities
				.stream()
				.skip(this.offset * limit)
				.limit(this.limit)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return result;
	}
	
	public List<T> previous() throws PagerIndexOutOfBoundsException {
		if (!this.hasPrevious()) {
			throw new PagerIndexOutOfBoundsException();
		}
		
		this.offset--;
		
		List<T> result = entities
				.stream()
				.skip(this.offset * limit)
				.limit(this.limit)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return result;
	}

}
