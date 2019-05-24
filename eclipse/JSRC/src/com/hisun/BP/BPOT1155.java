package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1155 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP063";
    String CPN_FPRD_MAINTAIN = "BP-F-S-MAINTAIN-FPRD";
    String CPN_ITM_INQ = "BP-P-CHK-ACCT-CODE";
    String CPN_U_MAINTAIN_UBAS = "BP-F-U-MAINTAIN-FBAS";
    String K_CMP_CALL_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_CNT = 0;
    String WS_TXT = " ";
    short WS_J = 0;
    int WS_DATE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFPRD BPCOFPRD = new BPCOFPRD();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    BPCOCKAT BPCOCKAT = new BPCOCKAT();
    BPCOPRDO BPCOPRDO = new BPCOPRDO();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1155_AWA_1155 BPB1155_AWA_1155;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1155 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1155_AWA_1155>");
        BPB1155_AWA_1155 = (BPB1155_AWA_1155) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CREATE_PRDFEE_PARM();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_CD);
        if (BPB1155_AWA_1155.PRD_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT;
            WS_FLD_NO = BPB1155_AWA_1155.PRD_CD_NO;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_BPZPQPRD();
        for (WS_J = 1; WS_J <= 20; WS_J += 1) {
            CEP.TRC(SCCGWA, WS_J);
            CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_FEE[WS_J-1].FEE_CODE);
            CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_FEE[WS_J-1].EFF_DATE);
            CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_FEE[WS_J-1].EXP_DATE);
            if (BPB1155_AWA_1155.PRD_FEE[WS_J-1].FEE_CODE.trim().length() > 0) {
                WS_FEE_CNT += 1;
            }
            if (WS_FEE_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT;
                WS_FLD_NO = BPB1155_AWA_1155.PRD_FEE[1-1].FEE_CODE_NO;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_FEE[WS_J-1].EFF_DATE);
            if (BPB1155_AWA_1155.PRD_FEE[WS_J-1].EFF_DATE == 0) {
                BPB1155_AWA_1155.PRD_FEE[WS_J-1].EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_FEE[WS_J-1].EXP_DATE);
            if (BPB1155_AWA_1155.PRD_FEE[WS_J-1].EXP_DATE == 0) {
                BPB1155_AWA_1155.PRD_FEE[WS_J-1].EXP_DATE = 99991231;
            }
        }
    }
    public void B020_CREATE_PRDFEE_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPRDO);
        for (WS_J = 1; BPB1155_AWA_1155.PRD_FEE[WS_J-1].FEE_CODE.trim().length() != 0 
            && WS_J <= 20; WS_J += 1) {
            IBS.init(SCCGWA, BPCOFPRD);
            BPCOFPRD.FUNC = 'A';
            BPCOFPRD.OUTPUT_FMT = K_OUTPUT_FMT;
            R000_TRANS_DATA_PARAMETER();
            S000_CALL_BPZFSPRD();
            BPCOPRDO.DATA[WS_J-1].FEE_CD = BPCOFPRD.KEY.FEE_CD;
            BPCOPRDO.DATA[WS_J-1].FEE_NO = BPCOFPRD.VAL.FEE_NO;
            BPCOPRDO.DATA[WS_J-1].REL_FLG = BPCOFPRD.VAL.REL_FLG;
            BPCOPRDO.DATA[WS_J-1].AC_ITEM = BPCOFPRD.VAL.AC_ITEM;
            BPCOPRDO.DATA[WS_J-1].SUS_ITEM = BPCOFPRD.VAL.SUS_ITEM;
            BPCOPRDO.DATA[WS_J-1].REB_ITEM = BPCOFPRD.VAL.REB_ITEM;
            BPCOPRDO.DATA[WS_J-1].EFF_DATE = BPCOFPRD.DATE.EFF_DATE;
            BPCOPRDO.DATA[WS_J-1].EXP_DATE = BPCOFPRD.DATE.EXP_DATE;
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.FUNC = 'I';
            BPCOUBAS.KEY.FEE_CODE = BPB1155_AWA_1155.PRD_FEE[WS_J-1].FEE_CODE;
            S000_CALL_BPZFUBAS();
            BPCOPRDO.DATA[WS_J-1].FEE_DESC = BPCOUBAS.VAL.FEE_DESC;
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOPRDO.FUNC = 'A';
        BPCOPRDO.KEY.PROD_CD = BPB1155_AWA_1155.PRD_CD;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP063";
        SCCFMT.DATA_LEN = 2723;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        SCCFMT.DATA_PTR = BPCOPRDO;
        SCCFMT.DATA_LEN = 2723;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFPRD.KEY.PROD_CD = BPB1155_AWA_1155.PRD_CD;
        BPCOFPRD.DATE.EFF_DATE = BPB1155_AWA_1155.PRD_FEE[WS_J-1].EFF_DATE;
        BPCOFPRD.DATE.EXP_DATE = BPB1155_AWA_1155.PRD_FEE[WS_J-1].EXP_DATE;
        BPCOFPRD.KEY.FEE_CD = BPB1155_AWA_1155.PRD_FEE[WS_J-1].FEE_CODE;
        BPCOFPRD.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFPRD.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        BPCPQPRD.PRDT_CODE = BPB1155_AWA_1155.PRD_CD;
        CEP.TRC(SCCGWA, BPB1155_AWA_1155.PRD_CD);
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_BPZPQPRD, BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_RECORD_NO_EXIST;
            WS_FLD_NO = BPB1155_AWA_1155.PRD_CD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFSPRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FPRD_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFPRD;
        SCCCALL.ERR_FLDNO = BPB1155_AWA_1155.PRD_CD;
        SCCCALL.NOFMT_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_UBAS, BPCOUBAS);
    }
    public void S000_CALL_BPZPCKAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ITM_INQ, BPCOCKAT);
        if (BPCOCKAT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKAT.RC);
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
