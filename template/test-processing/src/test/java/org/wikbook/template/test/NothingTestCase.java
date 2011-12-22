package org.wikbook.template.test;

import org.wikbook.template.processing.metamodel.MetaModel;

import java.io.FileNotFoundException;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class NothingTestCase extends AbstractProcessorTestCase {

  @Override
  public void setUp() throws Exception {

    super.setUp();
  }

  /*public void testA() throws Exception {

    try {
      buildClass("A");
      fail();
    }
    catch (FileNotFoundException e) {
      // no processing done
    }
    
  }*/

  public void testB() throws Exception {

    MetaModel metaModel = buildClass("B");
    assertNotNull(metaModel);

  }

}