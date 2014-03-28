package pl.edu.agh.student.wojcicks.taglib;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PaginatorTagHandlerTest {

    @Test
    public void testDisabledPrev() throws Exception {
        PaginatorTagHandler paginatorTagHandler = new PaginatorTagHandler();
        paginatorTagHandler.setBeanclass("a");
        paginatorTagHandler.setCurrentPage(1);
        String actual = paginatorTagHandler.getPrev();
        String expected = "<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDisabledNext() throws Exception {
        PaginatorTagHandler paginatorTagHandler = new PaginatorTagHandler();
        paginatorTagHandler.setBeanclass("a");
        paginatorTagHandler.setCurrentPage(3);
        paginatorTagHandler.setPagesCount(3);
        String actual = paginatorTagHandler.getNext();
        String expected = "<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>";
        Assert.assertEquals(expected, actual);
    }
}
