package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5450 {
    brParm DDTCHQ_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_LOC_CCY = "CNY";
    String CPN_DD_S_BCHQ_PROC = "DD-S-BCHQ-PROC";
    String WS_ERR_MSG = " ";
    short WS_RET = 0;
    short WS_RMDR = 0;
    String WS_STD_AC = " ";
    char WS_CHQ_FLG = ' ';
    char WS_STR_CHQ_FLG = ' ';
    char WS_END_CHQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRCHQ DDRCHQ = new DDRCHQ();
    CICQACAC CICQACAC = new CICQACAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DDB5450_AWA_5450 DDB5450_AWA_5450;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5450 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5450_AWA_5450>");
        DDB5450_AWA_5450 = (DDB5450_AWA_5450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_GET_ACAC_INFO();
        if (pgmRtn) return;
        B030_QUERY_CHQB_INF_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5450_AWA_5450.AC);
        CEP.TRC(SCCGWA, DDB5450_AWA_5450.CHQ_NO1);
        CEP.TRC(SCCGWA, DDB5450_AWA_5450.CHQ_NO2);
        if (DDB5450_AWA_5450.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB5450_AWA_5450.AC;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B030_QUERY_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        WS_STR_CHQ_FLG = 'N';
        WS_END_CHQ_FLG = 'N';
        T000_START_DDTCHQ();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        while (WS_CHQ_FLG != 'N' 
            && (WS_STR_CHQ_FLG != 'Y' 
            || WS_END_CHQ_FLG != 'Y')) {
            CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
            if (DDRCHQ.KEY.STR_CHQ_NO.compareTo(DDB5450_AWA_5450.CHQ_NO1) <= 0 
                && DDRCHQ.KEY.END_CHQ_NO.compareTo(DDB5450_AWA_5450.CHQ_NO1) >= 0) {
                WS_STR_CHQ_FLG = 'Y';
            }
            if (DDRCHQ.KEY.STR_CHQ_NO.compareTo(DDB5450_AWA_5450.CHQ_NO2) <= 0 
                && DDRCHQ.KEY.END_CHQ_NO.compareTo(DDB5450_AWA_5450.CHQ_NO2) >= 0) {
                WS_END_CHQ_FLG = 'Y';
            }
            T000_READNEXT_DDTCHQ();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCHQ();
        if (pgmRtn) return;
        if (WS_STR_CHQ_FLG == 'N' 
            || WS_END_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_START_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.STR_CHQ_NO = DDB5450_AWA_5450.CHQ_NO1;
        DDRCHQ.KEY.END_CHQ_NO = DDB5450_AWA_5450.CHQ_NO2;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
