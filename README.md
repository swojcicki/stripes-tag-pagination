stripes-tag-pagination
======================
### Clone repo

```
$ git clone https://github.com/swojcicki/stripes-tag-pagination.git
```

### Install project

```
$ cd stripes-tag-pagination
$ mvn source:jar install
```

### Add maven dependency

```
<dependency>
  <groupId>pl.edu.agh.student.wojcicks.taglib</groupId>
  <artifactId>stripes-tag-pagination</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### Set taglib definition

```
<%@taglib prefix="p" uri="http://student.agh.edu.pl/~wojcicks/taglib/paginator"%>
```

### Set bootstrap CSS file

```
<link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
```

### Use paginator as well

```
<p:paginator currentPage="2" pagesCount="3" beanclass="package.SampleAction"/>
```

```
<p:paginator currentPage="${actionBean.page}" pagesCount="${actionBean.pagesCount}"
                             beanclass="${actionBean.class.name}"/>
```

### Table counter

```
<td>${loop.count  + actionBean.pageResult * (actionBean.page - 1)}</td>
```
