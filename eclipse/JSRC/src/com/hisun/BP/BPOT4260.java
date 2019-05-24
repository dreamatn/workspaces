package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4260 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BPX01";
    String WS_CPN_BPZPACTY = "BP-P-QUERY-AC-TYPE";
    BPOT4260_WS_ERR_MSG WS_ERR_MSG = new BPOT4260_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPACTY BPCPACTY = new BPCPACTY();
    SCCGWA SCCGWA;
    BPB4260_AWA_4260 BPB4260_AWA_4260;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4260 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4260_AWA_4260>");
        BPB4260_AWA_4260 = (BPB4260_AWA_4260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        R300_TRANS_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4260_AWA_4260.AC_NO);
        if (BPB4260_AWA_4260.AC_NO.equalsIgnoreCase("0") 
            || BPB4260_AWA_4260.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = BPB4260_AWA_4260.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPACTY);
        BPCPACTY.INPUT_DATA.AC = BPB4260_AWA_4260.AC_NO;
        S000_CALL_BPZPACTY();
        if (BPCPACTY.OUTPUT_DATA.AC_TYPE != 'G') {
            if (BPCPACTY.OUTPUT_DATA.AC_TYPE == '0' 
                || BPCPACTY.OUTPUT_DATA.AC_TYPE == ' ' 
                || BPCPACTY.OUTPUT_DATA.AC_DETL.equalsIgnoreCase("0") 
                || BPCPACTY.OUTPUT_DATA.AC_DETL.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4260_AWA_4260.AC_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WS_CPN_BPZPACTY, BPCPACTY);
    }
    public void R300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "BEFORE OUTPUT");
        CEP.TRC(SCCGWA, BPCPACTY);
        CEP.TRC(SCCGWA, BPCPACTY.OUTPUT_DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCPACTY.OUTPUT_DATA.AC_DETL);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCPACTY;
        SCCFMT.DATA_LEN = 41;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "AFTER B-FMT");
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
