package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5091 {
    String CPN_MNT_EXR_DEV_AUTH = "BP-MNT-EXR-DEV-AUTH ";
    short K_MAX_ARRAY = 10;
    short WS_I = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_AROUND_OLD = 0;
    double WS_AROUND_NEW = 0;
    char WS_GRADE_OLD = ' ';
    char WS_GRADE_NEW = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCMEDA BPCMEDA = new BPCMEDA();
    BPCMCS BPCMCS = new BPCMCS();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5090_AWA_5090 BPB5090_AWA_5090;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5091 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5090_AWA_5090>");
        BPB5090_AWA_5090 = (BPB5090_AWA_5090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMEDA);
        BPCMEDA.FUNC = 'A';
        BPCMEDA.INP_DATA.EXR_TYP = BPB5090_AWA_5090.EXRA_CD;
        BPCMEDA.INP_DATA.CCY = BPB5090_AWA_5090.CCY;
        BPCMEDA.INP_DATA.CMP_FLG = BPB5090_AWA_5090.CMP_FLG;
        BPCMEDA.INP_DATA.TENOR = BPB5090_AWA_5090.TENOR;
        CEP.TRC(SCCGWA, BPCMEDA.INP_DATA.EXR_TYP);
        CEP.TRC(SCCGWA, BPCMEDA.INP_DATA.CCY);
        CEP.TRC(SCCGWA, BPCMEDA.INP_DATA.TENOR);
        for (WS_I = 1; WS_I <= K_MAX_ARRAY; WS_I += 1) {
            BPB5090_AWA_5090.AUTH_SET[WS_I-1].MSG_CD = " ";
            BPCMEDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT = BPB5090_AWA_5090.AUTH_SET[WS_I-1].FLT_LMT;
            BPCMEDA.INP_DATA.AUTH_SET[WS_I-1].ATH_LVL = BPB5090_AWA_5090.AUTH_SET[WS_I-1].ATH_LVL;
            BPCMEDA.INP_DATA.AUTH_SET[WS_I-1].MSG_CODE = BPB5090_AWA_5090.AUTH_SET[WS_I-1].MSG_CD;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB5090_AWA_5090.AUTH_SET[WS_I-1].FLT_LMT);
            CEP.TRC(SCCGWA, BPB5090_AWA_5090.AUTH_SET[WS_I-1].MSG_CD);
            CEP.TRC(SCCGWA, BPB5090_AWA_5090.AUTH_SET[WS_I-1].ATH_LVL);
        }
        IBS.CALLCPN(SCCGWA, CPN_MNT_EXR_DEV_AUTH, BPCMEDA);
        CEP.TRC(SCCGWA, BPCMEDA.RC.RC_CODE);
        if (BPCMEDA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCMEDA.RC);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAA");
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}