package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4281 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP353";
    String CPN_BPTMLCJN_MAINTAIN = "BP-F-S-MANT-MLCJN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMLCJ BPCSMLCJ = new BPCSMLCJ();
    BPRMLCJN BPRMLCJN = new BPRMLCJN();
    SCCGWA SCCGWA;
    BPB4280_AWA_4280 BPB4280_AWA_4280;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4281 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4280_AWA_4280>");
        BPB4280_AWA_4280 = (BPB4280_AWA_4280) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSMLCJ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CREATE_MLCJ_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CREATE_MLCJ_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMLCJ);
        IBS.init(SCCGWA, BPRMLCJN);
        BPCSMLCJ.INFO.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4280_AWA_4280.NET_NM);
        BPCSMLCJ.INFO.TR_DATE_BEGIN = BPB4280_AWA_4280.TRDT_B;
        BPCSMLCJ.INFO.TR_DATE_END = BPB4280_AWA_4280.TRDT_E;
        if (BPB4280_AWA_4280.NET_NM == 0) {
            CEP.TRC(SCCGWA, "TEST");
            BPCSMLCJ.INFO.NET_NM_MIN = 0;
            BPCSMLCJ.INFO.NET_NM_MAX = 999999;
        } else {
            BPCSMLCJ.INFO.NET_NM = BPB4280_AWA_4280.NET_NM;
        }
        if (BPB4280_AWA_4280.CI_NO.trim().length() == 0) {
            BPCSMLCJ.INFO.CI_NO = "%%%%%%%%%%%%";
        } else {
            BPCSMLCJ.INFO.CI_NO = BPB4280_AWA_4280.CI_NO;
        }
        if (BPB4280_AWA_4280.TL_ID.trim().length() == 0) {
            BPCSMLCJ.INFO.TX_TLR = "%%%%%%%%";
        } else {
            BPCSMLCJ.INFO.TX_TLR = BPB4280_AWA_4280.TL_ID;
        }
        if (BPB4280_AWA_4280.IDT_TYPE.trim().length() == 0) {
            BPCSMLCJ.INFO.IDT_TYPE = "%%%%%%";
        } else {
            BPCSMLCJ.INFO.IDT_TYPE = BPB4280_AWA_4280.IDT_TYPE;
        }
        S000_CALL_BPZSMLCJ();
    }
    public void S000_CALL_BPZSMLCJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPTMLCJN_MAINTAIN, BPCSMLCJ);
        if (BPCSMLCJ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMLCJ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
