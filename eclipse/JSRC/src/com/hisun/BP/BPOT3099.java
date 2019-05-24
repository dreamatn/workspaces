package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3099 {
    String CPN_S_BV_USE_OUT = "BP-S-BV-USE-OUT ";
    int K_CNT = 4;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVUO BPCSBVUO = new BPCSBVUO();
    SCCGWA SCCGWA;
    BPB3020_AWA_3020 BPB3020_AWA_3020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3099 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3020_AWA_3020>");
        BPB3020_AWA_3020 = (BPB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBVUO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DRAW_CASH_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.TR_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (!BPB3020_AWA_3020.TR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.TR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPB3020_AWA_3020.TR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DRAW_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVUO);
        BPCSBVUO.PAY_TYP = BPB3020_AWA_3020.PAY_TYP;
        BPCSBVUO.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        BPCSBVUO.BV_NAME = BPB3020_AWA_3020.BV_DATA[1-1].BV_NAME;
        BPCSBVUO.HEAD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO;
        BPCSBVUO.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
        BPCSBVUO.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
        BPCSBVUO.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
        BPCSBVUO.REMARK = BPB3020_AWA_3020.REMARK;
        BPCSBVUO.MOV_DT = BPB3020_AWA_3020.MOV_DT;
        BPCSBVUO.CONF_NO = BPB3020_AWA_3020.CONF_NO;
        S000_CALL_BPZSBVUO();
    }
    public void S000_CALL_BPZSBVUO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BV_USE_OUT;
        SCCCALL.COMMAREA_PTR = BPCSBVUO;
        SCCCALL.ERR_FLDNO = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
