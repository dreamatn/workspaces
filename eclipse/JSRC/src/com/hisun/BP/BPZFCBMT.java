package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCBMT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_R_BRW_BCUS = "BP-R-BRW-BCUS";
    String CPN_R_MGM_BHIS = "BP-R-MGM-BHIS";
    String WS_ERR_MSG = " ";
    int WS_POS = 0;
    short WS_I = 0;
    short WS_NOR_CNT = 0;
    int WS_LEN = 0;
    long WS_COMP_BVNO1 = 0;
    long WS_COMP_BVNO2 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRBCUB BPCRBCUB = new BPCRBCUB();
    BPCRBHIS BPCRBHIS = new BPCRBHIS();
    BPRBCUS BPRBCUS = new BPRBCUS();
    BPRBHIS BPRBHIS = new BPRBHIS();
    SCCGWA SCCGWA;
    BPCFCBMT BPCFCBMT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    public void MP(SCCGWA SCCGWA, BPCFCBMT BPCFCBMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCBMT = BPCFCBMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFCBMT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCFCBMT.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFCBMT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT();
        if (BPCFCBMT.FUNC != '7') {
            B200_MAIN_ROUTINE_NOR();
        } else {
            B200_MAIN_ROUTINE_CLO();
        }
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        if (BPCFCBMT.FUNC != '7') {
            if (BPCFCBMT.BV_CODE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCFCBMT.BV_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVNO_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFCBMT.FUNC == '1') {
            if (BPCFCBMT.ISSU_DT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ISSDT_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCFCBMT.ISSU_AMT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ISSAMT_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_MAIN_ROUTINE_NOR() throws IOException,SQLException,Exception {
        R_QUERY_BV_STS();
        if (BPCFCBMT.FUNC == '0') {
        } else if (BPCFCBMT.FUNC == '1') {
            if (BPCFCBMT.CUS_BV_STS == '0') {
                BPCFCBMT.CUS_BV_STS = '1';
            } else {
                R000_STS_ERR_PROC();
            }
        } else if (BPCFCBMT.FUNC == '2') {
            if (BPCFCBMT.CUS_BV_STS == '1') {
                BPCFCBMT.CUS_BV_STS = '0';
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STS_MUST_USED;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCFCBMT.FUNC == '3') {
            if (BPCFCBMT.CUS_BV_STS == '0') {
                BPCFCBMT.CUS_BV_STS = '3';
            } else {
                R000_STS_ERR_PROC();
            }
        } else if (BPCFCBMT.FUNC == '4') {
            if (BPCFCBMT.CUS_BV_STS == '3') {
                BPCFCBMT.CUS_BV_STS = '0';
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STS_MUST_SP;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCFCBMT.FUNC == '5') {
            if (BPCFCBMT.CUS_BV_STS == '0') {
                BPCFCBMT.CUS_BV_STS = '4';
            } else {
                R000_STS_ERR_PROC();
            }
        } else if (BPCFCBMT.FUNC == '6') {
            if (BPCFCBMT.CUS_BV_STS == '4') {
                BPCFCBMT.CUS_BV_STS = '0';
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STS_MUST_FRZ;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERROR_FUNC;
            S000_ERR_MSG_PROC();
        }
        if (BPCFCBMT.FUNC != '0') {
            if (BPCFCBMT.FUNC == '5'
                || BPCFCBMT.FUNC == '3'
                || BPCFCBMT.FUNC == '1') {
                BPRBCUS.NOR_CNT -= 1;
                if (BPRBCUS.STS == null) BPRBCUS.STS = "";
                JIBS_tmp_int = BPRBCUS.STS.length();
                for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
                JIBS_tmp_str[0] = "" + BPCFCBMT.CUS_BV_STS;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                BPRBCUS.STS = BPRBCUS.STS.substring(0, WS_POS - 1) + JIBS_tmp_str[0] + BPRBCUS.STS.substring(WS_POS + 1 - 1);
            } else if (BPCFCBMT.FUNC == '2'
                || BPCFCBMT.FUNC == '4'
                || BPCFCBMT.FUNC == '6') {
                BPRBCUS.NOR_CNT += 1;
                if (BPRBCUS.STS == null) BPRBCUS.STS = "";
                JIBS_tmp_int = BPRBCUS.STS.length();
                for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
                JIBS_tmp_str[0] = "" + BPCFCBMT.CUS_BV_STS;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                BPRBCUS.STS = BPRBCUS.STS.substring(0, WS_POS - 1) + JIBS_tmp_str[0] + BPRBCUS.STS.substring(WS_POS + 1 - 1);
            } else {
            }
            IBS.init(SCCGWA, BPCRBCUB);
            BPCRBCUB.INFO.FUNC = 'W';
            S000_CALL_BPZRBCUB();
            R_WRITE_BHIS_REC();
        }
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'E';
        S000_CALL_BPZRBCUB();
    }
    public void B200_MAIN_ROUTINE_CLO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCUS);
        BPRBCUS.KEY.AC = BPCFCBMT.AC_NO;
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = '2';
        S000_CALL_BPZRBCUB();
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'N';
        S000_CALL_BPZRBCUB();
        if (BPCRBCUB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BCUS_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        while (BPCRBCUB.RETURN_INFO != 'N') {
            if (BPRBCUS.STS == null) BPRBCUS.STS = "";
            JIBS_tmp_int = BPRBCUS.STS.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
            for (WS_I = 1; WS_I <= 999 
                && BPRBCUS.STS.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
                if (BPRBCUS.STS == null) BPRBCUS.STS = "";
                JIBS_tmp_int = BPRBCUS.STS.length();
                for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
                if (BPRBCUS.STS.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("0")) {
                    BPCFCBMT.CUS_BV_STS = '6';
                    if (BPRBCUS.STS == null) BPRBCUS.STS = "";
                    JIBS_tmp_int = BPRBCUS.STS.length();
                    for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
                    JIBS_tmp_str[0] = "" + BPCFCBMT.CUS_BV_STS;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    BPRBCUS.STS = BPRBCUS.STS.substring(0, WS_I - 1) + JIBS_tmp_str[0] + BPRBCUS.STS.substring(WS_I + 1 - 1);
                    WS_NOR_CNT += 1;
                }
            }
            BPRBCUS.NOR_CNT -= WS_NOR_CNT;
            IBS.init(SCCGWA, BPCRBCUB);
            BPCRBCUB.INFO.FUNC = 'W';
            S000_CALL_BPZRBCUB();
            R_WRITE_BHIS_REC();
            IBS.init(SCCGWA, BPCRBCUB);
            BPCRBCUB.INFO.FUNC = 'N';
            S000_CALL_BPZRBCUB();
        }
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'E';
        S000_CALL_BPZRBCUB();
    }
    public void R000_STS_ERR_PROC() throws IOException,SQLException,Exception {
        if (BPCFCBMT.CUS_BV_STS == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_STS_USED;
            S000_ERR_MSG_PROC();
        } else if (BPCFCBMT.CUS_BV_STS == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_STS_LOST;
            S000_ERR_MSG_PROC();
        } else if (BPCFCBMT.CUS_BV_STS == '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_STS_STOP;
            S000_ERR_MSG_PROC();
        } else if (BPCFCBMT.CUS_BV_STS == '4') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_STS_FRZ;
            S000_ERR_MSG_PROC();
        } else if (BPCFCBMT.CUS_BV_STS == '5') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_STS_CLOSE;
            S000_ERR_MSG_PROC();
        } else if (BPCFCBMT.CUS_BV_STS == '6') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_STS_CLOSE_AC;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_QUERY_BV_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCUS);
        BPRBCUS.KEY.AC = BPCFCBMT.AC_NO;
        BPRBCUS.KEY.BV_CODE = BPCFCBMT.BV_CODE;
        BPRBCUS.KEY.BEG_NO = BPCFCBMT.BV_NO;
        BPRBCUS.KEY.END_NO = BPCFCBMT.BV_NO;
        CEP.TRC(SCCGWA, BPCFCBMT.AC_NO);
        CEP.TRC(SCCGWA, BPCFCBMT.BV_CODE);
        CEP.TRC(SCCGWA, BPCFCBMT.BV_NO);
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = '1';
        S000_CALL_BPZRBCUB();
        BPCRBCUB.INFO.FUNC = 'N';
        S000_CALL_BPZRBCUB();
        if (BPCRBCUB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BCUS_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPCFCBMT.BV_NO == null) BPCFCBMT.BV_NO = "";
        JIBS_tmp_int = BPCFCBMT.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCBMT.BV_NO += " ";
        for (WS_LEN = 1; BPCFCBMT.BV_NO.substring(WS_LEN - 1, WS_LEN + 1 - 1).trim().length() != 0; WS_LEN += 1) {
        }
        WS_LEN -= 1;
        if (BPRBCUS.KEY.BEG_NO == null) BPRBCUS.KEY.BEG_NO = "";
        JIBS_tmp_int = BPRBCUS.KEY.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.BEG_NO += " ";
        if (BPRBCUS.KEY.BEG_NO.substring(0, WS_LEN).trim().length() == 0) WS_COMP_BVNO1 = 0;
        else WS_COMP_BVNO1 = Long.parseLong(BPRBCUS.KEY.BEG_NO.substring(0, WS_LEN));
        if (BPCFCBMT.BV_NO == null) BPCFCBMT.BV_NO = "";
        JIBS_tmp_int = BPCFCBMT.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCBMT.BV_NO += " ";
        if (BPCFCBMT.BV_NO.substring(0, WS_LEN).trim().length() == 0) WS_COMP_BVNO2 = 0;
        else WS_COMP_BVNO2 = Long.parseLong(BPCFCBMT.BV_NO.substring(0, WS_LEN));
        WS_POS = (int) (WS_COMP_BVNO2 - WS_COMP_BVNO1 + 1);
        if (BPRBCUS.STS == null) BPRBCUS.STS = "";
        JIBS_tmp_int = BPRBCUS.STS.length();
        for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
        BPCFCBMT.CUS_BV_STS = BPRBCUS.STS.substring(WS_POS - 1, WS_POS + 1 - 1).charAt(0);
    }
    public void R_WRITE_BHIS_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBHIS);
        BPRBHIS.KEY.AC = BPRBCUS.KEY.AC;
        BPRBHIS.KEY.BV_CODE = BPRBCUS.KEY.BV_CODE;
        BPRBHIS.KEY.HEAD_NO = BPRBCUS.HEAD_NO;
        if (BPCFCBMT.FUNC != '7') {
            BPRBHIS.KEY.BEG_NO = BPCFCBMT.BV_NO;
            BPRBHIS.KEY.END_NO = BPCFCBMT.BV_NO;
        } else {
            BPRBHIS.KEY.BEG_NO = BPRBCUS.KEY.BEG_NO;
            BPRBHIS.KEY.END_NO = BPRBCUS.KEY.END_NO;
        }
        BPRBHIS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBHIS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCFCBMT.FUNC == '1') {
            BPRBHIS.ISSU_DATE = BPCFCBMT.ISSU_DT;
            BPRBHIS.ISSU_AMT = BPCFCBMT.ISSU_AMT;
        }
        BPRBHIS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBHIS.TX_TLR = BPRTLT.KEY.TLR;
        BPRBHIS.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBHIS.STS = BPCFCBMT.CUS_BV_STS;
        IBS.init(SCCGWA, BPCRBHIS);
        BPCRBHIS.INFO.FUNC = 'A';
        S000_CALL_BPZRBHIS();
    }
    public void S000_CALL_BPZRBCUB() throws IOException,SQLException,Exception {
        BPCRBCUB.INFO.POINTER = BPRBCUS;
        BPCRBCUB.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BCUS, BPCRBCUB);
        if (BPCRBCUB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBCUB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBHIS() throws IOException,SQLException,Exception {
        BPCRBHIS.INFO.POINTER = BPRBHIS;
        BPCRBHIS.INFO.LEN = 163;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BHIS, BPCRBHIS);
        if (BPCRBHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFCBMT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCBMT = ");
            CEP.TRC(SCCGWA, BPCFCBMT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
