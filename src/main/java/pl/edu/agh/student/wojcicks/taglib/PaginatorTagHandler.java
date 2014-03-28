package pl.edu.agh.student.wojcicks.taglib;

import net.sourceforge.stripes.exception.StripesJspException;
import net.sourceforge.stripes.tag.LinkTagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import java.io.IOException;

/**
 * Created on: 28.03.14 19:23 <br/>
 *
 * @author Slawomir Wojcicki
 */
public class PaginatorTagHandler extends LinkTagSupport implements BodyTag {

    private Log log = LogFactory.getLog(PaginatorTagHandler.class);

    private int currentPage;
    private int pagesCount;

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    protected String getPrev() throws StripesJspException {
        if (currentPage == 1) {
            return "<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>";
        } else {
            addParameter("page", currentPage - 1);
            String li = "<li><a href=\"" + buildUrl() + "\">&laquo;</a></li>";
            clearParameters();
            return li;
        }
    }

    protected String getNext() throws StripesJspException {
        if (currentPage == pagesCount) {
            return ("<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>");
        } else {
            addParameter("page", currentPage + 1);
            String li = "<li><a href=\"" + buildUrl() + "\">&raquo;</a></li>";
            clearParameters();
            return li;
        }
    }

    protected String getNumbers() throws StripesJspException {
        StringBuilder li = new StringBuilder();
        for (int i = 1; i <= pagesCount; i++) {
            addParameter("page", i);
            li.append("<li" + (i == currentPage ? " class=\"active\"" : "") + "><a href=\"" + buildUrl() + "\">" + i + "</a></li>");
            clearParameters();
        }
        return li.toString();
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();

            //Perform substr operation on string.
            out.println("<ul class=\"pagination\" style=\"display: inline-block\">");
            out.println(getPrev());
            out.println(getNumbers());
            out.println(getNext());
            out.println("</ul>");

        } catch (IOException e) {
            log.error("Paginator tag error", e);
        }
        return SKIP_BODY;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    @Override
    public void doInitBody() throws JspException {
        /* Do Nothing. */
    }

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }
}
