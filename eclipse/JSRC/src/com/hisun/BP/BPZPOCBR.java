package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPOCBR {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    char K_STS_OPEN = 'O';
    char K_STS_CLOSE = 'C';
    char K_STS_FORCE = 'F';
    char K_STS_SIGNON = 'O';
    char K_STS_TMPOFF = 'T';
    String K_CPY_BPRORGS = "BPRORGS";
    String WS_ERR_MSG = " ";
    int WS_BR = 0;
    String WS_BCH = " ";
    String WS_LAST_BCH = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_END_FLG = ' ';
    char WS_READ_END_FLG = ' ';
    char WS_AUTO_O_C_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGS BPRORGS = new BPRORGS();
    BPRTLT BPRTLT = new BPRTLT();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPCPBACT BPCPBACT = new BPCPBACT();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    SCCGWA SCCGWA;
    BPCPOCBR BPCPOCBR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPOCBR BPCPOCBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPOCBR = BPCPOCBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPOCBR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRORGS);
        IBS.init(SCCGWA, BPCRMOGS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
            WS_BR = BPCPOCBR.INPUT_DATA.BR;
            B200_OPEN_CLOSE_PROC();
        } else {
            IBS.init(SCCGWA, BPCPBACT);
            BPCPBACT.INPUT_DATA.FUCN = 'S';
            S000_CALL_BPZPBACT();
            CEP.TRC(SCCGWA, "START BRW ACT");
            WS_END_FLG = 'N';
            WS_AUTO_O_C_FLG = 'N';
            WS_LAST_BCH = " ";
            while (WS_END_FLG != 'Y') {
                IBS.init(SCCGWA, BPCPBACT);
                BPCPBACT.INPUT_DATA.FUCN = 'B';
                S000_CALL_BPZPBACT();
                CEP.TRC(SCCGWA, "AFT REDNEX");
                if (BPCPBACT.OUTPUT_DATA.END_FLG == 'Y') {
                    WS_END_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPCPBACT.OUTPUT_DATA.ACT_CNT);
                for (WS_I = 1; WS_I <= BPCPBACT.OUTPUT_DATA.ACT_CNT; WS_I += 1) {
                    WS_BR = BPCPBACT.OUTPUT_DATA.ACT[WS_I-1];
                    CEP.TRC(SCCGWA, WS_BR);
                    JIBS_tmp_str[0] = "" + WS_BR;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_BCH = JIBS_tmp_str[0].substring(0, 3);
                    if (!WS_BCH.equalsIgnoreCase(WS_LAST_BCH)) {
                        CEP.TRC(SCCGWA, WS_BCH);
                        CEP.TRC(SCCGWA, WS_LAST_BCH);
                        WS_LAST_BCH = WS_BCH;
                        IBS.init(SCCGWA, BPCPQBCH);
                        BPCPQBCH.BCH = WS_BCH;
                        BPCPQBCH.ACCT = WS_BR;
                        S000_CALL_BPZPQBCH();
                        CEP.TRC(SCCGWA, BPCPQBCH.OPN_CHECK_FLG);
                        if (BPCPQBCH.OPN_CHECK_FLG == 'N') {
                            WS_AUTO_O_C_FLG = 'Y';
                        }
                        if (BPCPQBCH.OPN_CHECK_FLG == 'Y') {
                            WS_AUTO_O_C_FLG = 'N';
                        }
                    }
                    CEP.TRC(SCCGWA, WS_AUTO_O_C_FLG);
                    if (WS_AUTO_O_C_FLG == 'Y') {
                        CEP.TRC(SCCGWA, "AUTO OPEN CLOSE!");
                        B200_OPEN_CLOSE_PROC();
                    }
                }
            }
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPOCBR.INPUT_DATA.FLAG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        } else {
            if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                if (BPCPOCBR.INPUT_DATA.BR == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B200_OPEN_CLOSE_PROC() throws IOException,SQLException,Exception {
        if (BPCPOCBR.INPUT_DATA.FUCN == 'O') {
            B210_OPEN_ORG_PROC();
        } else if (BPCPOCBR.INPUT_DATA.FUCN == 'C') {
            B220_CLOSE_ORG_PROC();
        } else if (BPCPOCBR.INPUT_DATA.FUCN == 'F') {
            B230_FORCE_CLOSE_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B210_OPEN_ORG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BR);
        R000_QUERY_ORG_STS();
        CEP.TRC(SCCGWA, BPRORGS.STS);
        if (BPCRMOGS.RETURN_INFO == 'N') {
            B211_ADD_ORG_STS();
        } else {
            if (BPRORGS.STS != K_STS_CLOSE 
                && K_STS_CLOSE != K_STS_FORCE) {
                if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
                    S000_ERR_MSG_PROC();
                }
            } else {
                B212_MODIFY_ORG_STS();
            }
        }
    }
    public void B211_ADD_ORG_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMOGS);
        IBS.init(SCCGWA, BPRORGS);
        BPRORGS.KEY.BR = WS_BR;
        BPRORGS.STS = K_STS_OPEN;
        BPRORGS.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGS.OPN_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRMOGS.FUNC = 'C';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B212_MODIFY_ORG_STS() throws IOException,SQLException,Exception {
        if (BPRORGS.LAS_ACDT != SCCGWA.COMM_AREA.AC_DATE) {
            BPRORGS.CLS_CNT = 0;
            BPRORGS.CLS_TM = 0;
            BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPRORGS.OPN_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGS.STS = K_STS_OPEN;
        BPCRMOGS.FUNC = 'U';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B220_CLOSE_ORG_PROC() throws IOException,SQLException,Exception {
        R000_QUERY_ORG_STS();
        if (BPCRMOGS.RETURN_INFO == 'N') {
            if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
                S000_ERR_MSG_PROC();
            }
        } else {
            B231_01_GET_TELLER_CNT();
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT == 0) {
                if (BPRORGS.STS != K_STS_OPEN) {
                    if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    B221_MODIFY_ORG_STS();
                }
            } else {
                if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_SIGNOFF;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B221_MODIFY_ORG_STS() throws IOException,SQLException,Exception {
        BPRORGS.CLS_CNT = BPRORGS.CLS_CNT + 1;
        BPRORGS.CLS_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGS.STS = K_STS_CLOSE;
        BPCRMOGS.FUNC = 'U';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B230_FORCE_CLOSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMOGS);
        R000_QUERY_ORG_STS();
        if (BPCRMOGS.RETURN_INFO == 'N') {
            if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (BPRORGS.STS != K_STS_OPEN) {
                if (BPCPOCBR.INPUT_DATA.FLAG == 'S') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
                    S000_ERR_MSG_PROC();
                }
            } else {
                B231_CHECK_TELLER_SIGNON();
            }
        }
    }
    public void B231_CHECK_TELLER_SIGNON() throws IOException,SQLException,Exception {
        B231_01_GET_TELLER_CNT();
        B231_02_FORCE_CLOSE();
    }
    public void B231_01_GET_TELLER_CNT() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        BPRTLT.NEW_BR = WS_BR;
        BPRTLT.SIGN_STS = 'O';
        BPCRTLTB.INFO.FUNC = 'G';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        WS_CNT += BPCRTLTB.INFO.CNT;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        BPRTLT.NEW_BR = WS_BR;
        BPRTLT.SIGN_STS = 'T';
        BPCRTLTB.INFO.FUNC = 'G';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        WS_CNT += BPCRTLTB.INFO.CNT;
    }
    public void B231_02_FORCE_CLOSE() throws IOException,SQLException,Exception {
        WS_READ_END_FLG = 'N';
        R000_BRW_PROCESS_1();
        WS_CNT = 0;
        while (WS_READ_END_FLG != 'Y') {
            R000_RDNXT_PROCESS();
            if (WS_READ_END_FLG == 'N') {
                if ((BPRTLT.NEW_BR == WS_BR) 
                    && ((BPRTLT.SIGN_STS == K_STS_SIGNON) 
                    || (BPRTLT.SIGN_STS == K_STS_TMPOFF))) {
                    R000_REWRT_PROCESS();
                }
            }
        }
        R000_END_BRW_PROCESS();
        WS_READ_END_FLG = 'N';
        R000_BRW_PROCESS_2();
        WS_CNT = 0;
        while (WS_READ_END_FLG != 'Y') {
            R000_RDNXT_PROCESS();
            if (WS_READ_END_FLG == 'N') {
                if ((BPRTLT.NEW_BR == WS_BR) 
                    && ((BPRTLT.SIGN_STS == K_STS_SIGNON) 
                    || (BPRTLT.SIGN_STS == K_STS_TMPOFF))) {
                    R000_REWRT_PROCESS();
                }
            }
        }
        R000_END_BRW_PROCESS();
        BPRORGS.CLS_CNT = BPRORGS.CLS_CNT + 1;
        BPRORGS.CLS_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGS.STS = K_STS_FORCE;
        BPCRMOGS.FUNC = 'U';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void R000_BRW_PROCESS_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        BPRTLT.NEW_BR = WS_BR;
        BPRTLT.SIGN_STS = 'O';
        BPCRTLTB.INFO.FUNC = 'U';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
    }
    public void R000_BRW_PROCESS_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        BPRTLT.NEW_BR = WS_BR;
        BPRTLT.SIGN_STS = 'T';
        BPCRTLTB.INFO.FUNC = 'U';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
    }
    public void R000_RDNXT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        BPCRTLTB.INFO.FUNC = 'N';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (BPCRTLTB.RETURN_INFO == 'N') {
            WS_READ_END_FLG = 'Y';
        }
    }
    public void R000_END_BRW_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        BPCRTLTB.INFO.FUNC = 'E';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
    }
    public void R000_REWRT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTLTB);
        BPCRTLTB.INFO.FUNC = 'M';
        BPRTLT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRTLT.SIGN_STS = 'F';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
    }
    public void R000_QUERY_ORG_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGS);
        IBS.init(SCCGWA, BPCRMOGS);
        BPRORGS.KEY.BR = WS_BR;
        BPRORGS.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCRMOGS.FUNC = 'R';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPBACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-BRW-ACCT", BPCPBACT);
        CEP.TRC(SCCGWA, BPCPBACT.RC.RC_CODE);
        if (BPCPBACT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPBACT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-STARTBR-TLT", BPCRTLTB);
        if (BPCRTLTB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTMOGS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-ORGS-MAINTAIN", BPCRMOGS);
        if (BPCRMOGS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMOGS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPOCBR.RC.RC_CODE);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPOCBR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPOCBR = ");
            CEP.TRC(SCCGWA, BPCPOCBR);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
