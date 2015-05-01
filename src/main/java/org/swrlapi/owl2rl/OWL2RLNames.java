package org.swrlapi.owl2rl;

/**
 * Defines names for OWL 2 RL tables and rules and describes the allocation of rules to rule tables.
 *
 * @see org.swrlapi.owl2rl.OWL2RLEngine
 */
public interface OWL2RLNames
{
  public final static String SWRLA_NAMESPACE = "http://swrl.stanford.edu/ontologies/3.3/swrla.owl#";

  enum OWL2RLRuleTable {
    RuleTable4, RuleTable5, RuleTable6, RuleTable7, RuleTable8, RuleTable9
  }

  enum OWL2RLRule {
    EQ_REF, EQ_SYM, EQ_TRANS, EQ_REP_S, EQ_REP_P, EQ_REP_O, EQ_DIFF1, EQ_DIFF2, EQ_DIFF3, PRP_AP, PRP_DOM, PRP_RNG, PRP_FP, PRP_IFP, PRP_IRP, PRP_SYMP, PRP_ASYP, PRP_TRP, PRP_SPO1, PRP_SPO2, PRP_EQP1, PRP_EQP2, PRP_PDW, PRP_ADP, PRP_INV1, PRP_INV2, PRP_KEY, PRP_NPA1, PRP_NPA2, CLS_THING, CLS_NOTHING1, CLS_NOTHING2, CLS_INT1, CLS_INT2, CLS_UNI, CLS_COM, CLS_SFV1, CLS_SFV2, CLS_AVF, CLS_HV1, CLS_HV2, CLS_MAXC1, CLS_MAXC2, CLS_MAXQC1, CLS_MAXQC2, CLS_MAXQC3, CLS_MAXQC4, CLS_OO, CAX_SCO, CAX_EQC1, CAX_EQC2, CAX_DW, CAX_ADC, DT_TYPE1, DT_TYPE2, DT_EQ, DT_DIFF, DT_NOT_TYPE, SCM_CLS, SCM_SCO, SCM_EQC1, SCM_EQC2, SCM_OP, SCM_DP, SCM_SPO, SCM_EQP1, SCM_EQP2, SCM_DOM1, SCM_DOM2, SCM_RNG1, SCM_RNG2, SCM_HV, SCM_SVF1, SCM_SVF2, SCM_AVF1, SCM_AVF2, SCM_INT, SCM_UNI
  }

  OWL2RLRule[] Table4Rules = { OWL2RLRule.EQ_REF, OWL2RLRule.EQ_SYM, OWL2RLRule.EQ_TRANS, OWL2RLRule.EQ_REP_S,
      OWL2RLRule.EQ_REP_P, OWL2RLRule.EQ_REP_O, OWL2RLRule.EQ_DIFF1, OWL2RLRule.EQ_DIFF2, OWL2RLRule.EQ_DIFF3 };

  OWL2RLRule[] Table5Rules = { OWL2RLRule.PRP_AP, OWL2RLRule.PRP_DOM, OWL2RLRule.PRP_RNG, OWL2RLRule.PRP_FP,
      OWL2RLRule.PRP_IFP, OWL2RLRule.PRP_IRP, OWL2RLRule.PRP_SYMP, OWL2RLRule.PRP_ASYP, OWL2RLRule.PRP_TRP,
      OWL2RLRule.PRP_SPO1, OWL2RLRule.PRP_SPO2, OWL2RLRule.PRP_EQP1, OWL2RLRule.PRP_EQP2, OWL2RLRule.PRP_PDW,
      OWL2RLRule.PRP_ADP, OWL2RLRule.PRP_INV1, OWL2RLRule.PRP_INV2, OWL2RLRule.PRP_KEY, OWL2RLRule.PRP_NPA1,
      OWL2RLRule.PRP_NPA2 };

  OWL2RLRule[] Table6Rules = { OWL2RLRule.CLS_THING, OWL2RLRule.CLS_NOTHING1, OWL2RLRule.CLS_NOTHING2,
      OWL2RLRule.CLS_INT1, OWL2RLRule.CLS_INT2, OWL2RLRule.CLS_UNI, OWL2RLRule.CLS_COM, OWL2RLRule.CLS_SFV1,
      OWL2RLRule.CLS_SFV2, OWL2RLRule.CLS_AVF, OWL2RLRule.CLS_HV1, OWL2RLRule.CLS_HV2, OWL2RLRule.CLS_MAXC1,
      OWL2RLRule.CLS_MAXC2, OWL2RLRule.CLS_MAXQC1, OWL2RLRule.CLS_MAXQC2, OWL2RLRule.CLS_MAXQC3, OWL2RLRule.CLS_MAXQC4,
      OWL2RLRule.CLS_OO };

  OWL2RLRule[] Table7Rules = { OWL2RLRule.CAX_SCO, OWL2RLRule.CAX_EQC1, OWL2RLRule.CAX_EQC2, OWL2RLRule.CAX_DW,
      OWL2RLRule.CAX_ADC };

  OWL2RLRule[] Table8Rules = { OWL2RLRule.DT_TYPE1, OWL2RLRule.DT_TYPE2, OWL2RLRule.DT_EQ, OWL2RLRule.DT_DIFF,
      OWL2RLRule.DT_NOT_TYPE };

  OWL2RLRule[] Table9Rules = { OWL2RLRule.SCM_CLS, OWL2RLRule.SCM_SCO, OWL2RLRule.SCM_EQC1, OWL2RLRule.SCM_EQC2,
      OWL2RLRule.SCM_OP, OWL2RLRule.SCM_DP, OWL2RLRule.SCM_SPO, OWL2RLRule.SCM_EQP1, OWL2RLRule.SCM_EQP2,
      OWL2RLRule.SCM_DOM1, OWL2RLRule.SCM_DOM2, OWL2RLRule.SCM_RNG1, OWL2RLRule.SCM_RNG2, OWL2RLRule.SCM_HV,
      OWL2RLRule.SCM_SVF1, OWL2RLRule.SCM_SVF2, OWL2RLRule.SCM_AVF1, OWL2RLRule.SCM_AVF2, OWL2RLRule.SCM_INT,
      OWL2RLRule.SCM_UNI };

  public static interface Annotations
  {
    public final static String OWL2RL_RULE = (SWRLA_NAMESPACE + "OWL2RLRule").intern();
    public final static String IS_OWL2RL_RULE_ENABLED = (SWRLA_NAMESPACE + "isOWL2RLRuleEnabled").intern();
  }
}
