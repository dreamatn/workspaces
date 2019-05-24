package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQVFA {
    int JIBS_tmp_int;
    DBParm DCTIAMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_AC_INF = "DD814";
    String WS_ERR_MSG = " ";
    String WS_CCY = " ";
    String WS_AC_ENM = " ";
    String WS_AC_CNM = " ";
    char WS_CCY_FLG = ' ';
    char WS_MSTR_FLG = ' ';
    char WS_CHQ_FLG = ' ';
    DDCOINFA DDCOINFA = new DDCOINFA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMST DDRMST = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    DDRVCH DDRVCH = new DDRVCH();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DCRIAMST DCRIAMST = new DCRIAMST();
    SCCGWA SCCGWA;
    DDCSQVFA DDCSQVFA;
    DDCSQIFA DDCSQIFA;
    public void MP(SCCGWA SCCGWA, DDCSQVFA DDCSQVFA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQVFA = DDCSQVFA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQVFA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_AC_INF();
        if (pgmRtn) return;
        B050_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQVFA.INPUT_DATA.AC_NO);
        if (DDCSQVFA.INPUT_DATA.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DDCSQVFA.INPUT_DATA.AC_NO;
        CEP.TRC(SCCGWA, DCRIAMST.KEY.VIA_AC);
        T000_READ_DCTIAMST();
        if (pgmRtn) return;
        if (!DCRIAMST.PRD_TYPE.equalsIgnoreCase("07")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_SOCIAL_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.OPEN_DATE);
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD.substring(3 - 1, 3 + 1 - 1));
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD.substring(4 - 1, 4 + 1 - 1));
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
            && DCRIAMST.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
            DDCSQVFA.OUTPUT_DATA.YCHK_FLG = 'Y';
        }
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
            && DCRIAMST.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSQVFA.OUTPUT_DATA.YCHK_FLG = 'N';
        }
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && DCRIAMST.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
            DDCSQVFA.OUTPUT_DATA.YCHK_FLG = 'W';
        }
    }
    public void B050_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFA);
        DDCOINFA.AC_NO = DDCSQVFA.INPUT_DATA.AC_NO;
        DDCOINFA.YCHK_FLG = DDCSQVFA.OUTPUT_DATA.YCHK_FLG;
        CEP.TRC(SCCGWA, DDCOINFA.AC_NO);
        SCCFMT.FMTID = K_FMT_AC_INF;
        SCCFMT.DATA_PTR = DDCOINFA;
        SCCFMT.DATA_LEN = 3985;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
