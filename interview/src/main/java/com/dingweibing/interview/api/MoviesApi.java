/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.27).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.dingweibing.interview.api;

import com.dingweibing.interview.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-31T08:19:42.715Z")

@Validated
@Api(value = "movies", description = "the movies API")
public interface MoviesApi {

    Logger log = LoggerFactory.getLogger(MoviesApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Find movie by ID",
            nickname = "getMovieById",
            notes = "Returns a single movie",
            response = Movie.class,
            tags = {})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = Movie.class),
            @ApiResponse(code = 204, message = "No Movie Found")})
    @RequestMapping(value = "/movies/{movieId}", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<Movie> getMovieById(@ApiParam(value = "ID of movie to return", required = true)
                                       @PathVariable("movieId") final String movieId);


    @ApiOperation(value = "Search movies", nickname = "searcMovies",
            notes = "Return popular movies or what the user searched for",
            response = Movie.class,
            responseContainer = "List",
            tags = {})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = Movie.class, responseContainer = "List"),
            @ApiResponse(code = 204, message = "No Movie Found"),
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/movies", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<Movie>> searchMovies(@NotNull
                                             @ApiParam(value = "Keyword values that need to be considered for filter", required = true)
                                             @Valid
                                             @RequestParam(value = "search", required = true) String search,
                                             @Min(0)
                                             @ApiParam(value = "number of records to skip for pagination", defaultValue = "0")
                                             @Valid
                                             @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                             @Min(0)
                                             @Max(50)
                                             @ApiParam(value = "maximum number of records to return", defaultValue = "50")
                                             @Valid
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize);
}