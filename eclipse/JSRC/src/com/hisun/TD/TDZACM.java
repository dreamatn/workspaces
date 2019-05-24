package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSTAR;
import com.hisun.SC.SCCSUBS;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZACM {
    int JIBS_tmp_int;
    DBParm TDTCMST_RD;
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_INQ_MACT_FMT = "TD170";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    TDZACM_WS_LIST WS_LIST = new TDZACM_WS_LIST();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDRBVT TDRBVT = new TDRBVT();
    SCCHMPW SCCHMPW = new SCCHMPW();
    TDCCHPSW TDCCHPSW = new TDCCHPSW();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    TDCACM TDCACM;
    public void MP(SCCGWA SCCGWA, TDCACM TDCACM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCACM = TDCACM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZACM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACM.FUNC);
        CEP.TRC(SCCGWA, TDCACM.AC_NO);
        CEP.TRC(SCCGWA, TDCACM.CARD_PSW_OLD);
        CEP.TRC(SCCGWA, TDCACM.CARD_PSW_NEW);
        CEP.TRC(SCCGWA, TDCACM.ID_TYP);
        CEP.TRC(SCCGWA, TDCACM.ID_NO);
        CEP.TRC(SCCGWA, TDCACM.CI_NM);
        CEP.TRC(SCCGWA, TDCACM.OLD_AC_NO);
        CEP.TRC(SCCGWA, TDCACM.O_OLD_PSW);
        CEP.TRC(SCCGWA, TDCACM.O_NEW_PSW);
        B100_GET_AC_TYP_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_AC_TYP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCACM.AC_NO;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDRCMST.OPT_DT);
        if (SCCGWA.COMM_AREA.AC_DATE > TDRCMST.OPT_DT) {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            TDRCMST.STSW = TDRCMST.STSW.substring(0, 4 - 1) + "0" + TDRCMST.STSW.substring(4 + 1 - 1);
            TDRCMST.ERR_NUM = 0;
        }
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSW_LOCK_PLS_RLS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSW_LOS_PLS_RLS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRCMST.ERR_NUM >= 6) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.PSW_FAIL_TOO_MUCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = TDCACM.FUNC;
        WS_I = 1;
        if (TDCACM.AC_NO == null) TDCACM.AC_NO = "";
        JIBS_tmp_int = TDCACM.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACM.AC_NO += " ";
        while (TDCACM.AC_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0 
            && WS_I <= 32) {
            WS_I += 1;
        }
        CEP.TRC(SCCGWA, WS_I);
        if (TDCACM.AC_NO == null) TDCACM.AC_NO = "";
        JIBS_tmp_int = TDCACM.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACM.AC_NO += " ";
        DCCUPSWM.AGR_NO_6 = TDCACM.AC_NO.substring(( WS_I - 6 ) - 1, ( WS_I - 6 ) + 6 - 1);
        CEP.TRC(SCCGWA, DCCUPSWM.AGR_NO_6);
        DCCUPSWM.RC.RC_MMO = "TD";
        DCCUPSWM.AGR_NO = TDCACM.AC_NO;
        DCCUPSWM.OLD_AGR_NO = TDCACM.OLD_AC_NO;
        DCCUPSWM.CARD_PSW_OLD = TDCACM.CARD_PSW_OLD;
        DCCUPSWM.CARD_PSW_NEW = TDCACM.CARD_PSW_NEW;
        DCCUPSWM.ID_TYP = TDCACM.ID_TYP;
        DCCUPSWM.ID_NO = TDCACM.ID_NO;
        DCCUPSWM.CI_NM = TDCACM.CI_NM;
        if (TDCACM.FUNC == 'C') {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "1");
                DCCUPSWM.PSW_FLG = 'O';
                S000_CALL_DCZUPSWM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUPSWM.O_OLD_PSW);
                CEP.TRC(SCCGWA, TDRCMST.DRAW_INF);
                if (DCCUPSWM.O_OLD_PSW.equalsIgnoreCase(TDRCMST.DRAW_INF)) {
                    if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                    JIBS_tmp_int = TDRCMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                    TDRCMST.STSW = TDRCMST.STSW.substring(0, 5 - 1) + "0" + TDRCMST.STSW.substring(5 + 1 - 1);
                    TDRCMST.DRAW_INF = DCCUPSWM.O_NEW_PSW;
                    TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_REWRITE_TDTCMST();
                    if (pgmRtn) return;
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'Y';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                } else {
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'N';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, "2");
                DCCUPSWM.PSW_FLG = 'N';
                S000_CALL_DCZUPSWM();
                if (pgmRtn) return;
                if (DCCUPSWM.O_NEW_PSW.equalsIgnoreCase(TDRCMST.DRAW_INF)) {
                    if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                    JIBS_tmp_int = TDRCMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                    TDRCMST.STSW = TDRCMST.STSW.substring(0, 5 - 1) + "0" + TDRCMST.STSW.substring(5 + 1 - 1);
                    TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    TDRCMST.DRAW_INF = DCCUPSWM.O_NEW_PSW;
                    T000_REWRITE_TDTCMST();
                    if (pgmRtn) return;
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'Y';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                } else {
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'N';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else if (TDCACM.FUNC == 'R') {
            CEP.TRC(SCCGWA, "3");
            if (TDCACM.OPSW_FLG == 'Y') {
                DCCUPSWM.PSW_FLG = 'O';
                DCCUPSWM.FUNC = 'C';
                S000_CALL_DCZUPSWM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUPSWM.O_OLD_PSW);
                TDRCMST.DRAW_INF = DCCUPSWM.O_OLD_PSW;
                TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                JIBS_tmp_int = TDRCMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                TDRCMST.STSW = TDRCMST.STSW.substring(0, 5 - 1) + "1" + TDRCMST.STSW.substring(5 + 1 - 1);
                T000_REWRITE_TDTCMST();
                if (pgmRtn) return;
            } else {
                DCCUPSWM.PSW_FLG = 'N';
                S000_CALL_DCZUPSWM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUPSWM.O_NEW_PSW);
                TDRCMST.DRAW_INF = DCCUPSWM.O_NEW_PSW;
                TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                JIBS_tmp_int = TDRCMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                TDRCMST.STSW = TDRCMST.STSW.substring(0, 5 - 1) + "0" + TDRCMST.STSW.substring(5 + 1 - 1);
                T000_REWRITE_TDTCMST();
                if (pgmRtn) return;
            }
        } else if (TDCACM.FUNC == 'M') {
            CEP.TRC(SCCGWA, "4");
            DCCUPSWM.FUNC = 'C';
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "41");
                DCCUPSWM.PSW_FLG = 'O';
                S000_CALL_DCZUPSWM();
                if (pgmRtn) return;
                if (DCCUPSWM.O_OLD_PSW.equalsIgnoreCase(TDRCMST.DRAW_INF)) {
                    if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                    JIBS_tmp_int = TDRCMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                    TDRCMST.STSW = TDRCMST.STSW.substring(0, 5 - 1) + "0" + TDRCMST.STSW.substring(5 + 1 - 1);
                    TDRCMST.DRAW_INF = DCCUPSWM.O_NEW_PSW;
                    TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_REWRITE_TDTCMST();
                    if (pgmRtn) return;
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'Y';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                } else {
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'N';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, "42");
                DCCUPSWM.PSW_FLG = 'N';
                S000_CALL_DCZUPSWM();
                if (pgmRtn) return;
                DCCUPSWM.PSW_FLG = 'N';
                if (DCCUPSWM.O_NEW_PSW.equalsIgnoreCase(TDRCMST.DRAW_INF)) {
                    if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                    JIBS_tmp_int = TDRCMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                    TDRCMST.STSW = TDRCMST.STSW.substring(0, 5 - 1) + "0" + TDRCMST.STSW.substring(5 + 1 - 1);
                    TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_REWRITE_TDTCMST();
                    if (pgmRtn) return;
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'Y';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                } else {
                    TDCCHPSW.AC = TDRCMST.KEY.AC_NO;
                    TDCCHPSW.CHK_FLG = 'N';
                    S000_CALL_TDZCHPSW();
                    if (pgmRtn) return;
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "43");
            DCCUPSWM.FUNC = 'R';
            DCCUPSWM.PSW_FLG = 'N';
            S000_CALL_DCZUPSWM();
            if (pgmRtn) return;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            TDRCMST.DRAW_INF = DCCUPSWM.O_NEW_PSW;
            TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTCMST();
            if (pgmRtn) return;
        }
    }
    public void B212_OUTPUT_MAIN_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_MACT_FMT;
        SCCFMT.DATA_PTR = WS_LIST;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-PSW-MAINTAIN", DCCUPSWM);
        CEP.TRC(SCCGWA, DCCUPSWM.RC.RC_CODE);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZCHPSW() throws IOException,SQLException,Exception {
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "TDZCHPSW";
        WS_STAR_COMM.STAR_DATA = TDCCHPSW;
        IBS.START(SCCGWA, WS_STAR_COMM, false);
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
