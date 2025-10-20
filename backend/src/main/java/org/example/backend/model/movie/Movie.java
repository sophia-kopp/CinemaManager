package org.example.backend.model.movie;

import java.util.List;

public record Movie(boolean adult,
                    String backdrop_path,
                    List<Integer> genre_ids,
                    int id,
                    String original_language,
                    String original_title,
                    String overview,
                    double popularity,
                    String poster_path,
                    String release_date,
                    String title,
                    boolean video,
                    double vote_average,
                    int vote_count) {
}
