package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4051 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    char WS_EOF_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSGLM BPCSGLM = new BPCSGLM();
    SCCGWA SCCGWA;
    BPB4050_AWA_4050 BPB4050_AWA_4050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4051 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4050_AWA_4050>");
        BPB4050_AWA_4050 = (BPB4050_AWA_4050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GL_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4050_AWA_4050.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_MSG_ERR = "BP1814";
            WS_FLD_NO = BPB4050_AWA_4050.EFF_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4050_AWA_4050.EXP_DATE <= BPB4050_AWA_4050.EFF_DATE) {
            WS_MSG_ERR = "BP1561";
            WS_FLD_NO = BPB4050_AWA_4050.EFF_DATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GL_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        S000_CALL_BPZSGLM();
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGLM);
        BPCSGLM.DATA.KEY.REDEFINES15.MSTNO = BPB4050_AWA_4050.MSTNO;
        BPCSGLM.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA.KEY.REDEFINES15);
        BPCSGLM.DATA.DATA_TXT.COA_FLG = BPB4050_AWA_4050.COA_FLG;
        BPCSGLM.DATA.DATA_TXT.SNAME = BPB4050_AWA_4050.SNAME;
        BPCSGLM.DATA.DATA_TXT.LNAME = BPB4050_AWA_4050.LNAME;
        BPCSGLM.DATA.DATA_TXT.EFF_DATE = BPB4050_AWA_4050.EFF_DATE;
        BPCSGLM.DATA.DATA_TXT.EXP_DATE = BPB4050_AWA_4050.EXP_DATE;
        BPCSGLM.DATA.DATA_TXT.OPT_FLG = 'Y';
        BPCSGLM.DATA.DATA_TXT.CNTY1 = BPB4050_AWA_4050.CN_TYPE1;
        BPCSGLM.DATA.DATA_TXT.CNTY2 = BPB4050_AWA_4050.CN_TYPE2;
        BPCSGLM.DATA.DATA_TXT.CNTY3 = BPB4050_AWA_4050.CN_TYPE3;
        BPCSGLM.DATA.DATA_TXT.CKFLG = BPB4050_AWA_4050.CHK_FLG;
        BPCSGLM.DATA.DATA_TXT.REAL_GL = BPB4050_AWA_4050.REAL_GL;
        BPCSGLM.DATA.DATA_TXT.MEMO_GL = BPB4050_AWA_4050.MEMO_GL;
        BPCSGLM.DATA.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSGLM.DATA.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCSGLM.DATA.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCSGLM.DATA.DATA_TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCSGLM.DATA.DATA_TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCSGLM.DATA.P_EFF_DATE = BPB4050_AWA_4050.P_EFF_DT;
        BPCSGLM.DATA.P_EXP_DATE = BPB4050_AWA_4050.P_EXP_DT;
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO = BPB4050_AWA_4050.ITMS[WS_I-1].ITM_NO;
            BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_SEQ = BPB4050_AWA_4050.ITMS[WS_I-1].ITM_SEQ;
        }
        BPCSGLM.INFO.FUNC = 'A';
    }
    public void S000_CALL_BPZSGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-GLM", BPCSGLM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
