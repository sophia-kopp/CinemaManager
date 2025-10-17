package org.example.backend.model.movie;

public record Movie(boolean adult,
                    String backdrop_path,
                    int[] genre_ids,
                    int id,
                    String original_language,
                    String original_title,
                    String overview,
                    double popularity,
                    String poster_path,
                    String release_date,
                    String title,
                    boolean video,
                    float vote_average,
                    int vote_count) {
}
