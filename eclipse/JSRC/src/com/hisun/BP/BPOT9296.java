package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9296 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    BPOT9296_WS_MSGID WS_MSGID = new BPOT9296_WS_MSGID();
    short WS_FLD_NO = 0;
    String WS_TYPE = " ";
    String WS_CODE = " ";
    int WS_CI_NO = 0;
    short WS_NUM2 = 0;
    String WS_CI_NM = " ";
    String WS_AC_OFC = " ";
    int WS_ACCT = 0;
    String WS_REMARK = " ";
    short WS_I = 0;
    BPOT9296_WS_OUT_DATA WS_OUT_DATA = new BPOT9296_WS_OUT_DATA();
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
        CEP.TRC(SCCGWA, "BPOT9296 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BATH_RESERVE_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IIIIIIII");
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYPE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.NUM2);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NO2);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NAME);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.AC_OFC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.ACCT);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.REMARK);
        if (BPB1310_AWA_1310.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_NO_MUST_INPUT, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (!IBS.isNumeric(BPB1310_AWA_1310.CI_NO)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CINO_MST_NUMERIC, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB1310_AWA_1310.NUM2 > 100) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RSV_NUM_LAR_100, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BATH_RESERVE_PROC() throws IOException,SQLException,Exception {
        WS_TYPE = BPB1310_AWA_1310.TYPE;
        WS_CODE = BPB1310_AWA_1310.CODE;
        if (BPB1310_AWA_1310.CI_NO.trim().length() == 0) WS_CI_NO = 0;
        else WS_CI_NO = Integer.parseInt(BPB1310_AWA_1310.CI_NO);
        WS_CI_NM = BPB1310_AWA_1310.CI_NAME;
        WS_NUM2 = BPB1310_AWA_1310.NUM2;
        WS_AC_OFC = BPB1310_AWA_1310.AC_OFC;
        WS_ACCT = BPB1310_AWA_1310.ACCT;
        WS_REMARK = BPB1310_AWA_1310.REMARK;
        for (WS_I = 1; WS_I <= WS_NUM2; WS_I += 1) {
            IBS.init(SCCGWA, BPCSHSEQ);
            BPCSHSEQ.FUNC_CODE = 'A';
            BPCSHSEQ.TYPE = WS_TYPE;
            BPCSHSEQ.CODE = WS_CODE;
            JIBS_tmp_str[0] = "" + WS_CI_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(4-1).trim().length() == 0) BPCSHSEQ.SEQ_NO = 0;
            else BPCSHSEQ.SEQ_NO = Long.parseLong(JIBS_tmp_str[0].substring(4-1));
            BPCSHSEQ.CI_NO = "" + WS_CI_NO;
            JIBS_tmp_int = BPCSHSEQ.CI_NO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) BPCSHSEQ.CI_NO = "0" + BPCSHSEQ.CI_NO;
            BPCSHSEQ.CI_NAME = WS_CI_NM;
            BPCSHSEQ.USED_FLAG = 'N';
            BPCSHSEQ.AC_OFFICER = WS_AC_OFC;
            BPCSHSEQ.AC_ACCT = WS_ACCT;
            BPCSHSEQ.REMARK = WS_REMARK;
            S00_CALL_BPZSHSEQ();
            WS_OUT_DATA.WS_OUT_CI_NO[WS_I-1] = "" + WS_CI_NO;
            JIBS_tmp_int = WS_OUT_DATA.WS_OUT_CI_NO[WS_I-1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_OUT_DATA.WS_OUT_CI_NO[WS_I-1] = "0" + WS_OUT_DATA.WS_OUT_CI_NO[WS_I-1];
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CI_NO[WS_I-1]);
            WS_CI_NO += 1;
        }
        CEP.TRC(SCCGWA, WS_I);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPB1310_AWA_1310.TYPE;
        BPCUQSEQ.SEQ_CODE = BPB1310_AWA_1310.CODE;
        S000_CALL_BPZUQSEQ();
        WS_OUT_DATA.WS_OUT_CODE = BPB1310_AWA_1310.CODE;
        WS_OUT_DATA.WS_OUT_SEQ_DESC = BPCUQSEQ.SEQ_DESC;
        WS_OUT_DATA.WS_OUT_FST_CI = BPB1310_AWA_1310.CI_NO;
        WS_OUT_DATA.WS_OUT_RES_NUM = BPB1310_AWA_1310.NUM2;
        WS_OUT_DATA.WS_OUT_LST_CI = BPB1310_AWA_1310.CI_NO2;
        WS_OUT_DATA.WS_OUT_CI_NM = BPB1310_AWA_1310.CI_NAME;
        WS_OUT_DATA.WS_OUT_AC_OFC = BPB1310_AWA_1310.AC_OFC;
        WS_OUT_DATA.WS_OUT_ACCT = BPB1310_AWA_1310.ACCT;
        WS_OUT_DATA.WS_OUT_REMARK = BPB1310_AWA_1310.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP574";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1692;
        IBS.FMT(SCCGWA, SCCFMT);
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
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-HSEQ-MAINT", BPCSHSEQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
