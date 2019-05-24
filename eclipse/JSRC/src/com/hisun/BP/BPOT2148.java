package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2148 {
    DBParm BPTCMOV_RD;
    String K_OUTPUT_FMT = "BP224";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2148_WS_OUTPUT_DETAIL WS_OUTPUT_DETAIL = new BPOT2148_WS_OUTPUT_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCMOV BPRCMOV = new BPRCMOV();
    SCCGWA SCCGWA;
    BPB2148_AWA_2148 BPB2148_AWA_2148;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2148 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2148_AWA_2148>");
        BPB2148_AWA_2148 = (BPB2148_AWA_2148) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_READ_BPTCMOV();
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2148_AWA_2148.MOV_DT);
        CEP.TRC(SCCGWA, BPB2148_AWA_2148.CONF_NO);
        CEP.TRC(SCCGWA, BPB2148_AWA_2148.CCY);
        CEP.TRC(SCCGWA, BPB2148_AWA_2148.CASH_TYP);
    }
    public void B020_READ_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.KEY.MOV_DT = BPB2148_AWA_2148.MOV_DT;
        BPRCMOV.KEY.CONF_NO = BPB2148_AWA_2148.CONF_NO;
        BPRCMOV.KEY.CCY = BPB2148_AWA_2148.CCY;
        BPRCMOV.KEY.CASH_TYP = BPB2148_AWA_2148.CASH_TYP;
        BPTCMOV_RD = new DBParm();
        BPTCMOV_RD.TableName = "BPTCMOV";
        BPTCMOV_RD.where = "MOV_DT = :BPRCMOV.KEY.MOV_DT "
            + "AND CONF_NO = :BPRCMOV.KEY.CONF_NO "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP";
        IBS.READ(SCCGWA, BPRCMOV, this, BPTCMOV_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR183);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
        CEP.TRC(SCCGWA, BPRCMOV.AMT);
        WS_OUTPUT_DETAIL.WS_AMT = 0;
        WS_OUTPUT_DETAIL.WS_AMT = BPRCMOV.AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DETAIL;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
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
