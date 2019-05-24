package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1156 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP063";
    String CPN_FPRD_MAINTAIN = "BP-F-S-MAINTAIN-FPRD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    String WS_TXT = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFPRD BPCOFPRD = new BPCOFPRD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1155_AWA_1155 BPB1155_AWA_1155;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1156 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1155_AWA_1155>");
        BPB1155_AWA_1155 = (BPB1155_AWA_1155) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_CD);
        CEP.TRC(SCCGWA, BPB1155_AWA_1155.FEE_CD);
        B010_CHECK_INPUT();
        B020_UPDATE_PRDFEE_PARM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1155_AWA_1155.PRD_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT;
            WS_FLD_NO = BPB1155_AWA_1155.PRD_CD_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1155_AWA_1155.FEE_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT;
            WS_FLD_NO = BPB1155_AWA_1155.FEE_CD_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1155_AWA_1155.STR_DATE);
        if (BPB1155_AWA_1155.STR_DATE == 0) {
            BPB1155_AWA_1155.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1155_AWA_1155.END_DATE == 0) {
            BPB1155_AWA_1155.END_DATE = 99991231;
        }
    }
    public void B020_UPDATE_PRDFEE_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFPRD);
        BPCOFPRD.FUNC = 'U';
        BPCOFPRD.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSPRD();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFPRD.KEY.PROD_CD = BPB1155_AWA_1155.PRD_CD;
        BPCOFPRD.KEY.FEE_CD = BPB1155_AWA_1155.FEE_CD;
        BPCOFPRD.DATE.EFF_DATE = BPB1155_AWA_1155.STR_DATE;
        BPCOFPRD.DATE.EXP_DATE = BPB1155_AWA_1155.END_DATE;
        BPCOFPRD.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFPRD.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSPRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FPRD_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFPRD;
        SCCCALL.ERR_FLDNO = BPB1155_AWA_1155.PRD_CD;
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
