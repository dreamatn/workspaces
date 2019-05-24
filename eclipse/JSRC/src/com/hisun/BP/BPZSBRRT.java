package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBRRT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String BP_HIS_REMARKS = "MESSAGE MAINTENANCE";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRHITB BPCRHITB = new BPCRHITB();
    BPCOBRRT BPCOBRRT = new BPCOBRRT();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    SCCGWA SCCGWA;
    BPCSBRTH BPCSBRTH;
    public void MP(SCCGWA SCCGWA, BPCSBRTH BPCSBRTH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBRTH = BPCSBRTH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBRRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        IBS.init(SCCGWA, BPCOBRRT);
        IBS.init(SCCGWA, BPCOQRTD);
        CEP.TRC(SCCGWA, BPCSBRTH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSBRTH.START_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_START_DT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBRTH.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_END_DT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBRTH.BASE_TYP.trim().length() == 0 
            && BPCSBRTH.CCY.trim().length() == 0 
            && BPCSBRTH.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '1';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() == 0 
            && BPCSBRTH.CCY.trim().length() > 0 
            && BPCSBRTH.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '2';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() > 0 
            && BPCSBRTH.CCY.trim().length() == 0 
            && BPCSBRTH.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '3';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() == 0 
            && BPCSBRTH.CCY.trim().length() == 0 
            && BPCSBRTH.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '4';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() > 0 
            && BPCSBRTH.CCY.trim().length() > 0 
            && BPCSBRTH.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '5';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() == 0 
            && BPCSBRTH.CCY.trim().length() > 0 
            && BPCSBRTH.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '6';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() > 0 
            && BPCSBRTH.CCY.trim().length() == 0 
            && BPCSBRTH.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '7';
        }
        if (BPCSBRTH.BASE_TYP.trim().length() > 0 
            && BPCSBRTH.CCY.trim().length() > 0 
            && BPCSBRTH.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '8';
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 54;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        BPCRHITB.FUNC = 'S';
        BPCRHITB.STARTD = BPCSBRTH.START_DT;
        BPCRHITB.ENDD = BPCSBRTH.END_DT;
        BPCRHITB.BR = BPCSBRTH.BR;
        BPCRHITB.CCY = BPCSBRTH.CCY;
        BPCRHITB.BASE_TYP = BPCSBRTH.BASE_TYP;
        BPCRHITB.TENOR = BPCSBRTH.TENOR;
        CEP.TRC(SCCGWA, BPCRHITB);
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        while (BPCRHITB.RC.RC_CODE == 0 
            && SCCMPAG.FUNC != 'E') {
            BPCRHITB.FUNC = 'N';
            S000_CALL_BPZRHITB();
            if (pgmRtn) return;
            if (BPCRHITB.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, BPCOBRRT);
                CEP.TRC(SCCGWA, BPCRHITB.CCY);
                CEP.TRC(SCCGWA, BPCRHITB.BASE_TYP);
                BPCOBRRT.BR = BPCRHITB.BR;
                BPCOBRRT.CCY = BPCRHITB.CCY;
                BPCOQRTD.DATA.CCY = BPCRHITB.CCY;
                BPCOBRRT.RATE_TYPE = BPCRHITB.BASE_TYP;
                BPCOQRTD.DATA.BASE_TYP = BPCRHITB.BASE_TYP;
                BPCOBRRT.TENOR = BPCRHITB.TENOR;
                BPCOQRTD.DATA.TENOR = BPCRHITB.TENOR;
                BPCOQRTD.INQ_FLG = 'C';
                S000_CALL_BPZPQRTD();
                if (pgmRtn) return;
                if (BPCOQRTD.RC.RC_CODE == 0) {
                    BPCOBRRT.RATE_ID = BPCOQRTD.DATA.RATE_ID;
                } else {
                    BPCOBRRT.RATE_ID = " ";
                }
                CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
                BPCOBRRT.EFF_DT = BPCRHITB.DT;
                CEP.TRC(SCCGWA, BPCRHITB.DT);
                BPCOBRRT.RATE = BPCRHITB.RATE;
                CEP.TRC(SCCGWA, BPCRHITB.RATE);
                BPCOBRRT.LST_TLR = BPCRHITB.TELLER;
                CEP.TRC(SCCGWA, BPCRHITB.TELLER);
                B030_OUTPUT_BRRT_INFO();
                if (pgmRtn) return;
            }
        }
        BPCRHITB.FUNC = 'E';
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_BRRT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, BPCOBRRT);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBRRT);
        SCCMPAG.DATA_LEN = 54;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRHITB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-BRW", BPCRHITB);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
