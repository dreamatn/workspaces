package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1113 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP053";
    String CPN_F_S_MAIN_FCOM = "BP-F-S-MAINTAIN-FCOM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCOM BPCOFCOM = new BPCOFCOM();
    SCCGWA SCCGWA;
    BPB1000_AWA_1000 BPB1000_AWA_1000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1113 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1000_AWA_1000>");
        BPB1000_AWA_1000 = (BPB1000_AWA_1000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_COM_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DELETE_COM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCOM);
        R000_TRANS_DATA_PARAMETER();
        BPCOFCOM.FUNC = 'D';
        BPCOFCOM.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZFSCOM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFCOM.KEY.REG_CODE = BPB1000_AWA_1000.AREA;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.REG_CODE);
        BPCOFCOM.KEY.CHN_NO = BPB1000_AWA_1000.FEE_CHNL;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.CHN_NO);
        BPCOFCOM.KEY.FREE_FMT = BPB1000_AWA_1000.FERR_FMT;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.FREE_FMT);
        BPCOFCOM.KEY.FEE_CODE = BPB1000_AWA_1000.FEE_CD;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.FEE_CODE);
        for (WS_CNT = 1; WS_CNT <= 9; WS_CNT += 1) {
            BPCOFCOM.VAL.FAV_DATA[WS_CNT-1].FAV_CODE = BPB1000_AWA_1000.FAV_CODE[WS_CNT-1].FAV_CD;
            CEP.TRC(SCCGWA, BPCOFCOM.VAL.FAV_DATA[WS_CNT-1].FAV_CODE);
        }
        BPCOFCOM.VAL.FAV_SELECT = BPB1000_AWA_1000.FAV_FLG;
        CEP.TRC(SCCGWA, BPCOFCOM.VAL.FAV_SELECT);
        BPCOFCOM.EFF_DATE = BPB1000_AWA_1000.EFF_DATE;
        CEP.TRC(SCCGWA, BPCOFCOM.EFF_DATE);
        BPCOFCOM.EXP_DATE = BPB1000_AWA_1000.EXP_DATE;
        CEP.TRC(SCCGWA, BPCOFCOM.EXP_DATE);
        BPCOFCOM.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCOFCOM.LAST_DATE);
        BPCOFCOM.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCOFCOM.LAST_TELL);
    }
    public void S000_CALL_BPZFSCOM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_S_MAIN_FCOM, BPCOFCOM);
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
