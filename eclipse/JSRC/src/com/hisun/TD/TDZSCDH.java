package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSCDH {
    brParm TDTDWHH_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "TD515";
    String WS_ERR_MSG = " ";
    char WS_CALL_FLG = ' ';
    String WS_AC_NO = " ";
    int WS_SEQ = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    TDRDWHH TDRDWHH = new TDRDWHH();
    TDCOCDH TDCOCDH = new TDCOCDH();
    CICQACAC CICQACAC = new CICQACAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    TDCSCDH TDCSCDH;
    public void MP(SCCGWA SCCGWA, TDCSCDH TDCSCDH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSCDH = TDCSCDH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSCDH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSCDH.AC_NO);
        B100_GET_AC_TYP_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_AC_TYP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCSCDH.AC_NO;
        CICQACAC.DATA.BV_NO = TDCSCDH.BV_NO;
        CICQACAC.DATA.AGR_SEQ = TDCSCDH.AC_SEQ;
        S000_CALL_CICQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        TDCSCDH.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        IBS.init(SCCGWA, TDRDWHH);
        TDRDWHH.KEY.ACO_AC = TDCSCDH.AC_NO;
        TDRDWHH.STATUS = 'N';
        TDRDWHH.CRT_DATE = TDCSCDH.START_DT;
        TDRDWHH.SETT_DATE = TDCSCDH.END_DT;
        CEP.TRC(SCCGWA, TDRDWHH.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRDWHH.STATUS);
        CEP.TRC(SCCGWA, TDRDWHH.CRT_DATE);
        CEP.TRC(SCCGWA, TDRDWHH.UPD_DATE);
        T000_START_TDTDWHH();
        if (pgmRtn) return;
        T000_READNEXT_TDTDWHH();
        if (pgmRtn) return;
        if (WS_CALL_FLG == 'N') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B100_01_OUT_TITLE();
        if (pgmRtn) return;
        while (WS_CALL_FLG != 'N') {
            B100_TRANSFER_BRW_DATA();
            if (pgmRtn) return;
            B100_OUTPUT_BRW_CALL_DATA();
            if (pgmRtn) return;
            B100_SET_SUB_TRAN();
            if (pgmRtn) return;
            T000_READNEXT_TDTDWHH();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTDWHH();
        if (pgmRtn) return;
    }
    public void T000_START_TDTDWHH() throws IOException,SQLException,Exception {
        TDTDWHH_BR.rp = new DBParm();
        TDTDWHH_BR.rp.TableName = "TDTDWHH";
        TDTDWHH_BR.rp.where = "ACO_AC = :TDRDWHH.KEY.ACO_AC "
            + "AND STATUS = :TDRDWHH.STATUS "
            + "AND SETT_DATE >= :TDRDWHH.CRT_DATE "
            + "AND SETT_DATE <= :TDRDWHH.SETT_DATE";
        IBS.STARTBR(SCCGWA, TDRDWHH, this, TDTDWHH_BR);
    }
    public void T000_READNEXT_TDTDWHH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRDWHH, this, TDTDWHH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CALL_FLG = 'Y';
        } else {
            WS_CALL_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTDWHH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTDWHH_BR);
    }
    public void B100_OUTPUT_BRW_CALL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDCOCDH);
        SCCMPAG.DATA_LEN = 74;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B100_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        if ("99".trim().length() == 0) SCCMPAG.MAX_COL_NO = 0;
        else SCCMPAG.MAX_COL_NO = Short.parseShort("99");
        if ("99".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("99");
        if ("3".trim().length() == 0) SCCMPAG.SCR_COL_CNT = 0;
        else SCCMPAG.SCR_COL_CNT = Short.parseShort("3");
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B100_TRANSFER_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOCDH);
        TDCOCDH.AC = TDRDWHH.KEY.ACO_AC;
        CEP.TRC(SCCGWA, TDCOCDH.AC);
        TDCOCDH.PAY_SEQ = TDRDWHH.KEY.PAY_SEQ;
        TDCOCDH.TERM_NO = TDRDWHH.TERM_NO;
        TDCOCDH.NOR_SETT_DATE = TDRDWHH.NOR_SETT_DATE;
        TDCOCDH.SETT_DATE = TDRDWHH.SETT_DATE;
        TDCOCDH.SETT_CCY = TDRDWHH.SETT_CCY;
        TDCOCDH.SETT_AMT = TDRDWHH.SETT_AMT;
        CEP.TRC(SCCGWA, TDCOCDH.AC);
        CEP.TRC(SCCGWA, TDCOCDH.PAY_SEQ);
        CEP.TRC(SCCGWA, TDCOCDH.NOR_SETT_DATE);
        CEP.TRC(SCCGWA, TDCOCDH.SETT_DATE);
        CEP.TRC(SCCGWA, TDCOCDH.SETT_CCY);
        CEP.TRC(SCCGWA, TDCOCDH.SETT_AMT);
    }
    public void B100_SET_SUB_TRAN() throws IOException,SQLException,Exception {
        SCCSUBS.AP_CODE = 12;
        SCCSUBS.TR_CODE = 5154;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void S000_CALL_CICQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
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
