package org.example.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void getAllMovies_ShouldReturnListOfMovies_whenCalled() throws Exception {
        //GIVEN
        mockServer.expect(requestTo("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""  
                        {
                          "page": 1,
                          "results": [
                                {
                                "adult": false,
                                        "backdrop_path": "/7QirCB1o80NEFpQGlQRZerZbQEp.jpg",
                                        "genre_ids": [
                                            10749,
                                            18
                                        ],
                                        "id": 1156594,
                                        "original_language": "es",
                                        "original_title": "Culpa nuestra",
                                        "overview": "Jenna and Lion's wedding brings about the long-awaited reunion between Noah and Nick after their breakup. Nick's inability to forgive Noah stands as an insurmountable barrier. He, heir to his grandfather's businesses, and she, starting her professional life, resist fueling a flame that's still alive. But now that their paths have crossed again, will love be stronger than resentment?",
                                        "popularity": 1162.7582,
                                        "poster_path": "/yzqHt4m1SeY9FbPrfZ0C2Hi9x1s.jpg",
                                        "release_date": "2025-10-15",
                                        "title": "Our Fault",
                                        "video": false,
                                        "vote_average": 7.942,
                                        "vote_count": 52
                                    }],
                          "total_pages": 38020,
                          "total_results": 760385}
                        """, MediaType.APPLICATION_JSON
                ));
        //When/then
        mockMvc.perform(get("/api/movies"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                [
                                {
                                "adult": false,
                                        "backdrop_path": "/7QirCB1o80NEFpQGlQRZerZbQEp.jpg",
                                        "genre_ids": [
                                            10749,
                                            18
                                        ],
                                        "id": 1156594,
                                        "original_language": "es",
                                        "original_title": "Culpa nuestra",
                                        "overview": "Jenna and Lion's wedding brings about the long-awaited reunion between Noah and Nick after their breakup. Nick's inability to forgive Noah stands as an insurmountable barrier. He, heir to his grandfather's businesses, and she, starting her professional life, resist fueling a flame that's still alive. But now that their paths have crossed again, will love be stronger than resentment?",
                                        "popularity": 1162.7582,
                                        "poster_path": "/yzqHt4m1SeY9FbPrfZ0C2Hi9x1s.jpg",
                                        "release_date": "2025-10-15",
                                        "title": "Our Fault",
                                        "video": false,
                                        "vote_average": 7.942,
                                        "vote_count": 52
                                    }]
                                """
                ));
    }
}