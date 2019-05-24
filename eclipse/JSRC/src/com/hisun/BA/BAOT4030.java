package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT4030 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAR02";
    BAOT4030_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT4030_WS_TEMP_VARIABLE();
    BAOT4030_WS_OUT_DATA WS_OUT_DATA = new BAOT4030_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACUUUSE BACUUUSE = new BACUUUSE();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BAB4030_AWA_4030 BAB4030_AWA_4030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT4030 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB4030_AWA_4030>");
        BAB4030_AWA_4030 = (BAB4030_AWA_4030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_DATE_INFO();
        if (pgmRtn) return;
        B100_MAIN_PROC();
        if (pgmRtn) return;
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK_DATE_INFO() throws IOException,SQLException,Exception {
        if (BAB4030_AWA_4030.ID_NO.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB4030_AWA_4030.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB4030_AWA_4030.FEE_CNT != 0) {
            for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TEMP_VARIABLE.WS_CNT <= 5; WS_TEMP_VARIABLE.WS_CNT += 1) {
                if (BAB4030_AWA_4030.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP.trim().length() > 0) {
                    WS_TEMP_VARIABLE.WS_COUNT += 1;
                } else {
                    if (BAB4030_AWA_4030.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP.trim().length() == 0 
                        && BAB4030_AWA_4030.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].SXF_RMK.trim().length() == 0) {
                    } else {
                        WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FEE_DATA_NOT_MATCH;
                        S000_ERR_MSG_PROC_CONTINUE();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (BAB4030_AWA_4030.FEE_CNT != WS_TEMP_VARIABLE.WS_COUNT) {
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
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUUUSE);
        BACUUUSE.COMM_DATA.ID_NO = BAB4030_AWA_4030.ID_NO;
        CEP.TRC(SCCGWA, BAB4030_AWA_4030.FEE_CNT);
        BACUUUSE.COMM_DATA.FEE_CNT = BAB4030_AWA_4030.FEE_CNT;
        CEP.TRC(SCCGWA, BACUUUSE.COMM_DATA.FEE_CNT);
        for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TEMP_VARIABLE.WS_CNT <= 5; WS_TEMP_VARIABLE.WS_CNT += 1) {
            BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP = BAB4030_AWA_4030.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP;
            BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].SXF_RMK = BAB4030_AWA_4030.FEE_DATA[WS_TEMP_VARIABLE.WS_CNT-1].SXF_RMK;
        }
        S000_CALL_BAZUUUSE();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B210_TRANS_DATA_TO_OUT_FMT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 177;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_TRANS_DATA_TO_OUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_KHZ_AMT = BACUUUSE.COMM_DATA.KHZ_AMT;
        WS_OUT_DATA.WS_OUT_FEE_CNT = BACUUUSE.COMM_DATA.FEE_CNT;
        for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TEMP_VARIABLE.WS_CNT <= 5; WS_TEMP_VARIABLE.WS_CNT += 1) {
            WS_OUT_DATA.WS_OUT_FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].WS_OUT_FEE_TYP = BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].FEE_TYP;
            WS_OUT_DATA.WS_OUT_FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].WS_OUT_FEE_AMT = BACUUUSE.COMM_DATA.FEE_ARRAY[WS_TEMP_VARIABLE.WS_CNT-1].FEE_AMT;
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
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
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
