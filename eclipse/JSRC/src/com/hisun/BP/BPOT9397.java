package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9397 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_CNT = 0;
    int WS_CNT_CAP = 0;
    int WS_CNT_LOWER = 0;
    int WS_CNT_NUM = 0;
    int WS_CNT_SPE = 0;
    short WS_PSW_LEN = 0;
    int WS_CNT_CONTIN = 0;
    char WS_TEMP_CHAR = ' ';
    char WS_SPECIAL_CHAR = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    SCCGWA SCCGWA;
    BPB4930_AWA_4930 BPB4930_AWA_4930;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9397 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4930_AWA_4930>");
        BPB4930_AWA_4930 = (BPB4930_AWA_4930) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.PSW);
        WS_PSW_LEN = 6;
        CEP.TRC(SCCGWA, WS_PSW_LEN);
        if (BPB4930_AWA_4930.PSW.trim().length() > 0) {
            if (BPB4930_AWA_4930.PSW == null) BPB4930_AWA_4930.PSW = "";
            JIBS_tmp_int = BPB4930_AWA_4930.PSW.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPB4930_AWA_4930.PSW += " ";
            for (WS_CNT = 1; WS_CNT <= WS_PSW_LEN 
                && BPB4930_AWA_4930.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() != 0; WS_CNT += 1) {
                if (BPB4930_AWA_4930.PSW == null) BPB4930_AWA_4930.PSW = "";
                JIBS_tmp_int = BPB4930_AWA_4930.PSW.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPB4930_AWA_4930.PSW += " ";
                if () {
                    WS_CNT_NUM += 1;
                }
                if (BPB4930_AWA_4930.PSW == null) BPB4930_AWA_4930.PSW = "";
                JIBS_tmp_int = BPB4930_AWA_4930.PSW.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPB4930_AWA_4930.PSW += " ";
                if () {
                    WS_CNT_LOWER += 1;
                }
                if (BPB4930_AWA_4930.PSW == null) BPB4930_AWA_4930.PSW = "";
                JIBS_tmp_int = BPB4930_AWA_4930.PSW.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPB4930_AWA_4930.PSW += " ";
                if () {
                    WS_CNT_CAP += 1;
                }
                if (BPB4930_AWA_4930.PSW == null) BPB4930_AWA_4930.PSW = "";
                JIBS_tmp_int = BPB4930_AWA_4930.PSW.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPB4930_AWA_4930.PSW += " ";
                WS_SPECIAL_CHAR = BPB4930_AWA_4930.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ((WS_SPECIAL_CHAR == '!' 
                    || WS_SPECIAL_CHAR == '@' 
                    || WS_SPECIAL_CHAR == '#' 
                    || WS_SPECIAL_CHAR == '$' 
                    || WS_SPECIAL_CHAR == '%' 
                    || WS_SPECIAL_CHAR == '&' 
                    || WS_SPECIAL_CHAR == '*' 
                    || WS_SPECIAL_CHAR == '(' 
                    || WS_SPECIAL_CHAR == ')')) {
                    WS_CNT_SPE += 1;
                }
            }
            WS_I = 0;
            if (WS_CNT_NUM > 0) {
                WS_I += 1;
            }
            if (WS_CNT_CAP > 0) {
                WS_I += 1;
            }
            if (WS_CNT_LOWER > 0) {
                WS_I += 1;
            }
            if (WS_CNT_SPE > 0) {
                WS_I += 1;
            }
            CEP.TRC(SCCGWA, "HOW MANY NUMBER:");
            CEP.TRC(SCCGWA, WS_CNT_NUM);
            CEP.TRC(SCCGWA, "HOW MANY CAPS CHARS:");
            CEP.TRC(SCCGWA, WS_CNT_CAP);
            CEP.TRC(SCCGWA, "HOW MANY LOWER CHARS:");
            CEP.TRC(SCCGWA, WS_CNT_LOWER);
            CEP.TRC(SCCGWA, "HOW MANY SPECIAL CHARS:");
            CEP.TRC(SCCGWA, WS_CNT_SPE);
            CEP.TRC(SCCGWA, "HOW MANY ELEMENT:");
            CEP.TRC(SCCGWA, WS_I);
            if (WS_I < 2) {
                CEP.TRC(SCCGWA, "PASSWORDS STRENGTH:LOW");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSW_STRTH_LOW;
                S000_ERR_MSG_PROC();
            }
            if (WS_I == 2) {
                CEP.TRC(SCCGWA, "PASSWORDS STRENGTH:MIDDLE");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSW_STRTH_MID;
                S000_ERR_MSG_PROC();
            }
            if (WS_I > 2) {
                CEP.TRC(SCCGWA, "PASSWORDS STRENGTH:HIGH");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSW_STRTH_HIGH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
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
