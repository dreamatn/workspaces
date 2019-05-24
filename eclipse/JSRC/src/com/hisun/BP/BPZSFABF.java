package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFABF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFABF_RD;
    brParm BPTFABF_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP198";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_MAX_DT = 99999999;
    String WS_STR_CCY = " ";
    String WS_END_CCY = " ";
    char WS_CCY_TYP = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    String WS_STR_AC = " ";
    String WS_END_AC = " ";
    String WS_ERR_MSG = " ";
    String WS_REC = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
    short WS_REC_LEN = 0;
    String WS_TABLE_NAME = " ";
    BPZSFABF_WS_RTN_DATA_A WS_RTN_DATA_A = new BPZSFABF_WS_RTN_DATA_A();
    char WS_TABLE_REC = ' ';
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRFABF BPRFABF = new BPRFABF();
    BPC198 BPC198 = new BPC198();
    DDCSCINM DDCSCINM = new DDCSCINM();
    CICACCU CICACCU = new CICACCU();
    SCCBSP SCCBSP = new SCCBSP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSFABF BPCSFABF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSFABF BPCSFABF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFABF = BPCSFABF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFABF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSFABF.FUNC == 'I') {
            B001_INQ_BPTFABF_PROC();
            if (pgmRtn) return;
        } else if (BPCSFABF.FUNC == 'A') {
            B002_ADD_BPTFABF_PROC();
            if (pgmRtn) return;
            B008_WRITE_BPTPNHIS();
            if (pgmRtn) return;
        } else if (BPCSFABF.FUNC == 'U') {
            B003_UPD_BPTFABF_PROC();
            if (pgmRtn) return;
            B008_WRITE_BPTPNHIS();
            if (pgmRtn) return;
        } else if (BPCSFABF.FUNC == 'D') {
            B004_DEL_BPTFABF_PROC();
            if (pgmRtn) return;
            B008_WRITE_BPTPNHIS();
            if (pgmRtn) return;
        } else if (BPCSFABF.FUNC == 'B') {
            B005_BRW_BPTFABF_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSFABF.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B002_ADD_BPTFABF_PROC() throws IOException,SQLException,Exception {
        B020_CHK_RECORD_PROC();
        if (pgmRtn) return;
        R100_TRANS_DATA();
        if (pgmRtn) return;
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_CHK_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.FUNC = '1';
        DDCSCINM.INPUT_DATA.CCY = BPCSFABF.KEY.CCY;
        DDCSCINM.INPUT_DATA.AC_NO = BPCSFABF.KEY.AC;
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        if (BPCSFABF.FEE_AC1.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.CCY = BPCSFABF.KEY.CCY;
            DDCSCINM.INPUT_DATA.AC_NO = BPCSFABF.FEE_AC1;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
        }
        if (BPCSFABF.FEE_AC2.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.CCY = BPCSFABF.KEY.CCY;
            DDCSCINM.INPUT_DATA.AC_NO = BPCSFABF.FEE_AC2;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
        }
        if (BPCSFABF.FEE_AC3.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.CCY = BPCSFABF.KEY.CCY;
            DDCSCINM.INPUT_DATA.AC_NO = BPCSFABF.FEE_AC3;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
        }
        if (BPCSFABF.FEE_AC4.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.CCY = BPCSFABF.KEY.CCY;
            DDCSCINM.INPUT_DATA.AC_NO = BPCSFABF.FEE_AC4;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
        }
        if (BPCSFABF.FEE_AC5.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.CCY = BPCSFABF.KEY.CCY;
            DDCSCINM.INPUT_DATA.AC_NO = BPCSFABF.FEE_AC5;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
        }
        B020_CHK_EXIST_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B020_CHK_EXIST_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_AC = BPCSFABF.KEY.AC;
        WS_STR_CCY = BPCSFABF.KEY.CCY;
        WS_CCY_TYP = BPCSFABF.KEY.CCY_TYPE;
        T000_STARTBR_BPTFABF_EXIST_PROC();
        if (pgmRtn) return;
        B020_READ_NEXTPROC();
        if (pgmRtn) return;
        while (WS_TABLE_REC != 'N') {
            if ((BPCSFABF.KEY.EFF_DATE <= BPRFABF.KEY.EFF_DATE 
                && BPCSFABF.KEY.EXP_DATE >= BPRFABF.EXP_DATE) 
                || (BPCSFABF.KEY.EFF_DATE <= BPRFABF.KEY.EFF_DATE 
                && BPCSFABF.KEY.EXP_DATE >= BPRFABF.KEY.EFF_DATE) 
                || (BPCSFABF.KEY.EFF_DATE >= BPRFABF.KEY.EFF_DATE 
                && BPCSFABF.KEY.EXP_DATE <= BPRFABF.EXP_DATE) 
                || (BPCSFABF.KEY.EFF_DATE >= BPRFABF.KEY.EFF_DATE 
                && BPCSFABF.KEY.EFF_DATE <= BPRFABF.EXP_DATE)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CUST_CONFLICT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B020_READ_NEXTPROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B001_INQ_BPTFABF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFABF);
        BPRFABF.KEY.CHG_AC = BPCSFABF.KEY.AC;
        BPRFABF.KEY.CCY = BPCSFABF.KEY.CCY;
        BPRFABF.KEY.CCY_TYPE = BPCSFABF.KEY.CCY_TYPE;
        BPRFABF.KEY.EFF_DATE = BPCSFABF.KEY.EFF_DATE;
        CEP.TRC(SCCGWA, BPCSFABF.KEY.AC);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.CCY);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRFABF.KEY.CHG_AC);
        CEP.TRC(SCCGWA, BPRFABF.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFABF.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, BPRFABF.KEY.EFF_DATE);
        T000_READ_REC_PROC();
        if (pgmRtn) return;
        R100_TRANS_DATA_CPN();
        if (pgmRtn) return;
        B210_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B003_UPD_BPTFABF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFABF);
        BPRFABF.KEY.CHG_AC = BPCSFABF.KEY.AC;
        BPRFABF.KEY.CCY = BPCSFABF.KEY.CCY;
        BPRFABF.KEY.CCY_TYPE = BPCSFABF.KEY.CCY_TYPE;
        BPRFABF.KEY.EFF_DATE = BPCSFABF.KEY.EFF_DATE;
        CEP.TRC(SCCGWA, BPRFABF.KEY.CHG_AC);
        CEP.TRC(SCCGWA, BPRFABF.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFABF.KEY.EFF_DATE);
        T000_READ_REC_UPD_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R100_TRANS_DATA_UPDATE();
            if (pgmRtn) return;
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        }
    }
    public void B004_DEL_BPTFABF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFABF);
        BPRFABF.KEY.CHG_AC = BPCSFABF.KEY.AC;
        BPRFABF.KEY.CCY = BPCSFABF.KEY.CCY;
        BPRFABF.KEY.CCY_TYPE = BPCSFABF.KEY.CCY_TYPE;
        BPRFABF.KEY.EFF_DATE = BPCSFABF.KEY.EFF_DATE;
        T000_READ_REC_UPD_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        }
    }
    public void B005_BRW_BPTFABF_PROC() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STARTBR_BPTFABF_PROC();
        if (pgmRtn) return;
        B020_READ_NEXTPROC();
        if (pgmRtn) return;
        while (WS_TABLE_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_REL_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B020_READ_NEXTPROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void R100_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFABF);
        BPRFABF.KEY.CHG_AC = BPCSFABF.KEY.AC;
        BPRFABF.KEY.CCY = BPCSFABF.KEY.CCY;
        BPRFABF.KEY.CCY_TYPE = BPCSFABF.KEY.CCY_TYPE;
        CEP.TRC(SCCGWA, BPCSFABF.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, BPRFABF.KEY.CCY_TYPE);
        BPRFABF.KEY.EFF_DATE = BPCSFABF.KEY.EFF_DATE;
        BPRFABF.EXP_DATE = BPCSFABF.KEY.EXP_DATE;
        BPRFABF.CHG_AC1 = BPCSFABF.FEE_AC1;
        BPRFABF.CHG_AC2 = BPCSFABF.FEE_AC2;
        BPRFABF.CHG_AC3 = BPCSFABF.FEE_AC3;
        BPRFABF.CHG_AC4 = BPCSFABF.FEE_AC4;
        BPRFABF.CHG_AC5 = BPCSFABF.FEE_AC5;
        BPRFABF.REMARK = BPCSFABF.REMARK;
        BPRFABF.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFABF.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFABF.CREATE_TIME = SCCGWA.COMM_AREA.TR_TIME;
    }
    public void R100_TRANS_DATA_CPN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPC198);
        BPC198.DATA.AC = BPRFABF.KEY.CHG_AC;
        BPC198.DATA.CCY = BPRFABF.KEY.CCY;
        BPC198.DATA.CCY_TYPE = BPRFABF.KEY.CCY_TYPE;
        BPC198.DATA.EFF_DATE = BPRFABF.KEY.EFF_DATE;
        BPC198.DATA.EXP_DATE = BPRFABF.EXP_DATE;
        BPC198.DATA.FEE_AC1 = BPRFABF.CHG_AC1;
        BPC198.DATA.FEE_AC2 = BPRFABF.CHG_AC2;
        BPC198.DATA.FEE_AC3 = BPRFABF.CHG_AC3;
        BPC198.DATA.FEE_AC4 = BPRFABF.CHG_AC4;
        BPC198.DATA.FEE_AC5 = BPRFABF.CHG_AC5;
        BPC198.DATA.REMARK = BPRFABF.REMARK;
    }
    public void R100_TRANS_DATA_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFABF);
        BPRFABF.KEY.CHG_AC = BPCSFABF.KEY.AC;
        BPRFABF.KEY.CCY = BPCSFABF.KEY.CCY;
        BPRFABF.KEY.CCY_TYPE = BPCSFABF.KEY.CCY_TYPE;
        BPRFABF.KEY.EFF_DATE = BPCSFABF.KEY.EFF_DATE;
        BPRFABF.EXP_DATE = BPCSFABF.KEY.EXP_DATE;
        BPRFABF.CHG_AC1 = BPCSFABF.FEE_AC1;
        BPRFABF.CHG_AC2 = BPCSFABF.FEE_AC2;
        BPRFABF.CHG_AC3 = BPCSFABF.FEE_AC3;
        BPRFABF.CHG_AC4 = BPCSFABF.FEE_AC4;
        BPRFABF.CHG_AC5 = BPCSFABF.FEE_AC5;
        BPRFABF.REMARK = BPCSFABF.REMARK;
        BPRFABF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFABF.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        BPRFABF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_RTN_DATA_A);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 465;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        WS_REC = " ";
        WS_OUTPUT_FLG = 'N';
        R300_GET_AC_NAME();
        if (pgmRtn) return;
        WS_RTN_DATA_A.WS_CHG_AC = BPRFABF.KEY.CHG_AC;
        CEP.TRC(SCCGWA, BPRFABF.KEY.CHG_AC);
        WS_RTN_DATA_A.WS_CHG_CCY = BPRFABF.KEY.CCY;
        WS_RTN_DATA_A.WS_CCY_TYPE = BPRFABF.KEY.CCY_TYPE;
        WS_RTN_DATA_A.WS_CHG_AC1 = BPRFABF.CHG_AC1;
        WS_RTN_DATA_A.WS_CHG_AC2 = BPRFABF.CHG_AC2;
        WS_RTN_DATA_A.WS_CHG_AC3 = BPRFABF.CHG_AC3;
        WS_RTN_DATA_A.WS_CHG_AC4 = BPRFABF.CHG_AC4;
        WS_RTN_DATA_A.WS_CHG_AC5 = BPRFABF.CHG_AC5;
        WS_RTN_DATA_A.WS_EFF_DATE = BPRFABF.KEY.EFF_DATE;
        WS_RTN_DATA_A.WS_EXP_DATE = BPRFABF.EXP_DATE;
        if (BPCSFABF.RLT_AC.trim().length() > 0) {
            if (BPCSFABF.RLT_AC.equalsIgnoreCase(WS_RTN_DATA_A.WS_CHG_AC1) 
                || BPCSFABF.RLT_AC.equalsIgnoreCase(WS_RTN_DATA_A.WS_CHG_AC2) 
                || BPCSFABF.RLT_AC.equalsIgnoreCase(WS_RTN_DATA_A.WS_CHG_AC3) 
                || BPCSFABF.RLT_AC.equalsIgnoreCase(WS_RTN_DATA_A.WS_CHG_AC4) 
                || BPCSFABF.RLT_AC.equalsIgnoreCase(WS_RTN_DATA_A.WS_CHG_AC5)) {
                WS_OUTPUT_FLG = 'Y';
            }
        } else {
            WS_OUTPUT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_FLG);
        if (WS_OUTPUT_FLG == 'Y') {
            WS_REC = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
            WS_REC_LEN = 465;
            S000_WRITE_TS();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_BPTFABF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFABF);
        if (BPCSFABF.KEY.AC.trim().length() > 0) {
            WS_STR_AC = BPCSFABF.KEY.AC;
            WS_END_AC = BPCSFABF.KEY.AC;
            CEP.TRC(SCCGWA, WS_STR_AC);
            CEP.TRC(SCCGWA, WS_END_AC);
        } else {
            WS_STR_AC = "" + 0X00;
            JIBS_tmp_int = WS_STR_AC.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_STR_AC = "0" + WS_STR_AC;
            WS_END_AC = "" + 0XFF;
            JIBS_tmp_int = WS_END_AC.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_END_AC = "0" + WS_END_AC;
        }
        if (BPCSFABF.KEY.EFF_DATE != 0 
            || BPCSFABF.KEY.EFF_DATE != 0) {
            WS_STR_DT = BPCSFABF.KEY.EFF_DATE;
        } else {
            if (0X00 == ' ') WS_STR_DT = 0;
            else WS_STR_DT = Integer.parseInt(""+0X00);
        }
        if (BPCSFABF.KEY.EXP_DATE != 0 
            || BPCSFABF.KEY.EXP_DATE != 0) {
            WS_END_DT = BPCSFABF.KEY.EXP_DATE;
        } else {
            if (0XFF == ' ') WS_END_DT = 0;
            else WS_END_DT = Integer.parseInt(""+0XFF);
        }
        T000_STARTBR_BPTFABF_PROC();
        if (pgmRtn) return;
    }
    public void B020_READ_NEXTPROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            WS_TABLE_REC = 'Y';
        }
    }
    public void B210_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPC198;
        SCCFMT.DATA_LEN = 332;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R300_GET_AC_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = BPRFABF.KEY.CHG_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        if (CICACCU.RC.RC_CODE == 0) {
            WS_RTN_DATA_A.WS_AC_NAME = CICACCU.DATA.AC_CNM;
        } else {
            WS_RTN_DATA_A.WS_AC_NAME = " ";
        }
    }
    public void B008_WRITE_BPTPNHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE-BPTPNHIS");
        if (BPCSFABF.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSFABF.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        if (BPCSFABF.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPCSFABF";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LINK-BPZPNHIS-OK");
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSFABF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BPTFABF_RD = new DBParm();
        BPTFABF_RD.TableName = "BPTFABF";
        IBS.WRITE(SCCGWA, BPRFABF, BPTFABF_RD);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFABF, this, BPTFABF_BR);
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFABF_BR);
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_REC;
        CEP.TRC(SCCGWA, WS_REC);
        SCCMPAG.DATA_LEN = WS_REC_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTFABF_PROC() throws IOException,SQLException,Exception {
        BPTFABF_BR.rp = new DBParm();
        BPTFABF_BR.rp.TableName = "BPTFABF";
        BPTFABF_BR.rp.where = "( CHG_AC BETWEEN :WS_STR_AC "
            + "AND :WS_END_AC ) "
            + "AND ( EFF_DATE >= :WS_STR_DT "
            + "AND EFF_DATE <= :WS_END_DT )";
        IBS.STARTBR(SCCGWA, BPRFABF, this, BPTFABF_BR);
    }
    public void T000_STARTBR_BPTFABF_EXIST_PROC() throws IOException,SQLException,Exception {
        BPTFABF_BR.rp = new DBParm();
        BPTFABF_BR.rp.TableName = "BPTFABF";
        BPTFABF_BR.rp.where = "CHG_AC = :WS_STR_AC "
            + "AND CCY = :WS_STR_CCY "
            + "AND CCY_TYPE = :WS_CCY_TYP";
        IBS.STARTBR(SCCGWA, BPRFABF, this, BPTFABF_BR);
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BPTFABF_RD = new DBParm();
        BPTFABF_RD.TableName = "BPTFABF";
        IBS.READ(SCCGWA, BPRFABF, BPTFABF_RD);
    }
    public void T000_READ_REC_UPD_PROC() throws IOException,SQLException,Exception {
        BPTFABF_RD = new DBParm();
        BPTFABF_RD.TableName = "BPTFABF";
        BPTFABF_RD.upd = true;
        IBS.READ(SCCGWA, BPRFABF, BPTFABF_RD);
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        BPTFABF_RD = new DBParm();
        BPTFABF_RD.TableName = "BPTFABF";
        IBS.REWRITE(SCCGWA, BPRFABF, BPTFABF_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        BPTFABF_RD = new DBParm();
        BPTFABF_RD.TableName = "BPTFABF";
        IBS.DELETE(SCCGWA, BPRFABF, BPTFABF_RD);
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
