/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2017 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.apache.hop.pipeline.transforms.detectemptystream;

import org.apache.hop.core.CheckResult;
import org.apache.hop.core.CheckResultInterface;
import org.apache.hop.core.annotations.Transform;
import org.apache.hop.core.exception.HopXMLException;
import org.apache.hop.core.row.RowMetaInterface;
import org.apache.hop.core.variables.VariableSpace;
import org.apache.hop.i18n.BaseMessages;
import org.apache.hop.metastore.api.IMetaStore;
import org.apache.hop.pipeline.Pipeline;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.PipelineMeta.PipelineType;
import org.apache.hop.pipeline.transform.*;
import org.w3c.dom.Node;

import java.util.List;

/**
 * @author Samatar
 * @since 30-08-2008
 */
@Transform(
        id = "DetectEmptyStream",
        image = "ui/images/EMS.svg",
        i18nPackageName = "org.apache.hop.pipeline.transforms.detectemptystream",
        name = "BaseTransform.TypeLongDesc.DetectEmptyStream",
        description = "BaseTransform.TypeTooltipDesc.DetectEmptyStream",
        categoryDescription = "i18n:org.apache.hop.pipeline.transform:BaseTransform.Category.Flow"
)
public class DetectEmptyStreamMeta extends BaseTransformMeta implements TransformMetaInterface<DetectEmptyStream, DetectEmptyStreamData> {
  private static Class<?> PKG = DetectEmptyStreamMeta.class; // for i18n purposes, needed by Translator!!

  public DetectEmptyStreamMeta() {
    super(); // allocate BaseTransformMeta
  }

  public void loadXML( Node transformNode, IMetaStore metaStore ) throws HopXMLException {
    readData( transformNode );
  }

  public Object clone() {
    Object retval = super.clone();
    return retval;
  }

  private void readData( Node transformNode ) {
  }

  public void setDefault() {
  }

  public void check( List<CheckResultInterface> remarks, PipelineMeta pipelineMeta, TransformMeta transformMeta,
                     RowMetaInterface prev, String[] input, String[] output, RowMetaInterface info, VariableSpace space,
                     IMetaStore metaStore ) {
    CheckResult cr;
    if ( prev == null || prev.size() == 0 ) {
      cr =
        new CheckResult( CheckResult.TYPE_RESULT_WARNING, BaseMessages.getString(
          PKG, "DetectEmptyStreamMeta.CheckResult.NotReceivingFields" ), transformMeta );
      remarks.add( cr );
    } else {
      cr =
        new CheckResult( CheckResult.TYPE_RESULT_OK, BaseMessages.getString(
          PKG, "DetectEmptyStreamMeta.CheckResult.TransformRecevingData", prev.size() + "" ), transformMeta );
      remarks.add( cr );
    }

    // See if we have input streams leading to this transform!
    if ( input.length > 0 ) {
      cr =
        new CheckResult( CheckResult.TYPE_RESULT_OK, BaseMessages.getString(
          PKG, "DetectEmptyStreamMeta.CheckResult.TransformRecevingData2" ), transformMeta );
      remarks.add( cr );
    } else {
      cr =
        new CheckResult( CheckResult.TYPE_RESULT_ERROR, BaseMessages.getString(
          PKG, "DetectEmptyStreamMeta.CheckResult.NoInputReceivedFromOtherTransforms" ), transformMeta );
      remarks.add( cr );
    }
  }

  public DetectEmptyStream createTransform( TransformMeta transformMeta, DetectEmptyStreamData transformDataInterface, int cnr, PipelineMeta tr,
                                            Pipeline pipeline ) {
    return new DetectEmptyStream( transformMeta, transformDataInterface, cnr, tr, pipeline );
  }

  public DetectEmptyStreamData getTransformData() {
    return new DetectEmptyStreamData();
  }

  public PipelineType[] getSupportedPipelineTypes() {
    return new PipelineType[] { PipelineType.Normal, };
  }

  @Override
  public String getDialogClassName(){
    return DetectEmptyStreamDialog.class.getName();
  }
}