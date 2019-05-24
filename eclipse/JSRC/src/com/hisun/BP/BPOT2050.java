package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2050 {
    int JIBS_tmp_int;
    String CPN_S_BRW_THIB = "BP-S-BRW-THIS ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTHIB BPCSTHIB = new BPCSTHIB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2050_AWA_2050 BPB2050_AWA_2050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2050_AWA_2050>");
        BPB2050_AWA_2050 = (BPB2050_AWA_2050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_RGND_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2050_AWA_2050.BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BR);
        }
        if (BPB2050_AWA_2050.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            BPCFTLCM.BR = BPB2050_AWA_2050.BR;
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZFTLCM();
            if (BPCFTLCM.AUTH_FLG != 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NO_AUTH_TO_BR);
            }
        }
        if (BPB2050_AWA_2050.TLR.trim().length() > 0) {
            BPCFTLRQ.INFO.TLR = BPB2050_AWA_2050.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_EXIST);
            }
            if (BPCFTLRQ.INFO.NEW_BR != BPB2050_AWA_2050.BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_BELONG_TO_BR);
            }
        }
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_ERR);
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TX_LVL != '0' 
            || BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            if (BPB2050_AWA_2050.TLR.trim().length() > 0) {
                BPCSTHIB.TLR = BPB2050_AWA_2050.TLR;
            }
        } else {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2050_AWA_2050.TLR)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_OLY_QURY_SELF);
                }
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
            }
        }
        if (BPB2050_AWA_2050.BEG_DATE == 0 
            || BPB2050_AWA_2050.END_DATE == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DT_MUST_INPUT);
        }
        if (BPB2050_AWA_2050.BEG_DATE > BPB2050_AWA_2050.END_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_DT_GT_END_DT);
        }
        if (BPB2050_AWA_2050.BEG_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_DT_GT_AC_DT);
        }
    }
    public void B020_BROWSE_RGND_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTHIB);
        BPCSTHIB.FUNC = 'B';
        BPCSTHIB.DATE = BPB2050_AWA_2050.BEG_DATE;
        BPCSTHIB.END_DATE = BPB2050_AWA_2050.END_DATE;
        BPCSTHIB.VCH_NO = BPB2050_AWA_2050.VCH_NO;
        BPCSTHIB.AP_CODE = BPB2050_AWA_2050.AP_CODE;
        BPCSTHIB.TLR = BPB2050_AWA_2050.TLR;
        BPCSTHIB.BR = BPB2050_AWA_2050.BR;
        BPCSTHIB.AC = BPB2050_AWA_2050.AC;
        CEP.TRC(SCCGWA, BPCSTHIB.BR);
        S000_CALL_BPZSTHIB();
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-TLR-QRY-BR-CHK";
        SCCCALL.COMMAREA_PTR = BPCFTLCM;
        SCCCALL.ERR_FLDNO = BPB2050_AWA_2050.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZSTHIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BRW_THIB;
        SCCCALL.COMMAREA_PTR = BPCSTHIB;
        SCCCALL.ERR_FLDNO = BPB2050_AWA_2050.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
