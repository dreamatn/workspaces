package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPACTY;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
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

public class CMOT6100 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_R = 0;
    String WS_ACAC_NO = " ";
    String WS_ACO_STSW = " ";
    String WS_BV_STSW = " ";
    String WS_FRM_APP = " ";
    String WS_FRM_APP_OLD = " ";
    String WS_CARD_NO = " ";
    String WS_CUS_AC = " ";
    String WS_CUS_ACNO = " ";
    String WS_FRM_APPS = " ";
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
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    TDCACM TDCACM = new TDCACM();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQINFT IBCQINFT = new IBCQINFT();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    CMCF100 CMCF100 = new CMCF100();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMB6100_AWA_6100 CMB6100_AWA_6100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT6100 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB6100_AWA_6100>");
        CMB6100_AWA_6100 = (CMB6100_AWA_6100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF100);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B050_INPUT_CHECK();
        if (pgmRtn) return;
        B100_INQ_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B050_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMB6100_AWA_6100.CUS_AC.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B100_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMB6100_AWA_6100.FUNC);
        if (CMB6100_AWA_6100.FUNC == '3') {
            B150_INQ_CUS_AC();
            if (pgmRtn) return;
            B200_INQ_AC();
            if (pgmRtn) return;
        } else if (CMB6100_AWA_6100.FUNC == '4') {
            B300_INQ_CI();
            if (pgmRtn) return;
        } else if (CMB6100_AWA_6100.FUNC == '5') {
            B130_INF_APP();
            if (pgmRtn) return;
            B400_PSW_CHK();
            if (pgmRtn) return;
        } else if (CMB6100_AWA_6100.FUNC == '2') {
            B150_INQ_CUS_AC();
            if (pgmRtn) return;
            B200_INQ_AC();
            if (pgmRtn) return;
            B300_INQ_CI();
            if (pgmRtn) return;
        } else if (CMB6100_AWA_6100.FUNC == ' '
            || CMB6100_AWA_6100.FUNC == '1') {
            B130_INF_APP();
            if (pgmRtn) return;
            B150_INQ_CUS_AC();
            if (pgmRtn) return;
            B200_INQ_AC();
            if (pgmRtn) return;
            B300_INQ_CI();
            if (pgmRtn) return;
            B400_PSW_CHK();
            if (pgmRtn) return;
        } else if (CMB6100_AWA_6100.FUNC == '6') {
            B250_MXPT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_FUNC_TYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        B500_OUTPUT();
        if (pgmRtn) return;
    }
    public void B130_INF_APP() throws IOException,SQLException,Exception {
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMB6100_AWA_6100.CUS_AC;
        R000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
        WS_CUS_AC = CMB6100_AWA_6100.CUS_AC;
    }
    public void B150_INQ_CUS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMB6100_AWA_6100.CUS_AC;
        CICQACAC.DATA.AGR_SEQ = CMB6100_AWA_6100.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = CMB6100_AWA_6100.CCY;
        CICQACAC.DATA.CR_FLG = CMB6100_AWA_6100.CCY_TYP;
        CICQACAC.DATA.BV_NO = CMB6100_AWA_6100.BV_NO;
        R000_INQ_ACAC();
        if (pgmRtn) return;
        WS_FRM_APP_OLD = CICQACAC.O_DATA.O_OLD_ACR_DATA.O_FRM_APP_OLD;
        WS_CUS_ACNO = CMB6100_AWA_6100.CUS_AC;
        if (WS_FRM_APP_OLD.equalsIgnoreCase("DC")) {
            WS_CARD_NO = CMB6100_AWA_6100.CUS_AC;
        }
        CEP.TRC(SCCGWA, WS_FRM_APP_OLD);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.trim().length() > 0) {
            if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.equalsIgnoreCase("04")) {
                CMCF100.CARD_NO = CMB6100_AWA_6100.CUS_AC;
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.equalsIgnoreCase("09")) {
                WS_FRM_APPS = CICQACAC.O_DATA.O_OLD_ACR_DATA.O_FRM_APP_OLD;
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.TX_TYPE = 'I';
                DDCIMMST.DATA.KEY.AC_NO = CMB6100_AWA_6100.CUS_AC;
                R000_INQ_DD();
                if (pgmRtn) return;
                CMCF100.AC_STS = DDCIMMST.DATA.AC_STS;
            }
            CICQACAC.DATA.AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
            CMB6100_AWA_6100.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
            R000_INQ_ACAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CMB6100_AWA_6100.AC_NM);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM);
        if (CMB6100_AWA_6100.FUNC == '2' 
            && CMB6100_AWA_6100.AC_NM.trim().length() > 0 
            && !CMB6100_AWA_6100.AC_NM.equalsIgnoreCase(CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
        }
        CMCF100.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CMCF100.CPN_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        CEP.TRC(SCCGWA, CMCF100.CPN_NM);
        CMCF100.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CMCF100.FRM_APP = CICQACAC.O_DATA.O_OLD_ACR_DATA.O_FRM_APP_OLD;
        CMCF100.CUS_BR = CICQACAC.O_DATA.O_ACR_DATA.O_OWNER_BK_ACR;
        CMCF100.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        CEP.TRC(SCCGWA, CMCF100.CCY);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CMCF100.CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        CEP.TRC(SCCGWA, CMCF100.CCY_TYP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_OLD_ACR_DATA.O_FRM_APP_OLD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CMCF100.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        if (WS_FRM_APP_OLD.equalsIgnoreCase("DC") 
            && SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("600200")) {
            CMCF100.OPEN_BR = CICQACAC.O_DATA.O_ACAC_DATA.O_OPN_BR_ACAC;
            IBS.init(SCCGWA, DCCUBRRC);
            DCCUBRRC.INP_DATA.FUNC = '0';
            DCCUBRRC.INP_DATA.CARD_NO = WS_CARD_NO;
            CEP.TRC(SCCGWA, WS_CARD_NO);
            CEP.TRC(SCCGWA, CMB6100_AWA_6100.CUS_AC);
            S000_CALL_DCZUBRRC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUBRRC.OUT_DATA.O_AREA_NO);
            CMCF100.AREA_CD = "" + DCCUBRRC.OUT_DATA.O_AREA_NO;
            JIBS_tmp_int = CMCF100.AREA_CD.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) CMCF100.AREA_CD = "0" + CMCF100.AREA_CD;
            CEP.TRC(SCCGWA, CMCF100.AREA_CD);
        } else {
            CMCF100.OPEN_BR = CICQACAC.O_DATA.O_ACR_DATA.O_OPN_BR_ACR;
        }
        CMCF100.OPEN_DT = CICQACAC.O_DATA.O_ACAC_DATA.O_OPEN_DT_ACAC;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBDD") 
            || CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBTD")) {
            CMCF100.IBAC_TYP = CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMB6100_AWA_6100.CUS_AC;
        R000_INQ_CI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMB6100_AWA_6100.ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        if (CMB6100_AWA_6100.FUNC == '2' 
            && CMB6100_AWA_6100.ID_NO.trim().length() > 0 
            && !CMB6100_AWA_6100.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR);
        }
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            CMCF100.CPN_NM = CICCUST.O_DATA.O_CI_NM;
        }
    }
    public void B200_INQ_AC() throws IOException,SQLException,Exception {
        if (CMCF100.FRM_APP.equalsIgnoreCase("DC")) {
            B210_INQ_CARD_INF();
            if (pgmRtn) return;
        } else if (CMCF100.FRM_APP.equalsIgnoreCase("DD")) {
            B220_INQ_DD_INF();
            if (pgmRtn) return;
        } else if (CMCF100.FRM_APP.equalsIgnoreCase("TD")) {
            B230_INQ_TD_INF();
            if (pgmRtn) return;
        } else if (CMCF100.FRM_APP.equalsIgnoreCase("IB")) {
            B240_INQ_IB_INF();
            if (pgmRtn) return;
