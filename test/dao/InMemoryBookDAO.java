package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Book;

public class InMemoryBookDAO implements DataAccessObject<Book> {

	private final List<Book> cache = new ArrayList<>();

	@Override
	public void save(final Book book) {
		cache.add(book);
	}

	@Override
	public void delete(final Book book) {
		cache.remove(book);
	}

	@Override
	public void update(final Book t) {
		// No-op
	}

	@Override
	public List<Book> findAll() {
		return new ArrayList<>(cache);
	}

	@Override
	public Optional<Book> find(final long id) throws IOException {
		return cache.parallelStream().filter(book -> book.getId() == id).findAny();
	}

}
