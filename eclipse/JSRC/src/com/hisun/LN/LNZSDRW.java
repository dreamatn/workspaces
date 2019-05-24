package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSDRW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZSDRW_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCUDRW LNCUDRW = new LNCUDRW();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPLDPD BPCPLDPD = new BPCPLDPD();
    SCCGWA SCCGWA;
    LNCSDRW LNCSDRW;
    public void MP(SCCGWA SCCGWA, LNCSDRW LNCSDRW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSDRW = LNCSDRW;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSDRW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSDRW.RC.RC_APP = "LN";
        LNCSDRW.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSDRW.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    IBS.init(SCCGWA, LNCLOANM);
    LNCLOANM.FUNC = '4';
    LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSDRW.COMM_DATA.LN_AC;
    S000_CALL_LNZLOANM();
    if (pgmRtn) return;
    public void B010_CHECK_PROD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLDPD);
        BPCPLDPD.KIND = '1';
        BPCPLDPD.PRDT_CODE = LNCCONTM.REC_DATA.PROD_CD;
        CEP.TRC(SCCGWA, BPCPLDPD);
        S000_CALL_BPZPLDPD();
        if (pgmRtn) return;
        if (BPCPLDPD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLDPD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDRW.RC);
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_LIMIT_PROCESS();
        if (pgmRtn) return;
        if (!LNCSDRW.COMM_DATA.TRAN_AC.equalsIgnoreCase("0") 
            && LNCSDRW.COMM_DATA.TRAN_AC.trim().length() > 0) {
            B200_SET_FEE_INFO();
            if (pgmRtn) return;
        }
        B300_DRAWDOWN_PROCESS();
        if (pgmRtn) return;
        if (!LNCSDRW.COMM_DATA.TRAN_AC.equalsIgnoreCase("0") 
            && LNCSDRW.COMM_DATA.TRAN_AC.trim().length() > 0) {
            B400_DEPOSIT_TRANPROC();
            if (pgmRtn) return;
        }
        B500_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_LIMIT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B200_SET_FEE_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSDRW.COMM_DATA.TRAN_AC);
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = LNCSDRW.COMM_DATA.TRAN_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSDRW.COMM_DATA.CCY;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B300_DRAWDOWN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRW);
        LNCUDRW.COMM_DATA.LN_AC = LNCSDRW.COMM_DATA.LN_AC;
        LNCUDRW.COMM_DATA.SUF_NO = LNCSDRW.COMM_DATA.SUF_NO;
        LNCUDRW.COMM_DATA.CCY = LNCSDRW.COMM_DATA.CCY;
        LNCUDRW.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRW.COMM_DATA.DRAW_AMT = LNCSDRW.COMM_DATA.DRAW_AMT;
        LNCUDRW.COMM_DATA.VAL_DT = LNCSDRW.COMM_DATA.VAL_DT;
        LNCUDRW.COMM_DATA.TRAN_AC = LNCSDRW.COMM_DATA.TRAN_AC;
        S000_CALL_LNZUDRW();
        if (pgmRtn) return;
        LNCSDRW.COMM_DATA.SUF_NO = LNCUDRW.COMM_DATA.SUF_NO;
    }
    public void B400_DEPOSIT_TRANPROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSDRW.COMM_DATA.TRAN_AC);
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = LNCSDRW.COMM_DATA.TRAN_AC;
        DDCUCRAC.CCY = LNCSDRW.COMM_DATA.CCY;
        DDCUCRAC.TX_AMT = LNCSDRW.COMM_DATA.DRAW_AMT;
        DDCUCRAC.OTHER_AC = LNCSDRW.COMM_DATA.LN_AC;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_MMO = "NTD";
        DDCUCRAC.BANK_CR_FLG = 'Y';
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B500_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZUDRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-DRAWDOWN", LNCUDRW);
        if (LNCUDRW.RC.RC_RTNCODE != 0) {
            LNCSDRW.RC.RC_APP = LNCUDRW.RC.RC_APP;
            LNCSDRW.RC.RC_RTNCODE = LNCUDRW.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            LNCSDRW.RC.RC_APP = BPCFFTXI.RC.RC_MMO;
            LNCSDRW.RC.RC_RTNCODE = BPCFFTXI.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPLDPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-LOAD-PROD", BPCPLDPD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSDRW.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSDRW=");
            CEP.TRC(SCCGWA, LNCSDRW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
