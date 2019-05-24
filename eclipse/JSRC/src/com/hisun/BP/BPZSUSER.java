package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSUSER {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_TIME_AREA1 = 0;
    BPZSUSER_WS_TIME_N WS_TIME_N = new BPZSUSER_WS_TIME_N();
    int WS_TIME_AREA2 = 0;
    BPZSUSER_WS_TIME_O WS_TIME_O = new BPZSUSER_WS_TIME_O();
    BPZSUSER_WS_TIME WS_TIME = new BPZSUSER_WS_TIME();
    char WS_STOP = ' ';
    char WS_FIND = ' ';
    char WS_END_BRW = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRUSER BPCRUSER = new BPCRUSER();
    BPRUSER BPRUSER = new BPRUSER();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSUSER BPCSUSER;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSUSER BPCSUSER) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSUSER = BPCSUSER;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSUSER return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCSUSER.RC.RC_APP = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSUSER.FUNC == 'U') {
            B010_UPDATE_TUSER_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSUSER.FUNC == 'C') {
            B020_CHECK_TUSER_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSUSER.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_UPDATE_TUSER_PROCESS() throws IOException,SQLException,Exception {
        BPCRUSER.INFO.ID = BPCSUSER.ID;
        WS_FIND = 'Y';
        BPCRUSER.INFO.FUNC = 'B';
        BPCRUSER.INFO.OPT = 'S';
        BPCRUSER.INFO.UPD_FLAG = 'Y';
        BPCRUSER.LEN = 78;
        BPCRUSER.INFO.POINTER = BPRUSER;
        S000_CALL_BPZRUSER();
        if (pgmRtn) return;
        if (BPCRUSER.RETURN_INFO == 'N') {
            WS_FIND = 'N';
        }
        WS_END_BRW = 'N';
        while (BPCRUSER.RETURN_INFO != 'N' 
            && WS_END_BRW != 'Y') {
            BPCRUSER.INFO.FUNC = 'B';
            BPCRUSER.INFO.OPT = 'N';
            S000_CALL_BPZRUSER();
            if (pgmRtn) return;
            if (BPCRUSER.RETURN_INFO == 'F') {
                BPCRUSER.INFO.FUNC = 'D';
                BPCRUSER.LEN = 78;
                BPCRUSER.INFO.POINTER = BPRUSER;
                S000_CALL_BPZRUSER();
                if (pgmRtn) return;
                BPRUSER.KEY.USER_ID = BPCSUSER.ID;
                BPRUSER.KEY.SEQ_NO = BPCSUSER.SEQ_NO;
                BPRUSER.DATE = BPCSUSER.DATE;
                BPRUSER.TIME = BPCSUSER.TIME;
                BPCRUSER.INFO.FUNC = 'A';
                BPCRUSER.LEN = 78;
                BPCRUSER.INFO.POINTER = BPRUSER;
                S000_CALL_BPZRUSER();
                if (pgmRtn) return;
                WS_END_BRW = 'Y';
            } else {
                if (BPCRUSER.RETURN_INFO == 'N') {
                    WS_FIND = 'N';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        BPCRUSER.INFO.FUNC = 'B';
        BPCRUSER.INFO.OPT = 'E';
        S000_CALL_BPZRUSER();
        if (pgmRtn) return;
        if (WS_FIND == 'N') {
            BPRUSER.KEY.USER_ID = BPCSUSER.ID;
            BPRUSER.KEY.SEQ_NO = BPCSUSER.SEQ_NO;
            BPRUSER.DATE = BPCSUSER.DATE;
            BPRUSER.TIME = BPCSUSER.TIME;
            BPCRUSER.INFO.FUNC = 'A';
            BPCRUSER.LEN = 78;
            BPCRUSER.INFO.POINTER = BPRUSER;
            S000_CALL_BPZRUSER();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_TUSER_PROCESS() throws IOException,SQLException,Exception {
        BPCRUSER.INFO.ID = BPCSUSER.ID;
        BPCRUSER.INFO.FUNC = 'B';
        BPCRUSER.INFO.OPT = 'S';
        BPCRUSER.INFO.UPD_FLAG = 'Y';
        BPCRUSER.LEN = 78;
        BPCRUSER.INFO.POINTER = BPRUSER;
        S000_CALL_BPZRUSER();
        if (pgmRtn) return;
        WS_END_BRW = 'N';
        while (BPCRUSER.RETURN_INFO != 'N' 
            && WS_END_BRW != 'Y') {
            BPCRUSER.INFO.FUNC = 'B';
            BPCRUSER.INFO.OPT = 'N';
            S000_CALL_BPZRUSER();
            if (pgmRtn) return;
            if (BPCRUSER.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, BPRUSER.KEY.SEQ_NO);
                CEP.TRC(SCCGWA, BPCSUSER.SEQ_NO);
                if (BPRUSER.KEY.SEQ_NO.equalsIgnoreCase(BPCSUSER.SEQ_NO)) {
                    B020_01_CHECK_TIME();
                    if (pgmRtn) return;
                    B020_02_DEL_TUSER();
                    if (pgmRtn) return;
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_END_BRW = 'Y';
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPCRUSER.INFO.FUNC = 'B';
        BPCRUSER.INFO.OPT = 'E';
        S000_CALL_BPZRUSER();
        if (pgmRtn) return;
    }
    public void B020_01_CHECK_TIME() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, BPRUSER.DATE);
        if (SCCGWA.COMM_AREA.TR_DATE == BPRUSER.DATE) {
            WS_TIME_AREA1 = SCCGWA.COMM_AREA.TR_TIME;
            IBS.CPY2CLS(SCCGWA, WS_TIME_AREA1+"", WS_TIME_N);
            WS_TIME_AREA2 = BPRUSER.TIME;
            IBS.CPY2CLS(SCCGWA, WS_TIME_AREA2+"", WS_TIME_O);
            WS_TIME.WS_HOUR = (short) (WS_TIME_N.WS_HOUR_N - WS_TIME_O.WS_HOUR_O);
            CEP.TRC(SCCGWA, WS_TIME.WS_HOUR);
            if (WS_TIME.WS_HOUR < 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_TIME.WS_HOUR == 0) {
                WS_TIME.WS_MINU = (short) (WS_TIME_N.WS_MINU_N - WS_TIME_O.WS_MINU_O);
                CEP.TRC(SCCGWA, WS_TIME.WS_MINU);
                if (WS_TIME.WS_MINU < 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_TIME.WS_SECD_TOT_N = (short) (WS_TIME_N.WS_MINU_N * 60 + WS_TIME_N.WS_SECD_N);
                    WS_TIME.WS_SECD_TOT_O = (short) (WS_TIME_O.WS_MINU_O * 60 + WS_TIME_O.WS_SECD_O);
                    CEP.TRC(SCCGWA, WS_TIME.WS_SECD_TOT_O);
                    CEP.TRC(SCCGWA, WS_TIME.WS_SECD_TOT_N);
                    if (WS_TIME.WS_SECD_TOT_O > WS_TIME.WS_SECD_TOT_N) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        WS_TIME.WS_SECD = (short) (WS_TIME.WS_SECD_TOT_N - WS_TIME.WS_SECD_TOT_O);
                        CEP.TRC(SCCGWA, WS_TIME.WS_SECD);
                        if (WS_TIME.WS_SECD > 120) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
            if (WS_TIME.WS_HOUR > 0) {
                if (WS_TIME.WS_HOUR < 2) {
                    WS_TIME.WS_MINU1 = (short) (60 - WS_TIME_O.WS_MINU_O);
                    WS_TIME.WS_MINU = (short) (WS_TIME.WS_MINU1 + WS_TIME_N.WS_MINU_N);
                    WS_TIME.WS_SECD = (short) (60 * WS_TIME.WS_MINU + WS_TIME_N.WS_SECD_N - WS_TIME_O.WS_SECD_O);
                    CEP.TRC(SCCGWA, WS_TIME.WS_SECD);
                    if (WS_TIME.WS_SECD > 120) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVERTIME_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_02_DEL_TUSER() throws IOException,SQLException,Exception {
        BPCRUSER.INFO.FUNC = 'D';
        BPCRUSER.LEN = 78;
        BPCRUSER.INFO.POINTER = BPRUSER;
        S000_CALL_BPZRUSER();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZRUSER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-TUSER", BPCRUSER);
        CEP.TRC(SCCGWA, BPCRUSER.RC.RC_CODE);
        if (BPCRUSER.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCRUSER.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
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
