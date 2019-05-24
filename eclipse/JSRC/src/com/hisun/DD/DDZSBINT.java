package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBINT {
    DBParm DDTDDTD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD137";
    double WS_PAND_INT = 0;
    String WS_ERR_MSG = " ";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    CICQACAC CICQACAC = new CICQACAC();
    TDCIINT TDCIINT = new TDCIINT();
    DDRDDTD DDRDDTD = new DDRDDTD();
    SCCGWA SCCGWA;
    DDCSBINT DDCSBINT;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCSBINT DDCSBINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBINT = DDCSBINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBINT.AC_NO);
        CEP.TRC(SCCGWA, DDCSBINT.PROL_NO);
        CEP.TRC(SCCGWA, DDCSBINT.BRK_RATE);
        if (DDCSBINT.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSBINT.PROL_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4020;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSBINT.BRK_RATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4023;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        S000_GET_CON_NO_INF_PROC();
        if (pgmRtn) return;
        S000_GET_PAND_INT();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTDDTD_TBL_INQ() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        DDTDDTD_RD.where = "REF_NO = :DDRDDTD.REF_NO";
        IBS.READ(SCCGWA, DDRDDTD, this, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4030;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_GET_CON_NO_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDDTD);
        DDRDDTD.REF_NO = DDCSBINT.PROL_NO;
        CEP.TRC(SCCGWA, DDRDDTD.REF_NO);
        T000_READ_DDTDDTD_TBL_INQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CEP.TRC(SCCGWA, DDRDDTD.KEY.CON_NO);
        CICQACAC.DATA.ACAC_NO = DDRDDTD.KEY.CON_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void S000_GET_PAND_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCIINT);
        TDCIINT.FUNC = '2';
        TDCIINT.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        TDCIINT.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDCIINT.CCY = DDCSBINT.CCY;
        TDCIINT.TERM = DDCSBINT.TERM;
        TDCIINT.TXN_AMT = DDCSBINT.TXN_AMT;
        TDCIINT.BAL = DDCSBINT.BAL;
        TDCIINT.VAL_DATE = DDCSBINT.VAL_DATE;
        TDCIINT.EXP_DATE = DDCSBINT.EXP_DATE;
        TDCIINT.TXN_DATE = DDCSBINT.TXN_DATE;
        TDCIINT.RAT = DDCSBINT.BRK_RATE;
        TDCIINT.CAL_TYPE = '2';
        if (DDCSBINT.TERM.equalsIgnoreCase("S000")) {
            TDCIINT.PROD_CD = "9530000017";
        } else {
            TDCIINT.PROD_CD = "9530000015";
        }
        CEP.TRC(SCCGWA, TDCIINT.AC);
        CEP.TRC(SCCGWA, TDCIINT.AC_SEQ);
        CEP.TRC(SCCGWA, TDCIINT.CCY);
        CEP.TRC(SCCGWA, TDCIINT.TERM);
        CEP.TRC(SCCGWA, TDCIINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCIINT.BAL);
        CEP.TRC(SCCGWA, TDCIINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCIINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCIINT.RAT);
        S000_CALL_TDZIINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCIINT.CD_AMT);
        WS_PAND_INT = TDCIINT.CD_AMT;
        CEP.TRC(SCCGWA, WS_PAND_INT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_PAND_INT;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZIINT() throws IOException,SQLException,Exception {
        TDZIINT TDZIINT = new TDZIINT();
        TDZIINT.MP(SCCGWA, TDCIINT);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
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
