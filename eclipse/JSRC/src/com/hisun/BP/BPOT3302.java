package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3302 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_TXH_INQ = "BP-S-INQ-TX-HIS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_CMP_INQ_JRN = "SC-JOURNAL-MAINTAIN";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSQTXH BPCSQTXH = new BPCSQTXH();
    SCCPRMR SCCPRMR = new SCCPRMR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCPJRN SCCPJRN = new SCCPJRN();
    SCRJRN SCRJRN = new SCRJRN();
    BPRTRT BPRTRT = new BPRTRT();
    BPCPCSDT BPCPCSDT = new BPCPCSDT();
    SCCGWA SCCGWA;
    BPB3300_AWA_3300 BPB3300_AWA_3300;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3302 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3300_AWA_3300>");
        BPB3300_AWA_3300 = (BPB3300_AWA_3300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            B020_MAIN_PROCESS();
        } else {
            B010_CHECK_INPUT();
            B020_MAIN_PROCESS();
        }
    }
    public void B011_CHECK_CAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPJRN);
        IBS.init(SCCGWA, SCRJRN);
        SCRJRN.AC_DATE = BPB3300_AWA_3300.AC_DT;
        SCRJRN.KEY.JRN_NO = BPB3300_AWA_3300.JNO;
        SCCPJRN.FUNC = '1';
        CEP.TRC(SCCGWA, SCRJRN.AC_DATE);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        S000_CALL_SCZPJRN();
        CEP.TRC(SCCGWA, SCRJRN.CANCEL_IND);
        if (SCRJRN.CANCEL_IND == 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ALREADY_CANCEL);
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B011_CHECK_CAN_PROC();
        B013_CHECK_CAN_CODE();
        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
    }
    public void B013_CHECK_CAN_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRT);
        CEP.TRC(SCCGWA, SCRJRN.TR_AP);
        CEP.TRC(SCCGWA, SCRJRN.TR_CODE);
        if (BPRTRT.KEY.CD == null) BPRTRT.KEY.CD = "";
        JIBS_tmp_int = BPRTRT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRT.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + SCRJRN.TR_AP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<3-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRTRT.KEY.CD = JIBS_tmp_str[0] + BPRTRT.KEY.CD.substring(3);
        if (BPRTRT.KEY.CD == null) BPRTRT.KEY.CD = "";
        JIBS_tmp_int = BPRTRT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRT.KEY.CD += " ";
        if (BPRTRT.KEY.CD.substring(0, 1).trim().length() == 0) {
            if (BPRTRT.KEY.CD == null) BPRTRT.KEY.CD = "";
            JIBS_tmp_int = BPRTRT.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRT.KEY.CD += " ";
            BPRTRT.KEY.CD = "0" + BPRTRT.KEY.CD.substring(1);
        }
        if (BPRTRT.KEY.CD == null) BPRTRT.KEY.CD = "";
        JIBS_tmp_int = BPRTRT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRT.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + SCRJRN.TR_CODE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRTRT.KEY.CD = BPRTRT.KEY.CD.substring(0, 4 - 1) + JIBS_tmp_str[0] + BPRTRT.KEY.CD.substring(4 + 4 - 1);
        if (BPRTRT.KEY.CD == null) BPRTRT.KEY.CD = "";
        JIBS_tmp_int = BPRTRT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRT.KEY.CD += " ";
        BPRTRT.KEY.CD = BPRTRT.KEY.CD.substring(0, 7 - 1) + "9" + BPRTRT.KEY.CD.substring(7 + 1 - 1);
        CEP.TRC(SCCGWA, BPRTRT.KEY.CD);
        BPRTRT.KEY.TYP = "TRT";
        CEP.TRC(SCCGWA, BPRTRT.KEY.TYP);
        IBS.init(SCCGWA, SCCPRMR);
        SCCPRMR.DAT_PTR = BPRTRT;
        SCCPRMR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CALLCPN(SCCGWA, "SC-PARM-READ", SCCPRMR);
        CEP.TRC(SCCGWA, BPRTRT);
        CEP.TRC(SCCGWA, SCCPRMR.RC);
        if ((SCCPRMR.RC.RC_RTNCODE != ' ' 
            && SCCPRMR.RC.RC_RTNCODE != 0)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOCANCEL_TXCD);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.AC_DT);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.JNO);
        if (BPB3300_AWA_3300.AC_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB3300_AWA_3300.AC_DT;
            CEP.TRC(SCCGWA, BPB3300_AWA_3300.AC_DT);
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                CEP.ERRC(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_DT_ERR, BPB3300_AWA_3300.AC_DT_NO);
            }
        } else {
            BPB3300_AWA_3300.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B020_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.AC_DT);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.JNO);
        IBS.init(SCCGWA, BPCPCSDT);
        BPCPCSDT.INPUT.STOR_FRY = 'M';
        BPCPCSDT.INPUT.STOR_CYC = 3;
        BPCPCSDT.INPUT.SPLIT_FLAG = 'Y';
        BPCPCSDT.INPUT.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_BPZPCSDT();
        if (BPB3300_AWA_3300.AC_DT < BPCPCSDT.OUTPUT.STOR_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.M_CAN_DT_BEFORE_3_MON);
        }
        IBS.init(SCCGWA, BPCSQTXH);
        BPCSQTXH.DATA.AC_DT = BPB3300_AWA_3300.AC_DT;
        BPCSQTXH.DATA.JNO = BPB3300_AWA_3300.JNO;
        BPCSQTXH.FUNC = 'Q';
        BPCSQTXH.OUTPUT_FLG = 'N';
        S000_CALL_BPZSQTXH();
    }
    public void S000_CALL_SCZPJRN() throws IOException,SQLException,Exception {
        SCCPJRN.DATA_PTR = SCRJRN;
        SCCPJRN.DATA_LEN = 687;
        CEP.TRC(SCCGWA, "CALL SCZPJRN");
        IBS.CALLCPN(SCCGWA, K_CMP_INQ_JRN, SCCPJRN);
        CEP.TRC(SCCGWA, SCCPJRN.RC);
        if (SCCPJRN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPJRN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSQTXH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXH_INQ, BPCSQTXH);
    }
    public void S000_CALL_BPZPCSDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.STOR_FRY);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.STOR_CYC);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.SPLIT_FLAG);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.AC_DATE);
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-STOR-DATE", BPCPCSDT);
        CEP.TRC(SCCGWA, "CSDT-RC-MMO   :");
        CEP.TRC(SCCGWA, BPCPCSDT.RC.RC_MMO);
        CEP.TRC(SCCGWA, "CSDT-RC-CODE  :");
        CEP.TRC(SCCGWA, BPCPCSDT.RC.RC_CODE);
        CEP.TRC(SCCGWA, "CSDT-STOR-DATE:");
        CEP.TRC(SCCGWA, BPCPCSDT.OUTPUT.STOR_DATE);
        if (BPCPCSDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCSDT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
