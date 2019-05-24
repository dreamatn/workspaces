package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2080 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAV02";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT2080_WS_OUT_DATA WS_OUT_DATA = new BAOT2080_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACUCKIN BACUCKIN = new BACUCKIN();
    SCCGWA SCCGWA;
    BAB2080_AWA_2080 BAB2080_AWA_2080;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2080 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2080_AWA_2080>");
        BAB2080_AWA_2080 = (BAB2080_AWA_2080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SCCGSCA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.ID_NO);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.JDGM_NO);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.PYE_AC);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.PYE_NM);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.PYE_BKCD);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.PAY_MTH);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.PAY_CHNL);
        CEP.TRC(SCCGWA, BAB2080_AWA_2080.REMK);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_PROC_MAIN();
        if (pgmRtn) return;
        B030_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BAB2080_AWA_2080.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            WS_FLD_NO = BAB2080_AWA_2080.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2080_AWA_2080.PAY_MTH == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PAY_MTH_M_IN;
            WS_FLD_NO = BAB2080_AWA_2080.PAY_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2080_AWA_2080.PAY_CHNL == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PAY_CHNL_M_IN;
            WS_FLD_NO = BAB2080_AWA_2080.PAY_CHNL_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_ERR_FLG == 'Y') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        B021_TRANS_DATA_TO_BACUCKIN();
        if (pgmRtn) return;
        S000_CALL_BAZUCKIN();
        if (pgmRtn) return;
    }
    public void B021_TRANS_DATA_TO_BACUCKIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUCKIN);
        BACUCKIN.DATA.ID_NO = BAB2080_AWA_2080.ID_NO;
        BACUCKIN.DATA.JDGM_NO = BAB2080_AWA_2080.JDGM_NO;
        BACUCKIN.DATA.PYE_AC = BAB2080_AWA_2080.PYE_AC;
        BACUCKIN.DATA.PYE_NM = BAB2080_AWA_2080.PYE_NM;
        BACUCKIN.DATA.PYE_BKCD = BAB2080_AWA_2080.PYE_BKCD;
        BACUCKIN.DATA.PYE_BKNM = BAB2080_AWA_2080.PYE_BKNM;
        BACUCKIN.DATA.PAY_MTH = BAB2080_AWA_2080.PAY_MTH;
        BACUCKIN.DATA.PAY_CHNL = BAB2080_AWA_2080.PAY_CHNL;
        BACUCKIN.DATA.REMK = BAB2080_AWA_2080.REMK;
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B031_TRANS_DATA_TO_OUT_FMT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 2;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B031_TRANS_DATA_TO_OUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        CEP.TRC(SCCGWA, BACUCKIN.DATA.CLCT_STS);
        CEP.TRC(SCCGWA, BACUCKIN.DATA.LOSS_STS);
        WS_OUT_DATA.WS_OUT_CLCT_STS = BACUCKIN.DATA.CLCT_STS;
        WS_OUT_DATA.WS_OUT_LOSS_STS = BACUCKIN.DATA.LOSS_STS;
    }
    public void S000_CALL_BAZUCKIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-NOID-CKIN", BACUCKIN);
        if (BACUCKIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUCKIN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        WS_ERR_FLG = 'Y';
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
