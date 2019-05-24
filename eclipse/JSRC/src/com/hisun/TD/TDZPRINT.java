package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPRINT {
    TDCQDINT_INT_INFO INT_INFO;
    String JIBS_tmp_str[] = new String[10];
    brParm TDTDINT_BR = new brParm();
    boolean pgmRtn = false;
    String K_IRUL_TYP = "TIRUL";
    String K_INT_FMT = "TD011";
    String WS_ERR_MSG = " ";
    char WS_DINT_FLG = ' ';
    short WS_X = 0;
    char WS_STRBR_FLG = ' ';
    char WS_JRN_SAME = ' ';
    long WS_JRNNO = 0;
    double WS_DINT_INT = 0;
    double WS_TOT_TMP_INT = 0;
    double WS_DUE_INT = 0;
    String WS_TMP_CCY = " ";
    double WS_TOT_TMP_TAX = 0;
    char WS_END_FLG = ' ';
    short WS_CNT = 0;
    short WS_CNT_TAX = 0;
    char WS_TAX_CHANGE = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    TDCQDINT TDCQDINT = new TDCQDINT();
    TDRDINT TDRDINT = new TDRDINT();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    SCCGWA SCCGWA;
    TDCPRINT TDCPRINT;
    public void MP(SCCGWA SCCGWA, TDCPRINT TDCPRINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPRINT = TDCPRINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZPRINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRINT.TXN_JRN);
        CEP.TRC(SCCGWA, TDCPRINT.AC_DT);
        CEP.TRC(SCCGWA, TDCPRINT.AC);
        WS_STRBR_FLG = 'N';
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PASS CHECK");
        IBS.init(SCCGWA, TDRDINT);
        TDRDINT.KEY.ACO_AC = TDCPRINT.AC;
        TDRDINT.KEY.AC_DATE = TDCPRINT.AC_DT;
        if ((TDCPRINT.XH_FLG == 'N' 
            || TDCPRINT.CANCEL_FLG == 'Y') 
            && TDCPRINT.TXN_JRN != 0) {
            TDRDINT.KEY.JRNNO = TDCPRINT.TXN_JRN;
            if (TDCPRINT.CANCEL_FLG == 'Y') {
                T000_STRBR_BY_FULLKEY_CANCEL();
                if (pgmRtn) return;
            } else {
                T000_STRBR_BY_FULLKEY();
                if (pgmRtn) return;
            }
        } else {
            if (TDCPRINT.XH_FLG == 'Y') {
                T000_STRBR_BY_XH_XC();
                if (pgmRtn) return;
            } else {
                T000_STRBR_BY_AC();
                if (pgmRtn) return;
            }
        }
        WS_X = 1;
        IBS.init(SCCGWA, TDRDINT);
        T000_READNEXT_TDTDINT();
        if (pgmRtn) return;
        WS_JRNNO = TDRDINT.KEY.JRNNO;
        if (WS_DINT_FLG == 'Y') {
            while (WS_DINT_FLG != 'N') {
                WS_TMP_CCY = TDRDINT.CCY;
                if (TDRDINT.KEY.JRNNO != WS_JRNNO) {
                    WS_JRN_SAME = 'N';
                } else {
                    WS_JRN_SAME = 'Y';
                }
                B000_OUT_SUBINF();
                if (pgmRtn) return;
                WS_JRNNO = TDRDINT.KEY.JRNNO;
                IBS.init(SCCGWA, TDRDINT);
                T000_READNEXT_TDTDINT();
                if (pgmRtn) return;
                if (WS_X == 31 
                    && WS_DINT_FLG == 'Y') {
                    WS_END_FLG = 'N';
                }
            }
        }
        if (WS_STRBR_FLG == 'Y') {
            T000_ENDBR_TDTDINT();
            if (pgmRtn) return;
        }
        WS_X = (short) (WS_X - 1);
        TDCPRINT.TOT_NUM = WS_X;
        TDCQDINT.TOT_NUM = WS_X;
        INT_INFO = new TDCQDINT_INT_INFO();
        TDCQDINT.INT_INFO.add(INT_INFO);
        TDCPRINT.PAYED_INT = TDCPRINT.PAYING_INT - TDCPRINT.PAYING_TAX;
        TDCPRINT.TOT_AMT = TDCPRINT.DRAW_AMT + TDCPRINT.PAYED_INT;
        CEP.TRC(SCCGWA, TDCPRINT.TOT_NUM);
        if (TDCPRINT.TOT_NUM >= 1 
            && (TDCPRINT.OUT_TYPE == '1' 
            || TDCPRINT.OUT_TYPE == '3')) {
            B000_OUT_INT_INFO();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTDINT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRDINT, this, TDTDINT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DINT_FLG = 'Y';
        } else {
            WS_DINT_FLG = 'N';
        }
    }
    public void B000_OUT_SUBINF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_X);
        TDCQDINT.TOT_NUM = WS_X;
        INT_INFO = new TDCQDINT_INT_INFO();
        TDCQDINT.INT_INFO.add(INT_INFO);
        if (WS_X <= 30) {
            TDCPRINT.INT_INFO[WS_X-1].BAL = TDRDINT.BAL;
            INT_INFO.BAL = TDRDINT.BAL;
            TDCPRINT.INT_INFO[WS_X-1].START_DT = TDRDINT.STR_DATE;
            INT_INFO.START_DT = TDRDINT.STR_DATE;
            TDCPRINT.INT_INFO[WS_X-1].END_DT = TDRDINT.END_DATE;
            INT_INFO.END_DT = TDRDINT.END_DATE;
            TDCPRINT.INT_INFO[WS_X-1].INT_RAT = TDRDINT.RAT_INT;
            INT_INFO.INT_RAT = TDRDINT.RAT_INT;
            WS_DINT_INT = TDRDINT.AMT;
            CEP.TRC(SCCGWA, TDRDINT.AMT);
            CEP.TRC(SCCGWA, WS_DINT_INT);
            TDCPRINT.PAYING_INT = TDCPRINT.PAYING_INT + WS_DINT_INT;
            TDCPRINT.INT_INFO[WS_X-1].PERD_INT = WS_DINT_INT;
            INT_INFO.PERD_INT = WS_DINT_INT;
            TDCPRINT.INT_INFO[WS_X-1].INT_TYPE = TDRDINT.INT_TYPE;
            INT_INFO.INT_TYPE = TDRDINT.INT_TYPE;
            TDCQDINT.INT_INFO.get(WS_X-1).TAX_RAT_A = TDRDINT.TAX_RAT * 100;
            WS_DINT_INT = TDRDINT.TAX_AMT;
            INT_INFO.PAYING_TAX_A = WS_DINT_INT;
            TDCQDINT.INT_INFO.get(WS_X-1).PAYED_INT_A = TDCQDINT.INT_INFO.get(WS_X-1).PERD_INT - TDCQDINT.INT_INFO.get(WS_X-1).PAYING_TAX_A;
            CEP.TRC(SCCGWA, TDCQDINT.INT_INFO.get(WS_X-1).TAX_RAT_A);
            CEP.TRC(SCCGWA, TDCQDINT.INT_INFO.get(WS_X-1).PAYING_TAX_A);
            CEP.TRC(SCCGWA, TDCQDINT.INT_INFO.get(WS_X-1).PAYED_INT_A);
            TDCPRINT.INT_INFO[WS_X-1].SEQ = WS_X;
            INT_INFO.SEQ = WS_X;
            WS_CNT = WS_X;
            CEP.TRC(SCCGWA, "TEST1");
            WS_X = (short) (WS_X + 1);
        }
        TDCPRINT.DRAW_AMT = TDCPRINT.DRAW_AMT + TDRDINT.BAL;
        TDCPRINT.TAX_RAT = TDRDINT.TAX_RAT;
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        TDCPRINT.PAYED_INT = 0;
        TDCPRINT.PAYING_INT = 0;
        TDCPRINT.DRAW_AMT = 0;
        TDCPRINT.TOT_AMT = 0;
        TDCPRINT.TOT_NUM = 0;
        TDCPRINT.PAYING_TAX = 0;
        if (TDCPRINT.OUT_TYPE == '1' 
            || TDCPRINT.OUT_TYPE == '3') {
            IBS.init(SCCGWA, TDCQDINT);
        }
        if (TDCPRINT.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PRT_INT_NEED_AC_JRN, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCPRINT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CD_CNY_M_INPUT, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCPRINT.AC_DT == 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_OUT_INT_INFO() throws IOException,SQLException,Exception {
        TDCQDINT.TXN_JRN = TDCPRINT.TXN_JRN;
        TDCQDINT.BV_NO = TDCPRINT.BV_NO;
        TDCQDINT.AC_SEQ = TDCPRINT.AC_SEQ;
        if (TDCPRINT.MAC_AC.trim().length() > 0) {
            TDCQDINT.AC = TDCPRINT.MAC_AC;
        } else {
            TDCQDINT.AC = TDCPRINT.AC;
        }
        TDCQDINT.AC_NAME = TDCPRINT.AC_NAME;
        TDCQDINT.CCY = TDCPRINT.CCY;
        TDCQDINT.AC_DT = TDCPRINT.AC_DT;
        TDCQDINT.PROD_CD = TDCPRINT.PROD_CD;
        TDCQDINT.DRAW_AMT = TDCPRINT.DRAW_AMT;
        TDCQDINT.PAYING_AMT = TDCPRINT.PAYING_INT;
        TDCQDINT.TAXING_INT = TDCPRINT.TAXING_INT;
        TDCQDINT.TAX_RAT = TDCPRINT.TAX_RAT;
        TDCQDINT.PAYING_TAX = TDCPRINT.PAYING_TAX;
        TDCQDINT.PAYED_INT = TDCPRINT.PAYED_INT;
        TDCQDINT.TOT_AMT = TDCPRINT.TOT_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INT_FMT;
        SCCFMT.DATA_PTR = TDCQDINT;
        SCCFMT.DATA_LEN = 7912;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, TDCQDINT.TOT_NUM);
        CEP.TRC(SCCGWA, TDCQDINT);
    }
    public void B900_PROCESS_DEC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DINT_INT);
        if (WS_DINT_INT != 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = WS_TMP_CCY;
            BPCRDAMT.AMT = WS_DINT_INT;
            CEP.TRC(SCCGWA, TDRDINT.CCY);
            CEP.TRC(SCCGWA, BPCRDAMT.AMT);
            S00_CALL_BPZRDAMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
            WS_DINT_INT = BPCRDAMT.RESULT_AMT;
        }
    }
    public void S00_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        CEP.TRC(SCCGWA, BPCRDAMT.RC.RC_CODE);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STRBR_BY_FULLKEY() throws IOException,SQLException,Exception {
        TDTDINT_BR.rp = new DBParm();
        TDTDINT_BR.rp.TableName = "TDTDINT";
        TDTDINT_BR.rp.where = "ACO_AC = :TDRDINT.KEY.ACO_AC "
            + "AND AC_DATE = :TDRDINT.KEY.AC_DATE "
            + "AND JRNNO = :TDRDINT.KEY.JRNNO "
            + "AND STATUS = 'N'";
        TDTDINT_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, TDRDINT, this, TDTDINT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_PERD_INT, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STRBR_FLG = 'Y';
    }
    public void T000_STRBR_BY_FULLKEY_CANCEL() throws IOException,SQLException,Exception {
        TDTDINT_BR.rp = new DBParm();
        TDTDINT_BR.rp.TableName = "TDTDINT";
        TDTDINT_BR.rp.where = "ACO_AC = :TDRDINT.KEY.ACO_AC "
            + "AND AC_DATE = :TDRDINT.KEY.AC_DATE "
            + "AND JRNNO = :TDRDINT.KEY.JRNNO "
            + "AND STATUS = 'R'";
        TDTDINT_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, TDRDINT, this, TDTDINT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_PERD_INT, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STRBR_FLG = 'Y';
    }
    public void T000_STRBR_BY_XH_XC() throws IOException,SQLException,Exception {
        TDTDINT_BR.rp = new DBParm();
        TDTDINT_BR.rp.TableName = "TDTDINT";
        TDTDINT_BR.rp.where = "ACO_AC = :TDRDINT.KEY.ACO_AC "
            + "AND INT_TYPE IN ( '3' , '4' , '5' , '6' ) "
            + "AND STATUS = 'N'";
        IBS.STARTBR(SCCGWA, TDRDINT, this, TDTDINT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_PERD_INT, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STRBR_FLG = 'Y';
    }
    public void T000_STRBR_BY_AC() throws IOException,SQLException,Exception {
        TDTDINT_BR.rp = new DBParm();
        TDTDINT_BR.rp.TableName = "TDTDINT";
        TDTDINT_BR.rp.where = "ACO_AC = :TDRDINT.KEY.ACO_AC "
            + "AND AC_DATE = :TDRDINT.KEY.AC_DATE "
            + "AND STATUS = 'N'";
        TDTDINT_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, TDRDINT, this, TDTDINT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_PERD_INT, TDCPRINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STRBR_FLG = 'Y';
    }
    public void T000_ENDBR_TDTDINT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTDINT_BR);
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
        if (TDCPRINT.RC_MSG.RC != 0) {
            CEP.TRC(SCCGWA, "TDZPRINT=");
            CEP.TRC(SCCGWA, TDCPRINT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
