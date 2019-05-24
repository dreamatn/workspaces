package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1338 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_INT = 0;
    long WS_SEQ_NO_TMP = 0;
    int WS_CODE_INT = 0;
    BPOT1338_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT1338_WS_OUTPUT_DATA();
    char WS_CTL_LEN = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSHSEQ BPCSHSEQ = new BPCSHSEQ();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1338 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B040_ADD_RSVD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
        if (BPB1310_AWA_1310.CODE.substring(3 - 1, 3 + 1 - 1).trim().length() > 0) {
            if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
            JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
            if (!IBS.isNumeric(BPB1310_AWA_1310.CODE.substring(0, 3))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CODE_MUST_NUMERIC;
                WS_FLD_NO = BPB1310_AWA_1310.NUM_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
            JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
            if (!IBS.isNumeric(BPB1310_AWA_1310.CODE.substring(0, 2))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CODE_MUST_NUMERIC;
                WS_FLD_NO = BPB1310_AWA_1310.NUM_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        WS_CTL_LEN = '3';
        for (WS_INT = 1; WS_INT <= 20; WS_INT += 1) {
            if (!BPB1310_AWA_1310.ITMS[WS_INT-1].ACNO.equalsIgnoreCase("0") 
                && BPB1310_AWA_1310.ITMS[WS_INT-1].OBL_FLG == 'Y') {
                IBS.init(SCCGWA, BPCSHSEQ);
                BPCSHSEQ.FUNC_CODE = 'A';
                BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
                if (BPB1310_AWA_1310.ITMS[WS_INT-1].ACNO.trim().length() == 0) WS_SEQ_NO_TMP = 0;
                else WS_SEQ_NO_TMP = Long.parseLong(BPB1310_AWA_1310.ITMS[WS_INT-1].ACNO);
                if (WS_CTL_LEN == '3') {
                    if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
                    JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
                    BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE.substring(0, 3);
                    JIBS_tmp_str[0] = "" + WS_SEQ_NO_TMP;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1).trim().length() == 0) BPCSHSEQ.SEQ_NO = 0;
                    else BPCSHSEQ.SEQ_NO = Long.parseLong(JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1));
                } else {
                    if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
                    JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
                    BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE.substring(0, 2);
                    JIBS_tmp_str[0] = "" + WS_SEQ_NO_TMP;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(6 - 1, 6 + 10 - 1).trim().length() == 0) BPCSHSEQ.SEQ_NO = 0;
                    else BPCSHSEQ.SEQ_NO = Long.parseLong(JIBS_tmp_str[0].substring(6 - 1, 6 + 10 - 1));
                }
                BPCSHSEQ.AC_NO = BPB1310_AWA_1310.ITMS[WS_INT-1].ACNO;
                BPCSHSEQ.USED_FLAG = 'N';
                BPCSHSEQ.AC_OFFICER = BPB1310_AWA_1310.AC_OFC;
                BPCSHSEQ.REMARK = BPB1310_AWA_1310.REMARK;
                CEP.TRC(SCCGWA, "----------");
                CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
                CEP.TRC(SCCGWA, BPCSHSEQ.CODE);
                CEP.TRC(SCCGWA, BPCSHSEQ.SEQ_NO);
                CEP.TRC(SCCGWA, BPCSHSEQ.AC_NO);
                CEP.TRC(SCCGWA, BPCSHSEQ.USED_FLAG);
                CEP.TRC(SCCGWA, BPCSHSEQ.AC_OFFICER);
                CEP.TRC(SCCGWA, BPCSHSEQ.REMARK);
                S00_CALL_BPZSHSEQ();
            }
        }
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSMPTY.FUNC = 'Q';
        BPCSMPTY.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPTY();
        WS_OUTPUT_DATA.WS_ACNO_TYPE = BPCSMPTY.TYPE;
        WS_OUTPUT_DATA.WS_ACNO_TYPE_DESC = BPCSMPTY.INFO.NAME;
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSMPCD.CODE = BPB1310_AWA_1310.CODE;
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPCD();
        WS_OUTPUT_DATA.WS_ACNO_CODE = BPCSMPCD.CODE;
        WS_OUTPUT_DATA.WS_ACNO_CODE_DESC = BPCSMPCD.INFO.CODE_NAME;
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPB1310_AWA_1310.TYPE;
        BPCUQSEQ.SEQ_CODE = BPB1310_AWA_1310.CODE;
        S000_CALL_BPZUQSEQ();
        WS_OUTPUT_DATA.WS_ACNO_SEQ_DESC = BPCUQSEQ.SEQ_DESC;
        WS_OUTPUT_DATA.WS_ACNO_AC_OFC = BPB1310_AWA_1310.AC_OFC;
        WS_OUTPUT_DATA.WS_ACNO_REMARK = BPB1310_AWA_1310.REMARK;
        for (WS_INT = 1; WS_INT <= 20; WS_INT += 1) {
            WS_OUTPUT_DATA.WS_PRE_HOLD_DAT[WS_INT-1].WS_ACNO = BPB1310_AWA_1310.ITMS[WS_INT-1].ACNO;
            WS_OUTPUT_DATA.WS_PRE_HOLD_DAT[WS_INT-1].WS_ACNO_HLD_FLG = "" + BPB1310_AWA_1310.ITMS[WS_INT-1].OBL_FLG;
            JIBS_tmp_int = WS_OUTPUT_DATA.WS_PRE_HOLD_DAT[WS_INT-1].WS_ACNO_HLD_FLG.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_OUTPUT_DATA.WS_PRE_HOLD_DAT[WS_INT-1].WS_ACNO_HLD_FLG = "0" + WS_OUTPUT_DATA.WS_PRE_HOLD_DAT[WS_INT-1].WS_ACNO_HLD_FLG;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP568";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 1243;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_ADD_RSVD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        if (BPB1310_AWA_1310.ITMS[1-1].ACNO == null) BPB1310_AWA_1310.ITMS[1-1].ACNO = "";
        JIBS_tmp_int = BPB1310_AWA_1310.ITMS[1-1].ACNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPB1310_AWA_1310.ITMS[1-1].ACNO += " ";
        BPCSHSEQ.CI_NO = BPB1310_AWA_1310.ITMS[1-1].ACNO.substring(0, 8);
        BPCSHSEQ.AC_NO = BPB1310_AWA_1310.ITMS[1-1].ACNO;
        BPCSHSEQ.USED_FLAG = 'N';
        BPCSHSEQ.FUNC_CODE = 'A';
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYPE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.SEQ_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.ITMS[1-1].ACNO);
        CEP.TRC(SCCGWA, BPCSHSEQ.USED_FLAG);
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.AC_OFC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.REMARK);
        S00_CALL_BPZSHSEQ();
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAINT-PARM-TYPE", BPCSMPTY);
    }
    public void S000_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAINT-PARM-CODE", BPCSMPCD);
    }
    public void S000_CALL_BPZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-SEQ", BPCUQSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-HSEQ-MAINT", BPCSHSEQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
