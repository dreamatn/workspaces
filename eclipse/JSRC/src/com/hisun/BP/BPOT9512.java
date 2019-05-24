package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9512 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_F_S_MAIN_FCOM = "BP-BRW-FSAF-HIS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCOM BPCOFCOM = new BPCOFCOM();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSFSFB BPCSFSFB = new BPCSFSFB();
    SCCGWA SCCGWA;
    BPB9512_AWA_9512 BPB9512_AWA_9512;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9512 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9512_AWA_9512>");
        BPB9512_AWA_9512 = (BPB9512_AWA_9512) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFSFB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_QUERY_COM_INFO();
        B030_OUTPUT_DATA();
    }
    public void B020_QUERY_COM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFSFB);
        BPCSFSFB.TRN_DATE = BPB9512_AWA_9512.TRN_DATE;
        BPCSFSFB.JRNNO = BPB9512_AWA_9512.JRNNO;
        BPCSFSFB.INFO.FUNC = 'Q';
        BPCSFSFB.INFO.POINTER = BPCSFSFB;
        BPCSFSFB.INFO.LEN = 566;
        S000_CALL_BPZSFSFB();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFSFB);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSFSFB;
        SCCFMT.DATA_LEN = 566;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSFSFB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_S_MAIN_FCOM, BPCSFSFB);
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
