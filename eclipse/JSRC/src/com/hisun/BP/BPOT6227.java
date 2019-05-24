package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6227 {
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG";
    int K_MAX_CNT = 20;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_RECORD_FLAG = ' ';
    BPOT6227_WS_OUT_DATA WS_OUT_DATA = new BPOT6227_WS_OUT_DATA();
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6227_AWA_6227 BPB6227_AWA_6227;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6227 return!");
        Z_RET();
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6227_AWA_6227>");
        BPB6227_AWA_6227 = (BPB6227_AWA_6227) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_RECORD_FLAG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        B200_SEARCH_PROC();
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK DATA");
    }
    public void B200_SEARCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, BPCPCMWD);
        CEP.TRC(SCCGWA, BPB6227_AWA_6227.BNO);
        CEP.TRC(SCCGWA, BPB6227_AWA_6227.DAT);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPB6227_AWA_6227.BNO;
        S000_CALL_BPZPQORG();
        B001_CHECK_BRANCH();
        if (BPCPQORG.CALD_CD.trim().length() > 0) {
            BPCPCMWD.CALCD[1-1] = BPCPQORG.CALD_CD;
            if (BPB6227_AWA_6227.DAT.trim().length() == 0) BPCPCMWD.CHECK_DATE = 0;
            else BPCPCMWD.CHECK_DATE = Integer.parseInt(BPB6227_AWA_6227.DAT);
            S000_CALL_BPZPCMWD();
            if (BPCPCMWD.HOLIDAY_FLG_ALL == 'Y') {
                WS_OUT_DATA.RES_WORK_FLA = '1';
            } else {
                WS_OUT_DATA.RES_WORK_FLA = '0';
            }
        } else {
            for (WS_CNT = 0; BPCPQORG.SUPR_BR != BPCPQORG.BR 
                && WS_CNT < K_MAX_CNT 
                && WS_RECORD_FLAG != 'Y'; WS_CNT += 1) {
                IBS.init(SCCGWA, BPCPQORG);
                IBS.init(SCCGWA, BPCPCMWD);
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, "UP BRANCH");
                CEP.TRC(SCCGWA, BPCPQORG.BNK);
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                B001_CHECK_BRANCH();
                if (BPCPQORG.CALD_CD.trim().length() > 0) {
                    BPCPCMWD.CALCD[1-1] = BPCPQORG.CALD_CD;
                    if (BPB6227_AWA_6227.DAT.trim().length() == 0) BPCPCMWD.CHECK_DATE = 0;
                    else BPCPCMWD.CHECK_DATE = Integer.parseInt(BPB6227_AWA_6227.DAT);
                    S000_CALL_BPZPCMWD();
                    WS_RECORD_FLAG = 'Y';
                    if (BPCPCMWD.HOLIDAY_FLG_ALL == 'Y') {
                        WS_OUT_DATA.RES_WORK_FLA = '1';
                    } else {
                        WS_OUT_DATA.RES_WORK_FLA = '0';
                    }
                }
            }
        }
        R000_RTN_DATA_PROC();
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        CEP.TRC(SCCGWA, BPCPQORG.ORG_STS);
        CEP.TRC(SCCGWA, BPCPQORG.CALD_CD);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = (short) BPB6227_AWA_6227.BNO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B001_CHECK_BRANCH() throws IOException,SQLException,Exception {
        if (BPCPQORG.ORG_STS != 'O') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BNO_CLOSE;
            WS_FLD_NO = (short) BPCPQORG.BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCMWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_RTN_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
