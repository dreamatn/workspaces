package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSARQC {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    String K_PGM_NAME = "DCZSARQC";
    String CPN_PARM_MT = "DC-CHECK-ARQC";
    String K_HIS_REMARK = "ARQC CHECK";
    String K_HIS_COPYBOOK = "DCCSARQC";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT = "DC334";
    String K_PBOC_DATA = "PBOC.OLDIC-DES-AC-001.mk-ac";
    String WS_ERR_MSG = " ";
    char WS_MOVE_FLG = ' ';
    DCZSARQC_WS_TEMP WS_TEMP = new DCZSARQC_WS_TEMP();
    DCZSARQC_WS_OUTPUT WS_OUTPUT = new DCZSARQC_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCCALL SCCCALL = new SCCCALL();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCCF334 DCCF334 = new DCCF334();
    SCCGWA SCCGWA;
    DCCSARQC DCCSARQC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSARQC DCCSARQC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSARQC = DCCSARQC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        C000_ARQC_PROCESS();
        D000_OUTPUT_PROCESS();
        CEP.TRC(SCCGWA, "DCZSARQC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSARQC.CARD_NO);
        CEP.TRC(SCCGWA, DCCSARQC.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARQC);
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARQC_DATA);
        CEP.TRC(SCCGWA, DCCSARQC.ISSUE_DATA);
        CEP.TRC(SCCGWA, DCCSARQC.VERIFY_RLT);
        if (DCCSARQC.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCSARQC.CARD_SEQ == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_SEQ);
        }
        if (DCCSARQC.CARD_ARQC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ARQC);
        }
        if (DCCSARQC.CARD_ARQC_DATA.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ARQC_DATA);
        }
        if (DCCSARQC.ISSUE_DATA.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ISSU_DATA);
        }
        if (DCCSARQC.VERIFY_RLT.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_VERF_RLT);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSARQC.CARD_NO;
        T000_RAED_DCTCDDAT();
        WS_MOVE_FLG = DCRCDDAT.MOVE_FLG;
        CEP.TRC(SCCGWA, WS_MOVE_FLG);
    }
    public void C000_ARQC_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = 'C';
        if (DCCSARQC.CARD_NO == null) DCCSARQC.CARD_NO = "";
        JIBS_tmp_int = DCCSARQC.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSARQC.CARD_NO += " ";
        if (DCCSARQC.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSARQC.CARD_NO == null) DCCSARQC.CARD_NO = "";
            JIBS_tmp_int = DCCSARQC.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSARQC.CARD_NO += " ";
            SCCTSSC.COMM_AREA_C.C_DESIGNID = DCCSARQC.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        } else {
            if (DCCSARQC.CARD_NO == null) DCCSARQC.CARD_NO = "";
            JIBS_tmp_int = DCCSARQC.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSARQC.CARD_NO += " ";
            SCCTSSC.COMM_AREA_C.C_DESIGNID = DCCSARQC.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        if (DCCSARQC.CARD_NO == null) DCCSARQC.CARD_NO = "";
        JIBS_tmp_int = DCCSARQC.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSARQC.CARD_NO += " ";
        if (DCCSARQC.CARD_NO.substring(0, 6).equalsIgnoreCase("623265")) {
            if (DCCSARQC.CARD_NO == null) DCCSARQC.CARD_NO = "";
            JIBS_tmp_int = DCCSARQC.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSARQC.CARD_NO += " ";
            SCCTSSC.COMM_AREA_C.C_DESIGNID = DCCSARQC.CARD_NO.substring(0, 8);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        SCCTSSC.COMM_AREA_C.C_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        if (DCCSARQC.ISSUE_DATA == null) DCCSARQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSARQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSARQC.ISSUE_DATA += " ";
        if (DCCSARQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_C.C_KEYMODEL_ID = "MDK-Ac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        if (DCCSARQC.ISSUE_DATA == null) DCCSARQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSARQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSARQC.ISSUE_DATA += " ";
        if (DCCSARQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_C.C_KEYMODEL_ID = "RMDKAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        if (WS_MOVE_FLG == 'Y') {
            SCCTSSC.COMM_AREA_C.C_KEYMODEL_ID = K_PBOC_DATA;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        SCCTSSC.COMM_AREA_C.C_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        if (DCCSARQC.CARD_NO == null) DCCSARQC.CARD_NO = "";
        JIBS_tmp_int = DCCSARQC.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSARQC.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSARQC.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSARQC.CARD_SEQ;
        SCCTSSC.COMM_AREA_C.C_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        if (DCCSARQC.CARD_ARQC_DATA == null) DCCSARQC.CARD_ARQC_DATA = "";
        JIBS_tmp_int = DCCSARQC.CARD_ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSARQC.CARD_ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_C.C_SKGENE = DCCSARQC.CARD_ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCCTSSC.COMM_AREA_C.C_DATATO_ARQC = DCCSARQC.CARD_ARQC_DATA;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCCTSSC.COMM_AREA_C.C_ARQC = DCCSARQC.CARD_ARQC;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCCTSSC.COMM_AREA_C.C_ARC = "00";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_C.C_O_ARPC);
    }
    public void D000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF334);
        if (DCCSARQC.ISSUE_DATA == null) DCCSARQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSARQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSARQC.ISSUE_DATA += " ";
        if (DCCSARQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            WS_OUTPUT.WS_ARPC = SCCTSSC.COMM_AREA_C.C_O_ARPC;
        }
        if (DCCSARQC.ISSUE_DATA == null) DCCSARQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSARQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSARQC.ISSUE_DATA += " ";
        if (DCCSARQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            if (SCCTSSC.COMM_AREA_C.C_O_ARPC == null) SCCTSSC.COMM_AREA_C.C_O_ARPC = "";
            JIBS_tmp_int = SCCTSSC.COMM_AREA_C.C_O_ARPC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_C.C_O_ARPC += " ";
            WS_OUTPUT.WS_ARPC = SCCTSSC.COMM_AREA_C.C_O_ARPC.substring(0, 16);
        }
        WS_OUTPUT.WS_AUT_RC = "3030";
        DCCF334.ARPC = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARPC);
        DCCSARQC.CARD_ARPC = WS_OUTPUT.WS_ARPC;
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARPC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF334;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_RAED_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
