package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1660 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "DD166";
    String K_ITM_NO = " ";
    String K_TD_CHQ_CODE = "JXZQ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    String WS_ACNO = " ";
    short WS_NUM1 = 0;
    DDOT1660_WS_FMT WS_FMT = new DDOT1660_WS_FMT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDRBVT TDRBVT = new TDRBVT();
    BPCSBVUO BPCSBVUO = new BPCSBVUO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICPQIA AICPQIA = new AICPQIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    TDCACE TDCACE = new TDCACE();
    TDCOACE TDCOACE = new TDCOACE();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCOCINM DDCOCINM = new DDCOCINM();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AICPUITM AICPUITM = new AICPUITM();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDB1660_AWA_1660 DDB1660_AWA_1660;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1660 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1660_AWA_1660>");
        DDB1660_AWA_1660 = (DDB1660_AWA_1660) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.PAY_TYP);
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.PAY_AMT);
        if (DDB1660_AWA_1660.PAY_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB1660_AWA_1660.PAY_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.PAY_TYP);
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.PAY_AMT);
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.STL_ACNO);
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.OPPBVNO);
        if (DDB1660_AWA_1660.PAY_TYP == '1') {
            B100_SPEC_DRW_PROC();
            R000_CR_AC_PROC();
        } else {
            if (DDB1660_AWA_1660.PAY_TYP == '2') {
                B200_INT_RESUP_PROC();
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 94;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B100_SPEC_DRW_PROC() throws IOException,SQLException,Exception {
        S000_QRY_AIPQIA();
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D);
        if (AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D != 0) {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.CCY = DDB1660_AWA_1660.CCY;
            AICUUPIA.DATA.AMT = DDB1660_AWA_1660.PAY_AMT;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "DR";
            S000_CALL_AIZUUPIA();
            CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        } else {
            IBS.init(SCCGWA, AICPUITM);
            AICPUITM.DATA.EVENT_CODE = "DR";
            AICPUITM.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICPUITM.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICPUITM.DATA.CCY = DDB1660_AWA_1660.CCY;
            AICPUITM.DATA.ITM_NO = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
            AICPUITM.DATA.AMT = DDB1660_AWA_1660.PAY_AMT;
            AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_AIZPUITM();
        }
    }
    public void R000_CR_AC_PROC() throws IOException,SQLException,Exception {
        if (DDB1660_AWA_1660.PAY_MTH == '0') {
            IBS.init(SCCGWA, BPCUSBOX);
            BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUSBOX.CCY = DDB1660_AWA_1660.CCY;
            BPCUSBOX.CCY_TYP = DDB1660_AWA_1660.CCY_TYP;
            BPCUSBOX.AMT = DDB1660_AWA_1660.PAY_AMT;
            BPCUSBOX.OPP_AC = WS_ACNO;
            BPCUSBOX.CASH_NO = "220";
            S000_CALL_BPZUSBOX();
            WS_FMT.WS_PAY_TYP = DDB1660_AWA_1660.PAY_TYP;
            WS_FMT.WS_PAY_AMT = BPCUSBOX.AMT;
            WS_FMT.WS_CCY = BPCUSBOX.CCY;
            WS_FMT.WS_CCY_TYP = BPCUSBOX.CCY_TYP;
            WS_FMT.WS_PAY_MTH = DDB1660_AWA_1660.PAY_MTH;
        } else {
            if (DDB1660_AWA_1660.PAY_MTH == '1') {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = DDB1660_AWA_1660.STL_ACNO;
                AICUUPIA.DATA.RVS_SEQ = 0;
                AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.AMT = DDB1660_AWA_1660.PAY_AMT;
                AICUUPIA.DATA.CCY = DDB1660_AWA_1660.CCY;
                AICUUPIA.DATA.EVENT_CODE = "CR";
                AICUUPIA.DATA.POST_NARR = " ";
                AICUUPIA.DATA.EVENT_CODE = "CR";
                AICUUPIA.DATA.PAY_MAN = DDB1660_AWA_1660.STL_ACNO;
                S000_CALL_AICUUPIA();
                WS_FMT.WS_PAY_TYP = DDB1660_AWA_1660.PAY_TYP;
                WS_FMT.WS_PAY_AMT = AICUUPIA.DATA.AMT;
                WS_FMT.WS_CCY = AICUUPIA.DATA.CCY;
                WS_FMT.WS_CCY_TYP = DDB1660_AWA_1660.CCY_TYP;
                WS_FMT.WS_PAY_MTH = DDB1660_AWA_1660.PAY_MTH;
                WS_FMT.WS_STL_ACNO = DDB1660_AWA_1660.STL_ACNO;
            } else {
                if (DDB1660_AWA_1660.PAY_MTH == '2') {
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.AGR_NO = DDB1660_AWA_1660.STL_ACNO;
                    CICQACAC.DATA.CCY_ACAC = DDB1660_AWA_1660.CCY;
                    CICQACAC.DATA.CR_FLG = DDB1660_AWA_1660.CCY_TYP;
                    CICQACAC.FUNC = 'C';
                    S000_CALL_CIZQACAC();
                    DDB1660_AWA_1660.OPPBVNO = CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO;
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = DDB1660_AWA_1660.STL_ACNO;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    IBS.init(SCCGWA, DDCUCRAC);
                    DDCUCRAC.AC = DDB1660_AWA_1660.STL_ACNO;
                    DDCUCRAC.PSBK_NO = DDB1660_AWA_1660.OPPBVNO;
                    DDCUCRAC.CCY = DDB1660_AWA_1660.CCY;
                    DDCUCRAC.CCY_TYPE = DDB1660_AWA_1660.CCY_TYP;
                    DDCUCRAC.TX_AMT = DDB1660_AWA_1660.PAY_AMT;
                    DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDCUCRAC.OTHER_AC = WS_ACNO;
                    DDCUCRAC.OTH_TX_TOOL = WS_ACNO;
                    DDCUCRAC.OTHER_CCY = DDB1660_AWA_1660.CCY;
                    DDCUCRAC.TX_MMO = "S106";
                    DDCUCRAC.TX_TYPE = 'T';
                    DDCUCRAC.BANK_CR_FLG = 'N';
                    S000_CALL_DDZUCRAC();
                    CEP.TRC(SCCGWA, DDCUCRAC.AC);
                    WS_FMT.WS_PAY_TYP = DDB1660_AWA_1660.PAY_TYP;
                    WS_FMT.WS_PAY_AMT = DDCUCRAC.TX_AMT;
                    WS_FMT.WS_CCY = DDCUCRAC.CCY;
                    WS_FMT.WS_CCY_TYP = DDCUCRAC.CCY_TYPE;
                    WS_FMT.WS_PAY_MTH = DDB1660_AWA_1660.PAY_MTH;
                    WS_FMT.WS_STL_ACNO = DDB1660_AWA_1660.STL_ACNO;
                } else {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B200_INT_RESUP_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1660_AWA_1660.AC_NO);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB1660_AWA_1660.AC_NO;
        CICQACAC.DATA.AGR_SEQ = DDB1660_AWA_1660.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DDB1660_AWA_1660.CCY;
        CICQACAC.DATA.CR_FLG = DDB1660_AWA_1660.CCY_TYP;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS != '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_CLO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = DDB1660_AWA_1660.AC_NO;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            || CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.A_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            S000_CALL_TDZACE();
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].CCY);
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = "MMDP";
            BPCPOEWA.DATA.PROD_CODE = TDCACE.DATA[1-1].PROD_CD;
            BPCPOEWA.DATA.EVENT_CODE = "DR";
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[4-1].AMT = DDB1660_AWA_1660.PAY_AMT;
            BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
            BPCPOEWA.DATA.DESC = "A004";
            BPCPOEWA.DATA.BR_OLD = TDCACE.DATA[1-1].CHE_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDCACE.DATA[1-1].CCY;
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            BPCPOEWA.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
            BPCPOEWA.DATA.REF_NO = " ";
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[5-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
            if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
                && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0 
                && BPCPOEWA.DATA.AMT_INFO[3-1].AMT == 0 
                && BPCPOEWA.DATA.AMT_INFO[4-1].AMT == 0 
                && BPCPOEWA.DATA.AMT_INFO[5-1].AMT == 0 
                && BPCPOEWA.DATA.AMT_INFO[6-1].AMT == 0 
                && BPCPOEWA.DATA.AMT_INFO[7-1].AMT == 0) {
            } else {
                S000_CALL_BPZPOEWA();
            }
        } else {
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
                || CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDCSCINM);
                DDCSCINM.INPUT_DATA.AC_NO = DDB1660_AWA_1660.AC_NO;
                DDCSCINM.INPUT_DATA.CCY = DDB1660_AWA_1660.CCY;
                DDCSCINM.INPUT_DATA.FUNC = '2';
                S000_CALL_DDZSCINM();
                CEP.TRC(SCCGWA, DDCOCINM.PROD_CODE);
                CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.PROD_CODE);
                IBS.init(SCCGWA, BPCPOEWA);
                BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
                BPCPOEWA.DATA.PROD_CODE = DDCSCINM.OUTPUT_DATA.PROD_CODE;
                BPCPOEWA.DATA.EVENT_CODE = "BX";
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = DDB1660_AWA_1660.PAY_AMT;
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
                BPCPOEWA.DATA.DESC = "A004";
                BPCPOEWA.DATA.BR_OLD = DDCSCINM.OUTPUT_DATA.OWNER_BR;
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDB1660_AWA_1660.CCY;
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                BPCPOEWA.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
                BPCPOEWA.DATA.REF_NO = " ";
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[5-1].AMT);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
                if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
                    && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0 
                    && BPCPOEWA.DATA.AMT_INFO[3-1].AMT == 0 
                    && BPCPOEWA.DATA.AMT_INFO[4-1].AMT == 0 
                    && BPCPOEWA.DATA.AMT_INFO[5-1].AMT == 0 
                    && BPCPOEWA.DATA.AMT_INFO[6-1].AMT == 0 
                    && BPCPOEWA.DATA.AMT_INFO[7-1].AMT == 0) {
                } else {
                    S000_CALL_BPZPOEWA();
                }
            }
        }
        WS_ACNO = DDB1660_AWA_1660.AC_NO;
        R000_CR_AC_PROC();
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_QRY_AIPQIA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "3";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = "BK001";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = K_TD_CHQ_CODE;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D);
        if (AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D != 0) {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.BUSI_KND = K_TD_CHQ_CODE;
            CEP.TRC(SCCGWA, AICPQIA.CD.BUSI_KND);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICPQIA.CCY = DDB1660_AWA_1660.CCY;
            AICPQIA.SIGN = 'D';
            S000_CALL_AIZPQIA();
            CEP.TRC(SCCGWA, AICPQIA.AC);
            if (AICPQIA.AC == null) AICPQIA.AC = "";
            JIBS_tmp_int = AICPQIA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
            K_ITM_NO = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1);
            CEP.TRC(SCCGWA, K_ITM_NO);
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AICUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_ERR_MSG_PROC_A() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
