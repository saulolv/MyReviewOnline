package org.myreview.Application.Services;

import org.myreview.Domain.Entities.Movie;
import org.myreview.Domain.Repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Criar um novo filme
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Buscar filme por ID
    public Optional<Movie> getMovieById(UUID id) {
        return movieRepository.findById(id);
    }

    // Listar todos os filmes
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Atualizar filme
    public Movie updateMovie(UUID id, Movie updatedMovie) {
        return movieRepository.findById(id).map(existingMovie -> {
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setSynopsis(updatedMovie.getSynopsis());
            existingMovie.setStudio(updatedMovie.getStudio());
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
            existingMovie.setCoverImage(updatedMovie.getCoverImage());
            existingMovie.setDuration(updatedMovie.getDuration());
            return movieRepository.save(existingMovie);
        }).orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + id));
    }

    // Deletar filme
    public void deleteMovie(UUID id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Movie not found with ID: " + id);
        }
    }
}
