package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2040 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAR01";
    String K_HIS_REMARKS = "ACCRUAL DRAWDOWN CONTRACT DRAWDOWN";
    BAOT2040_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT2040_WS_TEMP_VARIABLE();
    BAOT2040_WS_OUT_DATA WS_OUT_DATA = new BAOT2040_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BACUBINF BACUBINF = new BACUBINF();
    BACUUUSE BACUUUSE = new BACUUUSE();
    SCCGWA SCCGWA;
    BAB2040_AWA_2040 BAB2040_AWA_2040;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2040 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2040_AWA_2040>");
        BAB2040_AWA_2040 = (BAB2040_AWA_2040) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB2040_AWA_2040);
        CEP.TRC(SCCGWA, BAB2040_AWA_2040.ID_NO);
        CEP.TRC(SCCGWA, BAB2040_AWA_2040.ENCR);
        CEP.TRC(SCCGWA, BAB2040_AWA_2040.RSN);
        CEP.TRC(SCCGWA, BAB2040_AWA_2040.FEE_CNT);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BAB2040_AWA_2040.ID_NO.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2040_AWA_2040.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2040_AWA_2040.ENCR.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_M_INPUT;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2040_AWA_2040.ENCR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2040_AWA_2040.RSN.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_RTN_RSN_M_IN;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2040_AWA_2040.RSN_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2040_AWA_2040.FEE_CNT != 0) {
            for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TEMP_VARIABLE.WS_CNT <= 5; WS_TEMP_VARIABLE.WS_CNT += 1) {
                if (BAB2040_AWA_2040.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP.trim().length() > 0) {
                    WS_TEMP_VARIABLE.WS_COUNT += 1;
                } else {
                    if (BAB2040_AWA_2040.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP.trim().length() == 0 
                        && BAB2040_AWA_2040.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].SXF_RMK.trim().length() == 0) {
                        CEP.TRC(SCCGWA, "BBBBBBBBBB");
                    } else {
                        WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FEE_DATA_NOT_MATCH;
                        S000_ERR_MSG_PROC_CONTINUE();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (BAB2040_AWA_2040.FEE_CNT != WS_TEMP_VARIABLE.WS_COUNT) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_CNT_WITH_REAL_UNFIT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_TEMP_VARIABLE.WS_ERR_FLG == 'Y') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUUUSE);
        BACUUUSE.COMM_DATA.ID_NO = BAB2040_AWA_2040.ID_NO;
        BACUUUSE.COMM_DATA.ENCR = BAB2040_AWA_2040.ENCR;
        BACUUUSE.COMM_DATA.RSN = BAB2040_AWA_2040.RSN;
        BACUUUSE.COMM_DATA.FEE_CNT = BAB2040_AWA_2040.FEE_CNT;
        for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TEMP_VARIABLE.WS_CNT <= 5; WS_TEMP_VARIABLE.WS_CNT += 1) {
            BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP = BAB2040_AWA_2040.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP;
            BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].SXF_RMK = BAB2040_AWA_2040.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].SXF_RMK;
        }
        CEP.TRC(SCCGWA, BACUUUSE.COMM_DATA.ID_NO);
        S000_CALL_BAZUUUSE();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B310_TRANS_DATA_TO_OUT_FMT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 177;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B310_TRANS_DATA_TO_OUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_KHZ_AMT = BACUUUSE.COMM_DATA.KHZ_AMT;
        WS_OUT_DATA.WS_OUT_FEE_CNT = BACUUUSE.COMM_DATA.FEE_CNT;
        for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TEMP_VARIABLE.WS_CNT <= 5; WS_TEMP_VARIABLE.WS_CNT += 1) {
            WS_OUT_DATA.WS_OUT_FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].WS_OUT_FEE_TYP = BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP;
            WS_OUT_DATA.WS_OUT_FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].WS_OUT_FEE_AMT = BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].FEE_AMT;
        }
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZUUUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-UUSE-RETN", BACUUUSE);
        if (BACUUUSE.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUUUSE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
        WS_TEMP_VARIABLE.WS_ERR_FLG = 'Y';
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
