package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8018 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BP803";
    String K_CALL_BPZRFLT = "BP-RELEASE-FLT      ";
    String K_PROC_DD = "DDPEOD92";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    char WS_FLG_STS = ' ';
    char WS_FLG_FUNC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFLT BPCRFLT = new BPCRFLT();
    SCCBSP SCCBSP = new SCCBSP();
    SCCGWA SCCGWA;
    BPB8018_AWA_8018 BPB8018_AWA_8018;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8018 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8018_AWA_8018>");
        BPB8018_AWA_8018 = (BPB8018_AWA_8018) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCRFLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_AWA_DATA();
        B020_GET_FLT_INFO();
        S000_CALL_BPZRFLT();
    }
    public void B010_CHECK_AWA_DATA() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FUNC);
            CEP.TRC(SCCGWA, BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE);
            CEP.TRC(SCCGWA, BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_STS);
            CEP.TRC(SCCGWA, BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM);
            CEP.TRC(SCCGWA, BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY);
            WS_FLG_FUNC = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FUNC;
            WS_FLG_STS = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_STS;
            if ((WS_FLG_FUNC != 'R' 
                && WS_FLG_FUNC != 'C') 
                && BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FUNC != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RELEASE_STS_ERR;
                WS_FLD_NO = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FUNC_NO;
                S000_ERR_MSG_PROC();
            }
            if ((WS_FLG_STS != 'D' 
                && WS_FLG_STS != 'T' 
                && WS_FLG_STS != 'O' 
                && WS_FLG_STS != 'B') 
                && BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_STS != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLOAT_STS_ERR;
                WS_FLD_NO = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_STS_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_GET_FLT_INFO() throws IOException,SQLException,Exception {
        BPCRFLT.FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCRFLT.FMT);
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE;
            BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].CCY = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY;
            CEP.TRC(SCCGWA, BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY);
            BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM;
            BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].STS = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_STS;
            BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FUNC = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FUNC;
            if (BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FUNC != ' ') {
                B030_08_START_BSP_PROC();
            }
        }
    }
    public void B030_08_START_BSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSP);
        SCCBSP.BH_SEQ.BH_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = "FLG=O," + SCCBSP.PARM_DA1.substring(6);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 7 - 1) + "TYPE=" + SCCBSP.PARM_DA1.substring(7 + 5 - 1);
        if (BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE == null) BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE = "";
        JIBS_tmp_int = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE += " ";
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 12 - 1) + BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_CODE.substring(2 - 1, 2 + 1 - 1) + SCCBSP.PARM_DA1.substring(12 + 1 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 13 - 1) + "," + SCCBSP.PARM_DA1.substring(13 + 1 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 14 - 1) + "ITEM=" + SCCBSP.PARM_DA1.substring(14 + 5 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        if (BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM == null) BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM = "";
        JIBS_tmp_int = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 19 - 1) + BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].FLT_ITEM + SCCBSP.PARM_DA1.substring(19 + 2 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 21 - 1) + "," + SCCBSP.PARM_DA1.substring(21 + 1 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 22 - 1) + "CCY=" + SCCBSP.PARM_DA1.substring(22 + 4 - 1);
        if (BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY.trim().length() > 0) {
            if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
            JIBS_tmp_int = SCCBSP.PARM_DA1.length();
            for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
            if (BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY == null) BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY = "";
            JIBS_tmp_int = BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY += " ";
            SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 26 - 1) + BPB8018_AWA_8018.FLT_ARRY[WS_CNT-1].CCY + SCCBSP.PARM_DA1.substring(26 + 3 - 1);
        } else {
            if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
            JIBS_tmp_int = SCCBSP.PARM_DA1.length();
            for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
            SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 26 - 1) + "000" + SCCBSP.PARM_DA1.substring(26 + 3 - 1);
        }
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 29 - 1) + "," + SCCBSP.PARM_DA1.substring(29 + 1 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 30 - 1) + "JRN=" + SCCBSP.PARM_DA1.substring(30 + 4 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 34 - 1) + JIBS_tmp_str[0] + SCCBSP.PARM_DA1.substring(34 + 12 - 1);
        CEP.TRC(SCCGWA, SCCBSP.PARM_DA1);
        SCCBSP.AP_PROC = K_PROC_DD;
        S000_CALL_SCZOBSP();
    }
    public void S000_CALL_BPZRFLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZRFLT, BPCRFLT);
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
