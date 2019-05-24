package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT6020 {
    String K_OUTPUT_FMT = "BAM01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_TR_BRAN = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    BACULOGP BACULOGP = new BACULOGP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BAB6020_AWA_6020 BAB6020_AWA_6020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT6020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB6020_AWA_6020>");
        BAB6020_AWA_6020 = (BAB6020_AWA_6020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANCHE_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B011_CHECK_DATA();
        B012_BANK_RIGHT_CHECK();
        WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B011_CHECK_DATA() throws IOException,SQLException,Exception {
        if (BAB6020_AWA_6020.FUN_COD == '1' 
            || BAB6020_AWA_6020.FUN_COD == '2') {
            if (BAB6020_AWA_6020.BILL_NO.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILL_NO_M_IPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BAB6020_AWA_6020.TX_ST_DT != 0) {
            if (BAB6020_AWA_6020.TX_DU_DT == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_DUE_DT_M_IPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            } else {
                if (BAB6020_AWA_6020.TX_ST_DT > BAB6020_AWA_6020.TX_DU_DT) {
                    WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_DUE_CNT_LIT_STR;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        } else {
            if (BAB6020_AWA_6020.TX_DU_DT != 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_STR_DT_M_IPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
    }
    public void B012_BANK_RIGHT_CHECK() throws IOException,SQLException,Exception {
        WS_TR_BRAN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (WS_TR_BRAN == 188000) {
            if ("100000".trim().length() == 0) WS_TR_BRAN = 0;
            else WS_TR_BRAN = Integer.parseInt("100000");
        }
        CEP.TRC(SCCGWA, WS_TR_BRAN);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_TR_BRAN;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        if (BPCPQORG.LVL == '2') {
            if (BAB6020_AWA_6020.TX_BR != WS_TR_BRAN) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_JUST_CAN_THIS_DATA;
                S000_ERR_MSG_PROC_CONTINUE();
            } else {
                BPCPQORG.LVL = '2';
            }
        } else {
            if (BAB6020_AWA_6020.TX_BR != WS_TR_BRAN) {
                BPCPQORG.BR = BAB6020_AWA_6020.TX_BR;
                S000_CALL_BPZPQORG();
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, WS_TR_BRAN);
                if (BPCPQORG.SUPR_BR != WS_TR_BRAN) {
                    WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BRAN_JUST_CAN_UNDER;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                BPCPQORG.LVL = '2';
            } else {
                BPCPQORG.LVL = '6';
            }
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.SND_TAG);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.SND_DT);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.SND_FLW);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.FUN_COD);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.TX_OPT);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.TX_BR);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.TX_ST_DT);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.TX_DU_DT);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.BILL_CUR);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.BILL_NO);
        CEP.TRC(SCCGWA, BAB6020_AWA_6020.RMK);
        IBS.init(SCCGWA, BACULOGP);
        BACULOGP.DATA.SND_TAG = BAB6020_AWA_6020.SND_TAG;
        BACULOGP.DATA.SND_DT = BAB6020_AWA_6020.SND_DT;
        BACULOGP.DATA.SND_FLW = BAB6020_AWA_6020.SND_FLW;
        BACULOGP.DATA.FUN_COD = BAB6020_AWA_6020.FUN_COD;
        BACULOGP.DATA.TX_OPT = BAB6020_AWA_6020.TX_OPT;
        BACULOGP.DATA.TX_BR = BAB6020_AWA_6020.TX_BR;
        BACULOGP.DATA.TX_BR_LEV = BPCPQORG.LVL;
        BACULOGP.DATA.TX_ST_DT = BAB6020_AWA_6020.TX_ST_DT;
        BACULOGP.DATA.TX_DU_DT = BAB6020_AWA_6020.TX_DU_DT;
        BACULOGP.DATA.BILL_CUR = BAB6020_AWA_6020.BILL_CUR;
        BACULOGP.DATA.BILL_NO = BAB6020_AWA_6020.BILL_NO;
        BACULOGP.DATA.PAGE_ROW = BAB6020_AWA_6020.PAGE_ROW;
        BACULOGP.DATA.PAGE_NUM = BAB6020_AWA_6020.PAGE_NUM;
        BACULOGP.DATA.RMK = BAB6020_AWA_6020.RMK;
        S000_CALL_BAZULOGP();
        if (BACULOGP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACULOGP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZULOGP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-LOGP", BACULOGP);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
