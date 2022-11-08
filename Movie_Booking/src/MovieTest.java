// Rudimentary/placeholder test using JUnit

import org.junit.jupiter.api.Assertions;

class MovieTest {
    @org.junit.jupiter.api.Test
    void numOfRowsTest() {
        Movie movie = new Movie();
        movie.setNumOfRows(5);

        Assertions.assertEquals(5, movie.getNumOfRows());
    }
}
