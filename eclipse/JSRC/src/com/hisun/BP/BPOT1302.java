package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1302 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_SC_CI_DIGIT = "SC-CI-DIGIT";
    short WS_J = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_BCH1 = " ";
    String WS_BCH2 = " ";
    BPOT1302_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT1302_WS_OUTPUT_DATA();
    String WS_CI_NO = " ";
    BPOT1302_REDEFINES18 REDEFINES18 = new BPOT1302_REDEFINES18();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSHSEQ BPCSHSEQ = new BPCSHSEQ();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    SCCKDGCI SCCKDGCI = new SCCKDGCI();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1302 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCKDGCI);
        WS_J = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_CI_DIGIT_PROCESS();
        B030_ADD_REC_PROC();
        B040_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYPE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.SEQ_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NAME);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.AC_OFC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.ACCT);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.REMARK);
    }
    public void B020_GET_CI_DIGIT_PROCESS() throws IOException,SQLException,Exception {
        if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
        if (BPB1310_AWA_1310.CODE.substring(0, 2).trim().length() == 0) REDEFINES18.WS_CI_SEG1.WS_CI_TYPE = 0;
        else REDEFINES18.WS_CI_SEG1.WS_CI_TYPE = Short.parseShort(BPB1310_AWA_1310.CODE.substring(0, 2));
        WS_CI_NO = IBS.CLS2CPY(SCCGWA, REDEFINES18);
        REDEFINES18.WS_CI_SEG1.WS_CI_SEQ = (int) BPB1310_AWA_1310.SEQ_NO;
        WS_CI_NO = IBS.CLS2CPY(SCCGWA, REDEFINES18);
        IBS.init(SCCGWA, SCCKDGCI);
        CEP.TRC(SCCGWA, REDEFINES18.WS_CI_SEG1.WS_CI_TYPE);
        CEP.TRC(SCCGWA, REDEFINES18.WS_CI_SEG1.WS_CI_SEQ);
        SCCKDGCI.NO = WS_CI_NO;
        SCCKDGCI.FUNC = 'G';
        CEP.TRC(SCCGWA, SCCKDGCI.FUNC);
        CEP.TRC(SCCGWA, SCCKDGCI.NO);
        CEP.TRC(SCCGWA, SCCKDGCI);
        S020_CALL_SCZKDGCI();
        CEP.TRC(SCCGWA, SCCKDGCI.DIG);
        CEP.TRC(SCCGWA, SCCKDGCI.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCKDGCI.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(SCCCTLM_MSG.SC_ERR_GEN_DIG) 
            && WS_J < 100) {
            B020_GET_CI_DIGIT_PROCESS();
            WS_J += WS_J;
        }
        if (SCCKDGCI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCKDGCI.RC);
            S000_ERR_MSG_PROC();
        } else {
            REDEFINES18.WS_CI_DIG = SCCKDGCI.DIG;
            WS_CI_NO = IBS.CLS2CPY(SCCGWA, REDEFINES18);
        }
    }
    public void B030_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.FUNC_CODE = 'A';
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        BPCSHSEQ.CI_NO = WS_CI_NO;
        BPCSHSEQ.USED_FLAG = 'N';
        BPCSHSEQ.CI_NAME = BPB1310_AWA_1310.CI_NAME;
        BPCSHSEQ.AC_OFFICER = BPB1310_AWA_1310.AC_OFC;
        BPCSHSEQ.AC_ACCT = BPB1310_AWA_1310.ACCT;
        BPCSHSEQ.REMARK = BPB1310_AWA_1310.REMARK;
        BPCSHSEQ.CI_TYPE = BPB1310_AWA_1310.CI_TYPE;
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_NO);
        S00_CALL_BPZSHSEQ();
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSMPTY.FUNC = 'Q';
        BPCSMPTY.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPTY();
        WS_OUTPUT_DATA.WS_CINO_TYPE = BPCSMPTY.TYPE;
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSMPCD.CODE = BPB1310_AWA_1310.CODE;
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPCD();
        WS_OUTPUT_DATA.WS_CINO_CODE = BPCSMPCD.CODE;
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPB1310_AWA_1310.TYPE;
        BPCUQSEQ.SEQ_CODE = BPB1310_AWA_1310.CODE;
        S000_CALL_BPZUQSEQ();
        WS_OUTPUT_DATA.WS_CINO_SEQ_DESC = BPCUQSEQ.SEQ_DESC;
        WS_OUTPUT_DATA.WS_CINO_SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        WS_OUTPUT_DATA.WS_CINO_CINO = WS_CI_NO;
        WS_OUTPUT_DATA.WS_CINO_CI_NM = BPB1310_AWA_1310.CI_NAME;
        WS_OUTPUT_DATA.WS_CINO_AC_OFC = BPB1310_AWA_1310.AC_OFC;
        WS_OUTPUT_DATA.WS_CINO_AC_ACCT = BPB1310_AWA_1310.ACCT;
        WS_OUTPUT_DATA.WS_CINO_REMARK = BPB1310_AWA_1310.REMARK;
        if (BPB1310_AWA_1310.CI_TYPE == 'P') {
            WS_OUTPUT_DATA.WS_CINO_CI_TYPE = "91";
        } else {
            WS_OUTPUT_DATA.WS_CINO_CI_TYPE = "96";
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_CODE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_SEQ_DESC);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_SEQ_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_CINO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_CI_NM);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_AC_OFC);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_AC_ACCT);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CINO_REMARK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP571";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 499;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S020_CALL_SCZKDGCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SC_CI_DIGIT, SCCKDGCI);
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
