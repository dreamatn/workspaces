package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSOSTF {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_U_CMOV_REGIST = "BP-U-CMOV-REGIST    ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String K_OUTPUT_FMT = " ";
    String WS_ERR_MSG = " ";
    int WS_CCY_CNT = 0;
    int WS_TEMP_CONF = 0;
    double WS_TMP_AMT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCURMOV BPCURMOV = new BPCURMOV();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCOOSTO BPCOOSTO = new BPCOOSTO();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCOOSTF BPCOOSTF = new BPCOOSTF();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCRCWAT SCRCWA = new SCRCWAT();
    SCCGWA SCCGWA;
    BPCSOSTF BPCSOSTF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSOSTF BPCSOSTF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSOSTF = BPCSOSTF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSOSTF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B020_OUT_STORE_PROC_FOR_CN();
                B030_ON_WAY_PROC();
                if (WS_CCY_CNT == 1) {
                    WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
                }
                B025_HISTORY_PROC();
            }
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        BPCSOSTF.CONF_NO = WS_TEMP_CONF;
        BPCSOSTF.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        B050_OUTPUT_PROC();
    }
    public void B020_OUT_STORE_PROC_FOR_CN() throws IOException,SQLException,Exception {
        B020_UPDATE_CASH_INFO_FOR_CN();
        B030_GEN_CASH_VCH_FOR_CN();
    }
    public void B020_UPDATE_CASH_INFO_FOR_CN() throws IOException,SQLException,Exception {
        B02_01_UPDATE_CASH_BAL_FOR_CN();
    }
    public void B030_GEN_CASH_VCH_FOR_CN() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT != 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = "CAS";
            if (BPCSOSTF.VB_BOX_OT == '0') {
                if (BPCSOSTF.PLBOX_TP == '6') {
                    BPCPOEWA.DATA.EVENT_CODE = "CSATMCT";
                } else {
                    BPCPOEWA.DATA.EVENT_CODE = "CR";
                }
            } else {
                BPCPOEWA.DATA.EVENT_CODE = "CSSTCR";
            }
            BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            S000_CALL_BPZPOEWA();
        }
    }
    public void B02_01_UPDATE_CASH_BAL_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBF);
        BPRCLIB.KEY.CCY = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPRCLIB.KEY.CASH_TYP = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPCSOSTF.PLBOX_NO;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_TMP_AMT = BPRCLIB.BAL + BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW);
            }
        } else {
            WS_TMP_AMT = BPRCLIB.BAL - BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        }
        if (WS_TMP_AMT < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
        }
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRCLIB.BAL = BPRCLIB.BAL + BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            }
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW);
            }
        } else {
            BPRCLIB.BAL = BPRCLIB.BAL - BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            }
        }
        if (BPRCLIB.BAL < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY);
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCSOSTF.PLBOX_NO;
        BPCPCHIS.CONF_SEQ = BPCURMOV.CONF_SEQ;
        BPCPCHIS.CASH_TYP = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        if (BPCSOSTF.PLBOX_NO == null) BPCSOSTF.PLBOX_NO = "";
        JIBS_tmp_int = BPCSOSTF.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSOSTF.PLBOX_NO += " ";
        if (BPCSOSTF.PLBOX_NO == null) BPCSOSTF.PLBOX_NO = "";
        JIBS_tmp_int = BPCSOSTF.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSOSTF.PLBOX_NO += " ";
        if (BPCSOSTF.PLBOX_NO.substring(0, 1).equalsIgnoreCase("3") 
            || BPCSOSTF.PLBOX_NO.substring(0, 1).equalsIgnoreCase("6")) {
            BPCPCHIS.VB_FLG = '0';
        } else {
            BPCPCHIS.VB_FLG = '1';
        }
        BPCPCHIS.IN_OUT = 'C';
        S000_CALL_BPZPCHIS();
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
        CEP.TRC(SCCGWA, BPCTLIBF.RC.RC_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '3';
        BPCURMOV.CASH_TYP = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSOSTF.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSOSTF.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        BPCURMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCURMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_BR = BPCSOSTF.IN_BR;
        BPCURMOV.IN_TLR = BPCSOSTF.IN_TLR;
        BPCURMOV.ONWAY_DT = BPCSOSTF.ONWAY_DT;
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        CEP.TRC(SCCGWA, BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT);
        BPCURMOV.TOTAL_AMT = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        S000_CALL_BPZURMOV();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOOSTF);
        BPCOOSTF.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOOSTF.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOOSTF.OUT_VB_FLG = BPCSOSTF.VB_BOX_OT;
        BPCOOSTF.IN_BR = BPCSOSTF.IN_BR;
        BPCOOSTF.IN_TLR = BPCSOSTF.IN_TLR;
        BPCOOSTF.IN_VB_FLG = BPCSOSTF.VB_BOX_IN;
        BPCOOSTF.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOOSTF.CONF_NO = WS_TEMP_CONF;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOOSTF.CCY_INF[WS_CCY_CNT-1].CCY = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCOOSTF.CCY_INF[WS_CCY_CNT-1].AMT = BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSOSTF.FMT;
        SCCFMT.DATA_PTR = BPCOOSTF;
        SCCFMT.DATA_LEN = 147;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZURMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_REGIST, BPCURMOV);
        if (BPCURMOV.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCURMOV.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
