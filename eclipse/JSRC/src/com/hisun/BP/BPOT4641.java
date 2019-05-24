package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4641 {
    String RTBK_TYPE = "CPN";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG";
    String K_OUTPUT_FMT = "BPX02";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCSORGS BPCSORGS = new BPCSORGS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4641_AWA_4641 BPB4641_AWA_4641;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4641 return!");
        Z_RET();
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4641_AWA_4641>");
        BPB4641_AWA_4641 = (BPB4641_AWA_4641) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSORGS);
        B000_CHECK_PROC();
        B000_CALL_BPZSORGS();
        R000_TRANS_DATA_OUTPUT();
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.PAGE_FLG);
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.BNO_CHK);
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.SEL_FLAG);
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.LINE_STS);
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.BNO);
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.PAGE_NUM);
        CEP.TRC(SCCGWA, BPB4641_AWA_4641.PAGE_ROW);
        if (BPB4641_AWA_4641.BNO_CHK == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BNO_SPACE;
            S000_ERR_MSG_PROC();
        }
        if (BPB4641_AWA_4641.PAGE_NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PAGE_NO_MUST;
            S000_ERR_MSG_PROC();
        }
        if (BPB4641_AWA_4641.PAGE_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PAGE_FLAG_MUST;
            S000_ERR_MSG_PROC();
        }
        if (BPB4641_AWA_4641.PAGE_ROW == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PAGE_RECNO_NONE;
            S000_ERR_MSG_PROC();
        }
        if (BPB4641_AWA_4641.SEL_FLAG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SEL_FLG_MUST;
            S000_ERR_MSG_PROC();
        }
        BPCSORGS.INP.BNO_CHK = BPB4641_AWA_4641.BNO_CHK;
        BPCSORGS.INP.SEL_FLG = BPB4641_AWA_4641.SEL_FLAG;
        BPCSORGS.INP.BNO = BPB4641_AWA_4641.BNO;
        BPCSORGS.INP.PG_NUM = BPB4641_AWA_4641.PAGE_NUM;
        BPCSORGS.INP.PG_ROW = BPB4641_AWA_4641.PAGE_ROW;
        BPCSORGS.INP.CNT = BPB4641_AWA_4641.PAGE_FLG;
        if (BPB4641_AWA_4641.LINE_STS == '0') {
            BPCSORGS.INP.STS = 'O';
        }
        if (BPB4641_AWA_4641.LINE_STS == '2') {
            BPCSORGS.INP.STS = 'C';
        }
        if (BPB4641_AWA_4641.LINE_STS == '3') {
            BPCSORGS.INP.STS = 'F';
        }
        if (BPB4641_AWA_4641.LINE_STS == '4') {
            BPCSORGS.INP.STS = "CF".charAt(0);
        }
        if (BPB4641_AWA_4641.LINE_STS == ' ') {
            BPCSORGS.INP.STS = ' ';
        }
    }
    public void B000_CALL_BPZSORGS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-FLG", BPCSORGS);
        CEP.TRC(SCCGWA, BPCSORGS.RC.RC_CODE);
        if (BPCSORGS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSORGS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSORGS.OUT;
        SCCFMT.DATA_LEN = 719;
        IBS.FMT(SCCGWA, SCCFMT);
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
