package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUIFHD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_INQ_FHIST = "BP-R-INQ-FHIST";
    String CPN_CI_CIZACCU_CN = "CI-INQ-ACCU";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String WS_COMPONENT_NAME = " ";
    String WS_CI_NO = " ";
    BPZUIFHD_WS_OUTPUT_8020 WS_OUTPUT_8020 = new BPZUIFHD_WS_OUTPUT_8020();
    BPZUIFHD_WS_AC_BASE_INFO_CN WS_AC_BASE_INFO_CN = new BPZUIFHD_WS_AC_BASE_INFO_CN();
    String WS_ERR_MSG = " ";
    char WS_TLR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    BPCO000 BPCO000 = new BPCO000();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICACCU CICACCU = new CICACCU();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCUIFHD BPCUIFHD;
    public void MP(SCCGWA SCCGWA, BPCUIFHD BPCUIFHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUIFHD = BPCUIFHD;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "*** NEW BPZUIFHD ***");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUIFHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUIFHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQ_FHIST_DETIAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_FHIST_DETIAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIFHD);
        IBS.init(SCCGWA, BPCIFHIS);
        IBS.init(SCCGWA, BPCQFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPCIFHIS.INPUT.FUNC = '5';
        BPRFHIST.KEY.AC_DT = BPCUIFHD.INPUT.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCUIFHD.INPUT.JRNNO;
        BPRFHIST.KEY.JRN_SEQ = BPCUIFHD.INPUT.JRN_SEQ;
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REC_LEN);
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        if (BPCIFHIS.OUTPUT.RC.RC_CODE == 0) {
            if (BPRFHIST.KEY.AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
                if (BPRFHIST.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_TLR_FLG = 'Y';
                } else {
                    if (BPRFHIST.TX_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                        || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 43400) {
                        WS_TLR_FLG = 'Y';
                    } else {
                        CEP.TRC(SCCGWA, "*** CAN-TLR-MUST-TXBR ***");
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXBR, BPCUIFHD.RC);
                    }
                }
            } else {
                if (BPRFHIST.TX_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                    || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 43400) {
                    WS_TLR_FLG = 'Y';
                } else {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAN_TLR_M_IN_TXBR, BPCUIFHD.RC);
                }
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, BPCO000);
        R000_FORMAT_DATA();
        if (pgmRtn) return;
        SCCFMT.FMTID = "BP000";
        CEP.TRC(SCCGWA, "3333333");
        SCCFMT.DATA_PTR = BPCO000;
        SCCFMT.DATA_LEN = 1325;
        CEP.TRC(SCCGWA, "1111111");
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "2222222");
        CEP.TRC(SCCGWA, "WILL_IF");
        CEP.TRC(SCCGWA, "4444444");
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "5555555");
        SCCFMT.DATA_PTR = BPCQFHIS;
        SCCFMT.DATA_LEN = 2000;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_FORMAT_DATA() throws IOException,SQLException,Exception {
        BPCO000.AC_DT = BPRFHIST.KEY.AC_DT;
        BPCO000.JRNNO = BPRFHIST.KEY.JRNNO;
        BPCO000.JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
        BPCO000.VCHNO = BPRFHIST.VCHNO;
        BPCO000.AC = BPRFHIST.KEY.AC;
        BPCO000.REF_NO = BPRFHIST.REF_NO;
        BPCO000.TX_TOOL = BPRFHIST.TX_TOOL;
        BPCO000.RLT_AC = BPRFHIST.RLT_AC;
        BPCO000.RLT_REF_NO = BPRFHIST.RLT_REF_NO;
        BPCO000.APP_MMO = BPRFHIST.APP_MMO;
        BPCO000.TX_CD = BPRFHIST.TX_CD;
        BPCO000.TX_MMO = BPRFHIST.TX_MMO;
        BPCO000.TX_STS = BPRFHIST.TX_STS;
        BPCO000.TX_DT = BPRFHIST.TX_DT;
        BPCO000.TX_TM = BPRFHIST.TX_TM;
        BPCO000.TX_VAL_DT = BPRFHIST.TX_VAL_DT;
        BPCO000.TX_REV_DT = BPRFHIST.TX_REV_DT;
        BPCO000.PRINT_FLG = BPRFHIST.PRINT_FLG;
        BPCO000.TX_CCY = BPRFHIST.TX_CCY;
        BPCO000.TX_AMT = BPRFHIST.TX_AMT;
        BPCO000.SUMUP_FLG = BPRFHIST.SUMUP_FLG;
        BPCO000.DRCRFLG = BPRFHIST.DRCRFLG;
        BPCO000.PROD_CD = BPRFHIST.PROD_CD;
        BPCO000.CI_NO = BPRFHIST.CI_NO;
        BPCO000.TX_BR = BPRFHIST.TX_BR;
        BPCO000.TX_DP = BPRFHIST.TX_DP;
        BPCO000.REMARK = BPRFHIST.REMARK;
        BPCO000.TX_CHNL = BPRFHIST.TX_CHNL;
        BPCO000.TX_TLR = BPRFHIST.TX_TLR;
        BPCO000.SUP1 = BPRFHIST.SUP1;
        BPCO000.SUP2 = BPRFHIST.SUP2;
        BPCO000.MAKER_TLR = BPRFHIST.MAKER;
        BPCO000.TX_REQ_CHNL = BPRFHIST.TX_REQ_CHNL;
        BPCO000.TX_CHNL_DTL = BPRFHIST.TX_CHNL_DTL;
        BPCO000.TX_TYPE = BPRFHIST.TX_TYPE;
        BPCO000.ACO_AC = BPRFHIST.ACO_AC;
        BPCO000.RLT_AC_CHNM = BPRFHIST.RLT_AC_NAME;
        BPCO000.RLT_OPN_BR_CHN = BPRFHIST.RLT_BK_NM;
        BPCO000.RLT_OPN_BR = BPRFHIST.RLT_BANK;
        BPCO000.CITY_FLG = BPRFHIST.CITY_FLG;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_FORMAT_DATA_CN();
            if (pgmRtn) return;
        }
    }
    public void R000_FORMAT_DATA_CN() throws IOException,SQLException,Exception {
        BPCO000.BV_CODE = BPRFHIST.BV_CODE;
        BPCO000.HEAD_NO = BPRFHIST.HEAD_NO;
        BPCO000.BV_NO = BPRFHIST.BV_NO;
        BPCO000.COM_PROD = BPRFHIST.COM_PROD;
        BPCO000.NARRATIVE = BPRFHIST.NARRATIVE;
        BPCO000.CCY_TYPE = BPRFHIST.TX_CCY_TYPE;
        BPCO000.TX_TYPE = BPRFHIST.TX_TYPE;
        BPCO000.ACO_AC = BPRFHIST.ACO_AC;
        if (BPCO000.ACO_AC.trim().length() > 0) {
            CICQACAC.DATA.ACAC_NO = BPCO000.ACO_AC;
            CICQACAC.FUNC = 'A';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            BPCO000.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        }
        IBS.init(SCCGWA, WS_AC_BASE_INFO_CN);
        WS_AC_BASE_INFO_CN.WS_AC_NO_CN = BPRFHIST.KEY.AC;
        CEP.TRC(SCCGWA, WS_AC_BASE_INFO_CN.WS_AC_NO_CN);
        if (BPRFHIST.SUMUP_FLG != '5') {
            R000_GET_AC_INFO_CN();
            if (pgmRtn) return;
        }
        BPCO000.TX_OPN_BR = WS_AC_BASE_INFO_CN.WS_OPN_BR_CN;
        CEP.TRC(SCCGWA, BPCO000.TX_OPN_BR);
        BPCO000.TX_OPN_BR_CHN = WS_AC_BASE_INFO_CN.WS_OPN_BR_CHN_CN;
        BPCO000.TX_AC_CHNM = WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN;
        if (BPCO000.CI_NO.trim().length() == 0) {
            BPCO000.CI_NO = WS_CI_NO;
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        if (!CICACCU.DATA.FRM_APP.equalsIgnoreCase("LN") 
            && BPRFHIST.SUMUP_FLG != '5') {
            if (BPRFHIST.KEY.AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
                R000_GET_AC_BAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_E);
                BPCO000.CUR_BAL = BPCUIBAL.OUTPUT.BAL_E;
            } else {
                BPCO000.CUR_BAL = BPRFHIST.CURR_BAL;
            }
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = BPRFHIST.ACO_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
            || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
            BPCO000.CUR_BAL = BPCO000.CUR_BAL * ( -1 );
        }
        if (BPRFHIST.RLT_AC.trim().length() > 0 
            || BPRFHIST.RLT_TX_TOOL.trim().length() > 0) {
            if (BPRFHIST.RLT_TX_TOOL.trim().length() > 0) {
                BPCO000.RLT_AC = BPRFHIST.RLT_TX_TOOL;
            } else {
                BPCO000.RLT_AC = BPRFHIST.RLT_AC;
            }
            if (BPRFHIST.RLT_BANK.trim().length() > 0) {
                BPCO000.RLT_AC_CHNM = BPRFHIST.RLT_AC_NAME;
                BPCO000.RLT_OPN_BR = BPRFHIST.RLT_BANK;
                BPCO000.RLT_OPN_BR_CHN = BPRFHIST.RLT_BK_NM;
                CEP.TRC(SCCGWA, BPCO000.RLT_OPN_BR_CHN);
            } else {
                if (BPRFHIST.RLT_BANK == null) BPRFHIST.RLT_BANK = "";
                JIBS_tmp_int = BPRFHIST.RLT_BANK.length();
                for (int i=0;i<14-JIBS_tmp_int;i++) BPRFHIST.RLT_BANK += " ";
                if (BPRFHIST.RLT_BANK.substring(7 - 1, 7 + 1 - 1).trim().length() == 0) {
                    IBS.init(SCCGWA, WS_AC_BASE_INFO_CN);
                    WS_AC_BASE_INFO_CN.WS_AC_NO_CN = BPCO000.RLT_AC;
                    R000_GET_AC_INFO_CN();
                    if (pgmRtn) return;
                    if (WS_AC_BASE_INFO_CN.WS_OPN_BR_CN != 0) {
                        BPCO000.RLT_OPN_BR = "" + WS_AC_BASE_INFO_CN.WS_OPN_BR_CN;
                        JIBS_tmp_int = BPCO000.RLT_OPN_BR.length();
                        for (int i=0;i<6-JIBS_tmp_int;i++) BPCO000.RLT_OPN_BR = "0" + BPCO000.RLT_OPN_BR;
                        BPCO000.RLT_OPN_BR_CHN = WS_AC_BASE_INFO_CN.WS_OPN_BR_CHN_CN;
                        BPCO000.RLT_AC_CHNM = WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN;
                    }
                }
            }
            CEP.TRC(SCCGWA, BPCO000.RLT_OPN_BR);
        } else {
            IBS.init(SCCGWA, WS_AC_BASE_INFO_CN);
            WS_AC_BASE_INFO_CN.WS_AC_NO_CN = BPRFHIST.OTH_TX_TOOL;
            if (BPRFHIST.OTH_TX_TOOL.trim().length() == 0 
                && BPRFHIST.OTH_AC.trim().length() > 0) {
                WS_AC_BASE_INFO_CN.WS_AC_NO_CN = BPRFHIST.OTH_AC;
            }
            R000_GET_AC_INFO_CN();
            if (pgmRtn) return;
            BPCO000.RLT_AC = WS_AC_BASE_INFO_CN.WS_AC_NO_CN;
            BPCO000.RLT_OPN_BR = "" + WS_AC_BASE_INFO_CN.WS_OPN_BR_CN;
            JIBS_tmp_int = BPCO000.RLT_OPN_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCO000.RLT_OPN_BR = "0" + BPCO000.RLT_OPN_BR;
            CEP.TRC(SCCGWA, BPCO000.RLT_OPN_BR);
            BPCO000.RLT_OPN_BR_CHN = WS_AC_BASE_INFO_CN.WS_OPN_BR_CHN_CN;
            if (WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN.trim().length() > 0) {
                BPCO000.RLT_AC_CHNM = WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN;
            } else {
                BPCO000.RLT_AC_CHNM = BPRFHIST.RLT_AC_NAME;
                CEP.TRC(SCCGWA, BPCO000.RLT_AC_CHNM);
            }
        }
        if (BPCO000.RLT_OPN_BR.equalsIgnoreCase("000000000")) {
            BPCO000.RLT_OPN_BR = " ";
        }
    }
    public void R000_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUIBAL);
        BPCUIBAL.INPUT.AC_DT = BPRFHIST.KEY.AC_DT;
        BPCUIBAL.INPUT.JRNNO = BPRFHIST.KEY.JRNNO;
        BPCUIBAL.INPUT.JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
        BPCUIBAL.INPUT.AC_IN = BPRFHIST.ACO_AC;
        BPCUIBAL.INPUT.CCY = BPRFHIST.TX_CCY;
        BPCUIBAL.INPUT.CCY_TYP = BPRFHIST.TX_CCY_TYPE;
        BPCUIBAL.INPUT.TX_AMT_IN = BPRFHIST.TX_AMT;
        BPCUIBAL.INPUT.TX_DRCRFLG = BPRFHIST.DRCRFLG;
        S000_CALL_BPZUIBAL();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUIBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-BAL", BPCUIBAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIBAL.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUIFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_AC_INFO_CN() throws IOException,SQLException,Exception {
        if (WS_AC_BASE_INFO_CN.WS_AC_NO_CN.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = WS_AC_BASE_INFO_CN.WS_AC_NO_CN;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
            if (CICACCU.RC.RC_CODE == 0) {
                if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                    WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN = CICACCU.DATA.AC_CNM;
                } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                    WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN = CICACCU.DATA.AC_ENM;
                } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                    WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN = CICACCU.DATA.CI_CNM;
                } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                    WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN = CICACCU.DATA.CI_ENM;
                } else {
                    WS_AC_BASE_INFO_CN.WS_AC_CHNM_CN = " ";
                }
                WS_AC_BASE_INFO_CN.WS_OPN_BR_CN = CICACCU.DATA.OPN_BR;
                CEP.TRC(SCCGWA, WS_AC_BASE_INFO_CN.WS_OPN_BR_CN);
                if (WS_AC_BASE_INFO_CN.WS_OPN_BR_CN != 0) {
                    CEP.TRC(SCCGWA, WS_AC_BASE_INFO_CN.WS_OPN_BR_CN);
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = WS_AC_BASE_INFO_CN.WS_OPN_BR_CN;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    WS_AC_BASE_INFO_CN.WS_OPN_BR_CHN_CN = BPCPQORG.CHN_NM;
                }
                WS_CI_NO = CICACCU.DATA.CI_NO;
            }
        }
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZACCU_CN, CICACCU);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE == BPCMSG_ERROR_MSG.BP_NORMAL) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUIFHD.RC);
        }
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INQ_FHIST, BPCIFHIS);
        CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUIFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUIFHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUIFHD = ");
            CEP.TRC(SCCGWA, BPCUIFHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
