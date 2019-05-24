package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRPRMT;
import com.hisun.DD.DDVMPRD;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCPRDP;
import com.hisun.TD.TDCPROD;
import com.hisun.TD.TDCQPMP;

public class DCZSFPRD {
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_CON_MDEL = "IRDD";
    String K_OUT_FMT = "DC944";
    String K_HIS_CPB_NM = "DDCHFPRD";
    String K_HIS_RMKS = "IRPRD PARM MAINTENANCE";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_BASIC_PROD_CD = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCHFPRD DCCFPRDO = new DCCHFPRD();
    DCCHFPRD DCCFPRDN = new DCCHFPRD();
    DCCOFPRD DCCOFPRD = new DCCOFPRD();
    DCRIRPRD DCRIRPRD = new DCRIRPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DCCUMPRM DCCUMPRM = new DCCUMPRM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCUPARM DDCUPARM = new DDCUPARM();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    DCCSFPRD DCCSFPRD;
    public void MP(SCCGWA SCCGWA, DCCSFPRD DCCSFPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSFPRD = DCCSFPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSFPRD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCSFPRD.FUNC == 'I') {
            B020_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B031_QUERY_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSFPRD.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B051_ADD_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSFPRD.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B071_MODIFY_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSFPRD.FUNC == 'D') {
            B020_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B091_DELETE_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSFPRD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSFPRD.FUNC);
        CEP.TRC(SCCGWA, DCCSFPRD.PROD_CODE);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_DESC);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.EFFDAT);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.EXPDAT);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.CI_TYP);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.CCY);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.OVR_CARD_FLG);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_DD_INFO[1-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_DD_INFO[3-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_DD_INFO[5-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_DD_INFO[10-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_TD_INFO[1-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_TD_INFO[3-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_TD_INFO[5-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.PROD_TD_INFO[10-1]);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.USAGE_MTH);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.DRAW_MTH);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.DRAW_ORDER);
        if (DCCSFPRD.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSFPRD.VAL.PROD_DESC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DSC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSFPRD.VAL.EFFDAT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCCSFPRD.VAL.EFFDAT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSFPRD.VAL.EXPDAT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCCSFPRD.VAL.EXPDAT;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSFPRD.VAL.EXPDAT < DCCSFPRD.VAL.EFFDAT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DATE_LT_EFFDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSFPRD.VAL.CI_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSFPRD.VAL.CI_TYP != '1' 
                && DCCSFPRD.VAL.CI_TYP != '2' 
                && DCCSFPRD.VAL.CI_TYP != '3') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CUS_TYP_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSFPRD.VAL.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DCCSFPRD.VAL.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCQCCY.DATA.TR_FLG);
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CHG_CCY);
            if (BPCQCCY.DATA.TR_FLG != 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSFPRD.VAL.CI_TYP == '1') {
            B010_10_CHECK_PER_DATA();
            if (pgmRtn) return;
        } else if (DCCSFPRD.VAL.CI_TYP == '2') {
            B010_20_CHECK_COM_DATA();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CUS_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSFPRD.VAL.PROD_DD_INFO[1-1].PROD_DD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_INFO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            if (DCCSFPRD.VAL.PROD_DD_INFO[WS_CNT-1].PROD_DD.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = DCCSFPRD.VAL.PROD_DD_INFO[WS_CNT-1].PROD_DD;
                CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, DDVMPRD);
                IBS.init(SCCGWA, DDCUPARM);
                DDCUPARM.TX_TYPE = 'I';
                DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
                DDCUPARM.DATA.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
                DDCUPARM.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_DDZUPARM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCUPARM.RC);
                if (DDCUPARM.RC.RC_CODE != 0) {
                    CEP.ERR(SCCGWA, DDCUPARM.RC);
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
                    CEP.TRC(SCCGWA, DDVMPRD);
                }
                B010_30_CHK_DD_PRD_DATA();
                if (pgmRtn) return;
            }
        }
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            if (DCCSFPRD.VAL.PROD_TD_INFO[WS_CNT-1].PROD_TD.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = DCCSFPRD.VAL.PROD_TD_INFO[WS_CNT-1].PROD_TD;
                CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, TDCQPMP);
                IBS.init(SCCGWA, TDCPROD);
                IBS.init(SCCGWA, TDCPRDP);
                TDCQPMP.FUNC = 'I';
                TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
                TDCQPMP.DAT_PTR = TDCPROD;
                S000_CALL_TDZQPMP();
                if (pgmRtn) return;
                TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
                CEP.TRC(SCCGWA, TDCPRDP);
                B010_40_CHK_TD_PRD_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_10_CHECK_PER_DATA() throws IOException,SQLException,Exception {
        if (DCCSFPRD.VAL.PERM_INFO[1-1].PERM_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_MTH_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.INOUT_FG);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.OUT_LVL);
        CEP.TRC(SCCGWA, DCCSFPRD.VAL.IN_LVL);
        if (DCCSFPRD.VAL.INOUT_FG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INOUT_ALLOW_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSFPRD.VAL.INOUT_FG == '0') {
                if (DCCSFPRD.VAL.OUT_LVL == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OUT_LVL_M_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (DCCSFPRD.VAL.OUT_LVL < 1 
                        || DCCSFPRD.VAL.OUT_LVL > 99) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OUT_LVL_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else if (DCCSFPRD.VAL.INOUT_FG == '1') {
                if (DCCSFPRD.VAL.IN_LVL == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IN_LVL_M_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (DCCSFPRD.VAL.IN_LVL < 1 
                        || DCCSFPRD.VAL.IN_LVL > 99) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IN_LVL_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else if (DCCSFPRD.VAL.INOUT_FG == '2') {
                if (DCCSFPRD.VAL.IN_LVL == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IN_LVL_M_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (DCCSFPRD.VAL.IN_LVL < 1 
                        || DCCSFPRD.VAL.IN_LVL > 99) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IN_LVL_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (DCCSFPRD.VAL.OUT_LVL == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OUT_LVL_M_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (DCCSFPRD.VAL.OUT_LVL < 1 
                        || DCCSFPRD.VAL.OUT_LVL > 99) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OUT_LVL_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INOUT_ALLOW_INVAD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSFPRD.VAL.OVR_BANK_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CROBK_FLG_MUST_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCSFPRD.VAL.OVR_CARD_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CRD_FLG_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSFPRD.VAL.OVR_CARD_FLG != '0' 
                && DCCSFPRD.VAL.OVR_CARD_FLG != '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CRD_FLG_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_20_CHECK_COM_DATA() throws IOException,SQLException,Exception {
    }
    public void B010_30_CHK_DD_PRD_DATA() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_PER_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_40_CHK_TD_PRD_DATA() throws IOException,SQLException,Exception {
        if (!DDVMPRD.KEY.PRDMO_CD.equalsIgnoreCase("MMDP")) {
        }
    }
    public void B020_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSFPRD.FUNC);
        CEP.TRC(SCCGWA, DCCSFPRD.PROD_CODE);
        if (DCCSFPRD.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCCSFPRD.PROD_CODE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_BASIC_PROD_CD = BPCPQPRD.PARM_CODE;
                CEP.TRC(SCCGWA, WS_BASIC_PROD_CD);
            }
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DCRIRPRD);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "999999999" + BPRPRMT.KEY.CD.substring(9);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (WS_BASIC_PROD_CD == null) WS_BASIC_PROD_CD = "";
        JIBS_tmp_int = WS_BASIC_PROD_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_BASIC_PROD_CD += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 10 - 1) + WS_BASIC_PROD_CD + BPRPRMT.KEY.CD.substring(10 + 10 - 1);
        CEP.TRC(SCCGWA, "1");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRIRPRD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IRPRD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
