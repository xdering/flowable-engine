package org.flowable.test.cmmn.converter;

import static org.flowable.cmmn.model.Criterion.EXIT_EVENT_TYPE_COMPLETE;
import static org.flowable.cmmn.model.Criterion.EXIT_EVENT_TYPE_EXIT;
import static org.flowable.cmmn.model.Criterion.EXIT_EVENT_TYPE_FORCE_COMPLETE;
import static org.flowable.cmmn.model.Criterion.EXIT_TYPE_ACTIVE_AND_ENABLED_INSTANCES;
import static org.flowable.cmmn.model.Criterion.EXIT_TYPE_ACTIVE_INSTANCES;
import static org.flowable.cmmn.model.Criterion.EXIT_TYPE_DEFAULT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.flowable.cmmn.model.CmmnModel;
import org.junit.Test;

public class ExitSentryExtendedAttributesConverterTest extends AbstractConverterTest {

    private static final String CMMN_RESOURCE = "org/flowable/test/cmmn/converter/exitSentryAttributesOnStageAndPlanModel.cmmn";

    @Test
    public void convertXMLToModel() throws Exception {
        CmmnModel cmmnModel = readXMLFile(CMMN_RESOURCE);
        validateModel(cmmnModel);
    }

    @Test
    public void convertModelToXML() throws Exception {
        CmmnModel cmmnModel = readXMLFile(CMMN_RESOURCE);
        CmmnModel parsedModel = exportAndReadXMLFile(cmmnModel);
        validateModel(parsedModel);
    }

    public void validateModel(CmmnModel cmmnModel) {
        assertNotNull(cmmnModel);

        assertCriterionExitEventType(cmmnModel, "exitCriterion1", null);
        assertCriterionExitEventType(cmmnModel, "exitCriterion2", EXIT_EVENT_TYPE_EXIT);
        assertCriterionExitEventType(cmmnModel, "exitCriterion3", EXIT_EVENT_TYPE_COMPLETE);
        assertCriterionExitEventType(cmmnModel, "exitCriterion4", EXIT_EVENT_TYPE_FORCE_COMPLETE);

        assertCriterionExitEventType(cmmnModel, "exitCriterion5", null);
        assertCriterionExitEventType(cmmnModel, "exitCriterion6", EXIT_EVENT_TYPE_EXIT);
        assertCriterionExitEventType(cmmnModel, "exitCriterion7", EXIT_EVENT_TYPE_COMPLETE);
        assertCriterionExitEventType(cmmnModel, "exitCriterion8", EXIT_EVENT_TYPE_FORCE_COMPLETE);

        assertCriterionExitType(cmmnModel, "exitCriterion9", null);
        assertCriterionExitType(cmmnModel, "exitCriterion10", EXIT_TYPE_DEFAULT);
        assertCriterionExitType(cmmnModel, "exitCriterion11", EXIT_TYPE_ACTIVE_INSTANCES);
        assertCriterionExitType(cmmnModel, "exitCriterion12", EXIT_TYPE_ACTIVE_AND_ENABLED_INSTANCES);
    }

    protected void assertCriterionExitEventType(CmmnModel cmmnModel, String criterionId, String expectedExitEventType) {
        assertNotNull(cmmnModel.getCriterion(criterionId));
        assertEquals(expectedExitEventType, cmmnModel.getCriterion(criterionId).getExitEventType());
    }

    protected void assertCriterionExitType(CmmnModel cmmnModel, String criterionId, String expectedExitType) {
        assertNotNull(cmmnModel.getCriterion(criterionId));
        assertEquals(expectedExitType, cmmnModel.getCriterion(criterionId).getExitType());
    }
}
