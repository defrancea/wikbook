package org.wikbook.template.test.javadoc;

import org.wikbook.template.processing.metamodel.MetaModel;
import org.wikbook.template.test.AbstractProcessorTestCase;

import java.util.Map;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class ChromatticTestCase extends AbstractProcessorTestCase {

  @Override
  public void setUp() throws Exception {

    super.setUp();
    annotations = "org.chromattic.api.annotations.PrimaryType";

  }

  public void testSimpleValue() throws Exception {

    MetaModel metaModel = buildClass("B_Chromattic");
    assertEquals(1, metaModel.getAnnotations().size());
    assertEquals("@PrimaryType", metaModel.getAnnotations().get(0).getName());
    assertEquals(3, metaModel.getAnnotations().get(0).getValues().size());

    Map<String, ? extends Object> data = metaModel.getAnnotations().get(0).getValues();
    assertEquals("b", data.get("name"));
    assertEquals("true", data.get("orderable"));
    assertEquals("true", data.get("abstract_"));

  }
}