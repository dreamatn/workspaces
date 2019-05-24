package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6160 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_CPB_NM1 = "LNCSSCO";
    String K_HIS_RMKS = "STOP INTEREST PROCESS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSSCI LNCSSCI = new LNCSSCI();
    LNCSSCO LNCSSCO = new LNCSSCO();
    LNCSSCO LNCSSCN = new LNCSSCO();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCF16 LNCF16 = new LNCF16();
    LNCLOANM LNCLOANM = new LNCLOANM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    LNB6160_AWA_6160 LNB6160_AWA_6160;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        R000_OUTPUT_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6160 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6160_AWA_6160>");
        LNB6160_AWA_6160 = (LNB6160_AWA_6160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        B030_GET_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.FUNC);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.CTA);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.BR);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.CI_NO);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.CI_CNM);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.PROD_CD);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.CCY);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.LON_STRT);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.LON_MATU);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.LON_PRIN);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.LON_BAL);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.RCV_INT);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.RCV_LC);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.RCV_RINT);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.OLD_STS);
        CEP.TRC(SCCGWA, LNB6160_AWA_6160.SC_DT);
        if (LNB6160_AWA_6160.FUNC != 'S' 
            && LNB6160_AWA_6160.FUNC != 'C') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            WS_FLD_NO = LNB6160_AWA_6160.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6160_AWA_6160.CTA.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB6160_AWA_6160.CTA_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6160_AWA_6160.SC_DT == 0) {
            LNB6160_AWA_6160.SC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (LNB6160_AWA_6160.FUNC == 'S') {
            if (LNB6160_AWA_6160.SC_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DATE_ERROR2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB6160_AWA_6160.FUNC == 'C') {
            if (LNB6160_AWA_6160.SC_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DATE_ERROR3;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSCI);
        IBS.init(SCCGWA, LNCSSCO);
        LNCSSCI.FUNC = LNB6160_AWA_6160.FUNC;
        LNCSSCI.CTA = LNB6160_AWA_6160.CTA;
        LNCSSCO.CTA = LNB6160_AWA_6160.CTA;
        LNCSSCI.BR = LNB6160_AWA_6160.BR;
        LNCSSCO.BR = LNB6160_AWA_6160.BR;
        LNCSSCI.CI_NO = LNB6160_AWA_6160.CI_NO;
        LNCSSCO.CI_NO = LNB6160_AWA_6160.CI_NO;
        LNCSSCI.CI_CNM = LNB6160_AWA_6160.CI_CNM;
        LNCSSCI.CI_CNM = LNB6160_AWA_6160.CI_CNM;
        LNCSSCI.PROD_CD = LNB6160_AWA_6160.PROD_CD;
        LNCSSCO.PROD_CD = LNB6160_AWA_6160.PROD_CD;
        LNCSSCI.CCY = LNB6160_AWA_6160.CCY;
        LNCSSCO.CCY = LNB6160_AWA_6160.CCY;
        LNCSSCI.LON_STRT = LNB6160_AWA_6160.LON_STRT;
        LNCSSCO.LON_STRT = LNB6160_AWA_6160.LON_STRT;
        LNCSSCI.LON_MATU = LNB6160_AWA_6160.LON_MATU;
        LNCSSCO.LON_MATU = LNB6160_AWA_6160.LON_MATU;
        LNCSSCI.LON_PRIN = LNB6160_AWA_6160.LON_PRIN;
        LNCSSCO.LON_PRIN = LNB6160_AWA_6160.LON_PRIN;
        LNCSSCI.LON_BAL = LNB6160_AWA_6160.LON_BAL;
        LNCSSCO.LON_BAL = LNB6160_AWA_6160.LON_BAL;
        LNCSSCI.RCV_INT = LNB6160_AWA_6160.RCV_INT;
        LNCSSCO.RCV_INT = LNB6160_AWA_6160.RCV_INT;
        LNCSSCI.RCV_LC = LNB6160_AWA_6160.RCV_LC;
        LNCSSCO.RCV_LC = LNB6160_AWA_6160.RCV_LC;
        LNCSSCI.RCV_RINT = LNB6160_AWA_6160.RCV_RINT;
        LNCSSCO.RCV_RINT = LNB6160_AWA_6160.RCV_RINT;
        LNCSSCI.STS = LNB6160_AWA_6160.OLD_STS;
        LNCSSCO.STS = LNB6160_AWA_6160.OLD_STS;
        LNCSSCI.SC_DT = LNB6160_AWA_6160.SC_DT;
        S000_CALL_LNZSSCI();
        if (pgmRtn) return;
    }
    public void B030_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICIQ);
        IBS.init(SCCGWA, LNCF16);
        LNCICIQ.DATA.CONT_NO = LNB6160_AWA_6160.CTA;
        LNCICIQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCICIQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCICIQ.DATA.SUB_CONT_NO = "0" + LNCICIQ.DATA.SUB_CONT_NO;
        LNCICIQ.FUNC = 'M';
        S000_CALL_LNZICIQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICIQ);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CI_SNAME);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CITY_CD);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CI_ENAME);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CI_CNAME);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CCY);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.AMT);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.BAL);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.STS);
        LNCF16.BR = LNCICIQ.DATA.BR;
        LNCF16.CI_NO = LNCICIQ.DATA.CI_NO;
        LNCF16.CI_CNM = LNCICIQ.DATA.CI_CNAME;
        LNCF16.PROD_CD = LNCICIQ.DATA.PROD_CD;
        LNCF16.CCY = LNCICIQ.DATA.CCY;
        LNCF16.LON_STRT = LNCICIQ.DATA.VAL_DT;
        LNCF16.LON_MATU = LNCICIQ.DATA.DUE_DT;
        LNCF16.LON_PRIN = LNCICIQ.DATA.AMT;
        LNCF16.LON_BAL = LNCICIQ.DATA.BAL;
    }
    public void R000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        LNCF16.FUNC = LNCSSCI.FUNC;
        LNCF16.CTA = LNCSSCI.CTA;
        LNCSSCN.CTA = LNCSSCI.CTA;
        LNCF16.RCV_INT = LNCICIQ.DATA.INT_REC;
        LNCSSCN.RCV_INT = LNCICIQ.DATA.INT_REC;
        LNCF16.RCV_LC = LNCICIQ.DATA.LC_REC;
        LNCSSCN.RCV_LC = LNCICIQ.DATA.LC_REC;
        LNCF16.RCV_RINT = LNCICIQ.DATA.LI_REC;
        LNCSSCN.RCV_RINT = LNCICIQ.DATA.LI_REC;
        LNCF16.SC_DT = LNCSSCI.SC_DT;
        LNCSSCN.SC_DT = LNCSSCI.SC_DT;
        CEP.TRC(SCCGWA, LNCF16.FUNC);
        CEP.TRC(SCCGWA, LNCF16.CTA);
        CEP.TRC(SCCGWA, LNCF16.BR);
        CEP.TRC(SCCGWA, LNCF16.CI_NO);
        CEP.TRC(SCCGWA, LNCF16.CI_CNM);
        CEP.TRC(SCCGWA, LNCF16.PROD_CD);
        CEP.TRC(SCCGWA, LNCF16.CCY);
        CEP.TRC(SCCGWA, LNCF16.LON_STRT);
        CEP.TRC(SCCGWA, LNCF16.LON_MATU);
        CEP.TRC(SCCGWA, LNCF16.LON_PRIN);
        CEP.TRC(SCCGWA, LNCF16.LON_BAL);
        CEP.TRC(SCCGWA, LNCF16.RCV_INT);
        CEP.TRC(SCCGWA, LNCF16.RCV_LC);
        CEP.TRC(SCCGWA, LNCF16.RCV_RINT);
        CEP.TRC(SCCGWA, LNCF16.SC_DT);
        SCCFMT.FMTID = "LN616";
        SCCFMT.DATA_PTR = LNCF16;
        SCCFMT.DATA_LEN = 457;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B000_NHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSSCI.CTA;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        if (LNB6160_AWA_6160.OLD_STS == 'Y') {
            LNCSSCO.SC_DT = LNCLOANM.REC_DATA.STOP_INT_DATE;
        }
        if (LNB6160_AWA_6160.OLD_STS == 'N') {
            LNCSSCO.SC_DT = LNCLOANM.REC_DATA.STOP_DUE_DT;
        }
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.REF_NO = LNCSSCI.CTA;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM1;
        BPCPNHIS.INFO.FMT_ID_LEN = 420;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSSCO;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSSCN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSSCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-STCL-INT", LNCSSCI);
        if (LNCSSCI.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSCI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "999999999");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
