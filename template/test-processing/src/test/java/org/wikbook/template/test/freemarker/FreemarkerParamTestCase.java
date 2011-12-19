/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.wikbook.template.test.freemarker;

import org.wikbook.template.freemarker.caller.ChildrenCallerMethod;
import org.wikbook.template.freemarker.caller.JavadocCallerMethod;
import org.wikbook.template.test.AbstractFreemarkerTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class FreemarkerParamTestCase extends AbstractFreemarkerTestCase {

  private ChildrenCallerMethod annotationChildrenCaller;
  private ChildrenCallerMethod pathChildrenCaller;

  private List<Map<String, Object>> paths;
  private List<Map<String, Object>> pathParams;
  private List<Map<String, Object>> queryParams;

  private JavadocCallerMethod pathParamsDocCaller;
  private JavadocCallerMethod queryParamsDocCaller;

  @Override
  public void setUp() throws Exception {

    super.setUp();

    Map<String, Object> data = buildModel("E");

    annotationChildrenCaller = (ChildrenCallerMethod) ((Map<String, Object>) data.get("@Path")).get("children");
    paths = (List<Map<String, Object>>) annotationChildrenCaller.exec(Arrays.asList("@Path"));

    pathChildrenCaller = (ChildrenCallerMethod) paths.get(0).get("children");
    pathParams = (List<Map<String, Object>>) pathChildrenCaller.exec(Arrays.asList("@PathParam"));
    queryParams = (List<Map<String, Object>>) pathChildrenCaller.exec(Arrays.asList("@QueryParam"));

    pathParamsDocCaller = (JavadocCallerMethod) pathParams.get(0).get("doc");
    queryParamsDocCaller = (JavadocCallerMethod) queryParams.get(0).get("doc");

  }

  public void testExists() throws Exception {

    assertEquals("PathParam", pathParams.get(0).get("name"));
    assertEquals("QueryParam", queryParams.get(0).get("name"));

  }

  public void testElementName() throws Exception {

    assertEquals("pathParameter", pathParams.get(0).get("elementName"));
    assertEquals("queryParameter", queryParams.get(0).get("elementName"));

  }

  public void testTypeName() throws Exception {

    assertEquals("String", ((Map<String, String>) pathParams.get(0).get("type")).get("name"));
    assertEquals("java.lang.String", ((Map<String, String>) pathParams.get(0).get("type")).get("fullName"));
    assertEquals("false", ((Map<String, String>) pathParams.get(0).get("type")).get("isArray"));

  }

  public void testArrayTypeName() throws Exception {

    assertEquals("String[]", ((Map<String, String>) queryParams.get(0).get("type")).get("name"));
    assertEquals("java.lang.String[]", ((Map<String, String>) queryParams.get(0).get("type")).get("fullName"));
    assertEquals("true", ((Map<String, String>) queryParams.get(0).get("type")).get("isArray"));

  }

  public void testJavadoc() throws Exception {

    assertEquals("Path parameter description", pathParamsDocCaller.exec(new ArrayList()).toString());
    assertEquals("Query parameter description", queryParamsDocCaller.exec(new ArrayList()).toString());

  }

}
