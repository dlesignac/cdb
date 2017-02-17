package fr.ebiz.cdb.service.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pager<T> {

	private List<T> entities;
	private int limit = 5;
	private int offset = 0;

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

	public void loadNext() throws PagerIndexOutOfBoundsException {
		if (!hasNext()) {
			throw new PagerIndexOutOfBoundsException();
		}

		offset++;
	}

	public void loadPrevious() throws PagerIndexOutOfBoundsException {
		if (!hasPrevious()) {
			throw new PagerIndexOutOfBoundsException();
		}

		offset--;
	}

	public List<T> values() {
		List<T> result = entities.stream().skip(this.offset * limit).limit(this.limit)
				.collect(Collectors.toCollection(ArrayList::new));

		return result;
	}

}
