package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSBALQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_RC_APP = " ";
    short WS_RC_RTNCODE = 0;
    String WS_ERR_MSG = " ";
    char WS_CAL_END_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCQ02 LNCQ02 = new LNCQ02();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCSPREQ LNCSPREQ = new LNCSPREQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSBALQ LNCSBALQ;
    public void MP(SCCGWA SCCGWA, LNCSBALQ LNCSBALQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSBALQ = LNCSBALQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        C000_OUTPUT_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSBALQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        LNCSBALQ.RC.RC_APP = "LN";
        LNCSBALQ.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSBALQ.CTA_NO);
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSBALQ.CTA_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.STS);
        CEP.TRC(SCCGWA, LNCSBALQ.VAL_DTE);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.VAL_DT);
        if (LNCSBALQ.VAL_DTE < LNCCLNQ.DATA.VAL_DT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_STDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSBALQ.CTA_NO;
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "" + 0;
        JIBS_tmp_int = LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "0" + LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = LNCSBALQ.VAL_DTE;
        LNCSPDQ.FUNC_CODE = 'E';
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        LNCSBALQ.PIA_AMT = LNCSPDQ.ARREAR_DATAS.PIA_AMT;
        LNCSBALQ.IIA_AMT = LNCSPDQ.ARREAR_DATAS.IIA_AMT;
        LNCSBALQ.PLC_AMT = LNCSPDQ.ARREAR_DATAS.IPIA_AMT;
        LNCSBALQ.ILC_AMT = LNCSPDQ.ARREAR_DATAS.IIIA_AMT;
        LNCSBALQ.LON_PRIN = LNCSPDQ.COMM_DATA.CTA_INFO.LON_PRIN;
        LNCSBALQ.LON_BAL = LNCSPDQ.COMM_DATA.CTA_INFO.LON_BAL;
        LNCSBALQ.SCH_BAL = LNCSBALQ.LON_BAL - LNCSBALQ.PIA_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (LNCSBALQ.VAL_DTE < LNCCLNQ.DATA.DUE_DT 
            && (!JIBS_tmp_str[0].equalsIgnoreCase("0134050") 
            && !JIBS_tmp_str[2].equalsIgnoreCase("0132270"))) {
            IBS.init(SCCGWA, LNCSPREQ);
            LNCSPREQ.CTA = LNCSBALQ.CTA_NO;
            LNCSPREQ.SUB_TRAN = 'N';
            LNCSPREQ.TR_VAL_DTE = LNCSBALQ.VAL_DTE;
            LNCSPREQ.INT_MODE = '1';
            LNCSPREQ.FUN_CODE = 'I';
            S000_CALL_LNZSPREQ();
            if (pgmRtn) return;
            LNCSBALQ.INT_ACCR = LNCSPREQ.TOT_I_AMT;
        }
        LNCSBALQ.TOT_AMT = LNCSBALQ.SCH_BAL + LNCSBALQ.INT_ACCR + LNCSBALQ.PIA_AMT + LNCSBALQ.IIA_AMT + LNCSBALQ.PLC_AMT + LNCSBALQ.ILC_AMT;
        CEP.TRC(SCCGWA, LNCSBALQ.SCH_BAL);
        CEP.TRC(SCCGWA, LNCSBALQ.INT_ACCR);
        CEP.TRC(SCCGWA, LNCSBALQ.PIA_AMT);
        CEP.TRC(SCCGWA, LNCSBALQ.IIA_AMT);
        CEP.TRC(SCCGWA, LNCSBALQ.PLC_AMT);
        CEP.TRC(SCCGWA, LNCSBALQ.ILC_AMT);
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        LNCQ02.BOOK_BR = LNCCLNQ.DATA.DOMI_BR;
        LNCQ02.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCQ02.CI_SNM = LNCCLNQ.DATA.CI_SNAME;
        LNCQ02.CITY_CD = LNCCLNQ.DATA.CITY_CD;
        LNCQ02.CI_ENM = LNCCLNQ.DATA.CI_ENAME;
        LNCQ02.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCQ02.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCQ02.CCY = LNCCLNQ.DATA.CCY;
        LNCQ02.AMT = LNCSBALQ.LON_PRIN;
        LNCQ02.OS_BAL = LNCSBALQ.LON_BAL;
        LNCQ02.VAL_DT = LNCCLNQ.DATA.VAL_DT;
        LNCQ02.DUE_DT = LNCCLNQ.DATA.DUE_DT;
        LNCQ02.STS = LNCCLNQ.DATA.STS;
        LNCQ02.INT_ACCR = LNCSBALQ.INT_ACCR;
        LNCQ02.PRIN_ARER = LNCSBALQ.PIA_AMT;
        LNCQ02.INT_ARER = LNCSBALQ.IIA_AMT;
        LNCQ02.PRIN_LC = LNCSBALQ.PLC_AMT;
        LNCQ02.INT_LC = LNCSBALQ.ILC_AMT;
        LNCQ02.TOT_AMT = LNCSBALQ.TOT_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNQ02";
        SCCFMT.DATA_PTR = LNCQ02;
        SCCFMT.DATA_LEN = 681;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, LNCQ02.BOOK_BR);
        CEP.TRC(SCCGWA, LNCQ02.CI_NO);
        CEP.TRC(SCCGWA, LNCQ02.CI_SNM);
        CEP.TRC(SCCGWA, LNCQ02.CITY_CD);
        CEP.TRC(SCCGWA, LNCQ02.CI_ENM);
        CEP.TRC(SCCGWA, LNCQ02.CI_CNM);
        CEP.TRC(SCCGWA, LNCQ02.PROD_CD);
        CEP.TRC(SCCGWA, LNCQ02.CCY);
        CEP.TRC(SCCGWA, LNCQ02.AMT);
        CEP.TRC(SCCGWA, LNCQ02.OS_BAL);
        CEP.TRC(SCCGWA, LNCQ02.VAL_DT);
        CEP.TRC(SCCGWA, LNCQ02.DUE_DT);
        CEP.TRC(SCCGWA, LNCQ02.STS);
        CEP.TRC(SCCGWA, LNCQ02.INT_ACCR);
        CEP.TRC(SCCGWA, LNCQ02.PRIN_ARER);
        CEP.TRC(SCCGWA, LNCQ02.INT_ARER);
        CEP.TRC(SCCGWA, LNCQ02.PRIN_LC);
        CEP.TRC(SCCGWA, LNCQ02.INT_LC);
        CEP.TRC(SCCGWA, LNCQ02.TOT_AMT);
    }
    public void S000_CALL_LNZSPREQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PREPAY-CALC", LNCSPREQ, true);
        if (LNCSPREQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPREQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSBALQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSBALQ=");
            CEP.TRC(SCCGWA, LNCSBALQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
