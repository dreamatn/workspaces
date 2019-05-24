package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5270 {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_RATEID = "RRTID";
    String K_TENOR = "OSTNO";
    String K_PROC_SCPWAT93 = "SCPWAT93";
    String K_PROC_SCPFTP99 = "SCPFTP99";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    char WS_BAT_FINISH = ' ';
    char WS_BAT_RUNNING = ' ';
    BPOT5270_WS_OSINT_REC WS_OSINT_REC = new BPOT5270_WS_OSINT_REC();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRTID BPRRTID = new BPRRTID();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPROSTNO BPROSTNO = new BPROSTNO();
    SCCCBSTS SCCCBSTS = new SCCCBSTS();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCSURTE BPCSURTE = new BPCSURTE();
    BPCSMMRT BPCSMMRT = new BPCSMMRT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5270_AWA_5270 BPB5270_AWA_5270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5270 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5270_AWA_5270>");
        BPB5270_AWA_5270 = (BPB5270_AWA_5270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_BATCH();
        if (pgmRtn) return;
        if (WS_BAT_FINISH == 'F' 
            && WS_BAT_RUNNING == ' ') {
            Z_RET();
            if (pgmRtn) return;
        } else {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 79 
            && (BPB5270_AWA_5270.TIMS[WS_CNT-1].CCY.trim().length() != 0 
            && BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY.charAt(0) != 0X00 
            && BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY.trim().length() != 0 
            && BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR.trim().length() != 0); WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "CHECK RATE TYPE");
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = "RBASE";
            CEP.TRC(SCCGWA, BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY);
            BPCOQPCD.INPUT_DATA.CODE = BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            if (BPCOQPCD.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                WS_FLD_NO = BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "CHECK TENOR");
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
            CEP.TRC(SCCGWA, BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR);
            BPCOQPCD.INPUT_DATA.CODE = BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            if (BPCOQPCD.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                WS_FLD_NO = BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "CHECK CCY");
            IBS.init(SCCGWA, BPCQCCY);
            CEP.TRC(SCCGWA, BPB5270_AWA_5270.TIMS[WS_CNT-1].CCY);
            BPCQCCY.DATA.CCY = BPB5270_AWA_5270.TIMS[WS_CNT-1].CCY;
            WS_FLD_NO = BPB5270_AWA_5270.TIMS[WS_CNT-1].CCY_NO;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "GET RATE ID");
            IBS.init(SCCGWA, BPCOQRTD);
            BPCOQRTD.DATA.CCY = BPB5270_AWA_5270.TIMS[WS_CNT-1].CCY;
            BPCOQRTD.DATA.BASE_TYP = BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY;
            B010_INQ_PARM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPROSTNO.DATA_TXT.TENOR_TY);
            BPCOQRTD.DATA.TENOR = BPROSTNO.DATA_TXT.TENOR_TY;
            BPCOQRTD.INQ_FLG = 'C';
            S000_CALL_BPZPQRTD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
            if (BPCOQRTD.DATA.RATE_ID.trim().length() > 0) {
                IBS.init(SCCGWA, BPCSURTE);
                B200_MOVE_DATA();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
                CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].RATE_ID);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_NOT_EXIST;
                CEP.TRC(SCCGWA, WS_ERR_MSG);
                WS_FLD_NO = BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MOVE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT);
        BPCSURTE.BASE_TYP = BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY;
        CEP.TRC(SCCGWA, BPCSURTE.BASE_TYP);
        BPCSURTE.CCY = BPB5270_AWA_5270.TIMS[WS_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSURTE.CCY);
        B010_INQ_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPROSTNO.DATA_TXT.TENOR_TY);
        BPCSURTE.TENOR = BPROSTNO.DATA_TXT.TENOR_TY;
        CEP.TRC(SCCGWA, BPCSURTE.TENOR);
        if (BPB5270_AWA_5270.TIMS[WS_CNT-1].EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            BPCSURTE.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, BPCSURTE.EFF_DT);
        } else {
            BPCSURTE.EFF_DT = BPB5270_AWA_5270.TIMS[WS_CNT-1].EFF_DT;
            CEP.TRC(SCCGWA, BPCSURTE.EFF_DT);
        }
        BPCSURTE.RATE = BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE;
        CEP.TRC(SCCGWA, BPCSURTE.RATE);
        CEP.TRC(SCCGWA, "11111111111111111");
        CEP.TRC(SCCGWA, BPCSURTE);
        B020_INQ_PARM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPCMWD);
        BPCPCMWD.DATE_TYPE = 'B';
        BPCPCMWD.CHECK_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_OSINT_REC.WS_PARMC_VAL.WS_CNTYS_CD;
        CEP.TRC(SCCGWA, WS_OSINT_REC.WS_PARMC_VAL.WS_CNTYS_CD);
        S000_CALL_BPZPCMWD();
        if (pgmRtn) return;
        if (BPCPCMWD.HOLIDAY_FLG[1-1] == 'N') {
            CEP.TRC(SCCGWA, "NOT_HOLIDAY");
            S000_CALL_BPZSURTE();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_BATCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCBSTS);
        SCCCBSTS.PROC_NAME = K_PROC_SCPWAT93;
        S000_CALL_SCZCBSTS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCCBSTS);
        WS_BAT_FINISH = SCCCBSTS.PROC_STUS;
        IBS.init(SCCGWA, SCCCBSTS);
        SCCCBSTS.PROC_NAME = K_PROC_SCPFTP99;
        S000_CALL_SCZCBSTS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCCBSTS);
        WS_BAT_RUNNING = SCCCBSTS.PROC_STUS;
    }
    public void S000_CALL_BPZSURTE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-UPD-RATE", BPCSURTE);
        CEP.TRC(SCCGWA, BPCSURTE.RC);
        if (BPCSURTE.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSURTE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_INQ_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMR.FUNC = ' ';
        BPCPRMR.TYP = "OSTNO";
        BPCPRMR.CD = BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR;
        CEP.TRC(SCCGWA, BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR);
        CEP.TRC(SCCGWA, BPCPRMR.TYP);
        CEP.TRC(SCCGWA, BPCPRMR.CD);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPROSTNO.DATA_TXT.TENOR_TY);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPROSTNO;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID;
            WS_FLD_NO = BPB5270_AWA_5270.TIMS[WS_CNT-1].TENOR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, WS_OSINT_REC);
        WS_OSINT_REC.WS_OSINT.WS_OSINT_TYP = "OSINT";
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (!WS_OSINT_REC.WS_PARMC_VAL.WS_BASE_TYP.equalsIgnoreCase(BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY) 
            && BPCRMBPM.RETURN_INFO != 'L') {
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        if (!WS_OSINT_REC.WS_PARMC_VAL.WS_BASE_TYP.equalsIgnoreCase(BPB5270_AWA_5270.TIMS[WS_CNT-1].RATE_TY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = WS_OSINT_REC;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        CEP.TRC(SCCGWA, WS_OSINT_REC.WS_PARMC_VAL);
        CEP.TRC(SCCGWA, WS_OSINT_REC.WS_PARMC_VAL.WS_BASE_TYP);
        CEP.TRC(SCCGWA, WS_OSINT_REC.WS_PARMC_DESC);
        if (WS_OSINT_REC.WS_PARMC_CDESC == null) WS_OSINT_REC.WS_PARMC_CDESC = "";
        JIBS_tmp_int = WS_OSINT_REC.WS_PARMC_CDESC.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) WS_OSINT_REC.WS_PARMC_CDESC += " ";
        CEP.TRC(SCCGWA, WS_OSINT_REC.WS_PARMC_CDESC.substring(0, 30));
        if (WS_OSINT_REC.WS_PARMC_CDESC == null) WS_OSINT_REC.WS_PARMC_CDESC = "";
        JIBS_tmp_int = WS_OSINT_REC.WS_PARMC_CDESC.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) WS_OSINT_REC.WS_PARMC_CDESC += " ";
        CEP.TRC(SCCGWA, WS_OSINT_REC.WS_PARMC_CDESC.substring(50 - 1, 50 + 10 - 1));
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCMWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZCBSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-CHECK-BAT-STATUS", SCCCBSTS);
        if (SCCCBSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCCBSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S010_CALL_BPZSMMRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MARKET-RATE", BPCSMMRT);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
