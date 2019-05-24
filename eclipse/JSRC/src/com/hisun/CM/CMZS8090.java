package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPACTY;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCIQBAL;
import com.hisun.IB.IBCQINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCACE;
import com.hisun.TD.TDCACM;

public class CMZS8090 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_R = 0;
    String WS_ACO_NO = " ";
    String WS_DDAC_STSW = " ";
    String WS_RET_CODE = " ";
    double WS_TMP_AMT = 0;
    char WS_CI_FLG = ' ';
    String WS_CHK_RES = " ";
    CMZS8090_WS_STSW WS_STSW = new CMZS8090_WS_STSW();
    char WS_STS = ' ';
    String WS_CUS_CUS_AC = " ";
    String WS_CUS_CI_NO = " ";
    String WS_CUS_ACO_NO = " ";
    int WS_CUS_CI_OPN_BR = 0;
    char WS_CUS_CI_TYP = ' ';
    String WS_CUS_CI_STSW = " ";
    String WS_CUS_ID_TYP = " ";
    String WS_CUS_ID_NO = " ";
    String WS_CUS_CI_NM = " ";
    String WS_CUS_CPN_NM = " ";
    String WS_CUS_CPN_ENM = " ";
    char WS_CUS_RESIDENT = ' ';
    String WS_CUS_AC_TYP = " ";
    String WS_CUS_FRG_CODE = " ";
    int WS_CUS_EXP_DT = 0;
    int WS_CUS_OPEN_DT = 0;
    String WS_CUS_REG_CNTY = " ";
    char WS_CUS_SID_FLG = ' ';
    String WS_CUS_TEL_NO = " ";
    int WS_CUS_OPEN_BR = 0;
    String WS_CUS_CARD_NO = " ";
    char WS_CUS_CD_MEDI = ' ';
    char WS_CUS_CD_STS = ' ';
    String WS_CUS_CD_STSW = " ";
    String WS_CUS_PROD_CD = " ";
    int WS_CUS_CD_OP_BR = 0;
    int WS_CUS_CD_CLT_BR = 0;
    char WS_CUS_LOST_FLG = ' ';
    int WS_CUS_AC_SEQ = 0;
    String WS_CUS_CCY = " ";
    char WS_CUS_CCY_TYP = ' ';
    char WS_CUS_AC_STS = ' ';
    String WS_CUS_AC_STSW = " ";
    int WS_CUS_AC_BR = 0;
    String WS_CUS_FRM_APP = " ";
    String WS_CUS_IBAC_TYP = " ";
    String WS_CUS_TDAC_TYP = " ";
    char WS_CUS_DDAC_TYP = ' ';
    char WS_CUS_CR_FLG = ' ';
    char WS_CUS_UEW_FLG = ' ';
    char WS_CUS_HLD_TYP = ' ';
    double WS_CUS_HLD_AMT = 0;
    double WS_CUS_LAST_BAL = 0;
    double WS_CUS_AVA_BAL = 0;
    double WS_CUS_CUR_BAL = 0;
    char WS_CUS_DRW_FLG = ' ';
    double WS_CUS_INT_RAT = 0;
    int WS_CUS_SDT = 0;
    int WS_CUS_EDT = 0;
    String WS_CUS_TERM_CD = " ";
    String WS_CUS_BV_CODE = " ";
    String WS_CUS_BV_NO = " ";
    char WS_CUS_BV_TYP = ' ';
    char WS_CUS_DRW_TYP = ' ';
    char WS_CUS_CASH_FLG = ' ';
    char WS_CUS_AC_KND = ' ';
    int WS_AC_BR = 0;
    short WS_ACNAME_FLG = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPACTY BPCPACTY = new BPCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACE TDCACE = new TDCACE();
    CICCUST CICCUST = new CICCUST();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    TDCACM TDCACM = new TDCACM();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQINFT IBCQINFT = new IBCQINFT();
    CICQACRI CICQACRI = new CICQACRI();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    CMCO8090 CMCO8090 = new CMCO8090();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS8090 CMCS8090;
    public void MP(SCCGWA SCCGWA, CMCS8090 CMCS8090) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS8090 = CMCS8090;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS8090 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCO8090);
        IBS.init(SCCGWA, CMCSIQAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        if (pgmRtn) return;
        if (CMCS8090.RCUS_AC.trim().length() > 0 
            && CMCS8090.RCUS_AC.charAt(0) != 0X00) {
            B250_RELATION_INQ_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B200_DIRECT_INQ_MAIN_PROC();
            if (pgmRtn) return;
        }
        if (WS_RET_CODE.trim().length() == 0) {
            B700_CHK_LIMT();
            if (pgmRtn) return;
        }
        B900_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        if ((CMCS8090.FUNC.trim().length() == 0 
            || CMCS8090.FUNC.charAt(0) == 0X00) 
            || (!CMCS8090.FUNC.equalsIgnoreCase("01") 
            && !CMCS8090.FUNC.equalsIgnoreCase("02") 
            && !CMCS8090.FUNC.equalsIgnoreCase("03"))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_TYP_ERR);
        }
        if (CMCS8090.CUS_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR);
        }
        if (CMCS8090.FUNC.equalsIgnoreCase("01") 
            && (CMCS8090.DC_FLG != 'D' 
            && CMCS8090.DC_FLG != 'C')) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_ERR);
        }
        if (CMCS8090.FUNC.equalsIgnoreCase("01") 
            && (CMCS8090.CCY.trim().length() == 0 
            || CMCS8090.CCY.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_INP_ERR);
        }
        if ((CMCS8090.FUNC.equalsIgnoreCase("02") 
            || CMCS8090.FUNC.equalsIgnoreCase("03")) 
            && (CMCS8090.AC_NM.trim().length() == 0 
            || CMCS8090.AC_NM.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
        }
        if ((CMCS8090.FUNC.equalsIgnoreCase("02") 
            || CMCS8090.FUNC.equalsIgnoreCase("01")) 
            && (CMCS8090.DC_FLG != 'D' 
            && CMCS8090.DC_FLG != 'C')) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_ERR);
        }
    }
    public void B200_DIRECT_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS8090.CUS_AC;
        CMCSIQAC.I_CCY = CMCS8090.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS8090.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        if (CMCS8090.FUNC.equalsIgnoreCase("03")) {
