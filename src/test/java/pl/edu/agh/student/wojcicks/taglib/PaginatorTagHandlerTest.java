package pl.edu.agh.student.wojcicks.taglib;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

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
  public void testDisabledPrevWithLink() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(2);

    Mockito.when(paginatorTagHandler.getPrev()).thenCallRealMethod();

    String actual = paginatorTagHandler.getPrev();
    String expected = "<li><a href=\"a\">&laquo;</a></li>";
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

  @Test
  public void testOther() throws Exception {
    PaginatorTagHandler paginatorTagHandler = new PaginatorTagHandler();
    paginatorTagHandler.setBeanclass("a");
    paginatorTagHandler.setCurrentPage(3);
    paginatorTagHandler.setPagesCount(3);
    Assert.assertEquals(3, paginatorTagHandler.getCurrentPage());
    Assert.assertEquals(3, paginatorTagHandler.getPagesCount());
    paginatorTagHandler.doInitBody();
    Assert.assertEquals(PaginatorTagHandler.EVAL_BODY_BUFFERED, paginatorTagHandler.doStartTag());
//    Assert.assertEquals(PaginatorTagHandler.SKIP_BODY, paginatorTagHandler.doEndTag());
    Assert.assertEquals(PaginatorTagHandler.SKIP_BODY, paginatorTagHandler.doAfterBody());
  }

  @Test
  public void testNextWithLink() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(1);

    paginatorTagHandler.setCurrentPage(1);
    paginatorTagHandler.setPagesCount(4);

    Mockito.when(paginatorTagHandler.getPagesCount()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPagesCount(4);

    Mockito.when(paginatorTagHandler.getNext()).thenCallRealMethod();

    String actual = paginatorTagHandler.getNext();
    String expected = "<li><a href=\"a\">&raquo;</a></li>";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testNumbersLessThan5() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(3);

    paginatorTagHandler.setCurrentPage(3);
    paginatorTagHandler.setPagesCount(5);

    Mockito.when(paginatorTagHandler.getPagesCount()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPagesCount(5);

    Mockito.when(paginatorTagHandler.getNumbers()).thenCallRealMethod();

    paginatorTagHandler.setCurrentPage(3);
    paginatorTagHandler.setPagesCount(5);

    String actual = paginatorTagHandler.getNumbers();
    String expected = "<li><a href=\"a\">1</a></li>"
        + "<li><a href=\"a\">2</a></li>"
        + "<li class=\"active\"><a href=\"a\">3</a></li>"
        + "<li><a href=\"a\">4</a></li>"
        + "<li><a href=\"a\">5</a></li>";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testNumbersMoreThan5() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(3);

    paginatorTagHandler.setCurrentPage(3);
    paginatorTagHandler.setPagesCount(6);

    Mockito.when(paginatorTagHandler.getPagesCount()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPagesCount(6);

    Mockito.when(paginatorTagHandler.getNumbers()).thenCallRealMethod();

    paginatorTagHandler.setCurrentPage(3);
    paginatorTagHandler.setPagesCount(6);

    String actual = paginatorTagHandler.getNumbers();
    String expected = "<li><a href=\"a\">1</a></li>"
        + "<li><a href=\"a\">2</a></li>"
        + "<li class=\"active\"><a href=\"a\">3</a></li>"
        + "<li><a href=\"a\">4</a></li>"
        + "<li class=\"disabled\"><a href=\"#\">...</a></li>"
        + "<li><a href=\"a\">6</a></li>";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testNumbersMoreThan5Current2() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(2);

    paginatorTagHandler.setCurrentPage(2);
    paginatorTagHandler.setPagesCount(6);

    Mockito.when(paginatorTagHandler.getPagesCount()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPagesCount(6);

    Mockito.when(paginatorTagHandler.getNumbers()).thenCallRealMethod();

    paginatorTagHandler.setCurrentPage(2);
    paginatorTagHandler.setPagesCount(6);

    String actual = paginatorTagHandler.getNumbers();
    String expected = "<li><a href=\"a\">1</a></li>"
        + "<li class=\"active\"><a href=\"a\">2</a></li>"
        + "<li><a href=\"a\">3</a></li>"
        + "<li class=\"disabled\"><a href=\"#\">...</a></li>"
        + "<li><a href=\"a\">6</a></li>";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testNumbersMoreThan5Current5() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(5);

    paginatorTagHandler.setCurrentPage(5);
    paginatorTagHandler.setPagesCount(6);

    Mockito.when(paginatorTagHandler.getPagesCount()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPagesCount(6);

    Mockito.when(paginatorTagHandler.getNumbers()).thenCallRealMethod();

    paginatorTagHandler.setCurrentPage(5);
    paginatorTagHandler.setPagesCount(6);

    String actual = paginatorTagHandler.getNumbers();
    String expected = "<li><a href=\"a\">1</a></li>"
        + "<li class=\"disabled\"><a href=\"#\">...</a></li>"
        + "<li><a href=\"a\">4</a></li>"
        + "<li class=\"active\"><a href=\"a\">5</a></li>"
        + "<li><a href=\"a\">6</a></li>";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testNumbersMoreThan5Current4() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.buildUrl()).thenReturn("a");

    Mockito.when(paginatorTagHandler.getCurrentPage()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setCurrentPage(4);

    paginatorTagHandler.setCurrentPage(4);
    paginatorTagHandler.setPagesCount(6);

    Mockito.when(paginatorTagHandler.getPagesCount()).thenCallRealMethod();
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPagesCount(6);

    Mockito.when(paginatorTagHandler.getNumbers()).thenCallRealMethod();

    paginatorTagHandler.setCurrentPage(4);
    paginatorTagHandler.setPagesCount(6);

    String actual = paginatorTagHandler.getNumbers();
    String expected = "<li><a href=\"a\">1</a></li>"
        + "<li class=\"disabled\"><a href=\"#\">...</a></li>"
        + "<li><a href=\"a\">3</a></li>"
        + "<li class=\"active\"><a href=\"a\">4</a></li>"
        + "<li><a href=\"a\">5</a></li>"
        + "<li><a href=\"a\">6</a></li>";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testDoEndTag() throws Exception {
    PaginatorTagHandler paginatorTagHandler = Mockito.mock(PaginatorTagHandler.class, Mockito.RETURNS_DEEP_STUBS);
    Mockito.when(paginatorTagHandler.getPrev()).thenReturn("");
    Mockito.when(paginatorTagHandler.getNext()).thenReturn("");
    Mockito.when(paginatorTagHandler.getNumbers()).thenReturn("");

    PageContext pc = Mockito.mock(PageContext.class);
    Mockito.doCallRealMethod().when(paginatorTagHandler).setPageContext(pc);
    paginatorTagHandler.setPageContext(pc);
    Mockito.when(paginatorTagHandler.getPageContext()).thenReturn(pc);
    Mockito.when(paginatorTagHandler.getPageContext().getOut()).thenReturn(Mockito.mock(JspWriter.class));

    Integer actual = paginatorTagHandler.doEndTag();
    Integer expected = PaginatorTagHandler.SKIP_BODY;
    Assert.assertEquals(expected, actual);
  }
}
